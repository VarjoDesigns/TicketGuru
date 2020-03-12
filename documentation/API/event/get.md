# Show All events

Show all Events the active User can access and with what permission level.

**URL** : `/api/events/`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : User can not see any Events or no events found.

**Code** : `404 NOT FOUND`

**Content** : `{[]}`

### OR

**Condition** : User can see one or more Events.

**Code** : `200 OK`

**Content** : In this example, the User can see a list of events, with links to event properties.

```json
{
  "_embedded": {
    "events": [
      {
        "name": "Koodari Kemut 2020",
        "dateTime": "2020-03-01T20:00:00",
        "ticketCapacity": 1500,
        "info": "Mika koodaa ja muut kattelee. Kannattaa tulla kauempaakin",
        "_links": {
          "self": {
            "href": "http://127.0.0.1:8080/api/events/1"
          },
          "eventType": {
            "href": "http://127.0.0.1:8080/api/events/1/eventType"
          },
          "eventOrganizer": {
            "href": "http://127.0.0.1:8080/api/events/1/eventOrganizer"
          },
          "venue": {
            "href": "http://127.0.0.1:8080/api/events/1/venue"
          },
          "ageLimit": {
            "href": "http://127.0.0.1:8080/api/events/1/ageLimit"
          },
          "eventTickets": {
            "href": "http://127.0.0.1:8080/api/events/1/eventTickets"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://127.0.0.1:8080/api/events"
    }
  }
}
```


# Show single Event

Show a singular event the active User can access and with what permission level.

**URL** : `/api/events/{id}`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : Event not found.

**Code** : `404 NOT FOUND`

**Content** :

```json
{
    "timestamp": "2020-03-12T12:07:56.682+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 5",
    "path": "/api/events/5"
}
```

### OR

**Condition** : Event found.

**Code** : `200 OK`

**Content** : In this example, the User can see the returned event with links to event properties.

```json
{
  "name": "Koodari Kemut 2020",
  "dateTime": "2020-03-01T20:00:00",
  "ticketCapacity": 1500,
  "info": "Mika koodaa ja muut kattelee. Kannattaa tulla kauempaakin",
  "_links": {
    "self": {
      "href": "http://127.0.0.1:8080/api/events/1"
    },
    "eventType": {
      "href": "http://127.0.0.1:8080/api/events/1/eventType"
    },
    "eventOrganizer": {
      "href": "http://127.0.0.1:8080/api/events/1/eventOrganizer"
    },
    "venue": {
      "href": "http://127.0.0.1:8080/api/events/1/venue"
    },
    "ageLimit": {
      "href": "http://127.0.0.1:8080/api/events/1/ageLimit"
    },
    "eventTickets": {
      "href": "http://127.0.0.1:8080/api/events/1/eventTickets"
    }
  }
}
```
# Show event type

Show event type for given event id.

**URL** : `/api/events/{id}/eventType`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : Event type not found.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "2020-03-12T12:07:56.682+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 5",
    "path": "/api/events/5/eventType"
}
```

### OR

**Condition** : Event type found.

**Code** : `200 OK`

**Content** : In this example, the User can see the returned event type.

```json
{
  "name": "Teatteri",
  "info": "Silkkaa teatteria",
  "_links": {
    "self": {
      "href": "http://127.0.0.1:8080/api/eventTypes/1"
    },
    "events": {
      "href": "http://127.0.0.1:8080/api/eventTypes/1/events"
    }
  }
}
```


# Show event organizer

Show event organizer for given event id.

**URL** : `/api/events/{id}/eventOrganizer`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : Event organizer not found.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "2020-03-12T12:07:56.682+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 5",
    "path": "/api/events/5/eventOrganizer"
}
```

### OR

**Condition** : Event organizer found.

**Code** : `200 OK`

**Content** : In this example, the User can see the returned event type.

```json
{
  "name": "GREAT EVENTS OY",
  "streetAddress": "Tapahtumakatu 16 a 78",
  "tel": "09 7865566",
  "email": "great@events.fi",
  "www": "www.greatevents.com",
  "contactPerson": "Texas Ted",
  "_links": {
    "self": {
      "href": "http://127.0.0.1:8080/api/eventOrganizers/1"
    },
    "events": {
      "href": "http://127.0.0.1:8080/api/eventOrganizers/1/events"
    }
  }
}
```


# Show venue

Show Venue for given event id.

**URL** : `/api/events/{id}/venue`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : Venue not found.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "2020-03-12T12:07:56.682+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 5",
    "path": "/api/events/5/venue"
}
```

### OR

**Condition** : Venue found.

**Code** : `200 OK`

**Content** : In this example, the User can see the returned venue.

```json
{
  "name": "Helsingin Teatteri",
  "address": "Kekkosenkatu 3",
  "tel": "09 1234566",
  "email": "teatteri@teatteri.fi",
  "www": "www.helsinginteatteri.com",
  "contactPerson": "John Wayne",
  "_links": {
    "self": {
      "href": "http://127.0.0.1:8080/api/venues/1"
    },
    "users": {
      "href": "http://127.0.0.1:8080/api/venues/1/events"
    }
  }
}
```


# Show event age limit

Show Event age limit for given event id.

**URL** : `/api/events/{id}/ageLimit`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : Age limit not found.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "2020-03-12T12:07:56.682+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 5",
    "path": "/api/events/5/ageLimit"
}
```

### OR

**Condition** : Age limit found.

**Code** : `200 OK`

**Content** : In this example, the User can see the returned age limit.

```json
{
  "name": "K13",
  "specifier": "Tapahtuma kielletty alle 13-vuotiailta",
  "_links": {
    "self": {
      "href": "http://127.0.0.1:8080/api/ageLimits/2"
    },
    "users": {
      "href": "http://127.0.0.1:8080/api/ageLimits/1/events"
    }
  }
}
```


# Show event tickets for sale

Show Event tickets for given event id.

**URL** : `/api/events/{id}/eventTickets`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : Event tickets not found.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "2020-03-12T12:07:56.682+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 5",
    "path": "/api/events/5/eventTickets"
}
```

### OR

**Condition** : Event tickets found.

**Code** : `200 OK`

**Content** : In this example, the User can see the returned event ticket.

```json
{
  "_embedded": {
    "eventTickets": [
      {
        "ticketCount": 1000,
        "price": 20,
        "eventTicket_ID": 1,
        "_links": {
          "self": {
            "href": "http://127.0.0.1:8080/api/eventTickets/1"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://127.0.0.1:8080/api/events"
    }
  }
}
```