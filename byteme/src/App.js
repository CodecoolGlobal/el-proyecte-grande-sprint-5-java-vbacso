import './App.css';
import {useEffect, useState} from "react";
import LoginPage from "./components/login/LoginPage";
import MainPage from "./components/MainPage";


function App() {

    const [loggedInUser, setLoggedInUser] = useState();

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
        localStorage.setItem("loggedInUser", "");
        setLoggedInUser(null);
    };
    return (
        loggedInUser === undefined ? <div className="main-container">"loading..."</div> :
            loggedInUser === null ? <LoginPage onLogin={onLogin}/> : <MainPage loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} onLogout={onLogout}/>
    );
}

export default App;
