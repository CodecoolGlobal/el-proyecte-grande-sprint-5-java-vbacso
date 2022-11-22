import AddFriendButton from "./AddFriendButton";
import DeleteFriendButton from "./DeleteFriendButton";

const Friend = ({loggedInUser, setLoggedInUser, showedUser, setShowedUser}) => {
    const loggedInUserId = loggedInUser.id;
    const showedUserId = showedUser.id;

    const isFriend = () => {
        let isFriend = false;
        for (let loggedInUserFriend of loggedInUser.friendList) {
            if (loggedInUserFriend.id === showedUserId) {
                isFriend = true;
            }
        }
        for (let showedUserFriend of showedUser.friendList) {
            if (showedUserFriend.id === loggedInUserId) {
                isFriend = true;
            }
        }
        return isFriend;
    };

    return (
        <div>
            {loggedInUserId === showedUserId ? ""
                : (loggedInUserId !== showedUserId && isFriend() === false) ?
                    <AddFriendButton loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}
                                     showedUser={showedUser} setShowedUser={setShowedUser}/>
                    : <DeleteFriendButton loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}
                                          showedUser={showedUser} setShowedUser={setShowedUser}/>}
        </div>
    );
};

export default Friend;