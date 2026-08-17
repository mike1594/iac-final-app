# iac-final-app

Microservicio de la Evaluación Final IaC (DevOps) desplegado en Azure Container Apps.

## Endpoints

| Método | Ruta      | Respuesta                                   |
|--------|-----------|---------------------------------------------|
| GET    | `/hello`  | `Hola, soy Joe Michelle Meza Cardama`       |
| GET    | `/secreto`| Valor del secreto `APP_SECRET` desde Key Vault |

## Stack

- Java 17 / Spring Boot 3.2 / Maven
- Docker multi-stage (build Maven, runtime JRE)

## Build y push local

```bash
docker login
docker build --platform linux/amd64 -t jmeza17/iac-final-app:1.0.0 .
docker push jmeza17/iac-final-app:1.0.0
```

> Azure Container Apps solo acepta imágenes `linux/amd64`.

## CI/CD (GitHub Actions)

El workflow `.github/workflows/docker-publish.yml` se gatilla en cada `push` a `main`:

1. Build de la imagen Docker.
2. Login a DockerHub.
3. Push con tags automáticos:
   - `jmeza17/iac-final-app:1.0.<run_number>` (versión por ejecución)
   - `jmeza17/iac-final-app:latest`
   - `jmeza17/iac-final-app:<commit sha>`

### Secrets requeridos en GitHub

Configurar en el repo (Settings → Secrets and variables → Actions):

- `DOCKERHUB_USERNAME` = `jmeza17`
- `DOCKERHUB_TOKEN` = token de acceso a DockerHub

## Ejecución local

```bash
docker build -t iac-final-app:local .
docker run --rm -p 8080:8080 -e APP_SECRET=test iac-final-app:local
# http://localhost:8080/hello
# http://localhost:8080/secreto
```