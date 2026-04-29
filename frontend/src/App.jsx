import { useState } from "react";
import "./App.css";

export default function App() {
  const [query, setQuery] = useState("");
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);

  async function handleSearch(e) {
    e.preventDefault();

    if (!query) return;

    setLoading(true);

    try {
     const res = await fetch("https://automatic-invention-wr7574x76v64frqx-2500.app.github.dev/search?q=" + query)



      const data = await res.json();
      setResults(data);
    } catch (err) {
      console.error("Erro:", err);
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="container">
      <h1 className="logo">MiniGoogle</h1>

      <form onSubmit={handleSearch} className="searchBox">
        <input
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          placeholder="Pesquisar..."
        />
        <button type="submit">Buscar</button>
      </form>

      {loading && <p>Buscando...</p>}

      <div className="results">
        {results.map((item) => (
          <div key={item.id} className="card">
            <a href={item.url} target="_blank">
              <h3>{item.title}</h3>
            </a>
            <p>{item.content?.slice(0, 200)}...</p>
            <small>{item.url}</small>
          </div>
        ))}
      </div>
    </div>
  );
}