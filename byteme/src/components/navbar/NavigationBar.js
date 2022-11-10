import logo from '../../images/byteMe-logo.png';
import Logout from "./Logout";
import LinkFeedPage from "./LinkFeedPage";
import LinkUserPage from "./LinkUserPage";
import SearchBar from "./searchbar/SearchBar";

const NavigationBar = ({loadFeedPage, loadUserPage, setUser, onSetUserId}) => {
    return (
        <div className="navbar-container">
            <nav className="navbar justify-content-between px-3">
                <div className="d-flex">
                    <a className="navbar-brand" href="http://localhost:3000"><img id="logo" src={logo} alt="logo"/></a>
                    <SearchBar onSetUserId={onSetUserId}/>
                </div>
                <div className="" id="navbarSupportedContent">
                    <ul className="navbar-nav flex-row mr-auto">
                        <li className="nav-item mx-2">
                            <LinkUserPage loadUserPage={loadUserPage} onSetUserId={onSetUserId}/>
                        </li>
                        <li className="nav-item mx-2">
                            <LinkFeedPage loadFeedPage={loadFeedPage}/>
                        </li>
                        <li className="nav-item mx-2">
                            <a className="nav-link" href="byteme/src/components/NavigationBar#">byte.Arrays</a>
                        </li>
                    </ul>
                </div>
                <Logout setUser={setUser}/>
            </nav>

        </div>
    );
};

export default NavigationBar;