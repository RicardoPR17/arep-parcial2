package com.edu.escuelaing.arep.parcial2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpConnection {
    private static final String USER_AGENT = "Mozilla/5.0";
    // private static final String[] GET_URL_LOCAL = {"http://localhost:5001/", "http://localhost:5002/"};
    private static List<String> GET_URL = new ArrayList<>();
    private static int server = 0;

    public static String connection(String method, int value) throws IOException {
        URL obj = new URL(GET_URL.get(server) + method + "?value=" + value);
        changeServer();
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return response.toString();
    }

    private static void changeServer() {
        server = (server + 1) % GET_URL.size();
        System.out.println("Next server:" + GET_URL.get(server));
    }

    public static void setURLS(String... args) {
        for (String url : args) {
            GET_URL.add("http://" + url + "/");
        }
    }
}
