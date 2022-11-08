import {useEffect, useState} from "react";
import ProfilePicture from "./ProfilePicture";
import {FaRegEdit} from "react-icons/fa";

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

    }

    if (!user) {
        console.log("loading...");
    } else {
        return (
            <div id="myModal" className="modal">
                <div className="modal-content">
                    <span className="close" onClick={closeModal}>&times;</span>
                    <div className="modal-body">
                        <p id="user-profile-picture">
                            <ProfilePicture image={user.profilePic}/>
                            <FaRegEdit/>
                        </p>
                        <p id="user-name">{user.name}</p>
                        <p id="user-age">{user.age}</p>
                        <p id="user-email">{user.email}</p>
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-secondary" data-dismiss="modal"
                                onClick={closeModal}>Close
                        </button>
                        <button type="button" className="btn btn-primary" onClick={saveProfileChanges}>Save changes
                        </button>
                    </div>
                </div>
            </div>
        );
    }
};

export default EditProfileModal;