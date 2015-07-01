import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.awt.event.KeyEvent;
import static org.hamcrest.CoreMatchers.*;

public class Tutorial1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private ReportDriver Report = new ReportDriver();
  private ParserXML parser = new ParserXML();
  private String browser = "firefox";
  
  @Before
  public void setUp() throws Exception {
	
	parser.getData();
	
	browser = parser.setBrowser(browser);
	
	if (browser.equals("chrome")) {
//	if (true) {
		if (System.getProperty("user.home").contains("C:\\Users\\")) {
//			System.setProperty("webdriver.chrome.driver", getClass().getResource("chromedriver.exe").toString().substring(6));
			System.setProperty("webdriver.chrome.driver", "src/squashTA/resources/chromedriver.exe");
		} else {
			String s="src/squashTA/resources/chromedriver";
			File file = new File(s);
			file.setExecutable(true, false);
//			System.setProperty("webdriver.chrome.driver", getClass().getResource("chromedriver").toString().substring(5));
			System.setProperty("webdriver.chrome.driver", s);
		}
		driver = new ChromeDriver();
	} else {
//	if (browser.equals("firefox")) {
//	   final String firebugPath = "C:\\firebug-2.0.10-fx.xpi";
//	   final String firebugPath = "C:\\eventbug-0.1b10.xpi";
	   FirefoxProfile profile = new FirefoxProfile();
//	   profile.addExtension(new File(firebugPath));
	   profile.setEnableNativeEvents(true);
	   driver = new FirefoxDriver(profile);
	}
	
//	driver = new RemoteWebDriver("http://localhost:9515", DesiredCapabilities.chrome());
	baseUrl = parser.setServer("https://game-qa.sw.co.ua/");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
  }

  @Test
  public void testTutorial() throws Exception {

	  try {

		  Report.WriteToLog("Open URL: "+baseUrl);
		  String NextQuest = "tutorial1"; 
		  int i = 0;
	driver.manage().window().maximize();
	driver.get(baseUrl + "#loginCode=" + parser.setLoginCode("5565ae3dfe985738f2e75581___$6$4c29944e-a752-4a$iLbxUryBFPCEMY94dgKpt7QRXszAFdLvRpZY1z/Tm3nNxhKHKvFvM3x6S0BdmwkOvdNgSvhq5Nfp130MNUldH1"));
	Thread.sleep(2000);

	  ReWebDriver rdriver = new ReWebDriver(driver, browser);
	  WebElement el = driver.findElement(By.cssSelector("body"));
	
	Report.WriteToLog("Load preset: \"Presetnt\"");
driver.get(baseUrl.replace("game","admin") + "#gameCharacter/list/");

for (int second = 0;; second++) {
	if (second >= 60) fail("timeout");
	if (isElementPresent(By.id("search-input"))) break;
	Thread.sleep(1000);
}


driver.findElement(By.id("search-input")).clear();
driver.findElement(By.id("search-input")).sendKeys("Presetnt");
driver.findElement(By.id("search-input")).sendKeys(Keys.ENTER);

Thread.sleep(1000);

for (int second = 0;; second++) {
	if (second >= 60) fail("timeout");
	if (isElementPresent(By.xpath("//td[text()='\"Presetnt\"']//..//button[@data-action='copy']"))) break;
	Thread.sleep(1000);
}
Thread.sleep(1000);
driver.findElement(By.xpath("//td[text()='\"Presetnt\"']//..//button[@data-action='copy']")).click();

Thread.sleep(1000);

driver.findElement(By.xpath("//label[text()='equip']//..//input[@type='checkbox']")).click();

driver.findElement(By.xpath("//label[text()='inventory']//..//input[@type='checkbox']")).click();

driver.findElement(By.xpath("//label[text()='quest']//..//input[@type='checkbox']")).click();

driver.findElement(By.xpath("//button[text()='ok']")).click();

Thread.sleep(1000);

assertEquals("success", closeAlertAndGetItsText());

Report.WriteToLog("Teleport to location: \"rock_messenger\"");

Thread.sleep(1000);

driver.get(baseUrl.replace("game","admin") + "#location/list/");

Thread.sleep(1000);

for (int second = 0;; second++) {
	if (second >= 60) fail("timeout");
	if (isElementPresent(By.xpath("//td[text()='\"rock_messenger\"']//..//button[@data-action='teleport']"))) break;
	Thread.sleep(1000);
}

Thread.sleep(1000);

driver.findElement(By.xpath("//td[text()='\"rock_messenger\"']//..//button[@data-action='teleport']")).click();

Thread.sleep(1000);

driver.switchTo().alert().accept();

Thread.sleep(1000);

assertEquals("success", closeAlertAndGetItsText());

Report.WriteToLog("Begin the scenario: tutorial(1-1)");

Thread.sleep(1000);

driver.get(baseUrl);
	
	Report.WriteToLog(NextQuest);
	
	driver.findElement(By.cssSelector("body")).sendKeys(Keys.F11);
	
	String s=NextQuest;

while (i<40) {
	i++;
	Report.WriteToLog("Step#"+i);
	
	for (int second = 0;; second++) {
		if (second >= 250) fail("timeout");
		if (isElementPresent(By.cssSelector("#activeQuestsList>div.questItem"))){
			try { s = driver.findElement(By.cssSelector("#activeQuestsList>div.questItem")).getAttribute("data-id");  } catch (Exception exx) {Report.WriteToLog("Missed questItem"); continue;}
			if (!s.equals(NextQuest)) {
				NextQuest=s;
				Report.WriteToLog(NextQuest);
			}
			if (NextQuest.equals("tutorial2")) break;
		}
		if (isElementPresent(By.cssSelector(".targetArea"))) break;
		Thread.sleep(200);
	}
	if (NextQuest.equals("tutorial2")) break;
	try { el = driver.findElement(By.cssSelector(".targetArea")); } catch (Exception exx) {Report.WriteToLog("Missed targetArea"); i=i-1; continue;}
	if (!rdriver.rClick(el)) {Report.WriteToLog("Missed targetArea"); i=i-1;}

	Thread.sleep(500);
}

if (!NextQuest.equals("tutorial2")) {
	fail("Scenario not finished");
}

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
