import {useEffect} from "react";
import DisplayFriends from "./DisplayFriends";
import Loading from "../common/Loading";

const UserDetails = ({showedUser, setShowedUser}) => {

    useEffect(() => {

    }, [JSON.stringify(showedUser)]);

    if (!showedUser) {
        return (<Loading/>)
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