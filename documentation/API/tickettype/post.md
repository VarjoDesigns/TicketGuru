# Create a TicketType

Create a TicketType.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/ticketTypes/`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide below required values.

```json
{
    "name": "String 100 chars max",
}
```

**Data example**

```json
{
	"name" : "Eläkeläislippu"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `201 Created`

**Content example**

```json
{
  "name": "Eläkeläislippu",
  "created": "2020-05-06T15:31:50.523",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ticketTypes/6"
    },
    "eventTickets": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ticketTypes/6/eventtickets"
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
                "NotEmpty.ticketType.name",
                "NotEmpty.name",
                "NotEmpty.java.lang.String",
                "NotEmpty"
            ],
            "arguments": [
                {
                    "codes": [
                        "ticketType.name",
                        "name"
                    ],
                    "arguments": null,
                    "defaultMessage": "name",
                    "code": "name"
                }
            ],
            "defaultMessage": "Ticket type name is required",
            "objectName": "ticketType",
            "field": "name",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotEmpty"
        }
    ],
    "message": "Validation failed for object='ticketType'. Error count: 1",
    "path": "/api/ticketTypes/"
}
```