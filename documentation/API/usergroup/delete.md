# Delete a Usergroup

Delete a User

**URL** : `/api/userGroups/{id}`

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Provide the URL of User to be deleted e.g. https://rbmk-ticketguru-backend.herokuapp.com/api/userGroup/12


## Success Response

**Condition** : If everything is OK.

**Code** : `204 No Content`

**Content example**

```json
 {}
```

## Error Responses

**Condition** : If {id} is missing.

**Code** : `405 Method Not Allowed`

**Content example**

```json
{
    "timestamp": "2020-05-04T07:30:39.036+0000",
    "status": 405,
    "error": "Method Not Allowed",
    "message": "Request method 'DELETE' not supported",
    "path": "/api/userGroups/"
}
```

**Condition** : If {id} is wrong.

**Code** : `404 Not Found`

**Content example**

```json
{
    "timestamp": "2020-05-04T07:32:05.468+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 1",
    "path": "/api/userGroups/1"
}
```
