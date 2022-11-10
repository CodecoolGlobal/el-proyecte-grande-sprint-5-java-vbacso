import './App.css';
import {useEffect, useState} from "react";
import LoginPage from "./components/login/LoginPage";
import {Navigate, Route, Routes, useNavigate} from "react-router-dom";
import MainPage from "./components/MainPage";


function App() {

    const loggedInUser = JSON.parse(localStorage.getItem("loggedInUser"))
    const [user, setUser] = useState();
    const navigate = useNavigate();

    useEffect(() => {
        if (loggedInUser) {
            setUser(loggedInUser);
        } else {
            setUser(null)
        }
    }, [])
    return (
        <Routes>
            <Route exact path='/'
                   element={user ? <Navigate replace to={"user/" + user.id}/> :
                       user === undefined ? <div>Loading...</div> :
                           <Navigate replace to={"/login"}/>}/>
            <Route exact path='/login'
                   element={<LoginPage setUser={setUser}/>}/>
            <Route exact path='user/:userId'
                   element={user ? <MainPage loggedInUserId={loggedInUser.id} setUser={setUser}/> :
                       <Navigate replace to={"/login"}/>}/>
        </Routes>
    )
    // if (user === undefined) {
    //      return <div>"loading..."</div>
    // } else if (user === null) {
    //     return <LoginPage onLogin={onLogin}/>;
    // } else {
    //     return <MainPage loggedInUserId={user.id} onLogout={onLogout}/>
    // }


    // user === undefined ? <div>"loading..."</div> :
    //     user === null ? <LoginPage onLogin={onLogin}/> : <MainPage loggedInUserId={user.id} onLogout={onLogout}/>;
}

export default App;
