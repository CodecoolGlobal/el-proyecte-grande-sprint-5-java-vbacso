import DisplayFriends from "./DisplayFriends";
import Loading from "../common/Loading";
import DisplayUserDatas from "./DisplayUserDatas";

const UserDetails = ({showedUser, setShowedUser}) => {

    if (!showedUser) {
        return (<Loading/>)
    } else {
        return (
            <div>
                <DisplayUserDatas showedUser={showedUser} setShowedUser={setShowedUser}/>
                <DisplayFriends showedUser={showedUser} setShowedUser={setShowedUser}/>
            </div>
        )
    }
};

export default UserDetails;
