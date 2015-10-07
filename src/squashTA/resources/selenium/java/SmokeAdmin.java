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



public class SmokeAdmin {
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
  public void testAdmin() throws Exception {
	  try {

Thread.sleep(2000);

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



driver.findElement(By.xpath("//td[text()='\"castle_namiko\"']//..//button[@data-action='teleport']")).click();

Thread.sleep(1000);

driver.switchTo().alert().accept();

Thread.sleep(1000);

assertEquals("success", closeAlertAndGetItsText());

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
