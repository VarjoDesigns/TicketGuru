# Edit an Event

Edit an Event.

**URL** : `/api/eventTickets/{id}`

**Method** : `PATCH`

**Content-Type** : `application/json`

**Auth required** : NO

**Permissions required** : None

**Data constraints**

Provide id of Event to be modified.

```json
{
  "ticketCount": "[LONG]",
  "price": "[DOUBLE]",
  "event": "[event_ID]",
  "ticketType": "[ticketType_ID]",
}
```

**Data example** All required fields must be sent.

```json
{
  "ticketCount": "500",
  "price": 15.00,
  "event": "http://127.0.0.1:8080/api/events/1",
  "ticketType": "http://127.0.0.1:8080/ticketTypes/2"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "ticketCount": 500,
  "price": 15.00,
  "eventTicket_ID": 2,
  "_links": {
    "self": {
      "href": "http://127.0.0.1:8080/api/eventTickets/2"
    },
    "event": {
      "href": "http://127.0.0.1:8080/api/eventTickets/2/event"
    },
    "ticketType": {
      "href": "http://127.0.0.1:8080/api/eventTickets/2/ticketType"
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
    "timestamp": "2020-03-12T12:04:41.640+0000",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "codes": [
                "NotNull.eventTicket.event",
                "NotNull.event",
                "NotNull.fi.rbmk.ticketguru.event.Event",
                "NotNull"
            ],
            "arguments": [
                {
                    "codes": [
                        "eventTicket.event",
                        "event"
                    ],
                    "arguments": null,
                    "defaultMessage": "event",
                    "code": "event"
                }
            ],
            "defaultMessage": "Event must be set",
            "objectName": "eventTicket",
            "field": "event",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotNull"
        }
    ],
    "message": "Validation failed for object='eventTicket'. Error count: 1",
    "path": "/api/eventTickets"
}
```
