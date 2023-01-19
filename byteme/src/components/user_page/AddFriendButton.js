import {getAuthenticationToken} from "../../util";

const AddFriendButton = ({loggedInUser, setLoggedInUser, showedUser, setShowedUser}) => {
    const loggedInUserId = loggedInUser.id;
    const showedUserId = showedUser.id;
    const addFriend = async () => {
        const resp = await fetch(`/user/${loggedInUserId}/${showedUserId}`, {
            method: "PUT",
            headers: {
                "Authorization": getAuthenticationToken(),
                'Content-type': 'application/json'
            }
        });
        if (resp.ok) {
            const updatedLoggedInUserFriendList = loggedInUser.friendList;
            updatedLoggedInUserFriendList.push({
                "id": showedUser.id,
                "name": showedUser.name,
                "profilePictureId": showedUser.profilePictureId});
            setLoggedInUser({...loggedInUser, "friendList": updatedLoggedInUserFriendList});

            const updatedShowedUserFriendList = showedUser.friendList;
            updatedShowedUserFriendList.push({
                "id": loggedInUser.id,
                "profilePictureId": loggedInUser.profilePictureId
            });
            setShowedUser({...showedUser, "friendList": updatedShowedUserFriendList});
        }
    };

    return (
        <div>
            <button className="button button-light" onClick={addFriend}>Add friend</button>
        </div>
    );
};

export default AddFriendButton;
