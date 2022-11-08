import React, {useState} from 'react';
import NavigationBar from "./navbar/NavigationBar";
import FeedPage from "./feed_page/FeedPage";
import UserPage from "./user_page/UserPage";
import EditProfilePage from "./user_page/EditProfilePage";

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
    const loadEditProfile = (e) => {
        e.preventDefault();
        setShowContent("editProfilePage")
    }

    const routeController = () => {
        if (showContent === "feedPage") {
            return <FeedPage loggedInUserId={loggedInUserId}/>
        } else if (showContent === "userPage") {
            return <UserPage loadEditProfile={loadEditProfile}/>
        } else if (showContent === "editProfilePage") {
            return <EditProfilePage/>
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
