import style from "./TextAreaInput.module.css";

interface TextAreaInputProps {
  placeholder: string;
  value: string;
  onChange: (value: string) => void;
  rows?: number;
  label?: string;
}

/**
 * Componente que renderiza un input de tipo textarea.
 * @param placeholder - Texto para mostrar en la barra de búsqueda.
 * @param value - Valor actual del input.
 * @param onChange - Función para cambiar y retornar el valor del input.
 * @param rows - (Opcional) Número de filas para el textarea.
 * @param label - (Opcional) Etiqueta para el input.
 * @returns {JSX.Element} Elemento JSX que representa el input.
 * @example
 * <TextAreaInput placeholder="Escribe algo..." value="" onChange={(value: string) => void} label="Comentario" />
 */
const TextAreaInput = ({
  placeholder,
  value,
  onChange,
  rows = 4,
  label,
}: TextAreaInputProps) => {
  return (
    <div className={style.wrapper}>
      {label && <label className={style.label}>{label}</label>}
      <textarea
        className={style.textarea}
        placeholder={placeholder}
        value={value}
        onChange={(e) => onChange(e.target.value)}
        rows={rows}
      />
    </div>
  );
};

export default TextAreaInput;
