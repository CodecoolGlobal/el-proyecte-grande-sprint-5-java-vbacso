import React from 'react';
import {getAuthenticationToken} from "../../util";

const LinkLoggedInUserPage = ({loggedInUser, loadUserPage, onSetShowedUser}) => {
    const navigateSelfUserPage = async (e) => {
        const res = await fetch(`http://localhost:8080/user/findById/${loggedInUser.id}`, {
            headers: {
                "Authorization": getAuthenticationToken()
            }
        });
        onSetShowedUser(await res.json());
        loadUserPage(e);
    };
    return (
        <a className="nav-link" id="user-page" onClick={navigateSelfUserPage}>byte.Me</a>
    );
};

export default LinkLoggedInUserPage;
