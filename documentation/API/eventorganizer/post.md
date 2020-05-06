# Create an EventOrganizer

Create an EventOrganizer.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/eventOrganizers/`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide below required values.

```json
{
    "name": "String 100 chars max",
    "streetAddress": "String 150 chars max",
    "tel": "String 25 chars max",
    "email": "String 150 chars max",
    "www": "String 250 chars max",
    "contactPerson": "String 150 chars max",
    "postcode": "postcode_id"
}
```

**Data example**

```json
{
	"name": "Wayward Wizards Oy",
	"streetAddress": "Tapahtumakatu 1 A 2",
	"tel": "09 7654321",
	"email": "wizards@wwoy.fi",
	"www": "www.waywardwizards.com",
	"contactPerson": "Fafar the Magnificant",
	"postcode": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00100"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `201 Created`

**Content example**

```json
{
  "name": "Wayward Wizards Oy",
  "streetAddress": "Tapahtumakatu 1 A 2",
  "tel": "09 7654321",
  "email": "wizards@wwoy.fi",
  "www": "www.waywardwizards.com",
  "contactPerson": "Fafar the Magnificant",
  "created": "2020-05-06T16:20:35.365",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventOrganizers/3"
    },
    "postcode": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventOrganizers/3/postcode"
    },
    "events": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventOrganizers/3/events"
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
                "NotEmpty.eventOrganizer.name",
                "NotEmpty.name",
                "NotEmpty.java.lang.String",
                "NotEmpty"
            ],
            "arguments": [
                {
                    "codes": [
                        "eventOrganizer.name",
                        "name"
                    ],
                    "arguments": null,
                    "defaultMessage": "name",
                    "code": "name"
                }
            ],
            "defaultMessage": "Event organizer name is required",
            "objectName": "eventOrganizer",
            "field": "name",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotEmpty"
        }
    ],
    "message": "Validation failed for object='eventOrganizer'. Error count: 1",
    "path": "/api/eventOrganizers/"
}
```