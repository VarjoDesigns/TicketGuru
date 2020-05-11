# Delete an AgeLimit

Delete an AgeLimit

**URL** : `/api/saleEvents/{id}`

**Method** : `DELETE`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide the URL of AgeLimit to be deleted e.g. /api/saleEvents/3

## Success Response

**Condition** : If everything is OK.

**Code** : `204 No Content`

**Content example**

```json
{}
```
</br>

## Error Responses

**Condition** : ID is missing.

**Code** : `405 Method not allowed`

**Content example**

```json
{
    "timestamp": "2020-05-06T20:02:53.943+0000",
    "status": 405,
    "error": "Method Not Allowed",
    "message": "Request method 'DELETE' not supported",
    "path": "/api/saleEvents/"
}
```
</br>

**Condition** : Invalid ID.

**Code** : `404 Not Found`

**Content example**

```json
{
    "timestamp": "2020-05-06T20:02:40.666+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 582",
    "path": "/api/saleEvents/582"
}
```
</br>

**Condition** : saleEvent is already marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "2020-05-06T20:02:01.058+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify SaleEvent that is marked as deleted",
    "path": "/api/saleEvents/5"
}
```
