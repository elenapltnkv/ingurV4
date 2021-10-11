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


}
