import io.restassured.path.json.JsonPath;
import utility.Payload;

public class CJsonParson {

    public static void main(String[] r) {

        JsonPath js = new JsonPath(Payload.coursePrice());

        int sumOfCourse = 0;
        //Print the number of courses
        int count = js.getInt("courses.size()");

        System.out.println(count);

//Print purchase amount

        int totalAmount = js.getInt("dashboard.purchaseAmount");

        System.out.println("The total amount is" + totalAmount);

        //Print title of the first course

        String fc_title = js.get("courses[0].title");

        System.out.println(fc_title);

//Print all course title and their respective Prices

        String title;


        for (int i = 0; i < count; i++) {
            title = js.get("courses[" + i + "].title");
            int ip = js.getInt("courses[" + i + "].price");
            System.out.println("Titles are:" + title + "and " + "prices are: " + ip);

            if (title.equalsIgnoreCase("RPA")) {

                int copy = js.getInt("courses[" + i + "].copies");
                System.out.println("Price of RPA is: " + copy);
                break;
            }
//Print no. of copies sold by RPA course

        }  //Verify if sum of all Course Prices matches with purchase Amount.

        for (int i = 0; i < count - 1; i++) {
            int ip = js.getInt("courses[" + i + "].price");
            int copy = js.getInt("courses[" + i + "].copies");

            sumOfCourse = sumOfCourse + ip * copy;
        }
        if (totalAmount == sumOfCourse)
            System.out.println("Validation done");
        else
            System.out.println("not matched");


        System.out.println(sumOfCourse);
    }
}

