const Array = ({group, loggedInUser, placement, setShowGroup}) => {

    const isOwnerPlacement = () => {
        if (group.owner.id === loggedInUser.id && placement === "owner") {
            return true;
        } else if (group.owner.id === loggedInUser.id && placement === "member") {
            return false;
        } else {
            return false;
        }
    };

    const isMemberPlacement = () => {
        for (let member of group.members) {
            if (member.id === loggedInUser.id && group.owner.id === loggedInUser.id) {
                return false;
            } else if (member.id === loggedInUser.id && placement === "member") {
                return true;
            }
        }
        return false;
    };

    const onShowGroupDetails = () => {
        setShowGroup(group);
    };

    return (
        <div className="nav-item array">
            {isOwnerPlacement() ?
                <p onClick={onShowGroupDetails} className="owner-array">{group.name}</p> : isMemberPlacement() ?
                    <p onClick={onShowGroupDetails} className="member-array">{group.name}</p> : <div></div>}
        </div>
    );
};

export default Array;
