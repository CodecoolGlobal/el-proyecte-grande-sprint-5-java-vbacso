import {useEffect, useState} from "react";

const UserDetails = () => {

    const [user, setUser] = useState();

    useEffect(() => {
        const getUser = async () => {
            const getUserFromServer = await fetchUser();
            setUser(getUserFromServer);
        }
        getUser().catch(console.error);
    }, []);

    const loggedInUserId = JSON.parse(localStorage.getItem("loggedInUser")).id;

    // Fetch user
    const fetchUser = async () => {
        const res = await fetch(`http://localhost:8080/user/findById/${loggedInUserId}`);
        return (await res.json());
    }

    if (!user) {
        console.log("loading...");
    } else {
        return (
            <div className="user-details-container">
                <div>Name: {user.name}</div>
                <div>Age: {user.age}</div>
            </div>
        )
    }
};

export default UserDetails;