package com.quix.quix.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@Repository
public class UserRepository {

    private final String supabaseUrl;
    private final String supabaseServiceKey;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public UserRepository(
            @Value("${supabase.url}") String supabaseUrl,
            @Value("${supabase.service-role-key}") String supabaseServiceKey) {
        this.supabaseUrl = supabaseUrl;
        this.supabaseServiceKey = supabaseServiceKey;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Prüft, ob ein User in Supabase existiert (über Admin API).
     */
    public boolean existsById(UUID userId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(supabaseUrl + "/auth/v1/admin/users/" + userId))
                    .header("apikey", supabaseServiceKey)
                    .header("Authorization", "Bearer " + supabaseServiceKey)
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // ✅ Status 200 = User existiert
            if (response.statusCode() == 200) {
                return true;
            }

            // ❌ Status 404 = User nicht gefunden
            if (response.statusCode() == 404) {
                return false;
            }

            System.err.println("Unexpected response: " + response.statusCode() + " " + response.body());
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Holt User-Infos aus Supabase (z. B. Email, Created At, etc.)
     */
    public User getUserInfoById(UUID userId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(supabaseUrl + "/auth/v1/admin/users/" + userId))
                    .header("apikey", supabaseServiceKey)
                    .header("Authorization", "Bearer " + supabaseServiceKey)
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) return null;

            JsonNode node = objectMapper.readTree(response.body());

            User user = new User();
            user.setId(UUID.fromString(node.get("id").asText()));
            user.setEmail(node.has("email") ? node.get("email").asText() : null);
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserDto registerUser(UserDto user) {

        ObjectMapper mapper = new ObjectMapper();
        try {
        String json = mapper.writeValueAsString(user);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(supabaseUrl + "/auth/v1/signup/"))
                    .header("apikey", supabaseServiceKey)
                    .header("Authorization", "Bearer " + supabaseServiceKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode node = objectMapper.readTree(response.body());

            UserDataDto userDataDto = new UserDataDto();
            userDataDto.setCreatedAt(node.get("user").get("created_at").asText());
            userDataDto.setDisplayName(node.get("user").get("user_metadata").get("display_name").asText());

            SecDto secDto = new SecDto();
            secDto.setJwtToken(node.get("access_token").asText());
            secDto.setRefreshToken(node.get("refresh_token").asText());
            UserDto userDto = new UserDto();
            userDto.setId(UUID.fromString(node.get("user").get("id").asText()));
            userDto.setEmail(node.get("user").get("email").asText());
            userDto.setData(userDataDto);
            userDto.setSec(secDto);
            return userDto;

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not map to Json: " + e);
        } catch (Exception e) {
            return  null;
        }


    }
}
