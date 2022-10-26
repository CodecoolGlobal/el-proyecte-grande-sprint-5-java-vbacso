import '../../App.css';
import { useState, useEffect} from 'react'
import Post from '../Post'
import CreatePost from '../CreatePost';

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

  // Fetch user
  const fetchUser = async () => {
    const loggedInUserId = JSON.parse(localStorage.getItem("loggedInUser")).id;

    const res = await fetch(`http://localhost:8080/user/findById/${loggedInUserId}`)
    const data = await res.json()


    return data
  }

  // Delete Post
  const deletePost = (id) => {
    setUser(user.filter((post) => post.id !== id))
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

    setUser([...user, data])
  }

  if(!user){
    console.log("loading...")
  }else {

  return (
    <div>
      <CreatePost onAdd={createPost}/>
      {console.log(user)}
      <Post user={user} onDelete={deletePost} />
    </div>
  )}
}

export default UserPage