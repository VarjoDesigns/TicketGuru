# Create an eventTicket

Create an eventTicket.

**URL** : `/api/eventTickets/`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide below required values.

```json
{
	"ticketCount" : "Long (optional)",
	"price" : "double (optional)",
	"created" : "[ISO 8601] (optional)",
	"event" : "event_id",
	"ticketType" : "ticketType_id"
}
```

**Data example**

```json
{
	"ticketCount" : "400",
	"price" : "25.7",
	"event" : "/api/events/3",
	"ticketType" : "api/ticketTypes/2"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `201 Created`

**Content example**

```json
{
  "ticketCount": 400,
  "price": 25.7,
  "created": "2020-05-06T21:19:03.763",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/7"
    },
    "event": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/7/event"
    },
    "ticketType": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/7/ticketType"
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
    "timestamp": "2020-05-06T21:19:23.151+0000",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "codes": [
                "NotNull.eventTicket.ticketType",
                "NotNull.ticketType",
                "NotNull.fi.rbmk.ticketguru.ticketType.TicketType",
                "NotNull"
            ],
            "arguments": [
                {
                    "codes": [
                        "eventTicket.ticketType",
                        "ticketType"
                    ],
                    "arguments": null,
                    "defaultMessage": "ticketType",
                    "code": "ticketType"
                }
            ],
            "defaultMessage": "Ticket type must be set",
            "objectName": "eventTicket",
            "field": "ticketType",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotNull"
        }
    ],
    "message": "Validation failed for object='eventTicket'. Error count: 1",
    "path": "/api/eventTickets/"
}
```
