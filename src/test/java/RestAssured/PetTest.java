package RestAssured;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class PetTest extends HelperMethods {

    public RequestSpecification rspec;
    public RequestSpecBuilder build;

    public static Map<String, String> item = new HashMap<String, String>();

    @BeforeClass
    public void requestSpec() {

        build = new RequestSpecBuilder();
        build.setBaseUri("https://petstore.swagger.io/v2");
        build.setBasePath("/pet");

        rspec = build.build();

    }

    @Test(priority = 1)
    public void postPet() {

        Pet pet = new Pet();
        pet.setId(51l);
        pet.setName("Bad_Wolf");
        pet.setStatus("sold");

        HelperMethods.addingPet(rspec, pet);

        String body = HelperMethods.getThePets(rspec, "/51").andReturn().body().asString();

        Pet pet2 = new Gson().fromJson(body, Pet.class);

        assertThat(pet2.getId()).isEqualTo(51);
        assertThat(pet2.getName()).isEqualTo("Bad_Wolf");
        assertThat(pet2.getStatus()).isEqualTo("sold");

    }

    @Test(dependsOnMethods = {"postPet"})
    public void getPetById() {

        Response response = HelperMethods.getThePets(rspec, "/51");

        String body = response.getBody().asString();
        Pet pet = new Gson().fromJson(body, Pet.class);

        assertThat(pet.getName()).isEqualTo("Bad_Wolf");
        assertThat(pet.getStatus()).isEqualTo("sold");

    }

    @Test(dependsOnMethods = {"postPet"}) // drugi nacin za dobavljanje po id
    public void getPetById2() {

        Pet pet = HelperMethods.getThePets2(rspec, "/51");

        assertThat(pet.getName()).isEqualTo("Bad_Wolf");
        assertThat(pet.getStatus()).isEqualTo("sold");

    }

    @Test(priority = 2)
    public void deletePet() {

        String testData = "/51";

        HelperMethods.removePet(rspec, testData);
        String pet = HelperMethods.getThePets(rspec, testData).andReturn().body().asString();

        assertThat(pet).contains("Pet not found");

    }

    @Test(dependsOnMethods = {"postPet"})
    public void getPetsByStatus() {

        Response response = HelperMethods.getThePetsWithParam(rspec, "/findByStatus", "sold");
        String body = response.getBody().asString();
        Pet[] pet = new GsonBuilder().create().fromJson(body, Pet[].class);

        assertThat(pet).extracting("status").containsOnly("sold");

    }

    @Test
    public void someListToAssert() {

        List<Pet> somePets = new ArrayList<Pet>();

        Pet pet1 = new Pet();
        pet1.setId(100L);
        pet1.setName("White_Wolf");
        pet1.setStatus("available");
        somePets.add(pet1);

        Pet pet2 = new Pet();
        pet2.setId(101L);
        pet2.setName("Buddy");
        pet2.setStatus("pending");
        somePets.add(pet2);

        Pet pet3 = new Pet();
        pet3.setId(102L);
        pet3.setName("Slowson");
        pet3.setStatus("pending");
        somePets.add(pet3);

        Pet pet4 = new Pet();
        pet4.setId(103L);
        pet4.setName("Black_Wolf");
        pet4.setStatus("sold");
        somePets.add(pet4);

        assertThat(somePets).filteredOn("status", "available")
                            .containsOnly(pet1);

        assertThat(somePets).filteredOn("status", notIn("available", "sold"))
                            .containsOnly(pet2, pet3);

        assertThat(somePets).filteredOn("status", "pending")
                            .filteredOn("name", not("Slowson"))
                            .containsOnly(pet2);

        assertThat(somePets).extracting("status").contains("available", "pending", "sold");

        assertThat(somePets).extracting("name").contains("White_Wolf", "Buddy", "Slowson", "Black_Wolf")
                            .doesNotContain("Gray_Wolf");

        assertThat(somePets).extracting("name", "status")
                            .contains(tuple("White_Wolf", "available"),
                                      tuple("Buddy", "pending"),
                                      tuple("Slowson", "pending"),
                                      tuple("Black_Wolf", "sold"));

        assertThat(pet2).isEqualToIgnoringGivenFields(pet3, "name", "id");

    }



}
