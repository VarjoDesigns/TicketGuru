# Edit a TicketType

Edit an Event.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/ticketTypes/{id}`

**Method** : `PATCH`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
    "name": "String 100 chars max"
}
```

**Data example**

```json
{
    "name": "Varusmies / Siviilipalvelusmies"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "name": "Varusmies / Siviilipalvelusmies",
  "created": "2020-04-11T00:55:14.961737",
  "invalid": null
}
```

## Error Responses

**Condition** : ID is missing.

**Code** : `405 Method not allowed`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 405,
    "error": "Method Not Allowed",
    "message": "Request method 'PATCH' not supported",
    "path": "/api/events/"
}
```
</br>

**Condition** : Invalid ID.

**Code** : `404 Not Found`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: {id}",
    "path": "/api/ticketTypes/{id}"
}
```
</br>

**Condition** : TicketType is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify TicketType that is marked as deleted",
    "path": "/api/ticketTypes/{id}"
}
```