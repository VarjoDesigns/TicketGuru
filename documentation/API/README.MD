# TicketGuru REST API

Here's documentation for TicketGuru REST API.

## Base-URL

`https://rbmk-ticketguru-backend.herokuapp.com`

## Endpoints require Authentication

Closed endpoints require a valid Token to be included in the header of the
request.

* [Authentication](authentication.md) : `POST /api/login/`

### Endpoint listing

Each endpoint manipulates or displays information::

* [Events](event/get.md) : `GET /api/events/`
* [Events](event/post.md) : `POST /api/events/`
* [Events](event/patch.md) : `PATCH /api/events/{id}`
* [Events](event/delete.md) : `DELETE /api/events/{id}`
* [SaleRows](salerow/get.md) : `GET /api/saleRows/`
* [SaleRows](salerow/post.md) : `POST /api/saleRows/`
* [SaleRows](salerow/patch.md) : `PATCH /api/saleRows/{id}`
* [SaleRows](salerow/delete.md) : `DELETE /api/saleRows/{id}`
* [Tickets](ticket/get.md) : `GET /api/tickets/`
* [Tickets](ticket/patch.md) : `PATCH /api/tickets/{id}`
* [Tickets](ticket/delete.md) : `DELETE /api/tickets/{id}`
* [Users](user/get.md) : `GET /api/users/`
* [Users](user/post.md) : `POST /api/users/`
* [Users](user/patch.md) : `PATCH /api/users/{id}`
* [Users](user/delete.md) : `DELETE /api/users/{id}`
* [Usergroups](usergroup/get.md) : `GET /api/userGroups/`
* [Usergroups](usergroup/post.md) : `POST /api/userGroups/`
* [Usergroups](usergroup/patch.md) : `PATCH /api/userGroups/{id}`
* [Usergroups](usergroup/delete.md) : `DELETE /api/userGroups/{id}`
