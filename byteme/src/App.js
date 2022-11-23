import './App.css';
import {useEffect, useState} from "react";
import LoginPage from "./components/login/LoginPage";
import {Navigate, Route, Routes, useNavigate} from "react-router-dom";
import MainPage from "./components/MainPage";


function App() {

    const [loggedInUser, setLoggedInUser] = useState(JSON.parse(localStorage.getItem("loggedInUser")));
    const navigate = useNavigate();
    useEffect(() => {
        if (localStorage.getItem("loggedInUser")) {
            localStorage.setItem("loggedInUser", JSON.stringify(loggedInUser))
        } else {
            setLoggedInUser(null)
        }
    }, [JSON.stringify(loggedInUser)])


    const onLogin = async (email) => {
        const resp = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                'Accept': 'application/json', 'Content-Type': 'application/json',
            },
            body: JSON.stringify({"email": email})
        })
        if (resp.ok) {
            const user = await resp.json();
            setLoggedInUser(user);
            localStorage.setItem("loggedInUser", JSON.stringify(user))
        } else {
            alert("Invalid email!")
        }
    }
    const onLogout = () => {
        localStorage.clear();
        setLoggedInUser(null);
    };
    return (
        <Routes>
            <Route path='/*'
                   element={loggedInUser ? <MainPage loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} onLogout={onLogout}/> :
                            loggedInUser === undefined ? <div>Loading...</div> :
                           <Navigate replace to={"/login"}/>}/>
            <Route exact path='/login'
                   element={<LoginPage onLogin={onLogin}/>}/>
        </Routes>
    );
}

export default App;
