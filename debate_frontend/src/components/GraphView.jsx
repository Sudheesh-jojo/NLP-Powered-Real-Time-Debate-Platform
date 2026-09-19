import { useEffect, useRef, useState } from "react";
import ForceGraph2D from "react-force-graph-2d";

const API_URL = "http://localhost:8081";

const DEBATE_ID = "c334422b-c0cb-4760-b1bb-8d087febe804";

function GraphView() {
    const [nodes, setNodes] = useState([]);
    const [edges, setEdges] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const graphRef = useRef();

    useEffect(() => {
        async function fetchGraph() {
            try {
                setLoading(true);

                const [nodesResponse, edgesResponse] = await Promise.all([
                    fetch(`${API_URL}/api/graph/${DEBATE_ID}/nodes`),
                    fetch(`${API_URL}/api/graph/${DEBATE_ID}/edges`),
                ]);

                if (!nodesResponse.ok || !edgesResponse.ok) {
                    throw new Error("Failed to fetch graph data");
                }

                const nodesData = await nodesResponse.json();
                const edgesData = await edgesResponse.json();

                setNodes(nodesData);
                setEdges(edgesData);
            } catch (err) {
                console.error(err);
                setError(err.message);
            } finally {
                setLoading(false);
            }
        }

        fetchGraph();
    }, []);

    if (loading) {
        return (
            <div className="graph-status">
                <h2>Loading Debate Graph...</h2>
                <p>Fetching arguments and relationships.</p>
            </div>
        );
    }

    if (error) {
        return (
            <div className="graph-status error">
                <h2>Failed to load graph</h2>
                <p>{error}</p>

                <small>
                    Make sure Spring Boot is running on port 8081.
                </small>
            </div>
        );
    }

    const graphData = {
        nodes: nodes.map((node) => ({
            ...node,
            name: node.text,
        })),

        links: edges.map((edge) => ({
            ...edge,
            source: edge.source,
            target: edge.target,
        })),
    };

    return (
        <section className="graph-container">

            <div className="graph-header">
                <div>
                    <h2>Debate Argument Graph</h2>

                    <p>
                        Visual representation of arguments and their relationships.
                    </p>
                </div>

                <div className="graph-stats">
                    <div>
                        <strong>{nodes.length}</strong>
                        <span>Arguments</span>
                    </div>

                    <div>
                        <strong>{edges.length}</strong>
                        <span>Connections</span>
                    </div>
                </div>
            </div>

            <div className="graph-wrapper">

                <ForceGraph2D
                    ref={graphRef}
                    graphData={graphData}

                    nodeLabel={(node) => {
                        return `
              <div style="
                max-width:300px;
                padding:8px;
              ">
                <strong>${node.argumentType || "UNKNOWN"}</strong>
                <br/>
                ${node.text}
              </div>
            `;
                    }}

                    nodeAutoColorBy="argumentType"

                    nodeRelSize={7}

                    linkLabel={(link) => {
                        return `
              Type: ${link.linkType}
              <br/>
              Similarity: ${link.similarityScore?.toFixed(3)}
            `;
                    }}

                    linkDirectionalArrowLength={5}
                    linkDirectionalArrowRelPos={1}

                    linkWidth={(link) => {
                        if (!link.similarityScore) {
                            return 1;
                        }

                        return Math.max(1, link.similarityScore * 4);
                    }}

                    onNodeClick={(node) => {
                        console.log("Clicked node:", node);

                        graphRef.current?.centerAt(
                            node.x,
                            node.y,
                            800
                        );

                        graphRef.current?.zoom(4, 800);
                    }}

                    nodeCanvasObject={(node, ctx, globalScale) => {

                        const label = node.text || "";

                        const fontSize = 12 / globalScale;

                        ctx.font = `${fontSize}px Sans-Serif`;

                        const textWidth = ctx.measureText(label).width;

                        const radius = 5;

                        ctx.beginPath();

                        ctx.arc(
                            node.x,
                            node.y,
                            radius,
                            0,
                            2 * Math.PI,
                            false
                        );

                        ctx.fillStyle =
                            node.argumentType === "CLAIM"
                                ? "#4f46e5"
                                : "#64748b";

                        ctx.fill();

                        if (globalScale > 1.5) {

                            ctx.textAlign = "center";
                            ctx.textBaseline = "middle";

                            ctx.fillStyle = "#ffffff";

                            ctx.fillText(
                                label.length > 35
                                    ? label.substring(0, 35) + "..."
                                    : label,
                                node.x,
                                node.y + 10
                            );
                        }
                    }}

                    cooldownTicks={100}

                    d3VelocityDecay={0.3}

                    backgroundColor="#0f172a"

                    width={window.innerWidth * 0.9}
                    height={window.innerHeight * 0.7}
                />

            </div>

            <div className="legend">

                <div className="legend-item">
                    <span className="legend-dot claim"></span>
                    CLAIM
                </div>

                <div className="legend-item">
                    <span className="legend-dot unknown"></span>
                    UNKNOWN
                </div>

                <div className="legend-item">
                    <span className="legend-line"></span>
                    RELATED
                </div>

            </div>

        </section>
    );
}

export default GraphView;