import EditProfileModal from "./EditProfileModal";

const EditProfileButton = () => {

    function openModal() {
        const modal = document.querySelector("#myModal")
        modal.style.display = "block";
    }

    return (
        <div>
            <button className="btn-primary btn" id="myBtn" onClick={openModal}>Edit Profile</button>
            <EditProfileModal/>
        </div>

    );
};

export default EditProfileButton;