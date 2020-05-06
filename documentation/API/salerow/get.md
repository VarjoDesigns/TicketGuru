# Get all Sale rows

Get all sale rows the active User can access with current permission level.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{[]}`

## Success Response

**Condition** : Sale rows found.

**Code** : `200 OK`

**Content** :

```json
{
  "_embedded": {
    "saleRows": [
      {
        "discount": "Long",
        "created": "LocalDateTime",
        "invalid": "LocalDateTime",
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/{id}"
          },
          "saleEvent": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/{id}/saleEvent"
          },
          "tickets": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/{id}/tickets"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows"
    }
  }
}
```
</br>

**Condition** : No sale rows found.

**Code** : `404 NOT FOUND`

**Content** : `{[]}`

</br>

# Get a sale row

Get a single sale row the active User can access with current permission level.

**URL** : `/api/saleRows/{id}`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : Sale row found.

**Code** : `200 OK`

**Content** :

```json
{
  "discount": "Long",
  "created": "LocalDateTime",
  "invalid": "LocalDateTime",
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/{id}"
    },
    "saleEvent": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/{id}/saleEvent"
    },
    "tickets": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/{id}/tickets"
    }
  }
}
```
</br>

## Failure Response

**Condition** : Sale row not found or invalid ID.

**Code** : `404 NOT FOUND`

**Content** :

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

# Get sale event
## Failure Responseesponse

**Condition** : Sale event found.

**Code** : `200 OK`

**Content** : 

```json
{
  "created": "LocalDateTime",
  "invalid": "LocalDateTime",
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/{id}"
    },
    "user": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/{id}/user"
    },
    "saleRows": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/{id}/saleRows"
    }
  }
}
```
</br>

## Failure Response

**Condition** : Invalid sale row ID.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "LocalDateTime",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: {id}",
    "path": "/api/saleRows/{id}/saleEvent"
}
```
</br>

# Get sale row tickets

Get tickets for given sale row id.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows/{id}/tickets`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : Tickets found.

**Code** : `200 OK`

**Content** : 

```json
{
  "_embedded": {
    "tickets": [
      {
        "checksum": "String",
        "created": "LocalDateTime",
        "invalid": "LocalDateTime",
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/tickets/{id}"
          },
          "eventTicket": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/tickets/{id}/eventTicket"
          },
          "ticketStatus": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/tickets/{id}/ticketStatus"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleRows"
    }
  }
}
```
</br>

## Failure Response

**Condition** : Invalid sale row ID.

**Code** : `404 NOT FOUND`

**Content** : 

```json
{
    "timestamp": "LocalDateTime",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: {id}",
    "path": "/api/saleRows/{id}/tickets"
}
```