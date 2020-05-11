# Create an Postcode

In this document we'll guide you through posting an AgeLimit.

**URL** : `/api/postcodes/`

**Method** : `POST`

**Content-Type** : `application/json`

**Auth required** : Yes

**Permissions required** : None

**Data constraints**

Provide below required values.

```json
{
	"postcode_id" : "String 10 chars max",
  "city" : "String 500 chars max",
	"country" : "String 500 chars max"
}
```

**Data example**

```json
{
    "postcode_id": "00760",
    "city": "Puistola",
    "country": "Suomi"
}
```

## Success Response

**Condition** : If everything is OK.

**Code** : `201 Created`

**Content example**

```json
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
```

## Error Responses


**Condition** : Required field (In this case, country) is missing.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "2020-05-06T19:02:15.126+0000",
    "status": 400,
    "error": "Bad Request",
    "errors": [
        {
            "codes": [
                "NotEmpty.postcode.country",
                "NotEmpty.country",
                "NotEmpty.java.lang.String",
                "NotEmpty"
            ],
            "arguments": [
                {
                    "codes": [
                        "postcode.country",
                        "country"
                    ],
                    "arguments": null,
                    "defaultMessage": "country",
                    "code": "country"
                }
            ],
            "defaultMessage": "Country name is required",
            "objectName": "postcode",
            "field": "country",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotEmpty"
        }
    ],
    "message": "Validation failed for object='postcode'. Error count: 1",
    "path": "/api/postcodes/"
}
```
