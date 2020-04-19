import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.Payload;

public class SumValidation {

    @Test
    public void sumOfCourses()
    {
        JsonPath js =new JsonPath(Payload.coursePrice());
        int count=js.getInt("courses.size()");
        int price=0;
        int copy=0;
        int sum=0;
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");


        for(int i=0; i<count-1; i++)
        {
            price=js.getInt("courses[" + i + "].price");
            copy=js.get("courses[" + i + "].copies");

            sum=sum+price*copy;

        }

        Assert.assertEquals(purchaseAmount,sum);
    }
}
