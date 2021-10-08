import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;
import static org.hamcrest.Matchers.equalTo;

public class ImageTest extends BaseTest {
    String imageDeleteHash;


    @Test
    public void uploadImagesFilePositiveTest() {
        given()
                .header("Authorization", token)
                .body(new File("src/test/resources/apple.jpg"))
                .when()
                .post("/image")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    public void uploadVideoFilePositiveTest() {
        given()
                .header("Authorization", token)
                .body(new File("src/test/resources/bench.mp4"))
                .when()
                .post("/image")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    public void updateImagesFilePositiveTest() {
        given()
                .header("Authorization", token)
                .basePath("/upload/")
                .when()
                .put()
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    public void uploadImagesFileNegativeTest() {
        given()
                .header("Authorization", token)
                .body(new File("src/test/resources/hhh.jpg"))
                .when()
                .post("/")
                .prettyPeek()
                .then()
                .statusCode(404);


    }
}