import React from "react";
import { useHideOnScroll } from "../../hooks/useHideOnScroll";
import style from "./Layout.module.css";
import CustomSearchBar from "../inputs/CustomSearchBar";
import NavbarIcon from "../buttons/NavbarIcon";

interface HeaderProps {
  placeholder: string;
  navigateTo: string;
}

const Header = ({ placeholder, navigateTo }: HeaderProps) => {
  const show = useHideOnScroll();

  return (
    <header
      className={`${style.header} ${show ? style.visible : style.hidden}`}
    >
      <section>
        <NavbarIcon type="home" />
      </section>
      <section className={style.searchBar}>
        <CustomSearchBar placeholder={placeholder} navigateTo={navigateTo} />
      </section>
      <nav>
        <NavbarIcon type="jobs" />
        <NavbarIcon type="messages" />
        <NavbarIcon type="notifications" />
      </nav>
    </header>
  );
};

export default Header;
