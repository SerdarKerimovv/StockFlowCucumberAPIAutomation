package utils;

import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class APIUtils {

    Response response;
    RequestSpecification request;


    public static String getToken() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", ConfigurationReader.getProperty("email"));
        requestBody.put("password", ConfigurationReader.getProperty("password"));

        String responseBody = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("apiBaseURL"))
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/api/auth/login")
                .getBody().asString();

        JSONObject jsonObject = new JSONObject(responseBody);
        return jsonObject.getString("token");
    }


}
