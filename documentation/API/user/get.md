# Show Accessible Users

Show all Users the active User can access and with what permission level.

**URL** : `/api/users/`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : User can not see any Users.

**Code** : `200 OK`

**Content** : `{[]}`

### OR

**Condition** : User can see one or more Users.

**Code** : `200 OK`

**Content** : In this example, the User can see a list of users, with a link to the Users Usergroup and sale events.

```json
[
     "_embedded": {
   "users": [
      {
        "name": "pepe",
        "active": true,
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/users/1"
          },
          "userGroup": {
            "href": "http://localhost:8080/api/users/1/userGroup"
          },
          "saleEvent": {
            "href": "http://localhost:8080/api/users/1/saleEvents"
          }
        }
      },
      {
        "name": "john",
        "active": true,
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/users/2"
          },
          "userGroup": {
            "href": "http://localhost:8080/api/users/2/userGroup"
          },
          "saleEvent": {
            "href": "http://localhost:8080/api/users/2/saleEvents"
          }
        }
      },
      {
        "name": "kuningas",
        "active": true,
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/users/3"
          },
          "userGroup": {
            "href": "http://localhost:8080/api/users/3/userGroup"
          },
          "saleEvent": {
            "href": "http://localhost:8080/api/users/3/saleEvents"
          }
        }
      }
]
```
