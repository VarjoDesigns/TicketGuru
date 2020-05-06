# Create a TicketStatus

Create a TicketStatus.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/ticketStatuses/`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide below required values.

```json
{
    "name": "String 50 chars max",
}
```

**Data example**

```json
{
	"name" : "Ei maksettu lippu"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `201 Created`

**Content example**

```json
{
  "name": "Ei maksettu lippu",
  "created": "2020-05-06T16:01:05.592",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ticketStatuses/8"
    },
    "ticketStatus": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ticketStatuses/8"
    },
    "tickets": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/ticketStatuses/8/tickets"
    }
  }
}
```

## Error Responses


**Condition** : Required field is missing.

**Code** : `500 Internal Server Error`

**Content example**

```json
{
    "timestamp": "2020-05-06T16:01:31.791+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Validation failed for classes [fi.rbmk.ticketguru.ticketStatus.TicketStatus] during persist time for groups [javax.validation.groups.Default, ]\nList of constraint violations:[\n\tConstraintViolationImpl{interpolatedMessage='Ticket status is required', propertyPath=name, rootBeanClass=class fi.rbmk.ticketguru.ticketStatus.TicketStatus, messageTemplate='Ticket status is required'}\n]",
    "path": "/api/ticketStatuses/"
}
```