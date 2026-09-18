import React, { useEffect, useState } from "react";
import ForceGraph2D from "react-force-graph-2d";

function GraphView({ debateId }) {

    const [nodes, setNodes] = useState([]);
    const [edges, setEdges] = useState([]);

    useEffect(() => {

        const fetchGraph = async () => {

            try {

                const nodesResponse = await fetch(
                    `http://localhost:8081/api/graph/${debateId}/nodes`
                );

                const edgesResponse = await fetch(
                    `http://localhost:8081/api/graph/${debateId}/edges`
                );

                const nodesData = await nodesResponse.json();
                const edgesData = await edgesResponse.json();

                setNodes(nodesData);
                setEdges(edgesData);

            } catch (error) {

                console.error("Error fetching graph:", error);

            }
        };

        fetchGraph();

    }, [debateId]);


    const graphData = {
        nodes: nodes,
        links: edges
    };


    return (
        <div style={{ width: "100%", height: "600px" }}>

            <ForceGraph2D
                graphData={graphData}

                nodeLabel={(node) =>
                    `${node.argumentType || "UNKNOWN"}: ${node.text}`
                }

                linkLabel={(link) =>
                    `${link.linkType} (${link.similarityScore})`
                }

                nodeAutoColorBy="argumentType"

                linkDirectionalArrowLength={4}
                linkDirectionalArrowRelPos={1}

                width={900}
                height={600}
            />

        </div>
    );
}

export default GraphView;