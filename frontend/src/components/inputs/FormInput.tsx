import { FaEye, FaEyeSlash } from "react-icons/fa";
import style from "./FormInput.module.css";

interface FormInputProps {
  type: "text" | "email" | "password";
  placeholder: string;
  value?: string;
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
  showPassword?: boolean;
  onTogglePassword?: () => void;
}

const FormInput = ({
  type,
  placeholder,
  value,
  onChange,
  showPassword,
  onTogglePassword,
}: FormInputProps) => {
  const isPassword = type === "password";
  const inputType = isPassword && showPassword ? "text" : type;

  return (
    <div className={style.inputWrapper}>
      <input
        className={style.input}
        type={inputType}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
      />

      {isPassword && onTogglePassword && (
        <button
          type="button"
          className={style.eyeButton}
          onClick={onTogglePassword}
        >
          {showPassword ? <FaEyeSlash size={18} /> : <FaEye size={18} />}
        </button>
      )}
    </div>
  );
};

export default FormInput;
