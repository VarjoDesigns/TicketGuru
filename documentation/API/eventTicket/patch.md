# Edit an eventTicket

Edit an eventTicket.

**URL** : `/api/eventTickets/{id}`

**Method** : `PATCH`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide ID and values to modify.

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
	"ticketCount" : "450",
	"price" : "25.7",
	"event" : "/api/events/3",
	"ticketType" : "api/ticketTypes/2"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "ticketCount": 450,
  "price": 25.7,
  "created": "2020-05-06T21:21:50.929",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/8"
    },
    "event": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/8/event"
    },
    "ticketType": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/8/ticketType"
    }
  }
}
```

## Error Responses

**Condition** : ID is missing.

**Code** : `405 Method not allowed`

**Content example**

```json
{
    "timestamp": "2020-05-06T21:22:47.171+0000",
    "status": 405,
    "error": "Method Not Allowed",
    "message": "Request method 'PATCH' not supported",
    "path": "/api/eventTickets/"
}
```
</br>

**Condition** : Invalid ID.

**Code** : `404 Not Found`

**Content example**

```json
{
    "timestamp": "2020-05-06T21:23:08.807+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 87",
    "path": "/api/eventTickets/87"
}
```
</br>

**Condition** : eventTicket is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "2020-05-06T21:24:39.241+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify EventTicket that is marked as deleted",
    "path": "/api/eventTickets/8"
}
```
