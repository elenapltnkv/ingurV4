import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.put;

public abstract class BaseTest {
    public static Map<String, String> map = new HashMap<String, String>();
    static Properties properties;
    static String host;
    static String username;
    static String token;


    @BeforeAll
    static void beforeAll() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/additional.properties"));
        host = properties.getProperty("host");
        username = properties.getProperty("username", "SansaStark");
        token = properties.getProperty("auth.token");
        map.put("title", "Red apple");
        map.put("description", "This is an apple");
        map.put("name", "This is an apple");
        RestAssured.basePath = "/upload";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = host;

        }
    }


