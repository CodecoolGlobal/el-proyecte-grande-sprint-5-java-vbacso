import React from 'react';

const LinkFeedPage = ({loadFeedPage}) => {
    return (<a className="nav-link" onClick={loadFeedPage}>byte.Feed</a>
    );
};

export default LinkFeedPage;
