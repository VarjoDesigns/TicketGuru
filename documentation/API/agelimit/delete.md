# Delete an AgeLimit

Delete an AgeLimit

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/ageLimits/{id}`

**Method** : `DELETE`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide the URL of AgeLimit to be deleted e.g. https://rbmk-ticketguru-backend.herokuapp.com/api/ageLimits/3

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
    "timestamp": "LocalDateTime",
    "status": 405,
    "error": "Method Not Allowed",
    "message": "Request method 'DELETE' not supported",
    "path": "/api/ageLimits/"
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
    "path": "/api/ageLimits/{id}"
}
```
</br>

**Condition** : AgeLimit is already marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "LocalDateTime",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify AgeLimit that is marked as deleted",
    "path": "/api/ageLimits/{id}"
}
```