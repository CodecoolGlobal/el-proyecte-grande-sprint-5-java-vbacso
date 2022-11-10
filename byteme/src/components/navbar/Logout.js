import React from 'react';
import {redirect} from "react-router-dom";

const Logout = ({setUser}) => {

    const onLogout = () => {
        localStorage.clear();
        setUser(null);
        redirect("/")
    };

    return (
        <div>
            <a className="navbar-nav nav-link" onClick={onLogout}>Logout</a>
        </div>
    );
};

export default Logout;
