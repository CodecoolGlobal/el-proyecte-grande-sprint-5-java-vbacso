import {useEffect} from "react";
import DisplayFriends from "./DisplayFriends";

const UserDetails = ({showedUser, setShowedUser}) => {

    useEffect(() => {

    }, [JSON.stringify(showedUser)]);

    if (!showedUser) {
        return (<div className="main-container">Loading...</div>)
    } else {
        return (
            <div className="user-details-container">
                <div className="details">Name: {showedUser.name}</div>
                <div className="details">Age: {showedUser.age}</div>
                <DisplayFriends showedUser={showedUser} setShowedUser={setShowedUser}/>
            </div>
        )
    }
};

export default UserDetails;