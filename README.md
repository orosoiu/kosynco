# KosyncO

KosyncO is a self-hosted sync server for [KOReader](https://koreader.rocks/). It allows synchronizing reading progress between devices running KOReader. You can find more information [here](https://github.com/koreader/koreader/wiki/Progress-sync).

I recommend running the server behind a reverse proxy such as [nginx](https://nginx.org/) or [Cloudflare tunnel](https://developers.cloudflare.com/cloudflare-one/connections/connect-networks/), in order to handle SSL and other security concerns.

## Run the server

To quickly start the server you can run the following command:

```shell
docker run -d -p 8080:8080 -v `pwd`:/data --name=kosynco occamro/kosynco:latest
```

This command will bind the current directory as the data folder and run the server in a container.
The database will be created in the current folder, and the server will be accessible on port 8080.

Alternatively, you can use the following [Docker Compose](https://docs.docker.com/compose/) file:
```yaml
version: "3"
services:
  kosynco:
    image: occamro/kosynco:latest
    container_name: kosynco
    restart: unless-stopped
    network_mode: bridge
    ports:
      - <external_port>:8080
    volumes:
      - <path/to/local/data/folder>:/data
    environment:
      - REGISTRATION_DISABLED=false
    user: 1000:1000
```

Make sure to make the configuration changes you desire in the docker compose file by replacing the following placeholders:

- `<external_port>` - replace this with the port which will expose the sync server, which will then be accessible at `http://localhost:<external_port>`
- `<path/to/local/data/folder>` - replace this with the path to the local folder where the database will be stored. This is required to make sure the database is persisted when the container is re-created (e.g. after updating the image)
- `REGISTRATION_DISABLED` - this flag can be set to `true` to entirely disable new user creation. You might want to do this after registering your first device with the server
- `user: 1000:1000` - this mapping ensures the database is created using your own user; replace with your actual user id and group id if different

To start the container run:
```shell
docker-compose up -d
```