import ProfilePicture from "./ProfilePicture";

const DisplayFriends = ({showedUser, setShowedUser}) => {

    const friendList = showedUser.friendList;

    if (friendList.length > 0) {
        return (
            <div>
                <p>Friends</p>
                <div className="flex-box friend-list">
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