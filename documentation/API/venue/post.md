# Create an Venue

Create an Venue.

**URL** : `/api/venues/`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide below required values.

```json
{
  "name" : "String 100 chars max",
  "address" : "String 150 chars max",
  "postcode" : "postcode_id",
  "tel" : "String 25 chars max",
  "email" : "String 150 chars max",
  "www" : "String 250 chars max (optional)",
  "contactPerson" : "String 150 chars max",
  "created" : "[ISO 8601] (optional)"
}
```

**Data example**

```json
{
  "name" : "Pepen Pubi",
  "address" : "Pepenmutka 16",
  "postcode" : "/api/postcodes/00100",
  "tel" : "0506362424",
  "email" : "pepe@pepenpubi.fi",
  "www" : "www.pepenpubikuppila.fi",
  "contactPerson" : "Pepe Pepe"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `201 Created`

**Content example**

```json
{
  "name": "Pepen Pubi",
  "address": "Pepenmutka 16",
  "tel": "0506362424",
  "email": "pepe@pepenpubi.fi",
  "www": "www.pepenpubikuppila.fi",
  "contactPerson": "Pepe Pepe",
  "created": "2020-05-06T20:39:57.592",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/3"
    },
    "postcode": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/3/postcode"
    },
    "events": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/3/events"
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
    "timestamp": "2020-05-06T20:42:25.812+0000",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "codes": [
                "NotEmpty.venue.name",
                "NotEmpty.name",
                "NotEmpty.java.lang.String",
                "NotEmpty"
            ],
            "arguments": [
                {
                    "codes": [
                        "venue.name",
                        "name"
                    ],
                    "arguments": null,
                    "defaultMessage": "name",
                    "code": "name"
                }
            ],
            "defaultMessage": "Venue name is required",
            "objectName": "venue",
            "field": "name",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotEmpty"
        }
    ],
    "message": "Validation failed for object='venue'. Error count: 1",
    "path": "/api/venues/"
}
```
