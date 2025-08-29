import { useWindowSize } from "../../hooks/useWindowsSize";
import DesktopHeader from "./DesktopHeader";
import MobileHeader from "./MobileHeader";

interface HeaderProps {
  placeholder: string;
  navigateTo: string;
}

const Header = ({ placeholder, navigateTo }: HeaderProps) => {
  const windowSize = useWindowSize();
  return (
    <>
      {windowSize.width > 768 ? (
        <DesktopHeader placeholder={placeholder} navigateTo={navigateTo} />
      ) : (
        <MobileHeader placeholder={placeholder} navigateTo={navigateTo} />
      )}
    </>
  );
};

export default Header;
