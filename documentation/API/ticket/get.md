# Show All event tickets

Show all Event tickes the active User can access and with what permission level.

**URL** : `/api/eventTickets/`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : User can not see any Event tickets or no event tickets found.

**Code** : `404 NOT FOUND`

**Content** : 

### OR

**Condition** : User can see one or more Event tickets.

**Code** : `200 OK`

**Content** : In this example, the User can see a list of event tickets, with links to event properties.

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


# Show single Event

Show a singular event ticket the active User can access and with what permission level.

**URL** : `/api/eventTickets/{id}`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : Event ticket not found.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "2020-03-12T12:07:56.682+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 5",
    "path": "/api/eventTickets/5"
}
```

### OR

**Condition** : Event ticket found.

**Code** : `200 OK`

**Content** : In this example, the User can see the returned event ticket.

```json
{
  "ticketCount": 1000,
  "price": 20,
  "_links": {
    "self": {
      "href": "http://127.0.0.1:8080/api/eventTickets/1"
    },
    "event": {
      "href": "http://127.0.0.1:8080/api/eventTickets/1/event"
    },
    "ticketType": {
      "href": "http://127.0.0.1:8080/api/eventTickets/1/ticketType"
    }
  }
}
```
# Show event

Show event for given event ticket id.

**URL** : `/api/eventTickets/{id}/event`

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
    "path": "/api/eventTickets/5/event"
}
```

### OR

**Condition** : Event found.

**Code** : `200 OK`

**Content** : In this example, the User can see the returned event.

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


# Show ticket type

Show ticket type for given event ticket id.

**URL** : `/api/events/{id}/ticketType`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

**Data constraints** : `{}`

## Success Responses

**Condition** : Ticket type not found.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "2020-03-12T12:07:56.682+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 5",
    "path": "/api/events/5/ticketType"
}
```

### OR

**Condition** : Ticket type found.

**Code** : `200 OK`

**Content** : In this example, the User can see the returned ticket type.

```json
{
  "name": "Lasten lippu",
  "_links": {
    "self": {
      "href": "http://127.0.0.1:8080/api/ticketTypes/2"
    },
    "eventtickets": {
      "href": "http://127.0.0.1:8080/api/ticketTypes/1/eventtickets"
    }
  }
}
```