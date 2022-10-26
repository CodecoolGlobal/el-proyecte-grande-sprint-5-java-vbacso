import React from 'react';

const LinkUserPage = ({loadUserPage}) => {
    return (
        <a className="nav-link" onClick={loadUserPage}>byte.Me</a>
    );
};

export default LinkUserPage;
