import { FaEye, FaEyeSlash } from "react-icons/fa";
import style from "./FormInput.module.css";

interface FormInputProps {
  type: "text" | "email" | "password" | "number";
  placeholder: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  showPassword?: boolean;
  onTogglePassword?: () => void;
  className?: string;
  label?: string;
}

/**
 * Componente que renderiza un input de formulario.
 * @property type - Tipo de input del formulario.
 * @property placeholder - Placeholder del input del formulario.
 * @property value - Valor actual del input del formulario.
 * @property onChange - Función para cambiar y retornar el valor del input del formulario.
 * @property showPassword - (Opcional) Indica si el input es de tipo password y agrega un botón para toggle la visibilidad de la contraseña.
 * @property onTogglePassword - (Opcional) Función para toggle la visibilidad de la contraseña.
 * @property className - (Opcional) Clase CSS para el componente.
 * @property label - (Opcional) Etiqueta para el input del formulario.
 */
const FormInput = ({
  type,
  placeholder,
  value,
  onChange,
  showPassword,
  onTogglePassword,
  className,
  label,
}: FormInputProps) => {
  const isPassword = type === "password";
  const inputType = isPassword && showPassword ? "text" : type;

  return (
    <>
      {label && <label className={style.label}>{label}</label>}
      <div className={`${style.inputWrapper} ${className ?? ""}`}>
        <input
          className={style.input}
          type={inputType}
          placeholder={placeholder}
          value={value}
          onChange={onChange}
          aria-label={label || placeholder}
        />

        {isPassword && onTogglePassword && (
          <button
            type="button"
            className={style.eyeButton}
            onClick={onTogglePassword}
            aria-label="Toggle password visibility"
          >
            {showPassword ? <FaEyeSlash size={18} /> : <FaEye size={18} />}
          </button>
        )}
      </div>
    </>
  );
};

export default FormInput;
