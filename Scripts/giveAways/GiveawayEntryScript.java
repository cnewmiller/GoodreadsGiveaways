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
        boolean doTags = false;
        String[] tags = null;
        int pages=10;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Please enter your Goodreads login email address:");
			email = console.readLine();
			System.out.println("Please enter your Goodreads login password \n(Change it before using this script? It'll display on the screen when you type it):");
			password = console.readLine();
			System.out.println("How many pages of giveaways do you want to enter? Enter a numeric value (0 is valid):");
			pages = Integer.parseInt(console.readLine());
			
			System.out.print("Do you want to enter giveaways by tags? (y/n): ");
			doTags = (console.readLine().equals("y"));
			if (doTags){
				System.out.println("Please enter the tags you wish to search, seperated by commas (press enter when finished):");
				tags = console.readLine().split(",");
			}
			
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
        //logged in
        
        

        if (doTags){
        	for (String tag : tags){
        		System.out.println("Entering tag "+tag+"...");
        		String url ="https://www.goodreads.com/giveaway/tag/" + tag + "?utf8=%E2%9C%93&sort=ending_soon&filter=print";
        		enterAllGiveawaysOnPage(driver, url, pages);
        	}
        }
        else{
        	System.out.println("Entering general giveaways...");
        	String url = "https://www.goodreads.com/giveaway?utf8=%E2%9C%93&sort=ending_soon&filter=print";
        	enterAllGiveawaysOnPage(driver, url, pages);
        }
        
        
        
        driver.quit();

    }
    
    public static void enterAllGiveawaysOnPage(WebDriver driver, String url, int pagesToEnter){
        driver.get(url);
        for (int pagenum=0;pagenum<pagesToEnter;pagenum++){
        	System.out.println("Beginning page "+(pagenum+1)+" of "+pagesToEnter+"...");
        	String pageToReturnTo = driver.getCurrentUrl();
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
              driver.get(pageToReturnTo);
              elements = driver.findElements(By.linkText("Enter Giveaway"));
          }
        	if (!driver.findElements(By.linkText("next »")).isEmpty()) driver.findElement(By.linkText("next »")).click();
        }
    }
}
