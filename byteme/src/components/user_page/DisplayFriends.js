import ProfilePicture from "./ProfilePicture";

const DisplayFriends = ({showedUser, setShowedUser}) => {

    const friendList = showedUser.friendList;

    if (friendList.length > 0) {
        return (
            <div className="friend-list-container">
                <p>FriendBytes</p>
                <div className="friend-pics-container flex-box">
                    {friendList.map(friend =>
                        <ProfilePicture userId={friend.id} key={friend.id} profilePictureId={friend.profilePictureId}
                                        placement="post" setShowedUser={setShowedUser}/>
                    )}
                </div>
            </div>
        )
    }
};

export default DisplayFriends;