import org.testng.annotations.Test;

public class FirstTestNg {

        @Test(priority=2)
         void setUp()
        {
            System.out.println("Opening Browser");
        }

        @Test(priority=1)
        void Login()
        {
            System.out.println("Login Successfully");

        }

        @Test(priority=3)
        void close()
        {
            System.out.println("Closing browser");

        }

    }

