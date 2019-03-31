package sk.itsovy.projectKaufland.main;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Internet {
    public static Double getUSDRate() throws IOException {

        double usd;


        URL url = new URL("https://api.exchangeratesapi.io/latest");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();


        JsonParser jsonParser = new JsonParser();

        JsonElement parse = jsonParser.parse(new InputStreamReader((InputStream) connection.getContent()));
        JsonObject jsonObject = parse.getAsJsonObject();
        jsonObject = jsonObject.getAsJsonObject("rates");

        usd = jsonObject.get("USD").getAsDouble();
        return usd;
    }
}
