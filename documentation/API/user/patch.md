# Edit a User

Edit a User.

**URL** : `/api/users/{id}`

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

**Data example**

```json
{
	"name": "Spede",
    "password": "oujee",
    "userGroup" : "https://rbmk-ticketguru-backend.herokuapp.com/api/userGroups/10"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "username": "Igor",
  "name": "Spede",
  "created": "2020-05-04T07:58:12.202",
  "invalid": null,
  "active": false,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/users/6"
    },
    "userGroup": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/users/6/userGroup"
    },
    "saleEvent": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/users/6/saleEvents"
    }
  }
}
```
