import { useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import { ScrollToTop } from "./utils/ScrollToTop";
import { initTheme } from "./utils/theme";

function App() {
  useEffect(() => {
    initTheme();
  }, []);
  return (
    <Router>
      <ScrollToTop />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="*" element={<h1>404 - Not Found</h1>} />
      </Routes>
    </Router>
  );
}

export default App;
