import {useEffect} from "react";

const UserDetails = ({showedUser}) => {

    useEffect(() => {

    }, [JSON.stringify(showedUser)]);

    if (!showedUser) {
        return (<div className="main-container">Loading...</div>)
    } else {
        return (
            <div className="user-details-container">
                <div className="details">Name: {showedUser.name}</div>
                <div className="details">Age: {showedUser.age}</div>
            </div>
        )
    }
};

export default UserDetails;