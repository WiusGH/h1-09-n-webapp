// Función para cambiar el tema
export function toggleDarkMode(): void {
  const root = document.documentElement;
  root.classList.toggle("dark");

  if (root.classList.contains("dark")) {
    localStorage.setItem("theme", "dark");
  } else {
    localStorage.setItem("theme", "light");
  }
}

// Sirve para cargar el tema al montar el componente
export const initTheme = () => {
  const storedTheme = localStorage.getItem("theme");
  const prefersDark = window.matchMedia("(prefers-color-scheme: dark)").matches;

  const theme = storedTheme || (prefersDark ? "dark" : "light");

  document.documentElement.classList.toggle("dark", theme === "dark");
};
