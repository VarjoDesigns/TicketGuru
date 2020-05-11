# Get all saleEvents

Get all saleEvents the active User can access with current permission level.

**URL** : `/api/saleEvents`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{[]}`

## Success Response

**Condition** : saleEvents found.

**Code** : `200 OK`

**Content** :

```json
{
  "_embedded": {
    "saleEvents": [
      {
        "created": "2020-04-11T00:55:53.77016",
        "invalid": null,
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/1"
          },
          "user": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/1/user"
          },
          "saleRows": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/1/saleRows"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents"
    }
  }
}
```
</br>

**Condition** : No saleEvents

**Code** : `404 Not Found`

**Content** :

```json
{ }
```
</br>

# Get an saleEvent

Get a single saleEvent the active User can access with current permission level.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/ageLimits/{id}`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : saleEvent found.

**Code** : `200 OK`

**Content** :

```json
{
  "created": "2020-04-22T07:23:49.725",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/2"
    },
    "user": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/2/user"
    },
    "saleRows": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/saleEvents/2/saleRows"
    }
  }
}
```
</br>

## Failure Response

**Condition** : saleEvent not found or invalid ID.

**Code** : `404 NOT FOUND`

**Content** :

```json
{
    "timestamp": "2020-05-06T19:29:08.145+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 203",
    "path": "/api/saleEvents/203"
}
```
</br>
