import React from 'react';

const LinkUserPage = ({loadUserPage, onSetUserId}) => {
    const navigateSelfUserPage = (e) => {
        const loggedInUserId = JSON.parse(localStorage.getItem("loggedInUser")).id
        onSetUserId(loggedInUserId);
        loadUserPage(e);
    };
    return (
        <a className="nav-link" onClick={navigateSelfUserPage}>byte.Me</a>
    );
};

export default LinkUserPage;
