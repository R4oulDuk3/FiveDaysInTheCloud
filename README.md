# Levi9 CryptoSolution
## _Gavrilo Vojteski_
### gavrilovojteski37@gmail.com

Web aplikacija za trgovinu kriptovalutama.

# Tehnologije
- [Java Spring] - Backend
- [MongoDB] - Database (odabran za efikasniji razvoj aplikacije, za produkciono resenje verovatno je bolja relaciona baza podataka)
- [Maven] - Package manager i build
- [Docker] - Deployment

# Izvrsno okruzenje

### Preduslov:  **Docker**
Build-ovanje web aplikacije je prikazano u **Dockerfile**
```
FROM openjdk:19-jdk-alpine
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN dos2unix mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
```

# Build i pokretanje aplikacije
#### 1. Buildovanje crypto-levi9 image-a
```sh
cd API
docker build -t crypto-levi9 .
```
#### 2. Podizanje container-a
```sh
docker-compose up
```

#### 3. Gasenje containera
```sh
docker-compose down
```