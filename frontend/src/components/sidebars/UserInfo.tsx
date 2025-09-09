import { useEffect, useState } from "react";
import type { UserData } from "../../types/Types";
import UserInfoButton from "../buttons/UserInfoButton";
import style from "./UserInfo.module.css";
<<<<<<< HEAD
=======
import { getUserData } from "../../utils/userStorage";
>>>>>>> efde57135acd6807ed8ff66c4e1b7e32cd740d1b

const UserInfo = () => {
  const [user, setUser] = useState<UserData | null>(null);

  useEffect(() => {
    const storedUser = getUserData();
    if (storedUser) {
      setUser(storedUser);
    }
  }, []);

  if (!user) return null;

  return (
    <aside className={style.container}>
      <section className={style.profile}>
        <img src="https://avatar.iran.liara.run/public/29"></img>
        <div>
          <h3>{`${user.firstName} ${user.lastName}`}</h3>
          <h4>{user.title}asd</h4>
          <p>{user.role.toLocaleLowerCase()}</p>
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
