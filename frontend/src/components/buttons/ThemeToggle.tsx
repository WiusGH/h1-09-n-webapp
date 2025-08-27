import { useEffect, useState } from "react";
import style from "./ThemeToggle.module.css";
import { FaSun, FaMoon } from "react-icons/fa";
import { toggleDarkMode } from "../../utils/theme";

const ThemeToggle = () => {
  const [darkMode, setDarkMode] = useState(
    localStorage.getItem("theme") === "dark"
  );

  const handleToggle = () => {
    toggleDarkMode();
    setDarkMode((prev) => !prev);
  };

  // Sirve para persistir el tema entre recargas de la página
  useEffect(() => {
    setDarkMode(localStorage.getItem("theme") === "dark");
  }, []);

  return (
    <button
      className={`${style.themeToggle} ${darkMode ? style.darkBg : ""}`}
      onClick={handleToggle}
    >
      <FaMoon
        className={`${style.moonIcon} ${!darkMode ? style.hidden : ""}`}
      />
      <FaSun className={`${style.sunIcon} ${darkMode ? style.hidden : ""}`} />
      <div className={`${style.toggle} ${darkMode ? style.dark : ""}`} />
    </button>
  );
};

export default ThemeToggle;
