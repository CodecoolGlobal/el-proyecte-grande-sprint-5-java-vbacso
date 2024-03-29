import {getAuthenticationToken} from "../../util";

const DeleteFriendButton = ({loggedInUser, setLoggedInUser, showedUser, setShowedUser}) => {
    const loggedInUserId = loggedInUser.id;
    const showedUserId = showedUser.id;
    const deleteFriend = async () => {
        const resp = await fetch(`/user/${loggedInUserId}/${showedUserId}`, {
            method: "DELETE",
            headers: {
                "Authorization": getAuthenticationToken(),
                'Content-type': 'application/json'
            }
        });
        if (resp.ok) {
            const updatedLoggedInUserFriendList = loggedInUser.friendList.filter(friend => friend.id !== showedUserId);
            setLoggedInUser({...loggedInUser, "friendList": updatedLoggedInUserFriendList});

            const updatedShowedUserFriendList = showedUser.friendList.filter(friend => friend.id !== loggedInUserId);
            setShowedUser({...showedUser, "friendList": updatedShowedUserFriendList});
        }
    }

    return (
        <div>
            <button className="button button-light" onClick={deleteFriend}>Delete friend</button>
        </div>
    );
};

export default DeleteFriendButton;
