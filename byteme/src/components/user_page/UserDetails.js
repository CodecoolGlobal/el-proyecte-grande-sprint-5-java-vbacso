import {useEffect, useState} from "react";

const UserDetails = ({userId}) => {

    const [user, setUser] = useState();

    // Get user
    useEffect(() => {
        const getUser = async () => {
            const res = await fetchUser();
            setUser(res);
        }
        getUser().catch(console.error);
    }, [userId])

    // Fetch user
    const fetchUser = async () => {
        const res = await fetch(`http://localhost:8080/user/findById/${userId}`);
        return (await res.json());
    }

    if (!user) {
        return (<div className="main-container">Loading...</div>)
    } else {
        return (
            <div className="user-details-container">
                <div className="details">Name: {user.name}</div>
                <div className="details">Age: {user.age}</div>
            </div>
        )
    }
};

export default UserDetails;