import {useEffect, useState} from "react";

const ProfilePicture = ({profilePictureId, placement}) => {
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

    if (!image) {
        return (<div className="main-container">Loading...</div>)
    } else {
        return (<div className={(placement === "post") ? "profile-pic-in-post" : "profile-pic-user-page"}>
            <img className="rounded-circle ratio" src={image} alt="Profile Picture"/>
        </div>)
    }
}


export default ProfilePicture;