import io.restassured.path.json.JsonPath;
import utility.Payload;

public class ComplexJsonParse {

    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.coursePrice());
        int count = js.getInt("courses.size()");
        int copy=0;
        int p=0;
        System.out.println(count);

        int purchaseAmount = js.getInt("dashboard.purchaseAmount");

        System.out.println(purchaseAmount);

//Print title of first Element

        String firstTitle = js.getString("courses[0].title");

        System.out.println(firstTitle);
int t=0;
        for (int i = 0; i < count; i++) {
            String allTitle = js.getString("courses[" + i + "].title");
             p = js.getInt("courses[" + i + "].price");
            System.out.println(allTitle);
            System.out.println(p);
        }
//Print no. of copies sold by RPA

        for (int i = 0; i < count; i++) {
            String temp = js.getString("courses[" + i + "].title");
            copy=js.get("courses[" + i + "].copies");
            if (temp.equalsIgnoreCase("RPA")) {
                int c = js.get("courses[" + i + "].copies");
                System.out.println(c);
                break;
            }
        }

        //verify purchase amount equals to sum of all course price

for(int i=0; i<count; i++)
{
    int c=js.get("courses[" + i + "].copies");
int prid=js.getInt("courses[" + i + "].price");

    t=t+c*prid;
}

if(purchaseAmount==t)
    System.out.println("The value is matched"+t);
    }
}
