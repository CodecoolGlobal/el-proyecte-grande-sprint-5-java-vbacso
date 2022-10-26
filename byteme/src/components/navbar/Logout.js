import React from 'react';

const Logout = ({onLogout}) => {
    return (
        <div>
            <a className="navbar-nav nav-link" onClick={onLogout}>Logout</a>
        </div>
    );
};

export default Logout;
