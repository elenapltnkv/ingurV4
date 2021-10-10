import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImageTest extends BaseTest {
    String imageDeleteHash;

    @Order(1)
    @Test
    public void uploadImagesFilePositiveTest() {
        given()
                .header("Authorization", token)
                .body(new File("src/test/resources/apple.jpg"))
                .when()
                .post("/3/image")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Order(2)
    @Test
    public void updateImagesFilePositiveTest() {
        given()
                .header("Authorization", token)
                .contentType(ContentType.MULTIPART)
                .multiPart("title", "red apple")
                .when()
                .post("/3/image/j3Tj3emBLreTB9A")
                .prettyPeek()
                .then()
                .statusCode(200);
    }
    @Order(3)
    @Test
    public void updateImagesFileNegativeTest () {
        given()
                .header("Authorization", token)
                .contentType(ContentType.MULTIPART)
                .multiPart("image","yyyy")
                .when()
                .post("/3/image/yyyy")
                .prettyPeek()
                .then()
                .statusCode(404);
    }
    @Order(4)
    @Test
    public void favoriteImageTest() {
        given()
                .header("Authorization", token)
                .expect()
                .statusCode(200)
                .when()
                .post(host + "/3/image/j3Tj3emBLreTB9A" + "/favorite")
                .prettyPeek();
    }
    @Order(5)
    @Test
    public void shareCommunityPositiveTest() {
        given()
                .header("Authorization", token)
                .contentType(ContentType.MULTIPART)
                .multiPart("title", "Get this apple")
                .when()
                .post(host + "/3/image/j3Tj3emBLreTB9A")
                .prettyPeek()
                .then()
                .statusCode(200);
    }
    @Order(6)
    @Test
    public void shareCommunityNegativeTest() {
        given()
                .header("Authorization", token)
                .contentType(ContentType.MULTIPART)
                .multiPart("title", "     ")
                .when()
                .post(host + "/3/image/j3Tj3emBLreTB9A")
                .prettyPeek()
                .then()
                .statusCode(200);
    }
    //@Order(7)
    //@Test
    //public void deleteImageTest(){
    //given()
    //.header("Authorization", token)
    //.expect()
    //.statusCode(200)
    //.body("data", is(true))
    //.when()
    //.delete(host+"/3/image/j3Tj3emBLreTB9A");

    // }
    @Order(8)
    @Test
    public void updateImagesAfterDelPositiveTest() {
        given()
                .header("Authorization", token)
                .contentType(ContentType.MULTIPART)
                .multiPart("title", "red apple")
                .when()
                .post("/3/image/j3Tj3emBLreTB9A")
                .prettyPeek()
                .then()
                .statusCode(404);

    }

    @Order(9)
    @Test
    public void favoriteImageAfterDelTest() {
        given()
                .header("Authorization", token)
                .expect()
                .statusCode(404)
                .when()
                .post(host + "/3/image/j3Tj3emBLreTB9A" + "/favorite")
                .prettyPeek();
    }
}