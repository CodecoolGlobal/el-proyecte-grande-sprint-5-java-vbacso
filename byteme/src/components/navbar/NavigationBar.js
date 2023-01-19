import logo from '../../images/byteMe-logo.png';
import Logout from "./Logout";
import LinkFeedPage from "./LinkFeedPage";
import LinkLoggedInUserPage from "./LinkLoggedInUserPage";
import SearchBar from "./searchbar/SearchBar";
import LinkArrayPage from "../array_page/LinkArrayPage";

const NavigationBar = ({loggedInUser, loadFeedPage, loadUserPage, loadArrayPage, onLogout, onSetShowedUser}) => {
    return (
        <div className="navbar-container">
            <nav className="navbar justify-content-between px-3">
                <div className="d-flex">
                    <a className="navbar-brand" href="http://localhost:3000"><img id="logo" src={logo} alt="logo"/></a>
                    <SearchBar onSetShowedUser={onSetShowedUser}/>
                </div>
                <div className="" id="navbarSupportedContent">
                    <ul className="navbar-nav flex-row mr-auto">
                        <li className="nav-item mx-2">
                            <LinkLoggedInUserPage loggedInUser={loggedInUser} loadUserPage={loadUserPage}
                                                  onSetShowedUser={onSetShowedUser}/>
                        </li>
                        <li className="nav-item mx-2">
                            <LinkFeedPage loadFeedPage={loadFeedPage}/>
                        </li>
                        <li className="nav-item mx-2">
                            <LinkArrayPage loadArrayPage={loadArrayPage}/>
                        </li>
                    </ul>
                </div>
                <Logout onLogout={onLogout}/>
            </nav>

        </div>
    );
};

export default NavigationBar;