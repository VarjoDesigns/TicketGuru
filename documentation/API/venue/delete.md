# Delete an Venue

Delete an Venue

**URL** : `/api/venues/{id}`

**Method** : `DELETE`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide the URL of Venue to be deleted e.g. /api/venues/3

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
    "timestamp": "2020-05-06T20:59:16.717+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 5",
    "path": "/api/venues/5"
}
```
</br>

**Condition** : Invalid ID.

**Code** : `404 Not Found`

**Content example**

```json
{
    "timestamp": "2020-05-06T20:59:16.717+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 5",
    "path": "/api/venues/5"
}
```
</br>

**Condition** : venue is already marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "2020-05-06T21:00:12.576+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify Venue that is marked as deleted",
    "path": "/api/venues/4"
}
```
