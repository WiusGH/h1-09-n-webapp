import style from "./TextAreaInput.module.css";

interface TextAreaInputProps {
  placeholder: string;
  value: string;
  onChange: (value: string) => void;
  rows?: number;
  label?: string;
}

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
