package Example;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;
import javafx.scene.input.DataFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WebExample1
{
    static protected WebDriver driver;
    String expected = "My registration Successfully";
    public void waitForClickable(By by,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }


    public void waitForVisibility(By by,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //wait for element present
    public void waitForElementsPresent(By by,int time)
    {  WebDriverWait wait = new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    @After
    public void closeBrowser()
    {driver.quit();
    }
    @Before
    public void openBrowser()
    {
        String expected = "My registration Successfully";
        System.setProperty("webdriver.chrome.driver", "src\\test\\java\\BrowserDrivers\\chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //clear text input box
    public void clearText(By by)
    {
        driver.findElement(by).clear();
    }


   // select by visible text
    public void selectByVisibleText(By by,String text)
    { Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }
    //
    public void selectByIndex(By by,int number)
    {Select select = new Select(driver.findElement(by));
    select.selectByIndex(number);
    }

    public void selectByValue(By by,String text)
    { Select select = new Select(driver.findElement(by));
        select.selectByValue(text);
    }


    public String timeStamp()
    {
        DateFormat dateFormat = new SimpleDateFormat("ddmmyyhhmmss");
        Date date=new Date();
        return dateFormat.format(date);
    }
    public void clickOnElement(By by)
    {
        driver.findElement(by).click();
    }

    public void clearAndEnterText(By by, String text)
    {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

    public void EnterText(By by,String text)
    {
        driver.findElement(by).sendKeys(text);

    }
    public String getTextElement(By by)
    {
        return driver.findElement(by).getText();

    }
    @Test
    public void userShouldAbleToRegisterSuccessfully()
    {

        driver.get("https://demo.nopcommerce.com/");
       clickOnElement(By.linkText("Register"));

        waitForClickable(By.id("register-button"),20);


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("FirstName")));
        //entering first name
        EnterText(By.id("FirstName"),"Hetal");

        //entering last name
        EnterText(By.id("LastName"),"Dham");

        //selecting date of birthday
        selectByValue(By.xpath("//select[@name=\"DateOfBirthDay\"]"),"23");

        //selecting month of birthday
        selectByIndex(By.xpath("//select[@name='DateOfBirthMonth']"),10);

        //selecting year of birthday
        selectByVisibleText(By.xpath("//select[@name='DateOfBirthYear']"),"1979");

        //entering email
        EnterText(By.id("Email"),"hetaldhameliya+"+timeStamp()+"@gmail.com");

        //entering password
        EnterText(By.id("Password"),"venisa2848");

        //entering conformpassword
        EnterText(By.id("ConfirmPassword"),"venisa2848");

        //click on register
        clickOnElement(By.id("register-button"));

        String actual = getTextElement(By.className("result"));


        //veryfing  userShouldAbleToRegisterSuccessfully
        Assert.assertEquals("Failed",expected,actual);



    }




}

