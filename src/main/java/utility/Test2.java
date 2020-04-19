package utility;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Test2 {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String newAddress="70 Summer walk, USA";


        String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().
                statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response()
                .asString();

        System.out.println("The response body is: "+response);

        JsonPath js=new JsonPath(response);
        String place_id=js.getString("place_id");

        System.out.println("the place id is: "+place_id);


        //Add Place --Update Place with new Address--Get Place to validate if new Address is present
        //in new Address.

        //Update Place with new Address

        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
           .body("{\n" +
                "\"place_id\":\""+place_id+"\",\n" +
                "\"address\":\""+newAddress+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}\n" +
                "\n")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));

        //get place

        String getPlaceResponse=given().log().all().queryParam("key","qaclick123").
                queryParam("place_id",place_id)
                .when().get("/maps/api/place/get/json")
                .then().log().all().statusCode(200).extract().response().asString();

        JsonPath j= new JsonPath(getPlaceResponse);
       String actualAddress= j.getString("address");
        System.out.println("The updated address is: "+actualAddress);

    }
}