import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;

public class OAuthTest {

    public static void main(String[]args)

    {


        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kvish\\OneDrive\\Desktop\\all cv\\chromedriver.exe");
WebDriver driver= new ChromeDriver();
driver.get("");


        String acessTokenResponse=given().queryParam("code","")
                .queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type","authorization_code").
                when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath js = new JsonPath(acessTokenResponse);

        String accessToken=js.getString("access_token");

        System.out.println(accessToken);





        String  response = given().queryParam("access_token", accessToken).
            when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();

       System.out.println(response);

       System.out.println("Modification done to the code");


    }

}
