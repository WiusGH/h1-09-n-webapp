import style from "./GenericButton.module.css";

interface GenericButtonProps {
  disabled?: boolean;
  submit?: boolean;
  text: string;
  width?: string;
}

const GenericButton: React.FC<GenericButtonProps> = ({
  disabled,
  submit,
  text,
  width,
}) => {
  return (
    <button
      className={style.button}
      style={{ width: width + "%" }}
      disabled={disabled}
      type={submit ? "submit" : "button"}
    >
      {text}
    </button>
  );
};

export default GenericButton;
