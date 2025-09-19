import style from "./GenericButton.module.css";

interface GenericButtonProps {
  disabled?: boolean;
  submit?: boolean;
  text: string;
  width?: string;
  onClick?: (e: React.MouseEvent<HTMLButtonElement>) => void;
}

/**
 * Generic button component
 * @param text - Texto del botón
 * @param disabled - (Opcional) Para deshabilitar el botón
 * @param submit - (Opcional) Para indicar que el botón es de tipo submit
 * @param width - (Opcional) Ancho del botón
 * @param onClick - (Opcional) Función que se ejecuta al hacer clic en el botón
 * @returns {JSX.Element} Button component
 */
const GenericButton: React.FC<GenericButtonProps> = ({
  disabled,
  submit,
  text,
  width,
  onClick,
}) => {
  return (
    <button
      className={style.button}
      style={{ width: width + "%" }}
      disabled={disabled}
      type={submit ? "submit" : "button"}
      onClick={onClick}
    >
      {text}
    </button>
  );
};

export default GenericButton;
