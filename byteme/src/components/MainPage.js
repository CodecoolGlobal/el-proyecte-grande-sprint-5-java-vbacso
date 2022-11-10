import React, {useState} from 'react';
import NavigationBar from "./navbar/NavigationBar";
import FeedPage from "./feed_page/FeedPage";
import UserPage from "./user_page/UserPage";

const MainPage = ({loggedInUserId, onLogout}) => {
    const [showContent, setShowContent] = useState("feedPage")
    const [userId,setUserId] = useState(loggedInUserId)

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
            return <UserPage userId={userId}/>
        }
    };

    const onSetUserId = (userId) => {
        setUserId(userId);
        setShowContent("userPage")
    };

    return (
        <div className="main-container">
            <NavigationBar loadFeedPage={loadFeedPage} loadUserPage={loadUserPage} onLogout={onLogout} onSetUserId={onSetUserId}/>
            {routeController()}
        </div>
    );
};

export default MainPage;
