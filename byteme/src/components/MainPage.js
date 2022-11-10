import React, {useState} from 'react';
import NavigationBar from "./navbar/NavigationBar";
import FeedPage from "./feed_page/FeedPage";
import UserPage from "./user_page/UserPage";

const MainPage = ({loggedInUserId, setUser}) => {
    const [showContent, setShowContent] = useState("feedPage")
    const [userPageUserId,setUserPageUserId] = useState(loggedInUserId)

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
            return <UserPage userId={userPageUserId}/>
        }
    };

    const onSetUserId = (userId) => {
        setUserPageUserId(userId);
        setShowContent("userPage")
    };

    return (
        <div className="main-container">
            <NavigationBar loadFeedPage={loadFeedPage} loadUserPage={loadUserPage} setUser={setUser} onSetUserId={onSetUserId}/>
            {routeController()}
        </div>
    );
};

export default MainPage;
