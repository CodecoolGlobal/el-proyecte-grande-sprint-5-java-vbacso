import './App.css';
import { useState, useEffect} from 'react'
import Post from './components/Post'
import CreatePost from './components/CreatePost';

const User = () => {

  const [posts, setPost] = useState([])

  // Fetch user
  useEffect(()=> {
    const getUser = async () => {
      const usersFromServer = await fetchUser()
      setPost(usersFromServer)
    }

    getUser()
  }, [])

  // Fetch user
  const fetchUser = async () => {
    const res = await fetch('http://localhost:8080/user/all')
    const data = await res.json()

    console.log(data)
    return data
  }

  // Delete Post
  const deletePost = (id) => {
    setPost(posts.filter((post) => post.id !== id))
  }

  // Create Post
  const createPost = async (input) => {

    const res = await fetch('http://localhost:8080/user/all', {
      method: 'POST',
      headers:{
        'Content-type':'application/json'
      },
      body: JSON.stringify(input)
    })

    const data = await res.json()

    setPost([...posts, data])

    //const id = Math.floor(Math.random() * 10000) + 1
    //const newComment = {id, ...input }
    

    //setPost([...posts, newComment])
  }

  return (
    <div>
      <CreatePost onAdd={createPost}/>
      <Post postsMain={posts} onDelete={deletePost} />
    </div>
  )
}

export default User