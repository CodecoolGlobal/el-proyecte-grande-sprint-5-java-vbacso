import {useEffect, useState} from "react";

const ProfilePicture = ({userId, profilePictureId, placement, setShowedUser}) => {
    const [image, setImage] = useState();
    useEffect(() => {
        const fetchImage = async () => {
            const resp = await fetch(`http://localhost:8080/image/${profilePictureId}`)
            const imageBlob = await resp.blob();
            const imageObjectURL = URL.createObjectURL(imageBlob);
            setImage(imageObjectURL);
        };
        fetchImage().catch(console.error);
    }, [profilePictureId]);

    const onLoadUserPage = async (e) => {
        e.preventDefault();
        const showedUserId = e.target.dataset.userId;
        if (showedUserId !== undefined) {
            const res = await fetch(`http://localhost:8080/user/findById/${showedUserId}`);
            setShowedUser(await res.json());
        }
    }

    if (!image) {
        return (<div className="main-container">Loading...</div>)
    } else {
        return (<div onClick={onLoadUserPage}
                     className={(placement === "post") ? "profile-pic-in-post" : "profile-pic-user-page"}>
            <img className="rounded-circle ratio" data-user-id={userId} src={image} alt="Profile Picture"/>
        </div>)
    }
}


export default ProfilePicture;