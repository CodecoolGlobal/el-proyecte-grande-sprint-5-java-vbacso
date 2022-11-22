import React from 'react';

const LinkLoggedInUserPage = ({loggedInUser, loadUserPage, onSetShowedUser}) => {
    const navigateSelfUserPage = async (e) => {
        const res = await fetch(`http://localhost:8080/user/findById/${loggedInUser.id}`);
        onSetShowedUser(await res.json());
        loadUserPage(e);
    };
    return (
        <a className="nav-link" onClick={navigateSelfUserPage}>byte.Me</a>
    );
};

export default LinkLoggedInUserPage;
