## KAFKA DOCKER testing repo

# RUN

- start colima
- start docker container with Kafka
```bash
colima start
docker-compose up -d
```


## API Endpoints
- GET `localhost:8081/topic`
- POST `localhost:8081/topic`

BODY:
```
{
"name": "NEW_NAME"
}
```