package com.mountain.notif.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mountain.notif.service.PushNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PushNotificationServiceImpl implements PushNotificationService {

    private Map<String, Object> jsonResponse = new HashMap<>();
    private String APP_ID = null;
    private String REST_API_KEY = null;


    public static Map<String, Object> mountResponseRequest(HttpURLConnection con, int httpResponse) throws IOException {
        Map<String, Object> jsonResponse;
        InputStream in;
        ObjectMapper objectMapper = new ObjectMapper();
        if (httpResponse >= HttpURLConnection.HTTP_OK
                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
            in = new BufferedInputStream(con.getInputStream());
        } else {
            in = new BufferedInputStream(con.getErrorStream());
        }
        jsonResponse = objectMapper.readValue(in, Map.class);
        in.close();
        return jsonResponse;
    }


    @Override
    public Map<String, Object> pushRegisteredClimber(String firstName, String status, String reason, String deviceId, String appId, String appKey)
            throws IOException {

        URL url = new URL("https://onesignal.com/api/v1/notifications");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setUseCaches(false);
        con.setDoOutput(true);
        con.setDoInput(true);

        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Authorization", REST_API_KEY);
        con.setRequestMethod("POST");

        String strJsonBody = "{"
                + "\"app_id\": \"" + APP_ID + "\","
                + "\"include_player_ids\": [\"" + deviceId + "\"],"
                + "\"data\": {\"foo\": \"bar\"},"
                + "\"headings\": {\"en\": \"" + firstName + " - " + status + "\"},"
                + "\"contents\": {\"en\": \"" + reason + "\"}"
                + "}";

        byte[] sendBytes = strJsonBody.getBytes(StandardCharsets.UTF_8);
        con.setFixedLengthStreamingMode(sendBytes.length);

        OutputStream outputStream = con.getOutputStream();
        outputStream.write(sendBytes);

        int httpResponse = con.getResponseCode();

        jsonResponse = mountResponseRequest(con, httpResponse);

        return jsonResponse;
    }
}
