import React from 'react';

const LinkFeedPage = ({loadFeedPage}) => {
    return (<a className="nav-link" id="feed-page" onClick={loadFeedPage}>byte.Feed</a>
    );
};

export default LinkFeedPage;
