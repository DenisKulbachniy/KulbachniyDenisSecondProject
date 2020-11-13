package task1;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


public class MailTest {
    WebDriver driver = new ChromeDriver();


    @Test
    public void test() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://meta.ua/");
        driver.findElement(By.xpath("/html/body/div[1]/section/div[1]/span/a[1]/label")).click(); // open mail menu to log in
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/input[1]")).sendKeys("Denismerefa@meta.ua"); // enter login
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[3]/input")).sendKeys("123456789denis"); //enter password
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/button")).click(); //click to button log in
        WebElement sendMessage = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable
                (By.xpath("//*[@id=\"id_send_email\"]")));  // click to button send message
        sendMessage.click();

        driver.findElement(By.xpath("//*[@id=\"send_to\"]")).sendKeys("denismerefa@meta.ua"); // enter @mail
        driver.findElement(By.xpath("//*[@id=\"subject\"]")).sendKeys("Hello"); //enter topic
        driver.findElement(By.xpath("//*[@id=\"body\"]")).sendKeys("Hello, hello im here"); //enter body message
        driver.findElement(By.xpath("//*[@id=\"b3_cnt\"]/div[2]/div[1]/div[1]/input[2]")).click();        //click to send
        driver.navigate().refresh();   //refresh
        driver.findElement(By.xpath("//*[@id=\"messlist\"]/tbody/tr[2]/td[5]/a")).click();

        WebElement checkTopic = driver.findElement(By.xpath("//*[@id=\"add_link\"]/div[1]/h1"));
        WebElement checkMailSender = driver.findElement(By.xpath("//*[@id=\"from_line\"]/table/tbody/tr[1]/td[2]/span"));
        Assert.assertEquals(checkTopic.getText(),"Hello");
        Assert.assertEquals(checkMailSender.getText(),"\"Kulbachniy Denis\" <denismerefa@meta.ua>");

        driver.quit();





    }
}
