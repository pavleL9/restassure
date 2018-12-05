package RestAssured;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class HelperMethods {

    public static void checkStatusIs200(Response res) {
        assertEquals(res.getStatusCode(), 200);
    }

    public static Response getThePets(RequestSpecification rspec, String searchBy) {
        return given().spec(rspec).when().get(searchBy);
    }

    public static void addingPet(RequestSpecification rspec, Pet pet) {
        given().spec(rspec).contentType("application/json").body(pet).when().post();
    }

    public static void removePet(RequestSpecification rspec, String id) {
        given().spec(rspec).delete(id);
    }

    public static Response getThePetsWithParam(RequestSpecification rspec, String searchBy, String status) {
        return given().spec(rspec).param("status", status).when().get(searchBy);
    }

    public static Pet getThePets2(RequestSpecification rspec, String searchBy) {
        return given().spec(rspec).when().get(searchBy).then().extract().as(Pet.class);
    }
}
