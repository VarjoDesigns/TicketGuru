# Create a sale row

Create a sale row.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide below required values.

```json
{
  "discount": "Long (optional)",
	"saleEvent": "sale_event_id",
	"eventTicket": "event_ticket_id",
	"count": "Long"
}
```

**Data example**

```json
{
	"saleEvent": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/1",
	"eventTicket": "https://rbmk-ticketguru-backend.herokuapp.com/api/eventTickets/3",
	"count": 1
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `201 Created`

**Content example**

```json
{
  "discount": 0,
  "created": "2020-05-05T15:17:28.569",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/54"
    },
    "saleEvent": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/54/saleEvent"
    },
    "tickets": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/54/tickets"
    }
  }
}
```

## Error Responses


**Condition** : Event ticket is missing.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "defaultMessage": "EventTicket is required",
            "field": "eventTicket",
            "rejectedValue": "null"
        }
    ]
}
```
</br>

**Condition** : Sale event is missing.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "defaultMessage": "Sale event is required",
            "field": "saleEvent",
            "rejectedValue": "null"
        }
    ]
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
    "path": "/api/saleRows"
}
```
</br>

**Condition** : EventTicket is marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot create SaleRow with EventTicket that is marked as deleted",
    "path": "/api/saleRows"
}
```