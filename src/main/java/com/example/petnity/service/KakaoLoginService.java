package com.example.petnity.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@Service
public class KakaoLoginService {

    private final Logger LOGGER = LoggerFactory.getLogger(KakaoLoginService.class);

    public String getKakaoAccessToken(String code) throws IOException {
        LOGGER.info("[KakaoService] perform {} of Petnity API.", "getKakaoAccessToken");
        LOGGER.info("[KakaoService] Param :: code = {}", code);

        String access_token = "";
        String refresh_token = "";
        String host = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(host);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=fd3786d57849589d07ee5f9e8ec1ef8d");
            sb.append("&redirect_uri=http://localhost:8080/login/kakao");
            sb.append("&code=" + code);

            bw.write(sb.toString());
            bw.flush();

            int responseCode = urlConnection.getResponseCode();
            System.out.println("response code: " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String response_body = "";
            String line = "";
            while ((line = br.readLine()) != null) {
                response_body += line;
            }
            System.out.println("response body: " + response_body);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response_body);

            LOGGER.info("[KakaoService] Response :: element = {}", element.toString());

            access_token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token: " + access_token);
            System.out.println("refresh_token: " + refresh_token);

            br.close();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_token;
    }

    public Map<String, Object> getUserInfo(String access_token) throws IOException{
        LOGGER.info("[KakaoService] perform {} of Petnity API.", "getUserInfo");
        LOGGER.info("[KakaoService] Param :: access_token = {}", access_token);

        String host = "https://kapi.kakao.com/v2/user/me";
        Map<String, Object> result = new HashMap<>();

        try {
            URL url = new URL(host);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Authorization", "Bearer " + access_token);

            int responseCode = urlConnection.getResponseCode();
            System.out.println("response code: " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String response_body = "";
            String line = "";
            while ((line = br.readLine()) != null) {
                response_body += line;
            }
            System.out.println("response body: " + response_body);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(response_body);
            LOGGER.info("[KakaoService] Response :: element = {}", element.toString());

            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            LOGGER.info("[KakaoService] Response :: kakao_acount = {}", kakao_account.toString());


            JsonObject properties = (JsonObject) element.getAsJsonObject().get("properties");
            LOGGER.info("[KakaoService] Response :: properties = {}", properties.toString());


            String id = element.getAsJsonObject().get("id").getAsString();
            LOGGER.info("[KakaoService] Response :: id = {}", id);


            String nickname = properties.get("nickname").getAsString();
            LOGGER.info("[KakaoService] Response :: nickname = {}", nickname);

            String email = "";

            boolean hasEmail = kakao_account.get("has_email").getAsBoolean();
            LOGGER.info("[KakaoService] Response :: has_email = {}", hasEmail);

            if (hasEmail) {
                email = kakao_account.get("email").getAsString();
                LOGGER.info("[KakaoService] Response :: email = {}", email);
            }

            boolean hasGender = kakao_account.get("has_gender").getAsBoolean();
            LOGGER.info("[KakaoService] Response :: has_gender = {}", hasGender);

            String gender = "";
            if (hasGender) {
                gender = kakao_account.get("gender").getAsString();
                LOGGER.info("[KakaoService] Response :: gender = {}", gender);
            }


            result.put("id", id);
            result.put("nickname", nickname);
            if (hasEmail) {
                result.put("email", email);
            }
            result.put("gender", gender);

            result.entrySet().forEach(elem -> {
                System.out.println(elem.getKey() + " : " + elem.getValue() + '\n');
            });

            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    public String getAgreementInfo(String access_token){
        String response_body = "";
        String host = "https://kapi.kakao.com/v2/user/scopes";
        try{
            URL url = new URL(host);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Authorization", "Bearer " + access_token);

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            while((line = br.readLine()) != null) {
                response_body += line;
            }

            int responseCode = urlConnection.getResponseCode();
            System.out.println("response code: " + responseCode);

            br.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response_body;
    }
}
