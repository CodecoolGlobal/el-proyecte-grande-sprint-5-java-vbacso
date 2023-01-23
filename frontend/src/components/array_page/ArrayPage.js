import {useEffect, useState} from "react";
import ArrayPageLeftContainer from "./ArrayPageLeftContainer";
import ArrayPageRightContainer from "./ArrayPageRightContainer";
import {getAuthenticationToken} from "../../util";

const ArrayPage = ({loggedInUser}) => {

    const [groups, setGroups] = useState([]);

    const [showGroup, setShowGroup] = useState([]);

    useEffect(() => {
        const getGroups = async () => {
            const resp = await fetch(`/group/user/${loggedInUser.id}`, {
                headers: {
                    "Authorization": getAuthenticationToken()
                }
            });
            if (resp.ok) {
                setGroups(await resp.json());
            }
        };
        getGroups().catch(console.error);
    }, [showGroup]);

    return (
        <div className="group-main-container flex-fill">
            <ArrayPageLeftContainer groups={groups} setGroups={setGroups} loggedInUser={loggedInUser}
                                    setShowGroup={setShowGroup}/>
            {showGroup ? <ArrayPageRightContainer showGroup={showGroup} setShowGroup={setShowGroup}
                                                  loggedInUser={loggedInUser}/> : ""}
        </div>
    );
}

export default ArrayPage;
