# Delete a User

Delete a User

**URL** : `/api/users/{id}`

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Provide the URL of User to be deleted e.g. https://rbmk-ticketguru-backend.herokuapp.com/api/users/4


## Success Response

**Condition** : If everything is OK.

**Code** : `204 No Content`

**Content example**

```json
 {}
```

## Error Responses

**Condition** : If {id} is wrong.

**Code** : `404 Not Found`

**Content example**

```json
{
     "timestamp": "2020-03-12T10:57:14.902+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 4",
    "path": "/api/users/4"
}
```
**Condition** : If {id} is missing.

**Code** : `405 Method Not Allowed`

**Content example**

```json
{
    "timestamp": "2020-05-04T07:34:28.116+0000",
    "status": 405,
    "error": "Method Not Allowed",
    "message": "Request method 'DELETE' not supported",
    "path": "/api/users"
}
```
