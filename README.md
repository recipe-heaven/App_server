# Project no.twct.recipeheaven

**Requirements**
- Docker

**Steps to run this project:**

1. Start your Docker daemon
2. Execute `./start.sh` (Linux/MacOs)
    - this will firstly build the war in a builder container
    - This will launch
        1. Mail service
        2. Database (Postgres SQL)
        3. Application server

**Development**

1. Running the QOL script `./reload_server.sh` will rebuild the war and relaunch the payara image
