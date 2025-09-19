import { useState, useEffect } from "react";

/**
 * Este hook utiliza el evento "scroll" del objeto window para
 * detectar el movimiento del usuario y decidir si el
 * elemento debe ser visible o no.
 *
 * El elemento es  visible si el usuario no ha hecho scroll
 * hacia abajo o si ha hecho scroll hacia arriba.
 *
 * El elemento no ser  visible si el usuario ha hecho scroll
 * hacia abajo y el valor de window.scrollY es mayor a 50.
 *
 * @returns {boolean} Valor booleano que indica si el elemento
 * debe ser visible o no.
 */
export function useHideOnScroll() {
  const [show, setShow] = useState(true);
  const [lastScrollY, setLastScrollY] = useState(0);

  useEffect(() => {
    const handleScroll = () => {
      const currentScrollY = window.scrollY;
      if (currentScrollY > lastScrollY && currentScrollY > 50) {
        setShow(false);
      } else {
        setShow(true);
      }
      setLastScrollY(currentScrollY);
    };

    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, [lastScrollY]);

  return show;
}
