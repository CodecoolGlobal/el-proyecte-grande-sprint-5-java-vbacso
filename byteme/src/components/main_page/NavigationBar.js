import logo from '../../images/byteMe-logo.png';

const NavigationBar = () => {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <a className="navbar-brand" href="http://localhost:3000"><img id="logo" src={logo}/></a>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item">
                            <a className="nav-link" href="#">byte.Me</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">byte.Feed</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">byte.Arrays</a>
                        </li>
                    </ul>
                </div>
            </nav>

        </div>
    );
};

export default NavigationBar;