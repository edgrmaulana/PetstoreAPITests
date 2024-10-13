package steps;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data.PetStoreData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class PetStoreSteps {

    PetStoreData petStoreData = new PetStoreData();
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode jsonBody = objectMapper.createObjectNode();

    @Given("Set the API Endpoint to {string}")
    public void setTheAPIEndpoint(String value) {
        petStoreData.setEndpointUrl(value);
    }

    @And("Set the username to {string}")
    public void setTheUsernameTo(String value) {
        value = "randomUsername".equalsIgnoreCase(value) ? UUID.randomUUID().toString().substring(0, 18) : value;
        petStoreData.setUsername(value);
    }

    @And("Set the firstName to {string}")
    public void setTheFirstNameTo(String value) {
        petStoreData.setFirstName(value);
    }

    @And("Set the lastName to {string}")
    public void setTheLastNameTo(String value) {
        petStoreData.setLastName(value);
    }

    @And("Set the email to {string}")
    public void setTheEmailTo(String value) {
        value = "randomEmail".equalsIgnoreCase(value) ? UUID.randomUUID().toString().substring(0, 18) + "@gmail.com" : value;

        petStoreData.setEmail(value);
    }

    @And("Set the password to {string}")
    public void setThePasswordTo(String value) {
        petStoreData.setPassword(value);
    }

    @And("Set the phone to {string}")
    public void setThePhoneTo(String value) {
        petStoreData.setPhone(value);
    }

    @Then("Hit the API Endpoint to create the user")
    public void hitTheAPIEndpointToCreateTheUser() {
        jsonBody.put("username", petStoreData.getUsername());
        jsonBody.put("firstName", petStoreData.getFirstName());
        jsonBody.put("lastName", petStoreData.getLastName());
        jsonBody.put("email", petStoreData.getEmail());
        jsonBody.put("password", petStoreData.getPassword());
        jsonBody.put("phone", petStoreData.getPhone());

        System.out.println(jsonBody);

        io.restassured.response.Response response = given()
                .baseUri(petStoreData.getEndpointUrl())
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post("/user");

        petStoreData.setWebResponse(response.getBody().prettyPrint());
    }

    @Then("Verify the create user API return {int} code")
    public void verifyTheCreateUserAPIReturnCode(int value) {
        assertThat("Wrong response code", petStoreData.getWebResponse(), containsString("\"code\": " + value));
    }

    @Then("Hit the API Endpoint to get the user")
    public void hitTheAPIEndpointToGetTheUser() {
        io.restassured.response.Response response = given()
                .baseUri(petStoreData.getEndpointUrl())
                .contentType(ContentType.JSON)
                .get("/user/" + petStoreData.getUsername());

        petStoreData.setWebResponse(response.getBody().prettyPrint());
    }

    @Then("Verify the username is correct")
    public void verifyTheUsernameIsCorrect() {
        assertThat("Wrong username", petStoreData.getWebResponse(), containsString(petStoreData.getUsername()));
    }

    @Then("Verify the firstName is correct")
    public void verifyTheFirstNameIsCorrect() {
        assertThat("Wrong First Name", petStoreData.getWebResponse(), containsString(petStoreData.getFirstName()));
    }

    @Then("Verify the lastName is correct")
    public void verifyTheLastNameIsCorrect() {
        assertThat("Wrong First Name", petStoreData.getWebResponse(), containsString(petStoreData.getLastName()));
    }

    @Then("Verify the email is correct")
    public void verifyTheEmailIsCorrect() {
        assertThat("Wrong First Name", petStoreData.getWebResponse(), containsString(petStoreData.getEmail()));
    }

    @Then("Verify the password is correct")
    public void verifyThePasswordIsCorrect() {
        assertThat("Wrong Password", petStoreData.getWebResponse(), containsString(petStoreData.getPassword()));
    }

    @Then("Verify the phone is correct")
    public void verifyThePhoneIsCorrect() {
        assertThat("Wrong Phone Number", petStoreData.getWebResponse(), containsString(petStoreData.getPhone()));
    }
}
