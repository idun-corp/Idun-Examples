![ProptechOS logo](../images/ProptechOS-logotype-ex.png)
# API


The resources and state of them are exposed via an endpoint each per top level class, with GET and POST methods for reading and creating/changing them. Please see Open API Specification (Swagger) docs for up-to-date details.

# Authentication

Authentication in ProptechOS uses OAuth 2.0 protocol.
It can be separated into two categories:
* interactive authentication (for applications accessing the API on behalf of user, like web apps and UIs)
* application (daemon) authentication, for applications working without user interaction.

See more in [the Authentication section](ProptechOS-Api/authentication)

# JSON and JSON-LD
The API follow RealEstateCore, which means JSON-LD and [hydra](https://hydra-cg.com). (link to JSON-LD parsers to be added here). However ProptechOS also includes plain JSON versions of all endpoints using the `/json/` prefix for the endpoints. E.g. the `/api/sensor` endpoint has a JSON version at `/api/json/sensor`.

Note that besides the endpoints and the contents being different, the different flavours of endpoints rely on different content type headers:

_JSON-LD_  
`type: application/ld+json`  
Using curl: `-H "accept: application/ld+json"`

_JSON_  
`type: application/json`  
Using curl: `-H "accept: application/json"`

# Using Aliases and AliasNamespaces



See more in [the Alias and AliasNamespace section](ProptechOS-Api/alias-aliasnamespace)
