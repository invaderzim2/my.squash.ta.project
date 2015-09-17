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



public class InOutChrome {
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
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testInOutChrome() throws Exception {
	  try {
		  Report.WriteToLog("Open URL: "+baseUrl.replace("game","www"));
	driver.manage().window().maximize();
	driver.get(baseUrl.replace("game","www"));
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.id("open-login"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    Thread.sleep(5000);
    driver.findElement(By.id("open-login")).click();
    driver.findElement(By.id("TopLoginForm_email")).clear();
//    driver.findElement(By.id("TopLoginForm_email")).sendKeys("7@7.ru");
//    driver.findElement(By.id("TopLoginForm_email")).sendKeys("${IT_CUF_login}");
//    driver.findElement(By.id("TopLoginForm_email")).sendKeys(name);
    driver.findElement(By.id("TopLoginForm_email")).sendKeys(parser.setLogin("autotest@sw.co.ua"));
    driver.findElement(By.id("TopLoginForm_password")).clear();
    driver.findElement(By.id("TopLoginForm_password")).sendKeys(parser.setPassword("123456"));
    driver.findElement(By.id("login")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
//    	try { if ("7@7.ru".equals(driver.findElement(By.cssSelector("div.email")).getText())) break; } catch (Exception e) {}
    	try { if (parser.setLogin("autotest@sw.co.ua").equals(driver.findElement(By.cssSelector("div.email")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.id("open-register")).click();
    
    //////////***********


Thread.sleep(2000);

ReWebDriver rdriver = new ReWebDriver(driver, browser);
WebElement el = driver.findElement(By.cssSelector("body"));

Report.WriteToLog("Load preset: \"Presetdva\"");
driver.get(baseUrl.replace("game","admin") + "#gameCharacter/list/0/presetdva");

for (int second = 0;; second++) {
if (second >= 60) fail("timeout");
if (isElementPresent(By.id("search-input"))) break;
Thread.sleep(1000);
}


Thread.sleep(1000);

for (int second = 0;; second++) {
if (second >= 60) fail("timeout");
if (isElementPresent(By.xpath("//td[text()='\"Presetdva\"']//..//button[@data-action='copy']"))) break;
Thread.sleep(1000);
}
Thread.sleep(1000);
driver.findElement(By.xpath("//td[text()='\"Presetdva\"']//..//button[@data-action='copy']")).click();

Thread.sleep(1000);

driver.findElement(By.xpath("//label[text()='equip']//..//input[@type='checkbox']")).click();

driver.findElement(By.xpath("//label[text()='inventory']//..//input[@type='checkbox']")).click();

driver.findElement(By.xpath("//label[text()='quest']//..//input[@type='checkbox']")).click();

driver.findElement(By.xpath("//button[text()='ok']")).click();

Thread.sleep(1000);

assertEquals("success", closeAlertAndGetItsText());

Report.WriteToLog("Teleport to location: \"castle_namiko\"");

Thread.sleep(1000);

driver.get(baseUrl.replace("game","admin") + "#location/list/");

Thread.sleep(1000);

for (int second = 0;; second++) {
if (second >= 60) fail("timeout");
if (isElementPresent(By.xpath("//td[text()='\"castle_namiko\"']//..//button[@data-action='teleport']"))) break;
Thread.sleep(1000);
}

Thread.sleep(1000);

driver.findElement(By.xpath("//td[text()='\"castle_namiko\"']//..//button[@data-action='teleport']")).click();

Thread.sleep(1000);

driver.switchTo().alert().accept();

Thread.sleep(1000);

assertEquals("success", closeAlertAndGetItsText());

Report.WriteToLog("Begin the scenario: smoke_test1");

Thread.sleep(1000);

driver.get(baseUrl);

driver.findElement(By.cssSelector("body")).sendKeys(Keys.F11);

    
    //////////***********
    
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("div.buttonExit"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.cssSelector("div.buttonExit")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
//    	try { if ("7@7.ru".equals(driver.findElement(By.cssSelector("div.email")).getText())) break; } catch (Exception e) {}
    	try { if (parser.setLogin("autotest@sw.co.ua").equals(driver.findElement(By.cssSelector("div.email")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.id("close-login")).click();
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
