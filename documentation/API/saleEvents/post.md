# Create an saleEvent

Create an saleEvent.

**URL** : `/api/saleEvents/`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide below required values.

```json
{
	"created" : "[ISO 8601] (optional)",
	"user" : "user_id"
}
```

**Data example**

```json
{
	"created" : "2020-05-06T22:44:00",
	"user" : "https://rbmk-ticketguru-backend.herokuapp.com/api/users/3"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `201 Created`

**Content example**

```json
{
  "created": "2020-05-06T19:45:23.521",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/52"
    },
    "user": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/52/user"
    },
    "saleRows": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/52/saleRows"
    }
  }
}
```

## Error Responses


**Condition** : Required field is missing.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "2020-05-06T19:47:19.218+0000",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "codes": [
                "NotNull.saleEvent.user",
                "NotNull.user",
                "NotNull.fi.rbmk.ticketguru.user.User",
                "NotNull"
            ],
            "arguments": [
                {
                    "codes": [
                        "saleEvent.user",
                        "user"
                    ],
                    "arguments": null,
                    "defaultMessage": "user",
                    "code": "user"
                }
            ],
            "defaultMessage": "User is required",
            "objectName": "saleEvent",
            "field": "user",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotNull"
        }
    ],
    "message": "Validation failed for object='saleEvent'. Error count: 1",
    "path": "/api/saleEvents/"
}
```
