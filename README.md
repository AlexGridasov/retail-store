# retail-store

## Order service
To invoke the microservice, open your command-line client and execute the following curl command:
```
\> curl -v http://localhost:8080/orders \
-H 'Content-Type: application/json' \
--data-binary @- << EOF
{
"items":[
{
"itemCode":"IT0001",
"quantity":3
},
{
"itemCode":"IT0004",
"quantity":1
}
],
"shippingAddress":"No 4, Castro Street, Mountain View, CA, USA"
}
EOF
```

With OAuth2 enabled:
```
\> curl -v http://localhost:8080/orders \
-H 'Content-Type: application/json' \
-H "Authorization: Bearer 480d69b4-77ca-4de5-b9ef-76264b7d1cb1" \
--data-binary @- << EOF
{
"items":[
{
"itemCode":"IT0001",
"quantity":3
},
{
"itemCode":"IT0004",
"quantity":1
}
],
"shippingAddress":"No 4, Castro Street, Mountain View, CA, USA"
}
EOF
```

## Oauth2 server
To get an access token from the OAuth 2.0 authorization server:
```
\> curl -u orderprocessingapp:orderprocessingappsecret \
-H "Content-Type: application/json" \
-d '{"grant_type": "client_credentials", "scope": "read write"}' \
http://localhost:8085/oauth/token
```
Response (example):
```
{
    "access_token": "e4a6a11e-3d49-4358-9a8d-07553aec5c0d",
    "token_type": "bearer",
    "expires_in": 3524,
    "scope": "read write"
}
```
