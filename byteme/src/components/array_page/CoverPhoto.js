import {useEffect, useState} from "react";
import Loading from "../common/Loading";

const CoverPhoto = ({photoId}) => {
    const [image, setImage] = useState();

    useEffect(() => {
        const fetchImage = async () => {
            const resp = await fetch(`http://localhost:8080/image/${photoId}`);
            const imageBlob = await resp.blob();
            const imageObjectURL = URL.createObjectURL(imageBlob);
            setImage(imageObjectURL);
        };
        if (photoId) {
            fetchImage().catch(console.error);
        } else {
            setImage("/img.png")
        }
    }, [photoId]);
    if (!image) {
        return (<Loading/>)
    } else {
        return (<div className="profile-pic-user-page">
            <img src={image} alt="Cover Picture"/>
        </div>)
    }
};

export default CoverPhoto;