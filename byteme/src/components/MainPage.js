import React, {useEffect, useState} from 'react';
import NavigationBar from "./navbar/NavigationBar";
import FeedPage from "./feed_page/FeedPage";
import UserPage from "./user_page/UserPage";
import {Navigate, Route, Routes, useNavigate} from "react-router-dom";
import ArrayPage from "./array_page/ArrayPage";

const MainPage = ({loggedInUser, setLoggedInUser, onLogout}) => {
    const [showedUser, setShowedUser] = useState(loggedInUser);
    const navigate = useNavigate();

    useEffect(() => {
        if (showedUser === undefined) {
            setShowedUser(loggedInUser);
        }
    }, [loggedInUser]);

    const loadFeedPage = (e) => {
        e.preventDefault();
        navigate("/feed")
    };

    const loadUserPage = (e) => {
        e.preventDefault();
        navigate("/user/" + loggedInUser.id)
    };

    const onSetShowedUser = (user) => {
        setShowedUser(user);
    };

    return (
        <div className="main-container">
            <NavigationBar loggedInUser={loggedInUser} loadFeedPage={loadFeedPage} loadUserPage={loadUserPage}
                           onLogout={onLogout} onSetShowedUser={onSetShowedUser} loadArrayPage={loadArrayPage}/>

            <Routes>
                <Route path="/*" element={<Navigate to={"feed"}/>}/>
                <Route exact path='/feed'
                       element={<FeedPage loggedInUser={loggedInUser}/>}/>
                <Route path='/user/:userId'
                       element={<UserPage loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}
                                          showedUser={showedUser}
                                          setShowedUser={setShowedUser}/>}
                />
            </Routes>
        </div>
    );
};

export default MainPage;
