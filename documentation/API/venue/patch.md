# Edit an venue

Edit an venue.

**URL** : `/api/venues/{id}`

**Method** : `PATCH`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
  "name" : "String 100 chars max",
  "address" : "String 150 chars max",
  "postcode" : "postcode_id",
  "tel" : "String 25 chars max",
  "email" : "String 150 chars max",
  "www" : "String 250 chars max (optional)",
  "contactPerson" : "String 150 chars max",
  "created" : "[ISO 8601] (optional)"
}
```

**Data example**

```json
{
  "name" : "Pepen Pubi",
  "address" : "Pepenkaarre 202",
  "postcode" : "/api/postcodes/00100",
  "tel" : "0506362424",
  "email" : "pepe@pepenpubikuppila.com",
  "www" : "www.pepenpubikuppila.com",
  "contactPerson" : "Pepe Pepe"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "name": "Pepen Pubi",
  "address": "Pepenkaarre 202",
  "tel": "0506362424",
  "email": "pepe@pepenpubikuppila.com",
  "www": "www.pepenpubikuppila.com",
  "contactPerson": "Pepe Pepe",
  "created": "2020-05-06T20:39:57.592",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/3"
    },
    "postcode": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/3/postcode"
    },
    "events": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/venues/3/events"
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
    "timestamp": "2020-05-06T20:56:05.515+0000",
    "status": 405,
    "error": "Method Not Allowed",
    "message": "Request method 'PATCH' not supported",
    "path": "/api/venues/"
}
```
</br>

**Condition** : Invalid ID.

**Code** : `404 Not Found`

**Content example**

```json
{
    "timestamp": "2020-05-06T20:55:48.246+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 37",
    "path": "/api/venues/37"
}
```
</br>

**Condition** : venue is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "2020-05-06T20:58:56.195+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify Venue that is marked as deleted",
    "path": "/api/venues/4"
}
```
