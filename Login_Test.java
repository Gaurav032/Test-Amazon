package com.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.seleniumhq.jetty9.server.handler.ThreadLimitHandler;

import java.util.concurrent.TimeUnit;

public class Login_Test {


    //Login Credentials

    public static String Email_id="saitgaurav032@gmail.com";
    public static String Password="P@ssw0rd";

    //Site to be visited
    String URL="https://www.amazon.com/";

    WebDriver driver;


    //Invoke Driver


    public void Invokedriver(){

        try {
            System.setProperty("webdriver.chrome.driver", "/Users/gaurav.kumar17/Documents/Amazon-Test-Code/chromedriver");
            driver= new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);


          Login_Amazon();
          Select_Item();
          Select_and_Addto_Cart();
          Search_and_addto_card();
          SelectCartFromHome();
            DecrQty();
            ProceedtoCheckout();
            Logout_Amazon();
            driver.close();
            //driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //Login to Amazon

    public void Login_Amazon() throws InterruptedException{

        driver.get(URL);
        System.out.println("Amazon page loaded");
        SessionId sessionid = ((RemoteWebDriver) driver).getSessionId();

        System.out.println(sessionid);


        // driver.findElement(By.id("nav-link-accountList")).isDisplayed();


        if(driver.findElement(By.id("nav-link-accountList")).isDisplayed()==true)
        {
            driver.findElement(By.id("nav-link-accountList")).click();
            driver.findElement(By.id("ap_email")).sendKeys(Email_id);
            //wait(1000);
            //Thread.sleep(2000);
            driver.findElement(By.id("ap_password")).sendKeys(Password);
            //
            // wait(1000);
           // Thread.sleep(2000);
            driver.findElement(By.id("signInSubmit")).click();

        }

        else {
                driver.findElement(By.id("nav-link-accountList")).click();
                driver.findElement(By.id("ap_email")).sendKeys(Email_id);
                driver.findElement(By.id("ap_password")).sendKeys(Password);
                driver.findElement(By.id("signInSubmit")).click();
        }
    }



    public void Select_Item(){

        try {
            Actions action1= new Actions(driver);
            WebElement element1=driver.findElement(By.xpath("//*[@id=\"nav-link-shopall\"]"));
            action1.moveToElement(element1).build().perform();

            driver.findElement(By.xpath("//*[@id=\"nav-link-shopall\"]"));
            // Thread.sleep(3000);
            driver.findElement(By.linkText("Electronics")).click();
            driver.findElement(By.partialLinkText("HEADPHONES")).click();
            //driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[1]/div/div[1]/div[4]/div/div[1]/div/a/div/span")).click();

            //driver.findElement(By.id("nav-link-shopall")).click();
            //driver.findElement(By.linkText("Electronics")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



        public void Select_and_Addto_Cart() throws InterruptedException{
        driver.findElement(By.className("s-image")).click();
        WebElement identifier = driver.findElement(By.id("quantity"));
        Select select = new Select(identifier);
        select.selectByValue("1");
        driver.findElement(By.id("add-to-cart-button")).click();
        //wait(1000);
        //Thread.sleep(2000);
        driver.findElement(By.id("hlb-view-cart-announce")).click();
        //wait();
        //Thread.sleep(2000);
        //driver.getCurrentUrl();
        //Write code to navigate


       // driver.navigate().back();

            }

            public void Search_and_addto_card() throws InterruptedException{

                driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Macbook pro");
                driver.findElement(By.className("nav-input")).click();
                JavascriptExecutor jse = (JavascriptExecutor)driver;
                jse.executeScript("window.scrollBy(0,250)");
                driver.findElement(By.className("s-image")).click();
                WebElement identifier = driver.findElement(By.id("quantity"));
                Select select = new Select(identifier);
                select.selectByValue("2");
               // wait();
                //Thread.sleep(1000);
                driver.findElement(By.id("add-to-cart-button")).click();
                driver.navigate().to("https://www.amazon.com/");

            }



            public void SelectCartFromHome(){

                    //driver.findElement(By.id("nav-cart")).isDisplayed();
                    driver.findElement(By.id("nav-cart")).click();
                    driver.findElement(By.xpath("//input[contains(@aria-label,'Delete Apple EarPods with Lightning Connector - White')]")).click();

                }


                public void DecrQty(){


                    driver.findElement(By.xpath("//span[@class='a-button-text a-declarative'][contains(.,'2')]")).click();
                    WebElement identifier = driver.findElement(By.name("quantity"));
                    Select select = new Select(identifier);
                    select.selectByValue("1");

                    System.out.println("Quantity update to 1"+identifier);

                }

                public void ProceedtoCheckout(){

                    driver.findElement(By.xpath("//input[@name='proceedToCheckout']")).click();

                }

    //Logout from Amazon

      public void Logout_Amazon() throws InterruptedException{

          try {
              //driver.findElement(By.id("nav-link-accountList"))


              Actions action= new Actions(driver);
              WebElement element=driver.findElement(By.xpath(".//*[@id='nav-link-accountList']"));
              action.moveToElement(element).build().perform();

              driver.findElement(By.xpath(".//*[@id='nav-al-your-account']"));
              Thread.sleep(3000);
              driver.findElement(By.id("nav-item-signout")).click();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }


      }



    public static void main(String args[]){
        Login_Test obj=new Login_Test();
        obj.Invokedriver();



    }
}
