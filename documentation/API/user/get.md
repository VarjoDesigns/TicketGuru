# Show Accessible Users

Show all Users the active User can access and with what permission level.

**URL** : `/api/users/`

**Method** : `GET`

**Auth required** : YES

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

  {
  "_embedded": {
    "users": [
      {
        "username": "pepe",
        "name": "pepe",
        "created": "2020-04-11T00:49:37.503516",
        "invalid": null,
        "active": true,
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/users/3"
          },
          "userGroup": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/users/3/userGroup"
          },
          "saleEvent": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/users/3/saleEvents"
          }
        }
      },
      {
        "username": "john",
        "name": "john",
        "created": "2020-04-11T00:49:37.503516",
        "invalid": null,
        "active": true,
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/users/4"
          },
          "userGroup": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/users/4/userGroup"
          },
          "saleEvent": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/users/4/saleEvents"
          }
        }
      },
      {
        "username": "kuningas",
        "name": "kuningas",
        "created": "2020-04-11T00:49:37.503516",
        "invalid": null,
        "active": true,
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/users/5"
          },
          "userGroup": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/users/5/userGroup"
          },
          "saleEvent": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/users/5/saleEvents"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/users"
    }
  }
}

```
