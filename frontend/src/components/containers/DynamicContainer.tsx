import React from "react";
import style from "./DynamicContainer.module.css";

interface DynamicContainerProps {
  main: React.ReactNode;
  side?: React.ReactNode;
}

function DynamicContainer({ main, side }: DynamicContainerProps) {
  return (
    <div className={`${style.container} ${side ? style.twoCol : ""}`}>
      <div className={style.main}>{main}</div>
      {side && <aside className={style.side}>{side}</aside>}
    </div>
  );
}

export default DynamicContainer;
