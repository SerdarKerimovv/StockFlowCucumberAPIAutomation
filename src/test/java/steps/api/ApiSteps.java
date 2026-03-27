package steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import utils.APIUtils;
import utils.ConfigurationReader;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiSteps extends APIUtils {
    RequestSpecification request;
    Response response;
    JSONObject requestBody = new JSONObject();
    String sku;

    @Given("base url")
    public void base_url() {
        request = RestAssured.given()
                .baseUri(
                        ConfigurationReader.getProperty("apiBaseURL"))
                .contentType(ContentType.JSON);
    }

    @Given("user has valid authorization")
    public void user_has_valid_authorization() {
        request = request.header("Authorization", "Bearer " + ConfigurationReader.getProperty("apiToken"));
        this.sku = sku;
    }

    @When("user hits POST {string}")
    public void user_hits_post(String endPoint) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", ConfigurationReader.getProperty("email"));
        requestBody.put("password", ConfigurationReader.getProperty("password"));
        response = request
                .body(requestBody.toString())
                .post(endPoint);
    }

    @When("user hits POST {string} to logout")
    public void user_hit_post_to_logout(String endPoint) {
        response = request
                .post(endPoint);
    }

    @Then("verify status code is {int}")
    public void verify_status_code_is(Integer statusCode) {
        int actualStatusCode = response.getStatusCode();
        assertEquals(statusCode, actualStatusCode);

        System.out.println("REsponse ==========:  " + response.asPrettyString());
    }


    @Given("product with sku {string} exists")
    public void product_with_sku_exists(String sku) {
        response = request
                .when()
                .get("/api/products/" + sku);

        response.then().statusCode(200);
    }

    @When("user sends GET request with valid product data {string}")
    public void user_sends_get_request_with_valid_product_data(String sku) {
        response = request
                .when()
                .get("/api/products/" + sku);
    }

    @Then("response status should be {int}")
    public void response_status_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @When("user hits GET {string} with query params")
    public void user_hits_get_with_query_params(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> queryParams = dataTable.asMap(String.class, String.class);

        response = request
                .queryParams(queryParams)
                .when()
                .get(endpoint);

        System.out.println("Response:\n" + response.getBody().asPrettyString());
    }

    @Then("the response {string} should be {int}")
    public void the_response_field_should_be(String fieldName, Integer expectedValue) {

        // Convert response body to String
        String responseBody = response.getBody().asString();

        // Parse JSON manually
        JSONObject jsonObject = new JSONObject(responseBody);

        // Extract actual value
        int actualValue = jsonObject.getInt(fieldName);

        // Assertion
        assertEquals(expectedValue.intValue(), actualValue);
    }

    @Then("the response data array should be empty")
    public void the_response_data_array_should_be_empty() {

        // Convert response to String
        String responseBody = response.getBody().asString();

        // Parse JSON
        JSONObject jsonObject = new JSONObject(responseBody);

        // Get "data" array
        JSONArray dataArray = jsonObject.getJSONArray("data");

        // Assert it's empty
        assertTrue(dataArray.isEmpty());
    }

}

