import React, {useState} from 'react';
import NavigationBar from "./navbar/NavigationBar";
import FeedPage from "./feed_page/FeedPage";
import UserPage from "./user_page/UserPage";

const MainPage = ({loggedInUserId, onLogout}) => {
    const [showContent, setShowContent] = useState("feedPage")

    const loadFeedPage = (e) => {
        e.preventDefault();
        setShowContent("feedPage")
    };
    const loadUserPage = (e) => {
        e.preventDefault();
        setShowContent("userPage")
    };

    const routeController = () => {
        if (showContent === "feedPage") {
            return <FeedPage loggedInUserId={loggedInUserId}/>
        } else if (showContent === "userPage") {
            return <UserPage/>
        }
    };

    return (
        <div>
            <NavigationBar loadFeedPage={loadFeedPage} loadUserPage={loadUserPage} onLogout={onLogout}/>
            {routeController()}
        </div>
    );
};

export default MainPage;
