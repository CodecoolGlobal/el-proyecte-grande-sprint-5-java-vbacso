import '../../App.css';
import { useState, useEffect} from 'react'
import Post from '../post/Post'
import CreatePost from '../post/CreatePost';

const UserPage = () => {

  const [user, setUser] = useState()

  // Fetch user
  useEffect(()=> {
    const getUser = async () => {
      const usersFromServer = await fetchUser()
      setUser(usersFromServer)
    }

    getUser()
  }, [])

  const loggedInUserId = JSON.parse(localStorage.getItem("loggedInUser")).id;

  // Fetch user
  const fetchUser = async () => {
    const res = await fetch(`http://localhost:8080/user/findById/${loggedInUserId}`)
    const data = await res.json()


    return data
  }

  // Delete Post
  const deletePost = async (id) => {
    const res = await fetch(`http://localhost:8080/user/findById/${loggedInUserId}/delete`, {
      method:'DELETE',
      headers:{
        'Content-type':'application/json'
      },
      body: JSON.stringify(id)
    })

    setUser(user.posts.filter((p)=>p.id !== id))
  }

  // Create Post
  const createPost = async (input) => {
    const res = await fetch(`http://localhost:8080/user/findById/${loggedInUserId}/add`,{
      method: 'POST',
      headers:{
        'Content-type':'application/json'
      },
      body: JSON.stringify(input)
    })

    const data = await res.json()

    setUser(data)
  }

  if(!user){
    console.log("loading...")
  }else {

  return (
    <div>
      <h1 style={{textAlign:"center"}}>{user.name}</h1>
      <CreatePost onAdd={createPost}/>
      {console.log(user)}
      <Post user={user} onDelete={deletePost} />
    </div>
  )}
}

export default UserPage