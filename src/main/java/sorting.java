import com.aventstack.extentreports.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Arrays;
import java.util.List;

public class sorting {
    static String sort;
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        try {
            driver.get("https://sakshingp.github.io/assignment/home.html");

            Thread.sleep(5000);
            driver.findElement(By.xpath("//th[@id='amount']")).click();
            Thread.sleep(5000);
            List<WebElement> names = driver.findElements(By.xpath("//table[@id='transactionsTable']/tbody/tr/td[5]"));
            String[] a = new String[names.size()];
            System.out.println("Befor sorting ");
            for (int i = 0; i < names.size(); i++) {
                a[i] = names.get(i).getText().trim();
                System.out.println(a[i]);
            }
            double[] arr = new double[]{+1250.00, +952.23, -320.00, +17.99, -244.00, +340.00};
            double temp = 0;

            //Displaying elements of original array
            // System.out.println("Elements of original array: ");
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i] + " USD");
            }

            //Sort the array in ascending order
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i] > arr[j]) {
                        temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }

            System.out.println();

            //Displaying elements of array after sorting
            System.out.println("Elements of array sorted in ascending order: ");
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i] + " USD");
            }
        }
        catch (Exception e){}
    }
}