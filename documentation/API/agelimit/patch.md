# Edit an AgeLimit

Edit an AgeLimit.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/ageLimits/{id}`

**Method** : `PATCH`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
    "name": "String 250 chars max",
    "info": "String 500 chars max"
}
```

**Data example**

```json
{
    "name": "K30",
    "info": "Kielletty alle 30-vuotiailta!"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "name": "K30",
  "info": "Kielletty alle 30-vuotiailta!",
  "created": "2020-04-11T00:52:20.565417",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ageLimits/3"
    },
    "events": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ageLimits/3/events"
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
    "timestamp": "LocalDateTime",
    "status": 405,
    "error": "Method Not Allowed",
    "message": "Request method 'PATCH' not supported",
    "path": "/api/ageLimits/"
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
    "path": "/api/ageLimits/{id}"
}
```
</br>

**Condition** : AgeLimit is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify AgeLimit that is marked as deleted",
    "path": "/api/ageLimits/{id}"
}
```