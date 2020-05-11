# Get all venues

Get all venues the active User can access with current permission level.

**URL** : `/api/venues/`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{[]}`

## Success Response

**Condition** : venues found.

**Code** : `200 OK`

**Content** :

```json
{
  "_embedded": {
    "venues": [
      {
        "name": "Helsingin Teatteri",
        "address": "Kekkosenkatu 3",
        "tel": "09 1234566",
        "email": "teatteri@teatteri.fi",
        "www": "www.helsinginteatteri.com",
        "contactPerson": "John Wayne",
        "created": "2020-04-11T00:53:01.633167",
        "invalid": "2020-05-05T14:20:08.641",
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/2"
          },
          "postcode": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/2/postcode"
          },
          "events": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/2/events"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues"
    }
  }
}
```
</br>

**Condition** : No venues

**Code** : `404 Not Found`

**Content** :

```json
{ }
```
</br>

# Get an venue

Get a single venue the active User can access with current permission level.

**URL** : `/api/venues/{id}`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : VEnue found.

**Code** : `200 OK`

**Content** :

```json
{
  "name": "Helsingin Teatteri",
  "address": "Kekkosenkatu 3",
  "tel": "09 1234566",
  "email": "teatteri@teatteri.fi",
  "www": "www.helsinginteatteri.com",
  "contactPerson": "John Wayne",
  "created": "2020-04-11T00:53:01.633167",
  "invalid": "2020-05-05T14:20:08.641",
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/2"
    },
    "postcode": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/2/postcode"
    },
    "events": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/2/events"
    }
  }
}
```
</br>

## Failure Response

**Condition** : venue not found or invalid ID.

**Code** : `404 NOT FOUND`

**Content** :

```json
{
    "timestamp": "2020-05-06T20:21:21.721+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 23",
    "path": "/api/venues/23"
}
```
</br>
