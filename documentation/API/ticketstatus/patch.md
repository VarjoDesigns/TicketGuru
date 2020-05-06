# Edit a TicketStatus

Edit a TicketStatus.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/ticketStatuses/{id}`

**Method** : `PATCH`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
    "name": "String 50 chars max"
}
```

**Data example**

```json
{
	"name" : "Virheellinen lippu"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "name": "Virheellinen lippu",
  "created": "2020-04-11T00:55:20.885919",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ticketStatuses/6"
    },
    "ticketStatus": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ticketStatuses/6"
    },
    "tickets": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ticketStatuses/6/tickets"
    }
  }
}
```

## Error Responses

**Condition** : ID is missing.

**Code** : `404 Not Found`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 404,
    "error": "Not Found",
    "message": "No message available",
    "path": "/api/ticketStatuses/"
}
```
</br>

**Condition** : Invalid ID.

**Code** : `404 Not Found`

**Content example**

```json
{
{}
}
```
</br>

**Condition** : TicketStatus is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify Event that is marked as deleted",
    "path": "/api/events/{id}"
}
```