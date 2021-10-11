import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.matcher.DetailedCookieMatcher;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.*;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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
                .body(new File("src/test/resources/apple.jpg"))
                .when()
                .post("/3/image")
                .prettyPeek()
                .then();

    }

    @Order(2)
    @Test
    public void updateImagesFilePositiveTest() {
        given()
                .contentType(ContentType.MULTIPART)
                .multiPart("title", "red apple")
                .when()
                .post("/3/image/j3Tj3emBLreTB9A")
                .prettyPeek()
                .then();
    }
    @Order(3)
    @Test
    public void updateImagesFileNegativeTest () {
        given()
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
                .expect()
                .statusCode(404)
                .when()
                .post(host + "/3/image/j3Tj3emBLreTB9A" + "/favorite")
                .prettyPeek();
    }
}