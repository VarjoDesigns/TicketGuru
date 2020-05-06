# Authentication

All endpoints require authentication. Authentication is done by using JSON Web Token. After signing in you must use the response token in every query.

**URL** : `/api/login`

**Method** : `POST`

**Data example** All fields must be sent.

```json
{
    "username": "YOUR_GIVEN_USERNAME",
    "password": "******",
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `20O OK

**Content example**

```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlIiwiZXhwIjoxNTg4NTkxOTA2LCJpYXQiOjE1ODg1NzM5MDZ9.4_fRf7tDGj1zjqXqCxZz2Q1_UaS6b_aOSA5RyhaOX-l-cukIDvSJLOR81B6Vj3ASvnMbo2r-uZ7ptlrnqroyKg"
}
```

## Error Responses

**Condition** : If fields are missed.

**Code** : `401 UNAUTHORIZED`

**Content example**

```json
{
    "timestamp": "2020-05-04T06:43:11.906+0000",
    "status": 401,
    "error": "Unauthorized",
    "message": "Unauthorized",
    "path": "/api/login"
}
```
## Using the Token

In authentication, when the user successfully logs in using their credentials, a JSON Web Token will be returned and must be saved locally (typically in local or session storage, but cookies can also be used).

Whenever the user wants to access a protected route or resource, the user agent should send the JWT, typically in the Authorization header using the Bearer schema. The content of the header might look like the following:

```
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwZXBlIiwiZXhwIjoxNTg4NTkxOTA2LCJpYXQiOjE1ODg1NzM5MDZ9.4_fRf7tDGj1zjqXqCxZz2Q1_UaS6b_aOSA5RyhaOX-l-cukIDvSJLOR81B6Vj3ASvnMbo2r-uZ7ptlrnqroyKg
```

This is a stateless authentication mechanism as the user state is never saved in server memory. The server's protected routes will check for a valid JWT in the Authorization header, and if it is present, the user will be allowed to access protected resources. As JWTs are self-contained, all the necessary information is there, reducing the need to query the database multiple times.

**Example using fetch**

```

fetch('https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents', {
      method: 'POST',
      headers: {
        Authorization: `Bearer ${auth.token}`,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        user: 'https://rbmk-ticketguru-backend.herokuapp.com/api/users/3',
      }),
    })
      .then((response) => response.json())
      .then((data) => setSaleEvent(data))
      .catch((err) => console.error(err));
      
```


