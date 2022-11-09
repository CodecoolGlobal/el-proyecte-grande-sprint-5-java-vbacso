import EditProfileModal from "./EditProfileModal";

const EditProfileButton = () => {

    function openModal() {
        const modal = document.querySelector("#myModal")
        modal.style.display = "block";
    }

    return (
        <div className="edit-profile-btn-container">
            <button className="button button-light" id="myBtn" onClick={openModal}>Edit Profile</button>
            <EditProfileModal/>
        </div>
    );
};

export default EditProfileButton;