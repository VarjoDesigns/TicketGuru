# Edit an Event

Edit an Event.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}`

**Method** : `PATCH`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
    "name": "String 250 chars max",
    "eventType": "event_type_id",
    "dateTime": "[ISO 8601]",
    "eventOrganizer": "event_organizer_id",
    "venue": "venue_id",
    "ticketCapacity": "Long",
    "ageLimit": "age_limit_id",
    "info": "String 500 chars max"
}
```

**Data example**

```json
{
    "name": "Java on hirveä kieli ja tänään sitä koodataan",
    "eventType": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTypes/1",
    "dateTime": "2020-03-01T20:00:00",
    "eventOrganizer": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventOrganizers/1",
    "venue": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/1",
    "ticketCapacity": 1000,
    "ageLimit": "https://rbmk-ticketguru-backend.herokuapp.com/api/ageLimits/1",
    "info": "Muutos"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "name": "Java on hirveä kieli ja tänään sitä koodataan",
  "dateTime": "2020-03-01T20:00:00",
  "ticketCapacity": 1000,
  "created": "2020-03-01T20:00:00",
  "invalid": null,
  "info": "Muutettu",
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/2"
    },
    "eventType": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/2/eventType"
    },
    "eventOrganizer": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/2/eventOrganizer"
    },
    "venue": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/2/venue"
    },
    "ageLimit": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/2/ageLimit"
    },
    "eventTickets": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/2/eventTickets"
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
    "path": "/api/events/{id}"
}
```
</br>

**Condition** : Event is marked as deleted.

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
</br>

**Condition** : Event type is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot link EventType that is marked as deleted",
    "path": "/api/events/{id}"
}
```
</br>

**Condition** : Event organizer is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot link EventOrganizer that is marked as deleted",
    "path": "/api/events/{id}"
}
```
</br>

**Condition** : Venue is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot link Venue that is marked as deleted",
    "path": "/api/events/{id}"
}
```
</br>

**Condition** : Age limit is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot link AgeLimit that is marked as deleted",
    "path": "/api/events/{id}"
}
```