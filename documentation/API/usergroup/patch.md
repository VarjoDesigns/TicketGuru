# Edit a Usergroup

Edit a Usergroup.

**URL** : `/api/userGroups/{id}`

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

**Data example**.

```json
{
   "name": "administrator"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "name": "administrator",
  "created": "2020-04-11T00:49:01.27566",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/userGroups/11"
    },
    "users": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/userGroups/11/users"
    }
  }
}


## Error Responses

**Condition** : If fields are missed.

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2020-05-04T07:42:50.717",
    "status": 400,
    "error": "Bad Request",
    "errors": []
}
```
