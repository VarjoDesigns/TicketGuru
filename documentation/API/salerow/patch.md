# Edit an Event

Edit an Event.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/{id}`

**Method** : `PATCH`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide ID and values to modify.

```json
{
    "discount": "Long",
    "saleEvent": "sale_event_id"
}
```

**Data example**

```json
{
  "discount": 10,
  "saleEvent": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/2"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `200 OK`

**Content example**

```json
{
  "discount": 10,
  "created": "2020-04-22T09:46:15.509",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/2"
    },
    "saleEvent": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/2/saleEvent"
    },
    "tickets": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/2/tickets"
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
    "path": "/api/SaleRows/"
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
    "path": "/api/saleRows/{id}"
}
```
</br>

**Condition** : Sale row is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify sale row that is marked as deleted",
    "path": "/api/saleRows/{id}"
}
```
</br>

**Condition** : Sale event is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot create SaleRow for SaleEvent that is marked as deleted",
    "path": "/api/saleRows/{id}"
}
```