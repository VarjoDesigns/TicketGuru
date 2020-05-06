# Create an Event

Create an Event.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/events/`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide below required values.

```json
{
    "name": "String 250 chars max",
    "eventType": "event_type_id",
    "dateTime": "[ISO 8601]",
    "eventOrganizer": "event_organizer_id",
    "venue": "venue_id",
    "ticketCapacity": "Long",
    "ageLimit": "age_limit_id",
    "info": "String 500 chars max (optional)"
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

**Code** : `201 Created`

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
                "NotNull.event.ageLimit",
                "NotNull.ageLimit",
                "NotNull.fi.rbmk.ticketguru.ageLimit.AgeLimit",
                "NotNull"
            ],
            "arguments": [
                {
                    "codes": [
                        "event.ageLimit",
                        "ageLimit"
                    ],
                    "arguments": null,
                    "defaultMessage": "ageLimit",
                    "code": "ageLimit"
                }
            ],
            "defaultMessage": "Age limit must be set",
            "objectName": "event",
            "field": "ageLimit",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotNull"
        }
    ],
    "message": "Validation failed for object='event'. Error count: 1",
    "path": "/api/events"
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