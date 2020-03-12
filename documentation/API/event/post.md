# Edit an Event

Edit an Event.

**URL** : `/api/events`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : NO

**Permissions required** : None

**Data constraints**

```json
{
    "name": "[VARCHAR 250 chars max]"
    "eventType": "[eventType_ID]"
    "dateTime": "[ISO 8601]"
    "eventOrganizer": "[eventOrganizer_ID]"
    "venue": "[venue_ID]"
    "ticketCapacity": "[INT]"
    "ageLimit": "[ageLimit_ID]"
    "info": "[VARCHAR 500 CHARS MAX]"
}
```

**Data example** All fields must be sent.

```json
{
    "name": "Java on hirveä kieli ja tänään sitä koodataan",
    "eventType": "http://127.0.0.1:8080/api/eventTypes/1",
    "dateTime": "2020-03-01T20:00:00",
    "eventOrganizer": "http://127.0.0.1:8080/api/eventOrganizers/1",
    "venue": "http://127.0.0.1:8080/api/venues/1",
    "ticketCapacity": 1000,
    "ageLimit": "http://127.0.0.1:8080/api/ageLimits/1",
    "info": "Ei kannata tulla"
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
  "info": "Ei kannata tulla",
  "_links": {
    "self": {
      "href": "http://127.0.0.1:8080/api/events/2"
    },
    "eventType": {
      "href": "http://127.0.0.1:8080/api/events/2/eventType"
    },
    "eventOrganizer": {
      "href": "http://127.0.0.1:8080/api/events/2/eventOrganizer"
    },
    "venue": {
      "href": "http://127.0.0.1:8080/api/events/2/venue"
    },
    "ageLimit": {
      "href": "http://127.0.0.1:8080/api/events/2/ageLimit"
    },
    "eventTickets": {
      "href": "http://127.0.0.1:8080/api/events/2/eventTickets"
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
    "timestamp": "2020-03-12T11:32:57.233+0000",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "codes": [
                "NotNull.event.dateTime",
                "NotNull.dateTime",
                "NotNull.java.time.LocalDateTime",
                "NotNull"
            ],
            "arguments": [
                {
                    "codes": [
                        "event.dateTime",
                        "dateTime"
                    ],
                    "arguments": null,
                    "defaultMessage": "dateTime",
                    "code": "dateTime"
                }
            ],
            "defaultMessage": "Event datetime is required",
            "objectName": "event",
            "field": "dateTime",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotNull"
        }
    ],
    "message": "Validation failed for object='event'. Error count: 1",
    "path": "/api/events"
}
```
