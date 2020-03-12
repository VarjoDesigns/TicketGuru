# Edit a User

Edit a User.

**URL** : `/api/users/{id}`

**Method** : `PATCH`

**Auth required** : NO

**Permissions required** : None

**Data constraints**

**Data example** All fields must be sent.

```json
{
    "name": "Johnny",
    "password": "UliUli",
    "active": "false",
    "userGroup" : "http://localhost:8080/api/userGroups/2"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `201 Created`

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
   "timestamp": "2020-03-12T11:42:45.921+0000",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "codes": [
                "NotEmpty.user.password",
                "NotEmpty.password",
                "NotEmpty.java.lang.String",
                "NotEmpty"
            ],
            "arguments": [
                {
                    "codes": [
                        "user.password",
                        "password"
                    ],
                    "arguments": null,
                    "defaultMessage": "password",
                    "code": "password"
                }
            ],
            "defaultMessage": "Password is required",
            "objectName": "user",
            "field": "password",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotEmpty"
        }
    ],
    "path": "/api/users/1"
}
```
