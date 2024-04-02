package com.edu.escuelaing.arep.parcial2;

import static spark.Spark.get;
import static spark.Spark.port;

import java.util.ArrayList;
import java.util.List;

public class MathServices {
    public static void main(String... args) {
        port(getPort());
        get("hello", (req, res) -> "Hello Math!");
        get("factors", (req, res) -> {
            res.type("application/json");
            int value = Integer.parseInt(req.queryParams("value"));
            return "{\"operation\": \"factors\", \"input\": " + value + ", \"output\": "
                    + factors(value).toString().replace("[", "").replace("]", "") + "}";
        });
        get("primes", (req, res) -> {
            res.type("application/json");
            int value = Integer.parseInt(req.queryParams("value"));
            return "{\"operation\": \"primes\", \"input\": " + value + ", \"output\": "
                    + primes(value).toString().replace("[", "").replace("]", "") + "}";
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5001;
    }

    private static List<Integer> factors(int number) {
        List<Integer> factorList = new ArrayList<>();

        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                factorList.add(i);
            }
        }

        factorList.add(number);
        return factorList;
    }

    private static List<Integer> primes(int number) {
        List<Integer> primeList = new ArrayList<>();

        for (int i = 1; i <= number; i++) {
            List<Integer> checkPrime = factors(i);
            if (checkPrime.size() == 2) {
                primeList.add(i);
            }
        }
        return primeList;
    }
}
