import {useEffect, useState} from "react";
import ProfilePicture from "./ProfilePicture";
import EditButton from "./EditButton";

const EditProfileModal = () => {

    const [user, setUser] = useState();

    useEffect(() => {
        const getUser = async () => {
            const getUserFromServer = await fetchUser();
            setUser(getUserFromServer);
        }
        getUser().catch(console.error);
    }, []);

    const loggedInUserId = JSON.parse(localStorage.getItem("loggedInUser")).id;

    // Fetch user
    const fetchUser = async () => {
        const res = await fetch(`http://localhost:8080/user/findById/${loggedInUserId}`);
        return (await res.json());
    }

    // Close Modal
    function closeModal() {
        const modal = document.querySelector("#myModal")
        modal.style.display = "none";
    }

    // Save changes
    function saveProfileChanges() {
        const modal = document.querySelector(".modal");
        const userId = modal.dataset.id;

        const savedUserName = document.querySelector(".user-data-name").textContent;
        const savedUserAge = document.querySelector(".user-data-age").textContent;
        const savedUserEmail = document.querySelector(".user-data-email").textContent;
        const updateUser = {"id": userId, "name": savedUserName, "age": savedUserAge, "email": savedUserEmail};

        const saveUserData = async (user) => {
            const resp = fetch('http://localhost:8080/user/update', {
                method: "PUT",
                headers: {
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(user)
            });
            return (await resp).json();
        };
        saveUserData(updateUser).catch(console.error);
        closeModal();
    }

    if (!user) {
        console.log("loading...");
    } else {
        return (
            <div id="myModal" className="modal" data-id={user.id}>
                <div className="modal-content">
                    <div className="modal-header">
                        <span className="close" onClick={closeModal}>&times;</span>
                        <h3 id="modal-title">Edit your profile</h3>
                    </div>
                    <div className="modal-body">
                        <div id="user-profile-picture">
                            <p>Profile Picture</p>
                            <ProfilePicture image={user.profilePic}/>
                        </div>
                        <div id="user-name">
                            <p>Name</p>
                            <p className="user-data-name">{user.name}</p>
                            <EditButton/>
                        </div>
                        <div id="user-age">
                            <p>Age</p>
                            <p className="user-data-age">{user.age}</p>
                            <EditButton/>
                        </div>
                        <div id="user-email">
                            <p>E-mail</p>
                            <p className="user-data-email">{user.email}</p>
                            <EditButton/>
                        </div>
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="button button-dark" data-dismiss="modal"
                                onClick={closeModal}>Close
                        </button>
                        <button type="button" className="button button-light" onClick={saveProfileChanges}>Save changes
                        </button>
                    </div>
                </div>
            </div>
        );
    }
};

export default EditProfileModal;