package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        
        //testi1();
        //testi2();
        //testi3();
        //testi4();
        testi5();
        
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
    
    private static void testi1(){
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(1);
        element.submit();

        sleep(1);
         
        
        driver.quit();
    }
    
    private static void testi2() {
        
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("login"));
        
        sleep(1);
        element.submit();

        sleep(1);
         
        
        driver.quit();

    }
    
    private static void testi3() {
        
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("ekkap");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(1);
        element.submit();

        sleep(1);
         
        
        driver.quit();

    }
    
    private static void testi4() {
        
        Random r = new Random();
        WebDriver driver = new ChromeDriver();


        driver.get("http://localhost:4567");
        
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("arto"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("ekep");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("ekep");
        element = driver.findElement(By.name("signup"));
        
        sleep(1);
        element.submit();

        sleep(1);
         
        
        driver.quit();

    }
    
    private static void testi5() {
        
        Random r = new Random();
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("arto"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("ekep");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("ekep");
        element = driver.findElement(By.name("signup"));
        
        sleep(1);
        element.submit();

        sleep(1);
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(1);
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        sleep(1);
         
        
        driver.quit();

    }
}
