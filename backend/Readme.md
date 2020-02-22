## Spring with gRPC
This project consist of two subproject, producer-server and consumer-server.

### Requirements
- OpenJDK 8
- Docker and docker-compose

### Preparation
- run `docker-compose up -d`

### Producer server
This subproject is the gRPC server. gRPC port will be opened in 6565. We provide REST endpoint (port 9090) in this subproject as well for benchmarking purposes.
- Run `./gradlew producer-server:bootRun`

### Consumer server
This is the gRPC client. It will call remote procedure to producer server. We provide REST API that call gRPC service for easier testing.
- Create database named `consumer`
- Make sure producer server is running
- Run `./gradlew producer-server:bootRun`
- GET `http://localhost:9091/books`, it will make gRPC call to producer-server
