import Pojo.API;
import Pojo.GetCourse;
import Pojo.WebAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthTest2 {


    public static void main(String[]args) throws InterruptedException {

        String[] courseTitles={"Selenium Webdriver Java","Cypress","Protractor"};

       /* String element="https://accounts." +
                "google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifydjss";

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kvish\\OneDrive\\Desktop\\all cv\\chromedriver.exe");
        WebDriver driver= new ChromeDriver();

        driver.get(element);

        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("kvishall481@gmail.com");
driver.findElement(By.xpath("//span[@contains(text(),'Next')]"));
        driver.findElement(By.name("password")).sendKeys("Flipkart@481");
        driver.findElement(By.xpath("//span[@contains(text(),'Next')]"));

        Thread.sleep(4000);

String url=driver.getCurrentUrl();*/

//Formatting URL String to retrieve code

String url="https://rahulshettyacademy.com/getCourse.php?state=verifydjss&code=4%2FyQHIJ-8F7Zo1xhApsvGAOy3UUawXpSX7Kzo5HiNEPyiad4dGhiIgzsZZY-yo9gBq7Rut5ITOa3A6XSQD5Uwpm94&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";

        String partialCode=url.split("code=")[1];
String completeCode=partialCode.split("&scope")[0];

System.out.println("The code is"+completeCode);

     String access_Token =given().urlEncodingEnabled(false).log().all().queryParam("code",completeCode).queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
.queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
              .queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
              .queryParam("grant_type","authorization_code")
            .when().post("https://www.googleapis.com/oauth2/v4/token")
            .asString();

     JsonPath js= new JsonPath(access_Token);
     String accessToken=js.getString("access_token");

        GetCourse response=given().queryParam("access_token",accessToken )
                .expect().defaultParser(Parser.JSON).when()
                .get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

        System.out.println(response.getLinkedIn());

        System.out.println(response.getExpertise());
        response.getCourses().getApi().get(1).getCourseTitle();

        ArrayList<String> l= new ArrayList<String>();

       List<API> apiCourses=response.getCourses().getApi();

        //Iterator itr=response.getCourses().getApi().iterator();

     List<WebAutomation> webAutomation= response.getCourses().getWebAutomation();

        for(int i=0; i<apiCourses.size();i++) {
            if (apiCourses.get(i).getCourseTitle().
                    equalsIgnoreCase("SoapUI Webservices api")) {
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

        for(int i=0; i<webAutomation.size();i++)
        {

                l.add(webAutomation.get(i).getCourseTitle());


        }

        List<String> expected= Arrays.asList(courseTitles);

        Assert.assertTrue(expected.equals(l));
    }
}
