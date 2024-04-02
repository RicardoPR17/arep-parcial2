package com.edu.escuelaing.arep.parcial2;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class ProxyService {
  public static void main(String... args) {
    port(getPort());
    staticFiles.location("public");
    HttpConnection.setURLS(args);
    get("hello", (req, res) -> "Hello Proxy!");
    get("factors", (req, res) -> {
      int value = Integer.parseInt(req.queryParams("value"));
      return HttpConnection.connection("factors", value);
    });
    get("primes", (req, res) -> {
      int value = Integer.parseInt(req.queryParams("value"));
      return HttpConnection.connection("primes", value);
    });
  }

  private static int getPort() {
    if (System.getenv("PORT") != null) {
      return Integer.parseInt(System.getenv("PORT"));
    }
    return 4567;
  }
}