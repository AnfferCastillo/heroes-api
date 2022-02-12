# heroes-api

An API for your heroes

# DISCLAIMER

- THIS IS NOT PRODUCTION READY. DEMONSTRATION PURPOSES ONLY.
- PASSWORDS ARE NOT ENCRYPTED AND SECURITY IMPLEMENTAION IS NOT PRODUCTION READY.
- USE IT AT YOUR OWN RISK.

## Dependecies

- Java 11
- Maven >3
- Docker
- docker-compose (if you want to use docker-compose)

## How to run

- You can run it from your IDE or use docker.

### Run it with docker

- The app is on Docker Hub, so just run the following command and you are good to go.

```shell
    ##Just run the container
    docker run -p 8080:8080 anffercastillo/hero-api:latest
```

- If you want to compile it go to the root folder of the repo and run the following commands in your terminal.

```shell
    ## compile the image and push it to your docker deamon
    mvn compile jib:dockerBuild
    
    ## run a container
    docker run -p 8080:8080 anffercastillo/hero-api:latest
```

### Run it with docker compose

- Go to the root folder of the repo and run in your terminal:

```shell
    ## run docker compose 
    docker-compose up
```