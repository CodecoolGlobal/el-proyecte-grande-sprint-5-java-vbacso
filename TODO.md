# Registration

- Don't send back the whole user object (for example the hashed password)

# General

## CrossOrigin

Make the **frontend** send same-origin requests:

```
GET /api/example
```

instead of

```
GET http://localhost:8080/api/example
```

# Frontend

Add authorization header to authorized requests.
