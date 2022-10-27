import React, {useState} from 'react';
import NavigationBar from "./navbar/NavigationBar";
import FeedPage from "./feed_page/FeedPage";
import UserPage from "./user_page/UserPage";

const MainPage = ({onLogout}) => {
    const [showContent, setShowContent] = useState("userPage")

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
            return <FeedPage />
        } else if (showContent === "userPage") {
            return <UserPage />
        }
    };

    return (
        <div className="main-container">
            <NavigationBar loadFeedPage={loadFeedPage} loadUserPage={loadUserPage} onLogout={onLogout}/>
            {routeController()}
        </div>
    );
};

export default MainPage;
