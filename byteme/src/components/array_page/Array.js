const Array = ({group, showedUser, placement}) => {

    const isOwnerPlacement = () => {
        if (group.owner.id === showedUser.id && placement === "owner") {
            return true;
        } else if (group.owner.id === showedUser.id && placement === "member") {
            return false;
        } else {
            return false;
        }
    };

    const isMemberPlacement = () => {
        for (let member of group.members) {
            if (member.id === showedUser.id && group.owner.id === showedUser.id) {
                return false;
            } else if (member.id === showedUser.id && placement === "member") {
                return true;
            }
        }
        return false;
    };

    const onShowGroupDetails = () => {
        console.log(group);
    };

    return (
        <div className="nav-item array">
            {isOwnerPlacement() ?
                <p onClick={onShowGroupDetails} className="owner-array">{group.name}</p> : isMemberPlacement() ?
                    <p className="member-array">{group.name}</p> : <div></div>}
        </div>
    );
};

export default Array;