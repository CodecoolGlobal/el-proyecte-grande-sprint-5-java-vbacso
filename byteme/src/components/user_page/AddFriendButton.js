const AddFriendButton = ({loggedInUser, setLoggedInUser, showedUser, setShowedUser}) => {
    const loggedInUserId = loggedInUser.id;
    const showedUserId = showedUser.id;

    const addFriend = async () => {
        const resp = await fetch(`http://localhost:8080/user/${loggedInUserId}/${showedUserId}`, {
            method: "PUT",
            headers: {
                'Content-type': 'application/json'
            }
        });
        if (resp.ok) {
            console.log(loggedInUser);
            console.log(showedUser);
            const updatedLoggedInUserFriendList = loggedInUser.friendList;
            updatedLoggedInUserFriendList.push(showedUser.id);
            setLoggedInUser({...loggedInUser, "friendList": updatedLoggedInUserFriendList});
            console.log(loggedInUser);

            const updatedShowedUserFriendList = showedUser.friendList;
            updatedShowedUserFriendList.push(loggedInUser.id);
            setShowedUser({...showedUser, "friendList": updatedShowedUserFriendList});
            console.log(showedUser);
        }
    };

    return (
        <div>
            <button className="button button-light" onClick={addFriend}>Add friend</button>
        </div>
    );
};

export default AddFriendButton;