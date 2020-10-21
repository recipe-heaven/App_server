# Project no.twct.recipeheaven

**Requirements**

- mvn
- Docker
- JDK 11

**Steps to run this project:**

1. Start your Docker daemon
2. Execute `./Build.sh` (Linux/MacOs)
3. This will launch
   1. Mail service
   2. Database (Postgres SQL)
   3. Application server

**Development**

1. Run ``` ./Build.sh``` - docker-compose up if images are already built
   1. Start ```./run.sh``` - This will destroy any application servers and create a separate application server mapping its volume to /target
2. Run `mvn package` in the root folder of the project for hot reloading the application server.
3. It will automatically restart the application