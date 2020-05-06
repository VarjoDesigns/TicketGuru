# Edit an EventOrganizer

Edit an EventOrganizer.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/eventOrganizers/{id}`

**Method** : `PATCH`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
	"name": "String 100 chars max",
	"streetAddress": "String 150 chars max",
	"tel": "String 25 chars max",
	"email": "String 150 chars max",
	"www": "String 250 chars max",
	"contactPerson": "String 150 chars max",
	"postcode": "postcode_id"
}
```

**Data example**

```json
{
	"name": "Wayward Wizards Oy",
	"streetAddress": "Tapahtumakatu 1 A 2",
	"tel": "09 7654321",
	"email": "wizards@wwoy.fi",
	"www": "www.waywardwizards.com",
	"contactPerson": "Jafar the Magnificant",
	"postcode": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00100"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "name": "Wayward Wizards Oy",
  "streetAddress": "Tapahtumakatu 1 A 2",
  "tel": "09 7654321",
  "email": "wizards@wwoy.fi",
  "www": "www.waywardwizards.com",
  "contactPerson": "Jafar the Magnificant",
  "created": "2020-05-06T16:20:35.365",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventOrganizers/3"
    },
    "postcode": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventOrganizers/3/postcode"
    },
    "events": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventOrganizers/3/events"
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
    "path": "/api/eventOrganizers/"
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
    "path": "/api/eventOrganizers/{id}"
}
```
</br>

**Condition** : EventOrganizer is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify EventOrganizer that is marked as deleted",
    "path": "/api/eventOrganizers/{id}"
}
```
</br>

**Condition** : Postcode is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot link Postcode that is marked as deleted",
    "path": "/api/eventOrganizers/{id}"
}
```