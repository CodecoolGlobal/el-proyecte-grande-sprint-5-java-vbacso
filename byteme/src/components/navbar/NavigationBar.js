import logo from '../../images/byteMe-logo.png';
import Logout from "./Logout";
import LinkFeedPage from "./LinkFeedPage";
import LinkUserPage from "./LinkUserPage";

const NavigationBar = ({loadFeedPage, loadUserPage, onLogout}) => {
    return (
        <div>
            <nav className="navbar justify-content-between px-3">
                <a className="navbar-brand" href="http://localhost:3000"><img id="logo" src={logo} alt="logo"/></a>

                <div className="" id="navbarSupportedContent">
                    <ul className="navbar-nav flex-row mr-auto">
                        <li className="nav-item mx-2">
                            <LinkUserPage loadUserPage={loadUserPage}/>
                        </li>
                        <li className="nav-item mx-2">
                            <LinkFeedPage loadFeedPage={loadFeedPage} />
                        </li>
                        <li className="nav-item mx-2">
                            <a className="nav-link" href="byteme/src/components/NavigationBar#">byte.Arrays</a>
                        </li>
                    </ul>
                </div>
                <Logout onLogout={onLogout} />
            </nav>

        </div>
    );
};

export default NavigationBar;