import style from "./ProfileView.module.css";
import { LuPencilLine } from "react-icons/lu";

const ProfileView = () => {
  return (
    <div className={style.container}>
      <div className={style.profile}>
        <img src="https://avatar.iran.liara.run/public/29"></img>
        <div className={style.info}>
          <span>
            <h3>Nombre de usuario</h3>
            <a>
              <LuPencilLine />
            </a>
          </span>
          <span>
            <p>Ocupación</p>
            <a>
              <LuPencilLine />
            </a>
          </span>
        </div>
        <div style={{ flex: 1 }}></div>
        <ul className={style.options}>
          <li>
            <a>Editar cv</a>
          </li>
          <li>
            <a>Editar contacto</a>
          </li>
          <li>
            <a>Editar foto</a>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default ProfileView;
