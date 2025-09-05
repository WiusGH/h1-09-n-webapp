import style from "./GenericButton.module.css";

interface GenericButtonProps {
  disabled?: boolean;
  submit?: boolean;
  text: string;
  width?: string;
   onClick?: (e: React.MouseEvent<HTMLButtonElement>) => void;
}

const GenericButton: React.FC<GenericButtonProps> = ({
  disabled,
  submit,
  text,
  width,
  onClick
}) => {
  return (
    <button
      className={style.button}
      style={{ width: width }}
      disabled={disabled}
      type={submit ? "submit" : "button"}
      onClick={onClick}
    >
      {text}
    </button>
  );
};

export default GenericButton;
