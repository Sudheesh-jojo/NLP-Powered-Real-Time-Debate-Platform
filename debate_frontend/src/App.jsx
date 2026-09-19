import GraphView from "./components/GraphView";
import "./App.css";

function App() {

  return (
      <div className="app">

        <header className="header">
          <h1>Debate Platform</h1>
          <p>AI-Powered Debate Argument Graph</p>
        </header>

        <main>
          <GraphView />
        </main>

      </div>
  );
}

export default App;