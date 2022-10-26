import './App.css';
import {useEffect, useState} from "react";
import LoginPage from "./components/LoginPage";
import MainPage from "./components/MainPage";



function App() {

    const [user, setUser] = useState("");

    useEffect(() => {
        const loggedInUser = localStorage.getItem("loggedInUser")
        if (loggedInUser) {
            setUser(JSON.parse(loggedInUser));
        }
    }, [])


    const onLogin = async (email) => {
        const resp = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                'Accept': 'application/json', 'Content-Type': 'application/json',
            },
            body: JSON.stringify({"email": email})
        })
        if (resp.ok) {
            const userDetails = await resp.json();
            setUser(userDetails);
            localStorage.setItem("loggedInUser", JSON.stringify(userDetails))
        } else {
            alert("Invalid email!")
        }
    }
    const onLogout = () => {
        localStorage.setItem("loggedInUser", "");
        setUser("");
    };

    return (<div>
        {
            user===""? <LoginPage onLogin={onLogin}/>:<MainPage onLogout={onLogout}/>
        }
    </div>);
}

export default App;
