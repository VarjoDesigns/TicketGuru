# Edit an postcode

Edit an postcode.

**URL** : `/api/postcodes/{id}`

**Method** : `PATCH`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
    "postcode_id" : "String 10 chars max",
    "city" : "String 500 chars max",
    "country" : "String 500 chars max"
}
```

**Data example**

```json
{
    "postcode_id": "00760",
    "city": "Puistola",
    "country": "Suomi"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "city": "Puistola",
  "country": "Suomi",
  "created": "2020-05-06T19:01:00.805",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00760"
    },
    "eventOrganizers": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00760/eventOrganizers"
    },
    "venues": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00760/venues"
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
    "timestamp": "2020-05-06T19:19:55.633+0000",
    "status": 405,
    "error": "Method Not Allowed",
    "message": "Request method 'PATCH' not supported",
    "path": "/api/postcodes/"
}
```
</br>

**Condition** : Invalid ID.

**Code** : `404 Not Found`

**Content example**

```json
{
    "timestamp": "2020-05-06T19:18:05.031+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 00799",
    "path": "/api/postcodes/00799"
}
```
</br>

**Condition** : postcode is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "2020-05-06T19:19:19.495+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify Postcode that is marked as deleted",
    "path": "/api/postcodes/00750"
}
```
