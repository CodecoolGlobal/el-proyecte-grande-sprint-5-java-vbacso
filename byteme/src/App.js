import './App.css';
import {useEffect, useState} from "react";
import LoginPage from "./components/login/LoginPage";
import {Navigate, Route, Routes, useNavigate} from "react-router-dom";
import MainPage from "./components/MainPage";
import RegistrationPage from "./components/registration/RegistrationPage";
import {extractEmailFromToken, getAuthenticationToken} from "./util";

function App() {

    const [loggedInUser, setLoggedInUser] = useState();
    const [token, setToken] = useState(getAuthenticationToken);
    const navigate = useNavigate();
    useEffect(() => {
        if (token) {
            const fetchUser = async () => {
                const userEmail = extractEmailFromToken(token);
                const resp = await fetch(`http://localhost:8080/user/findByEmail/${userEmail}`, {
                    headers: {
                        "Authorization": getAuthenticationToken()
                    }
                });
                if (resp.ok) {
                    setLoggedInUser(await resp.json());
                } else {
                    navigate("/login")
                }
            }
            fetchUser().catch(console.error);
        } else {
            navigate("/login")
        }
    }, [token])


    const onLogin = async (email, password) => {
        const resp = await fetch("http://localhost:8080/login", {
            method: "POST", headers: {
                'Content-Type': 'application/json',
            }, body: JSON.stringify({"email": email, "password": password})
        });
        if (resp.ok) {
            const userTokenString = resp.headers.get("Authorization");
            localStorage.setItem("token", userTokenString);
            setToken(userTokenString)
        } else {
            alert(resp.status);
        }
    }

    const onRegistration = async (email, password, name, age) => {
        const resp = await fetch("http://localhost:8080/registration", {
            method: "POST", headers: {
                'Accept': 'application/json', 'Content-Type': 'application/json',
            }, body: JSON.stringify({"email": email, "password": password, "name": name, "age": age})
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

    return (<Routes>
            <Route path='/*'
                   element={loggedInUser ? <MainPage loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}
                                                     onLogout={onLogout}/> : loggedInUser === undefined ?
                       <div>Loading...</div> : <Navigate replace to={"/login"}/>}/>
            <Route exact path='/login'
                   element={<LoginPage loggedInUser={loggedInUser} onLogin={onLogin}/>}/>
            <Route exact path='/registration'
                   element={<RegistrationPage loggedInUser={loggedInUser} onRegistration={onRegistration}/>}/>
        </Routes>);
}

export default App;
