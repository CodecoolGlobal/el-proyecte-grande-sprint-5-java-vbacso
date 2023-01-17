import ProfilePicture from "../user_page/ProfilePicture";

const DisplayFriendList = ({availableFriends, selectedFriends, inviteFriend}) => {
    return (
        <div>
            <div id="selected-friends">
                <p>Invited Friends:</p>
                {selectedFriends.map((friend) => <div className="selected-friends" key={friend.id} data-id={friend.id}>
                    <ProfilePicture userId={friend.id} profilePictureId={friend.profilePictureId} placement="post"/>
                    {friend.name}
                </div>)}
            </div>
            <br/>
            <select id="friends" onChange={inviteFriend}>
                <option id="choose-friend">Choose friend</option>
                {availableFriends.map((friend) => <option className="available-friends" key={friend.id}
                                                          data-id={friend.id}
                                                          data-picture={friend.profilePictureId}
                                                          value={friend.name}>{friend.name}</option>)}
            </select>
        </div>
    );
};

export default DisplayFriendList;
