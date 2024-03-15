package com.example;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.net.URL;
import java.net.HttpURLConnection;

public class Client {
    public static void main(String[] args) {
        // Load properties from file
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        // Read properties
        String serverAddress = properties.getProperty("server.address", "127.0.0.1");
        int port = Integer.parseInt(properties.getProperty("server.port", "8000"));
        int bytesToRead = Integer.parseInt(properties.getProperty("bytes.to.read", "1024"));
        boolean useHttps = Boolean.parseBoolean(properties.getProperty("use.https", "false"));
        String resourceUrl = properties.getProperty("resource.url");
        boolean authEnabled = Boolean.parseBoolean(properties.getProperty("authorization.enabled", "false"));
        String authToken = properties.getProperty("authorization.token");
        boolean responseComplete = Boolean.parseBoolean(properties.getProperty("response.complete", "true"));

        try {
            URL url = new URL((useHttps ? "https://" : "http://") + serverAddress + ":" + port + resourceUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Add authorization header if enabled
            if (authEnabled && authToken != null && !authToken.isEmpty()) {
                connection.setRequestProperty("Authorization", "Bearer " + authToken);
            }

            connection.connect();

            // Read response
            InputStream inputStream = connection.getInputStream();
            StringBuilder responseBuilder = new StringBuilder();
            byte[] buffer = new byte[bytesToRead];
            int bytesRead;

            // Read complete response if enabled
            if (responseComplete) {
                while ((bytesRead = inputStream.read(buffer, 0, bytesToRead)) != -1) {
                    responseBuilder.append(new String(buffer, 0, bytesRead));
                }
            } else {
                // Read only specified bytes
                bytesRead = inputStream.read(buffer, 0, bytesToRead);
                if (bytesRead != -1) {
                    responseBuilder.append(new String(buffer, 0, bytesRead));
                }
            }

            // Print or handle the complete response
            System.out.println("Received data: " + responseBuilder.toString());

            // Close the connection
            connection.disconnect();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
