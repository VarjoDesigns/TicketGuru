# Get all events

Get all Events the active User can access with current permission level.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/events`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{[]}`

## Success Response

**Condition** : Events found.

**Code** : `200 OK`

**Content** :

```json
{
  "_embedded": {
    "events": [
      {
        "name": "String",
        "dateTime": "LocalDateTime",
        "ticketCapacity": "Long",
        "info": "String",
        "created": "LocalDateTime",
        "invalid": "LocalDateTime",
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}"
          },
          "eventType": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/eventType"
          },
          "eventOrganizer": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/eventOrganizer"
          },
          "venue": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/venue"
          },
          "ageLimit": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/ageLimit"
          },
          "eventTickets": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/eventTickets"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events"
    }
  }
}
```
</br>

**Condition** : No events.

**Code** : `404 Not Found`

**Content** :

```json
{ }
```
</br>

# Get a Event

Get a single Event the active User can access with current permission level.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : Event found.

**Code** : `200 OK`

**Content** :

```json
{
  "name": "String",
  "dateTime": "LocalDateTime",
  "ticketCapacity": "Long",
  "info": "String",
  "created": "LocalDateTime",
  "invalid": "LocalDateTime",
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}"
    },
    "eventType": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/eventType"
    },
    "eventOrganizer": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/eventOrganizer"
    },
    "venue": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/venue"
    },
    "ageLimit": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/ageLimit"
    },
    "eventTickets": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/eventTickets"
    }
  }
}
```
</br>

## Failure Response

**Condition** : Event not found or invalid ID.

**Code** : `404 NOT FOUND`

**Content** :

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

# Get event type

Get event type for given event id.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/eventType`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : Event type found.

**Code** : `200 OK`

**Content** : 

```json
{
  "name": "String",
  "info": "String",
  "created": "LocalDateTime",
  "invalid": "LocalDateTime",
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTypes/{id}"
    },
    "events": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTypes/{id}/events"
    }
  }
}
```
</br>

## Failure Response

**Condition** : Invalid event ID.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "LocalDateTime",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: {id}",
    "path": "/api/events/{id}/eventType"
}
```
</br>

# Get event organizer

Get event organizer for given event id.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/eventOrganizer`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : Event organizer found.

**Code** : `200 OK`

**Content** : 

```json
{
  "name": "String",
  "streetAddress": "String",
  "tel": "String",
  "email": "String",
  "www": "String",
  "contactPerson": "String",
  "created": "LocalDateTime",
  "invalid": "LocalDateTime",
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventOrganizers/{id}"
    },
    "postcode": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventOrganizers/{id}/postcode"
    },
    "events": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventOrganizers/{id}/events"
    }
  }
}
```
</br>

## Failure Response

**Condition** : Invalid event ID.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "LocalDateTime",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: {id}",
    "path": "/api/events/{id}/eventOrganizer"
}
```
</br>

# Get event venue

Get event organizer for given event id.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/venue`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : Event venue found.

**Code** : `200 OK`

**Content** : 

```json
{
  "name": "String",
  "address": "String",
  "tel": "String",
  "email": "String",
  "www": "String",
  "contactPerson": "String",
  "created": "LocalDateTime",
  "invalid": "LocalDateTime",
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/{id}"
    },
    "postcode": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/{id}/postcode"
    },
    "events": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/{id}/events"
    }
  }
}
```
</br>

## Failure Response

**Condition** : Invalid event ID.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "LocalDateTime",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: {id}",
    "path": "/api/events/{id}/venue"
}
```
</br>

# Get event age limit

Get event age limit for given event id.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/ageLimit`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : Event age limit found.

**Code** : `200 OK`

**Content** : 

```json
{
  "name": "String",
  "info": "String",
  "created": "LocalDateTime",
  "invalid": "LocalDateTime",
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ageLimits/{id}"
    },
    "events": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ageLimits/{id}/events"
    }
  }
}
```
</br>

## Failure Response

**Condition** : Invalid event ID.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "LocalDateTime",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: {id}",
    "path": "/api/events/{id}/ageLimit"
}
```
</br>

# Get event tickets for sale

Get tickets for sale given event id.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/events/{id}/eventTickets`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : Event tickets found.

**Code** : `200 OK`

**Content** : 

```json
{
  "_embedded": {
    "eventTickets": [
      {
        "ticketCount": "Long",
        "price": "Double",
        "created": "LocalDateTime",
        "invalid": "LocalDateTime",
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/{id}"
          },
          "event": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/{id}/event"
          },
          "ticketType": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/{id}/ticketType"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/events"
    }
  }
}
```
</br>

## Failure Responses

**Condition** : Invalid event ID.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "LocalDateTime",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: {id}",
    "path": "/api/events/{id}/eventTickets"
}
```
</br>

**Condition** : Event has no tickets.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{}
```
</br>