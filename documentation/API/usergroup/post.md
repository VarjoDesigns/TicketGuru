# Create a User

Create a User.

**URL** : `/api/userGroups/`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Provide name of UserGroup to be created.

```json
{
	 "name": "[VARCHAR 50 chars max]"
}
```

**Data example** All fields must be sent.

```json
{
	 "name": "SuperUser"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "name": "SuperUser",
  "created": "2020-05-04T08:14:03.072",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/userGroups/13"
    },
    "users": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/userGroups/13/users"
    }
  }
}
```

## Error Responses

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

