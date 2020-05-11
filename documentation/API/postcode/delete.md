# Delete an postcode

Delete an postcode

**URL** : `/api/postcodes/{id}`

**Method** : `DELETE`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide the URL of postcode to be deleted e.g. /api/postcodes/3

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
    "timestamp": "2020-05-06T19:22:07.493+0000",
    "status": 405,
    "error": "Method Not Allowed",
    "message": "Request method 'DELETE' not supported",
    "path": "/api/postcodes/"
}
```
</br>

**Condition** : Invalid ID.

**Code** : `404 Not Found`

**Content example**

```json
{
    "timestamp": "2020-05-06T19:22:38.364+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 00799",
    "path": "/api/postcodes/00799"
}
```
</br>

**Condition** : postcode is already marked as deleted.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "2020-05-06T19:22:55.560+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Cannot modify Postcode that is marked as deleted",
    "path": "/api/postcodes/00750"
}
```
