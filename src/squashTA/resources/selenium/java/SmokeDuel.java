import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.awt.event.KeyEvent;



public class SmokeDuel {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private ReportDriver Report = new ReportDriver();
  private ParserXML parser = new ParserXML();
  private String browser = "chrome";
  

  @Before
  public void setUp() throws Exception {
//    driver = new FirefoxDriver();
	
		parser.getData();
		
		browser = parser.setBrowser(browser);
		
			if (System.getProperty("user.home").contains("C:\\Users\\")) {
//				System.setProperty("webdriver.chrome.driver", getClass().getResource("chromedriver.exe").toString().substring(6));
				System.setProperty("webdriver.chrome.driver", "src/squashTA/resources/chromedriver.exe");
			} else {
        String s="src/squashTA/resources/chromedriver";
				File file = new File(s);
				file.setExecutable(true, false);
//				System.setProperty("webdriver.chrome.driver", getClass().getResource("chromedriver").toString().substring(5));
				System.setProperty("webdriver.chrome.driver", s);
			}
			driver = new ChromeDriver();

//	parser.getData();
//	System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
//	driver = new ChromeDriver();
//	driver = new RemoteWebDriver("http://localhost:9515", DesiredCapabilities.chrome());
//	driver.get("http://www.google.com");
//    baseUrl = "https://www-uat.sw.co.ua/";
	baseUrl = parser.setServer("https://game-qa.sw.co.ua/");
	Report.WriteToLog("Begin the scenario: " + getClass().getName());
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get(baseUrl + "#loginCode=" + parser.setLoginCode("5565ae3dfe985738f2e75581___$6$4c29944e-a752-4a$iLbxUryBFPCEMY94dgKpt7QRXszAFdLvRpZY1z/Tm3nNxhKHKvFvM3x6S0BdmwkOvdNgSvhq5Nfp130MNUldH1"));

  }

  @Test
  public void testDuel() throws Exception {
	  try {

		  Thread.sleep(2000);

		  Report.WriteToLog("Open battleReg window");

		  for (int second = 0;; second++) {
		  	if (second >= 60) fail("timeout");
		  	try { if (isElementPresent(By.id("battleReg"))) break; } catch (Exception e) {}
		  	Thread.sleep(1000);
		  }
		  
		  driver.findElement(By.id("battleReg")).click();
		  
		  Report.WriteToLog("Open \"Duel\" tab");
		  
		  Thread.sleep(1000);
		  
		  for (int second = 0;; second++) {
			  	if (second >= 20) fail("timeout");
			  	try { if (isElementPresent(By.xpath("//div[@data-id='DUELING']"))) break; } catch (Exception e) {}
			  	Thread.sleep(1000);
			  }
		  
		  driver.findElement(By.xpath("//div[@data-id='DUELING']")).click();
		  
		  Report.WriteToLog("Find current player");
		  
		  Thread.sleep(2000);

		
		  driver.findElement(By.id("calledCharacter")).clear();
		  driver.findElement(By.id("calledCharacter")).sendKeys("Autotest");
		  
		  Thread.sleep(1000);
		  driver.findElement(By.id("findOpponent")).click();
		  
		  Report.WriteToLog("Check title (nickname [lvl])");

		  for (int second = 0;; second++) {
			  	if (second >= 20) fail("timeout");
			  	try { if (isElementPresent(By.cssSelector("div.profile > div.titleDark > span"))) break; } catch (Exception e) {}
			  	Thread.sleep(1000);
			  }
		  
		  assertEquals("Autotest [3]", driver.findElement(By.cssSelector("div.profile > div.titleDark > span")).getText());
		  
    Report.WriteToLog("Scenario finished!");
  } catch (Throwable e) {
	  	Report.PrintLog(driver,e);
	  }
}

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
