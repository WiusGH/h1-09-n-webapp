import UserInfoButton from "../buttons/UserInfoButton";
import style from "./UserInfo.module.css";

const UserInfo = () => {
  return (
    <aside className={style.container}>
      <section className={style.profile}>
        <img src="https://avatar.iran.liara.run/public/29"></img>
        <div>
          <h3>Nombre</h3>
          <h4>Título</h4>
        </div>
      </section>
      <section className={style.links}>
        <UserInfoButton type="profile" />
        <UserInfoButton type="cv" />
        <UserInfoButton type="bookmarks" />
        <UserInfoButton type="applications" />
        <UserInfoButton type="config" />
        <UserInfoButton type="logout" />
      </section>
    </aside>
  );
};

export default UserInfo;
