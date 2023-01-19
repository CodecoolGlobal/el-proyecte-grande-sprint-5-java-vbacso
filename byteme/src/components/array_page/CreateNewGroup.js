import React, {useEffect, useState} from 'react';
import DisplayFriendList from "./DisplayFriendList";
import {getAuthenticationToken} from "../../util";

const CreateNewGroup = ({loggedInUser, groups, setGroups}) => {

    const [friendList, setFriendList] = useState([]);

    const [availableFriends, setAvailableFriends] = useState([]);

    const [selectedFriends, setSelectedFriends] = useState([]);

    useEffect(() => {
        const getFriendListFromServer = async () => {
            setFriendList(await getUserFriendsById(loggedInUser.id));
        };
        getFriendListFromServer().catch(console.error);
    }, []);

    useEffect(() => {
        if (friendList.length > 0) {
            setAvailableFriends(friendList);
        }
    }, [friendList]);

    // Close Modal
    const closeModal = () => {
        const groupNameField = document.querySelector("#group-name");
        groupNameField.value = "";
        setAvailableFriends(friendList);
        setSelectedFriends([]);
        const modal = document.querySelector("#myModal")
        modal.style.display = "none";
    };

    // Save Group
    const saveGroup = async () => {
        const groupName = document.querySelector("#group-name").value;
        const members = [loggedInUser];
        const friendNodes = document.querySelectorAll(".selected-friends");
        for (const node of friendNodes) {
            const currentUser = await getUserById(node.dataset.id);
            members.push(currentUser);
        }
        const input = {"name": groupName, "owner": loggedInUser, "members": members};
        const newGroup = await saveNewGroup(input);
        setGroups([...groups, newGroup]);
        closeModal();
    };

    const saveNewGroup = async (input) => {
        return await fetchToServer(input);
    };

    const fetchToServer = async (input) => {
        const res = await fetch(`/group/add`, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
                "Authorization": getAuthenticationToken()
            },
            body: JSON.stringify(input)
        });
        return await res.json();
    };

    const getUserFriendsById = async (id) => {
        const resp = await fetch(`/user/friends/${id}`, {
            headers: {
                "Authorization": getAuthenticationToken()
            }
        });
        return await resp.json();
    };

    const getUserById = async (id) => {
        const resp = await fetch(`/user/findById/${id}`, {
            headers: {
                "Authorization": getAuthenticationToken()
            }
        });
        if (resp.ok) {
            return (await resp.json());
        }
    };

    // Invite Friends to Group
    const inviteFriend = (e) => {
        e.preventDefault();
        const name = e.target.value;
        const friends = document.querySelectorAll('.available-friends');
        let friendId = 0;
        let friendProfilePictureId = 0;
        for (const friend of friends) {
            if (friend.value === name) {
                friendId = friend.dataset.id;
                friendProfilePictureId = friend.dataset.picture;
            }
        }
        if (name !== "Choose friend") {
            setSelectedFriends([...selectedFriends, {
                "id": friendId,
                "name": name,
                "profilePictureId": friendProfilePictureId
            }]);
            setAvailableFriends(availableFriends.filter((friend) => friend.name !== name));
        }
    };

    return (
        <div id="myModal" className="modal">
            <div className="modal-content">
                <div className="modal-header">
                    <span className="close" onClick={closeModal}>&times;</span>
                    <h3 id="modal-title">Create new Group</h3>
                </div>
                <div className="modal-body">
                    <div className="edit-user-details">
                        <label htmlFor="group-name" className="datas-to-edit">Group Name</label>
                        <br/>
                        <input type="text" id="group-name"/>
                    </div>
                    <div>
                        {friendList ?
                            <DisplayFriendList availableFriends={availableFriends} selectedFriends={selectedFriends}
                                               inviteFriend={inviteFriend}/> : <div></div>}
                    </div>
                </div>
                <div className="modal-footer">
                    <button type="button" className="button button-dark" data-dismiss="modal"
                            onClick={closeModal}>Close
                    </button>
                    <button type="button" className="button button-light" onClick={saveGroup}>Save changes
                    </button>
                </div>
            </div>
        </div>
    );
};

export default CreateNewGroup;
