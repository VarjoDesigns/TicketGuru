# Create an AgeLimit

Create an AgeLimit.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/ageLimits/`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide below required values.

```json
{
	"name" : "String 50 chars max",
	"info" : "String 500 chars max (optional)"
}
```

**Data example**

```json
{
	"name" : "K100",
	"info" : "Kielletty alle 100-vuotiailta!"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `201 Created`

**Content example**

```json
{
  "name": "K100",
  "info": "Kielletty alle 100-vuotiailta!",
  "created": "2020-05-05T18:15:59.581",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ageLimits/6"
    },
    "events": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ageLimits/6/events"
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
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "codes": [
                "NotEmpty.ageLimit.name",
                "NotEmpty.name",
                "NotEmpty.java.lang.String",
                "NotEmpty"
            ],
            "arguments": [
                {
                    "codes": [
                        "ageLimit.name",
                        "name"
                    ],
                    "arguments": null,
                    "defaultMessage": "name",
                    "code": "name"
                }
            ],
            "defaultMessage": "Age limit name must be set",
            "objectName": "ageLimit",
            "field": "name",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotEmpty"
        }
    ],
    "message": "Validation failed for object='ageLimit'. Error count: 1",
    "path": "/api/ageLimits/"
}
```