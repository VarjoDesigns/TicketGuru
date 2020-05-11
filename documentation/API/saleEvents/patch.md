# Edit an AgeLimit

Edit an AgeLimit.

**URL** : `/api/saleEvents/{id}`

**Method** : `PATCH`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
	"user" : "user_id"
}
```

**Data example**

```json
{
	"user" : "https://rbmk-ticketguru-backend.herokuapp.com/api/users/5"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "created": "2020-05-06T19:49:01.988",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/55"
    },
    "user": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/55/user"
    },
    "saleRows": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/55/saleRows"
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
    "timestamp": "2020-05-06T19:54:22.739+0000",
    "status": 405,
    "error": "Method Not Allowed",
    "message": "Request method 'PATCH' not supported",
    "path": "/api/saleEvents/"
}
```
</br>

**Condition** : Invalid ID.

**Code** : `404 Not Found`

**Content example**

```json
{
    "timestamp": "2020-05-06T19:54:07.507+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 557",
    "path": "/api/saleEvents/557"
}
```
</br>

**Condition** : saleEvent is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "2020-05-06T19:57:36.121+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify SaleEvent that is marked as deleted",
    "path": "/api/saleEvents/5"
}
```
