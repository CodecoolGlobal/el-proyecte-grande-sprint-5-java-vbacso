import React, {useEffect, useState} from 'react';
import NavigationBar from "./navbar/NavigationBar";
import FeedPage from "./feed_page/FeedPage";
import UserPage from "./user_page/UserPage";
import ArrayPage from "./array_page/ArrayPage";

const MainPage = ({loggedInUser, setLoggedInUser, onLogout}) => {
    const [showContent, setShowContent] = useState("feedPage");
    const [showedUser, setShowedUser] = useState(loggedInUser);

    useEffect(() => {
        if (loggedInUser.id === showedUser.id) {
            setShowedUser(loggedInUser);
        }
    }, [loggedInUser]);

    const loadFeedPage = (e) => {
        e.preventDefault();
        setShowContent("feedPage")
    };

    const loadUserPage = (e) => {
        e.preventDefault();
        setShowContent("userPage")
    };

    const loadArrayPage = (e) => {
        e.preventDefault();
        setShowContent("arrayPage");
    };

    const routeController = () => {
        if (showContent === "feedPage") {
            return <FeedPage loggedInUser={loggedInUser}/>
        } else if (showContent === "userPage") {
            return <UserPage loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} showedUser={showedUser}
                             setShowedUser={setShowedUser}/>
        } else if (showContent === "arrayPage") {
            return <ArrayPage loggedInUser={loggedInUser} showedUser={showedUser}/>
        }
    };

    const onSetShowedUser = (user) => {
        setShowedUser(user);
        setShowContent("userPage")
    };
    return (
        <div className="main-container">
            <NavigationBar loggedInUser={loggedInUser} loadFeedPage={loadFeedPage} loadUserPage={loadUserPage}
                           loadArrayPage={loadArrayPage} onLogout={onLogout} onSetShowedUser={onSetShowedUser}/>
            {routeController()}
        </div>
    );
};

export default MainPage;
