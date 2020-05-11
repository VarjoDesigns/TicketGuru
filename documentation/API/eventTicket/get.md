# Get all eventTickets

Get all eventTickets the active User can access with current permission level.

**URL** : `/api/eventTickets`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{[]}`

## Success Response

**Condition** : eventTickets found.

**Code** : `200 OK`

**Content** :

```json
{
  "_embedded": {
    "eventTickets": [
      {
        "ticketCount": 1000,
        "price": 20.0,
        "created": "2020-04-11T00:55:48.247524",
        "invalid": null,
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/3"
          },
          "event": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/3/event"
          },
          "ticketType": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/3/ticketType"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets"
    }
  }
}
```
</br>

**Condition** : No eventTickets

**Code** : `404 Not Found`

**Content** :

```json
{ }
```
</br>

# Get an eventTicket

Get a single eventTicket the active User can access with current permission level.

**URL** : `/api/eventTicket/{id}`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : eventTicket found.

**Code** : `200 OK`

**Content** :

```json
{
  "ticketCount": 1000,
  "price": 20.0,
  "created": "2020-04-11T00:55:48.247524",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/3"
    },
    "event": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/3/event"
    },
    "ticketType": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/3/ticketType"
    }
  }
}
```
</br>

## Failure Response

**Condition** : eventTicket not found or invalid ID.

**Code** : `404 NOT FOUND`

**Content** :

```json
{
    "timestamp": "2020-05-06T21:09:09.683+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 81",
    "path": "/api/eventTickets/81"
}
```
</br>
