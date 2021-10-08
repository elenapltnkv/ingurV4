import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AccountBase extends BaseTest{
    @Test
    public void getAccountInfoTest() {
        given()
                .baseUri("https://api.imgur.com/3")
                .basePath("/account/username")
                .when()
                .then()
                .statusCode(200);
    }
    @Test
    public void getAccountPositiveTest(){
        given()
                .header("Authorization", token)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("account/{username}", username)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
    @Test
    public void getAccountInfoBaseTest() {
        given()
                .header("Authorization",token)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("account/" + username)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

}
