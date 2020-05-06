# Show Accessible Usergroups

Show all Users the active User can access and with what permission level.

**URL** : `/api/userGroups/`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : User can not see any Usergroups.

**Code** : `200 OK`

**Content** : `{[]}`

### OR

**Condition** : User can see one or more Usergroups.

**Code** : `200 OK`

**Content** : In this example, the User can see a list of usergroups, with a self link and users in the usergroup.

```json

 {
  "_embedded": {
    "userGroups": [
      {
        "name": "user",
        "created": "2020-04-11T00:49:01.27566",
        "invalid": null,
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/userGroups/10"
          },
          "users": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/userGroups/10/users"
          }
        }
      },
      {
        "name": "admin",
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
      },
      {
        "name": "boss",
        "created": "2020-04-11T00:49:01.27566",
        "invalid": null,
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/userGroups/12"
          },
          "users": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/userGroups/12/users"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/userGroups"
    }
  }
}

```

