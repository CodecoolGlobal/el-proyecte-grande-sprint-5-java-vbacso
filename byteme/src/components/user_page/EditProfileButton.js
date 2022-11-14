import EditProfileModal from "./EditProfileModal";

const EditProfileButton = ({loggedInUser, setLoggedInUser}) => {

    function openModal() {
        const modal = document.querySelector("#myModal")
        modal.style.display = "block";
    }

    return (
        <div className="edit-profile-btn-container">
            <button className="button button-light" id="myBtn" onClick={openModal}>Edit Profile</button>
            <EditProfileModal loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}/>
        </div>
    );
};

export default EditProfileButton;