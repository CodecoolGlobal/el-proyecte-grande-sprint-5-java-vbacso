import './App.css';
import { useState } from 'react'
import Post from './components/Post'
import CreatePost from './components/CreatePost';

const User = () => {

  const [posts, setPost] = useState([
    {
      id:1,
      title:"first title",
      body:"first comment"
    },
    {
      id:2,
      title:"second title",
      body:"second comment"
    },
    {
      id:3,
      title:"third title",
      body:"third comment"
    }
  ])

  const deletePost = (id) => {
    setPost(posts.filter((post) => post.id !== id))
  }

  const createPost = (input) => {

    const id = Math.floor(Math.random() * 10000) + 1
    const newComment = {id, ...input }
    

    setPost([...posts, newComment])
  }

  return (
    <div>
      <CreatePost onAdd={createPost}/>
      <Post posts={posts} onDelete={deletePost} />
    </div>
  )
}

export default User