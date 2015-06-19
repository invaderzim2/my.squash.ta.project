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

public class Tutorial {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private ReportDriver Report = new ReportDriver();
  private ParserXML parser = new ParserXML();
  private String browser = "chrome";
  
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

Report.WriteToLog("Begin the scenario: tutorial(1-9)");

Thread.sleep(1000);

driver.get(baseUrl);
	
	Report.WriteToLog(NextQuest);
	
	driver.findElement(By.cssSelector("body")).sendKeys(Keys.F11);
	

while (i<140) {
	i++;
	Report.WriteToLog("Step#"+i);
	for (int second = 0;; second++) {
		if (second >= 250) fail("timeout");
		if (isElementPresent(By.xpath("//div/div[contains(text(),'\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043E\u0442\u0432\u0435\u0442:')]"))){
			driver.findElement(By.xpath("//div/input")).sendKeys("Wolf");
			if (!rdriver.rClick(driver.findElement(By.xpath("//div[text()='\u041E\u041A']//..//div[@class='loader']")))) {Report.WriteToLog("Missed targetArea"); i=i-1;}
		} else if (isElementPresent(By.cssSelector(".targetArea"))) break;
		Thread.sleep(200);
	}
	try {el = driver.findElement(By.cssSelector(".targetArea")); } catch (Exception exx) {Report.WriteToLog("Missed targetArea"); i=i-1; continue;};
	if (!rdriver.rClick(el)) {Report.WriteToLog("Missed targetArea"); i=i-1;}
	if (isElementPresent(By.xpath("//div[text()='\u0414\u0432\u043E\u0439\u043D\u044B\u043C \u043D\u0430\u0436\u0430\u0442\u0438\u0435\u043C - \u0438\u0441\u043F\u043E\u043B\u044C\u0437\u0443\u0439\u0442\u0435 \u043F\u0440\u0435\u0434\u043C\u0435\u0442']"))) {
		if (!rdriver.rDoubleClick(el)) {Report.WriteToLog("Missed targetArea"); i=i-1;}
	}else if(isElementPresent(By.xpath("//div[text()='\u0422\u043E\u0432\u0430\u0440\u044B \u043D\u0430\u0445\u043E\u0434\u044F\u0442\u0441\u044F \u0432 \u0440\u0430\u043C\u043A\u0430\u0445, \u043D\u0438\u0436\u0435 \u043E\u0431\u043E\u0437\u043D\u0430\u0447\u0435\u043D\u0430 \u0438\u0445 \u0446\u0435\u043D\u0430. \u041D\u0430\u0436\u0430\u0442\u0438\u0435\u043C \u043D\u0430 \u0442\u043E\u0432\u0430\u0440 \u0432\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0435\u0433\u043E \u0432 \u043C\u0430\u0433\u0430\u0437\u0438\u043D\u0435']"))){
		if (!rdriver.rClick(el,50,60)) {Report.WriteToLog("Missed targetArea"); i=i-1;}
	}else if(isElementPresent(By.xpath("//div[text()='\u0412\u044B \u0443\u0434\u0430\u0447\u043D\u043E \u0441\u043E\u0432\u0435\u0440\u0448\u0438\u043B\u0438 \u0441\u0432\u043E\u044E \u043F\u0435\u0440\u0432\u0443\u044E \u043F\u043E\u043A\u0443\u043F\u043A\u0443']"))){
		if (!rdriver.rClick(driver.findElement(By.xpath("//div[text()='\u041E\u041A']//..//div[@class='loader']")))) {Report.WriteToLog("Missed targetArea"); i=i-1;}
	}else if(isElementPresent(By.xpath("//div[text()='\u041D\u0430\u0436\u0430\u0442\u0438\u0435\u043C \"\u0417\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043E\u0432\u0430\u0442\u044C\u0441\u044F\" \u0432\u044B \u043F\u043E\u0434\u0430\u0434\u0438\u0442\u0435 \u0437\u0430\u044F\u0432\u043A\u0443 \u043D\u0430 \u0443\u0447\u0430\u0441\u0442\u0438\u0435 \u0432 \u0431\u043E\u044F\u0445 \u0441 \u0434\u0440\u0443\u0433\u0438\u043C\u0438 \u0438\u0433\u0440\u043E\u043A\u0430\u043C\u0438']"))){
		break;
//	} else {
	}
	if (isElementPresent(By.cssSelector("div.questItem"))){
		try { String s = driver.findElement(By.cssSelector("div.questItem")).getAttribute("data-id");
		if (!s.equals(NextQuest)) {
			NextQuest=s;
			Report.WriteToLog(NextQuest);
		} } catch (Exception ex) {Report.WriteToLog("Missed questItem");}
		}
	Thread.sleep(500);
}

if(!isElementPresent(By.xpath("//div[text()='\u041D\u0430\u0436\u0430\u0442\u0438\u0435\u043C \"\u0417\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043E\u0432\u0430\u0442\u044C\u0441\u044F\" \u0432\u044B \u043F\u043E\u0434\u0430\u0434\u0438\u0442\u0435 \u0437\u0430\u044F\u0432\u043A\u0443 \u043D\u0430 \u0443\u0447\u0430\u0441\u0442\u0438\u0435 \u0432 \u0431\u043E\u044F\u0445 \u0441 \u0434\u0440\u0443\u0433\u0438\u043C\u0438 \u0438\u0433\u0440\u043E\u043A\u0430\u043C\u0438']"))) fail("Scenario not finished");

Thread.sleep(1000);

Report.WriteToLog("Move across the fight");

driver.get(baseUrl.replace("game","admin") + "#userQuest/list/");

for (int second = 0;; second++) {
	if (second >= 60) fail("timeout");
	if (isElementPresent(By.linkText("[edit]"))) break;
	Thread.sleep(1000);
}

Thread.sleep(1000);

driver.findElement(By.linkText("[edit]")).click();

Thread.sleep(2000);

driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
driver.findElement(By.xpath("//label[text()='scenarioProgress']//..//input")).clear();
driver.findElement(By.xpath("//label[text()='scenarioProgress']//..//input")).sendKeys("100");
driver.findElement(By.cssSelector("button")).click();

Thread.sleep(1000);

assertEquals("success", closeAlertAndGetItsText());

Thread.sleep(1000);

driver.get(baseUrl);

for (int second = 0;; second++) {
	if (second >= 60) fail("timeout");
	if (isElementPresent(By.cssSelector("div.passQuest"))) break;
	Thread.sleep(1000);
}

Thread.sleep(2000);
rdriver.rClick(driver.findElement(By.cssSelector("div.passQuest")));
Thread.sleep(3000);
driver.findElement(By.cssSelector("div.npc")).click();
Thread.sleep(3000);
driver.findElement(By.cssSelector("div.activeQuest")).click();
Thread.sleep(3000);
driver.findElement(By.cssSelector("div#finishQuest")).click();
Thread.sleep(3000);
driver.findElement(By.cssSelector("div#nextQuest")).click();
Thread.sleep(2000);
driver.findElement(By.cssSelector("div#takeQuest")).click();

while (i<210) {
	i++;
	Report.WriteToLog("Step#"+i);

	for (int second = 0;; second++) {
		if (second >= 250) fail("timeout");
		if (isElementPresent(By.cssSelector("div.questItem"))){
			String s = driver.findElement(By.cssSelector("div.questItem")).getAttribute("data-id");
			if (!s.equals(NextQuest)) {
				NextQuest=s;
				Report.WriteToLog(NextQuest);
			}
			if (NextQuest.equals("tutorial9")) break;
		}
		if (isElementPresent(By.cssSelector(".targetArea"))) break;
		Thread.sleep(200);
	}
	if (NextQuest.equals("tutorial9")) break;
	try { el = driver.findElement(By.cssSelector(".targetArea")); } catch (Exception exx) {Report.WriteToLog("Missed targetArea"); i=i-1; continue;}

	if (isElementPresent(By.xpath("//div[text()='\u0414\u0432\u043E\u0439\u043D\u044B\u043C \u043D\u0430\u0436\u0430\u0442\u0438\u0435\u043C - \u0438\u0441\u043F\u043E\u043B\u044C\u0437\u0443\u0439\u0442\u0435 \u043F\u0440\u0435\u0434\u043C\u0435\u0442']"))) {
		if (!rdriver.rDoubleClick(el)) {Report.WriteToLog("Missed targetArea"); i=i-1;}
	} else {
		if (!rdriver.rClick(el)) {Report.WriteToLog("Missed targetArea"); i=i-1;} }
	Thread.sleep(500);
}

if (!NextQuest.equals("tutorial9")) {
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
