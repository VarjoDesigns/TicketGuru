# Create a User

Create a User.

**URL** : `/api/users/`

**Method** : `POST`

**Auth required** : NO

**Permissions required** : None

**Data constraints**

Provide name of User to be created.

```json
{
    "name": "[VARCHAR 50 chars max]"
    "password": "[VARCHAR 100 chars max]"
    "active": "[Boolean]"
    "userGroup": "[userGoup href]"
}
```

**Data example** All fields must be sent.

```json
{
    "name": "Johnny",
    "password": "UliUli",
    "active": "true",
    "userGroup" : "http://localhost:8080/api/userGroups/2"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
 {
  "name": "Johnny",
  "active": true,
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/users/5"
    },
    "userGroup": {
      "href": "http://localhost:8080/api/users/5/userGroup"
    },
    "saleEvent": {
      "href": "http://localhost:8080/api/users/5/saleEvents"
    }
  }
}
```

## Error Responses

**Condition** : 

**Code** : 

**Headers** : 

**Content** : 

### Or

**Condition** : If fields are missed.

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2020-03-12T11:51:35.015+0000",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "codes": [
                "NotNull.user.userGroup",
                "NotNull.userGroup",
                "NotNull.fi.rbmk.ticketguru.userGroup.UserGroup",
                "NotNull"
            ],
            "arguments": [
                {
                    "codes": [
                        "user.userGroup",
                        "userGroup"
                    ],
                    "arguments": null,
                    "defaultMessage": "userGroup",
                    "code": "userGroup"
                }
            ],
            "defaultMessage": "User Group is required",
            "objectName": "user",
            "field": "userGroup",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotNull"
        }
    ],
    "path": "/api/users"
```

