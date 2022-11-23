import './App.css';
import {useEffect, useState} from "react";
import LoginPage from "./components/login/LoginPage";
import {Navigate, Route, Routes, useNavigate} from "react-router-dom";
import MainPage from "./components/MainPage";
import RegistrationPage from "./components/registration/RegistrationPage";

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


    const onLogin = async (email, password) => {
        const resp = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                'Accept': 'application/json', 'Content-Type': 'application/json',
            },
            body: JSON.stringify({"email": email, "password": password})
        })
        if (resp.ok) {
            const user = await resp.json();
            setLoggedInUser(user);
            localStorage.setItem("loggedInUser", JSON.stringify(user))
        } else {
            alert("Invalid email!")
        }
    }

    const onRegistration = async (email, password, name, age) => {
        const resp = await fetch("http://localhost:8080/registration", {
            method: "POST",
            headers: {
                'Accept': 'application/json', 'Content-Type': 'application/json',
            },
            body: JSON.stringify({"email": email, "password": password, "name": name, "age": age})
        })
        if (resp.ok) {
            const user = await resp.json();
            console.log(user);
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
                   element={loggedInUser ?
                       <MainPage loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} onLogout={onLogout}/> :
                       loggedInUser === undefined ? <div>Loading...</div> :
                           <Navigate replace to={"/login"}/>}/>
            <Route exact path='/login'
                   element={<LoginPage loggedInUser={loggedInUser} onLogin={onLogin}/>}/>
            <Route exact path='/registration'
                   element={<RegistrationPage loggedInUser={loggedInUser} onRegistration={onRegistration}/>}/>
        </Routes>
    );
}

export default App;
