import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import utility.Payload;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//import sun.jvm.hotspot.utilities.Assert;

public class Test1 {


    public static void main(String[]args)

    {

        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\kvish\\OneDrive\\Desktop\\all cv\\chromedriver.exe");

        //WebDriver driver=new ChromeDriver();
        //driver.get("https://www.amazon.com");

        RestAssured.baseURI="https://rahulshettyacademy.com";

       String response= given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
        .body(Payload.AddPlace()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().asString();


       JsonPath js= new JsonPath(response);
       String place_id=js.getString("place_id");
       System.out.println("Response"+response);

       System.out.println("The place id is:"+place_id);

       //Update Place

        String newAddress="70 Summer walk, USA";

        given().log().all().queryParam("key","qaclick").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+place_id+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n" +
                        "\n")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));


        //Get place

        String getPlaceResponse=given().log().all().queryParam("key","qaclick123").
                queryParam("place_id",place_id)
                .when().get("/maps/api/place/get/json")
                .then().log().all().statusCode(200).extract().response().asString();

        JsonPath j= new JsonPath(getPlaceResponse);
        String org=j.getString("address");

        System.out.println("get Address"+org);



    }


}
