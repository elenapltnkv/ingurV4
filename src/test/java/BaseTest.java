import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.put;
import static io.restassured.RestAssured.responseSpecification;

public abstract class BaseTest {
    public static Map<String, String> map = new HashMap<String, String>();
    static Properties properties;
    static String host;
    static String username;
    static String token;
    static String title;
    static String description;
    static String name;
    static ResponseSpecification positiveResponseSpecification;
    static RequestSpecification requestSpecification;

    @BeforeAll
    static void beforeAll() throws IOException {
        //requestSpecification = new RequestSpecBuilder()
        //        .addHeader("Authorization", token)
         //       .build();

        positiveResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                //.expectStatusCode(404)
                .build();



        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/additional.properties"));
        host = properties.getProperty("host");
        username = properties.getProperty("username", "SansaStark");
        token = properties.getProperty("auth.token");
        title = properties.getProperty("Green apple");
        name = properties.getProperty("An apple");
        description = properties.getProperty("This is an apple");
//        map.put("title", "Red apple");
//        map.put("description", "This is an apple");
//        map.put("name", "This is an apple");
        //RestAssured.basePath = "/upload";



        RestAssured.baseURI = host;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = positiveResponseSpecification;
        RestAssured.requestSpecification = requestSpecification;


        }

    }


