import {useEffect, useState} from "react";

const ProfilePicture = ({profilePictureId}) => {
    const [image, setImage] = useState();

    useEffect(() => {
        const fetchImage = async () => {
            const resp = await fetch(`http://localhost:8080/image/${profilePictureId}`)
            const imageBlob = await resp.blob();
            const imageObjectURL = URL.createObjectURL(imageBlob);
            setImage(imageObjectURL);
        };
        fetchImage().catch(console.error);
    },[profilePictureId]);
    return (
        image ?
            <div className="profile-pic-user-page">
                <img className="rounded-circle ratio" src={image} alt="Profile Picture"/>
            </div>
            :
            <div>Load image...</div>
    );
};

export default ProfilePicture;