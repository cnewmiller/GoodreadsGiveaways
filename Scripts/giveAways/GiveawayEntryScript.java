package giveAways;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Console;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GiveawayEntryScript  {
    public static void main(String[] args) throws InterruptedException {

    	Console c = System.console();
        
        String email=null;
        String password = null;
        boolean doTags = false;
        String[] tags = null;
        int[] nums = null;
        int pages=10;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Please enter your Goodreads login email address:");
			email = c.readLine();//console.readLine();
			System.out.println("Please enter your Goodreads login password: (it won't be saved) \n");
//			password = console.readLine();
			password = new String(c.readPassword());
			System.out.println("How many pages of giveaways do you want to enter? Enter a numeric value (0 is valid):");
			pages = Integer.parseInt(console.readLine());
			
			
			System.out.print("Do you want to enter giveaways by tags? (y/n): ");
			doTags = (console.readLine().equals("y"));
			if (doTags){
				System.out.println("\t\t******Attention!******\n"
						+ "Tags are searched seperately, NOT AS A SINGLE TERM.\n"
						+ "This feature is for selection, not bulk entry of soon-to-end giveaways.\n"
						+ "Please enter the tags you wish to search, then the number of pages of that tag, seperated by commas\n"
						+ "Example: 'fantasy,2,science-fiction,3' will enter 2 pages of the fantasy tag and 3 pages of the science-fiction tag\n"
						+ "Press enter when finished:");
				
				String raw[] = console.readLine().split(",");
				tags = new String[raw.length/2];
				nums = new int[raw.length/2];
				for (int i = 0;i<raw.length;i+=2){
					tags[i/2] = raw[i];
					nums[i/2] = Integer.parseInt(raw[i+1]);
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Something went wrong, exiting script...");
			System.exit(1);
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
        	for (int i = 0; i<tags.length;i++){
        		String tag = tags[i];
        		System.out.println("Entering tag "+tag+"...");
        		String url ="https://www.goodreads.com/giveaway/tag/" + tag + "?utf8=%E2%9C%93&sort=ending_soon&filter=print";
        		enterAllGiveawaysOnPage(driver, url, nums[i]);
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
        	
        	while (elements.size()>0){
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
