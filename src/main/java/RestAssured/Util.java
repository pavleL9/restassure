package RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Util {

    public static String path;

    public static void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    public static void setBasePath(String basePathTerm) {
        RestAssured.basePath = basePathTerm;
    }

    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }

    public static void resetBasePath() {
        RestAssured.basePath = null;
    }

    public static void setContentType(ContentType Type) {
        given().contentType(Type);
    }
}
