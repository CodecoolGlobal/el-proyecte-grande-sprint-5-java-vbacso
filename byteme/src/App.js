import './App.css';
import {useEffect, useState} from "react";
import LoginPage from "./components/login/LoginPage";
import {Navigate, Route, Routes, useNavigate} from "react-router-dom";
import MainPage from "./components/MainPage";
import RegistrationPage from "./components/registration/RegistrationPage";
import {getJwt, parseJwt} from "./util";

function App() {

    const [loggedInUser, setLoggedInUser] = useState();
    const [token, setToken] = useState(localStorage.getItem("token"));
    const navigate = useNavigate();
    useEffect(() => {
        if (localStorage.getItem("token")) {
            const fetchUser = async () => {
                const JSONToken = localStorage.getItem("token");
                const tokenData = parseJwt(JSONToken);
                const resp = await fetch(`/user/findByEmail/${tokenData.sub}`, {headers: {
                    "Authorization": JSONToken
                }});
                if (resp.ok) {
                    setLoggedInUser(await resp.json())
                }
            }
            fetchUser().catch(console.error)
        } else {
            setLoggedInUser(null)
        }
    }, [JSON.stringify(token)])


    const onLogin = async (email, password) => {
        const resp = await fetch("/login", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({"email": email, "password": password})
        });
        if (resp.ok) {
            const userToken = resp.headers.get("Authorization");
            const JSONToken = getJwt(userToken);
            localStorage.setItem("token", JSONToken);
            setToken(JSONToken)
        } else {
            alert(resp.status);
        }
    }

    const onRegistration = async (email, password, name, age) => {
        const resp = await fetch("/registration", {
            method: "POST",
            headers: {
                'Accept': 'application/json', 'Content-Type': 'application/json',
            },
            body: JSON.stringify({"email": email, "password": password, "name": name, "age": age})
        })
        if (resp.ok) {
            const user = await resp.json();
            if (user === null) {
                alert("This email is already assigned to one of our users, please register with an other email address!")
            }
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
