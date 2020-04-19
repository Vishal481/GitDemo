import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class StaticJson {


    @Test

    public void addBook() throws IOException



    {



        RestAssured.baseURI="http://fakerestapi.azurewebsites.net/";

        String resp=given().

                header("Content-Type","application/json").

                body(GenerateStringFromResource("C:\\Users\\mysql\\news.json")).

                when().

                post("api/Activities").

                then().assertThat().statusCode(200).

                extract().response().asString();

        JsonPath js= new JsonPath(resp);

        int id=js.get("ID");

        System.out.println(id);



        //deleteBOok

    }

    public static String GenerateStringFromResource(String path) throws IOException {



        return new String(Files.readAllBytes(Paths.get(path)));



    }

}



