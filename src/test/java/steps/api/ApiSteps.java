package steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import utils.APIUtils;
import utils.ConfigurationReader;

public class ApiSteps extends APIUtils {
    RequestSpecification request;
    Response response;
    JSONObject requestBody = new JSONObject();

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
        Assertions.assertEquals(statusCode, actualStatusCode);

        System.out.println("REsponse ==========:  " + response.asPrettyString());
    }


}
