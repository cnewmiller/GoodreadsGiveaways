package giveAways;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GiveawayEntryScript  {
    public static void main(String[] args) throws InterruptedException {

        
        String email=null;
        String password = null;
        int pages=10;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Please enter your Goodreads login email address:");
			email = console.readLine();
			System.out.println("Please enter your Goodreads login password \n(Change it before using this script? It'll display on the screen when you type it):");
			password = console.readLine();
			System.out.println("How many pages of giveaways do you want to enter? Enter a numeric value:");
			pages = Integer.parseInt(console.readLine());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
        
        
    	System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.goodreads.com/user/sign_in?returnurl=%2Fgiveaway");
        WebElement setup = driver.findElement(By.id("user_email"));
        setup.sendKeys(email);
        setup = driver.findElement(By.id("user_password"));
        setup.sendKeys(password);
        setup.submit();
        driver.get("https://www.goodreads.com/giveaway?utf8=%E2%9C%93&sort=ending_soon&filter=print");
        
        for (int pagenum=0;pagenum<pages;pagenum++){
        	String page = driver.getCurrentUrl();
        	
        	List<WebElement> elements = driver.findElements(By.linkText("Enter Giveaway"));
        	
        	for (int i=0;i<elements.size();i++){
              WebElement elementInFocus = driver.findElement(By.linkText("Enter Giveaway"));
              elementInFocus.click();
              elementInFocus = driver.findElement(By.linkText("Select This Address"));
              elementInFocus.click();
              elementInFocus = driver.findElement(By.name("entry_terms"));
              elementInFocus.click();
              elementInFocus = driver.findElement(By.name("want_to_read"));
              elementInFocus.click();
              elementInFocus = driver.findElement(By.name("commit"));
              elementInFocus.click();
              driver.get(page);
              elements = driver.findElements(By.linkText("Enter Giveaway"));
          }
        	driver.findElement(By.linkText("next »")).click();
        }
        
        
        driver.quit();

    }
}
