# Delete a Event

Delete a Event ticket

**URL** : `/api/eventTickets/{id}`

**Method** : `DELETE`

**Auth required** : NO

**Permissions required** : None

**Data constraints**

Provide the URL of Event to be deleted e.g. http://localhost:8080/api/eventTickets/4


## Success Response

**Condition** : If everything is OK.

**Code** : `204 No Content`

**Content example**

```json
 {}
```

## Error Responses

**Condition** : If {id} is missing.

**Code** : `404 Not Found`

**Content example**

```json
{
     "timestamp": "2020-03-12T10:57:14.902+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 4",
    "path": "/api/eventTickets/4"
}
```
