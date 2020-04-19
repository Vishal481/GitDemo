import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.Payload;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider="Profession")
    public void addBook(String name, String job)
    {
        RestAssured.baseURI="https://reqres.in";
         String resp=given().header("Content-Type", "application/json").
                 body(Payload.addLibrary(name,job)).
                         when().post("/api/users")
                 .then().assertThat().statusCode(201).extract().response().asString();

        JsonPath js= new JsonPath(resp);
        String id=js.get("id");
        System.out.println(id);
    }

    @DataProvider(name="Profession")
    public Object[][] getData()
    {
        //multidimensional array=collection of arrays

        return new Object[][] {{"kaushal","electrical"},{"vishal","electronics"},{"messi","footballer"}};
    }
}
