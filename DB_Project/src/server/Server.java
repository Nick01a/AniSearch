package server;

import database.Connector;

import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {
        get("/anime", (request, response) -> Connector.getRandomAnime());
    }
}
