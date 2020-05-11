# Get all postcodes

Get all postcodes the active User can access with current permission level.

**URL** : `https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{[]}`

## Success Response

**Condition** : postcodes found.

**Code** : `200 OK`

**Content** :

```json
{
  "_embedded": {
    "postcodes": [
      {
        "city": "Helsinki",
        "country": "Finland",
        "created": "2020-04-11T00:52:29.563972",
        "invalid": null,
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00100"
          },
          "eventOrganizers": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00100/eventOrganizers"
          },
          "venues": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00100/venues"
          }
        }
      },
      {
        "city": "Helsinki",
        "country": "Finland",
        "created": "2020-04-11T00:52:29.563972",
        "invalid": null,
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00130"
          },
          "eventOrganizers": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00130/eventOrganizers"
          },
          "venues": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00130/venues"
          }
        }
      },
      {
        "city": "Helsinki",
        "country": "Finland",
        "created": "2020-04-11T00:52:29.563972",
        "invalid": null,
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00140"
          },
          "eventOrganizers": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00140/eventOrganizers"
          },
          "venues": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00140/venues"
          }
        }
      },
      {
        "city": "Helsinki",
        "country": "Finland",
        "created": "2020-04-11T00:52:29.563972",
        "invalid": null,
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00150"
          },
          "eventOrganizers": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00150/eventOrganizers"
          },
          "venues": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00150/venues"
          }
        }
      },
      {
        "city": "Helsinki",
        "country": "Finland",
        "created": "2020-04-11T00:52:29.563972",
        "invalid": null,
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00160"
          },
          "eventOrganizers": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00160/eventOrganizers"
          },
          "venues": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00160/venues"
          }
        }
      },
      {
        "city": "Helsinki",
        "country": "Finland",
        "created": "2020-04-11T00:52:29.563972",
        "invalid": "2020-05-06T16:29:18.937",
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00002"
          },
          "eventOrganizers": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00002/eventOrganizers"
          },
          "venues": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00002/venues"
          }
        }
      },
      {
        "city": "Tapulikaupunki",
        "country": "Suomi",
        "created": "2020-05-06T18:53:01.258",
        "invalid": "2020-05-06T18:54:54.273",
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00750"
          },
          "eventOrganizers": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00750/eventOrganizers"
          },
          "venues": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00750/venues"
          }
        }
      },
      {
        "city": "Puistola",
        "country": "Suomi",
        "created": "2020-05-06T19:01:00.805",
        "invalid": null,
        "_links": {
          "self": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00760"
          },
          "eventOrganizers": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00760/eventOrganizers"
          },
          "venues": {
            "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00760/venues"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes"
    }
  }
}
```
</br>

**Condition** : No postcodes

**Code** : `404 Not Found`

**Content** :

```json
{ }
```
</br>

# Get an single postcode

Get a single postcode the active User can access with current permission level.

**URL** : `/api/postcodes/{id}`

**Method** : `GET`

**Auth required** : Yes

**Permissions required** : None

**Data constraints** : `{}`

## Success Response

**Condition** : Postcode found.

**Code** : `200 OK`

**Content** :

```json
{
  "city": "Helsinki",
  "country": "Finland",
  "created": "2020-04-11T00:52:29.563972",
  "invalid": null,
  "_links": {
    "self": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00100"
    },
    "eventOrganizers": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00100/eventOrganizers"
    },
    "venues": {
      "href": "https://rbmk-ticketguru-backend.herokuapp.com/api/postcodes/00100/venues"
    }
  }
}
```
</br>

## Failure Response

**Condition** : Postcode not found or invalid ID.

**Code** : `404 NOT FOUND`

**Content** :

```json
{
    "timestamp": "2020-05-06T19:08:03.587+0000",
    "status": 404,
    "error": "Not Found",
    "message": "Invalid ID: 00800",
    "path": "/api/postcodes/00800"
}
```
</br>
