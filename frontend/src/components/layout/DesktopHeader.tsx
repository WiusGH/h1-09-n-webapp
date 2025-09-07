import style from "./Layout.module.css";
import { useHideOnScroll } from "../../hooks/useHideOnScroll";
import NavbarIcon from "../buttons/NavbarIcon";
import ThemeToggle from "../buttons/ThemeToggle";
import CustomSearchBar from "../inputs/CustomSearchBar";
import { isLoggedIn } from "../../utils/userStorage";

interface HeaderProps {
  placeholder: string;
  navigateTo: string;
}

const DesktopHeader: React.FC<HeaderProps> = ({ placeholder, navigateTo }) => {
  const show = useHideOnScroll();
  return (
    <header
      className={`${style.header} ${show ? style.visible : style.hidden}`}
    >
      <div className={style.sideDivs}></div>
      <nav>
        <NavbarIcon type="home" />
      </nav>
      <section className={style.searchBar}>
        <CustomSearchBar placeholder={placeholder} navigateTo={navigateTo} />
      </section>
      <nav>
        <NavbarIcon type="jobs" />
        <NavbarIcon type="messages" />
        <NavbarIcon type="notifications" />
        {isLoggedIn() ? (
          <NavbarIcon type="logout" />
        ) : (
          <NavbarIcon type="login" />
        )}
      </nav>
      <div className={style.sideDivs}>
        <ThemeToggle />
      </div>
    </header>
  );
};

export default DesktopHeader;
