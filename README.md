# GMAP MicroServices


Build Project
``` 
mvn clean install
```
## bidservice

```
cd bidservice
mvn spring-boot:run
```
## userservice

```
cd userservice
mvn spring-boot:run

```

Use BID.postman_collection.json for trying out various request.
1. Teanant Creation (bidservice)
2. Bid Creation (bidservice)
3. Update Bid (bidservice)
4. Retrive Tenant (userservice)


# Using docker-compose
 To rebuild this image you must use `docker-compose build` or `docker-compose up --build`.
