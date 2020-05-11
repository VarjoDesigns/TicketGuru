# Delete an eventTicket

Delete an eventTicket

**URL** : `/api/eventTickets/{id}`

**Method** : `DELETE`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide the URL of eventTicket to be deleted e.g. /api/eventTickets/3

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
    "timestamp": "2020-05-06T21:26:37.927+0000",
    "status": 405,
    "error": "Method Not Allowed",
    "message": "Request method 'DELETE' not supported",
    "path": "/api/eventTickets/"
}
```
</br>

**Condition** : Invalid ID.

**Code** : `404 Not Found`

**Content example**

```json
{
    "timestamp": "2020-05-06T21:26:48.473+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 12",
    "path": "/api/eventTickets/12"
}
```
</br>

**Condition** : eventTicket is already marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "2020-05-06T21:27:00.581+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify EventTicket that is marked as deleted",
    "path": "/api/eventTickets/8"
}
```
