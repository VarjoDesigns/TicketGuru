# TicketGuru REST API

Here's documentation for TicketGuru REST API.

## Base-URL

`http://localhost:8080/api`

## Open Endpoints

Open endpoints require no Authentication.

At the moment all endpoints are open. Authentication will be added soon.

## Endpoints that require Authentication

Closed endpoints require a valid Token to be included in the header of the
request.

### Current Endpoints

Each endpoint manipulates or displays information::

* [Events](event/get.md) : `GET /api/events/`
* [Events](event/post.md) : `POST /api/events/`
* [Events](event/patch.md) : `PATCH /api/events/{id}`
* [Events](event/delete.md) : `DELETE /api/events/{id}`
