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

## Oauth2 server
To get an access token from the OAuth 2.0 authorization server:
```
curl -u orderprocessingapp:orderprocessingappsecret \
-H "Content-Type: application/json" \
-d '{"grant_type": "client_credentials", "scope": "read write"}' \
http://localhost:8085/oauth/token
```
