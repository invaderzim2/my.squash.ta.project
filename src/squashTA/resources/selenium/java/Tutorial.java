import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.interactions.internal.*;
import org.openqa.selenium.internal.*;
import java.io.File;

public class Tutorial {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private ReportDriver Report = new ReportDriver();
  
  @Before
  public void setUp() throws Exception {
//    driver = new FirefoxDriver();
	
//	System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
	if (System.getProperty("user.home").contains("C:\\Users\\")) {
		System.setProperty("webdriver.chrome.driver", getClass().getResource("chromedriver.exe").toString().substring(6));
	} else { 
		String s=getClass().getResource("chromedriver").toString().substring(5);
		File file = new File(s);
		file.setExecutable(true, false);
		System.setProperty("webdriver.chrome.driver", getClass().getResource("chromedriver").toString().substring(5));
//		System.setProperty("webdriver.chrome.driver", "/src/squashTA/resources/selenium/resources/chromedriver");
	}
//    driver = new FirefoxDriver();
	driver = new ChromeDriver();
//	driver = new RemoteWebDriver("http://localhost:9515", DesiredCapabilities.chrome());
//	driver.get("http://www.google.com");
	baseUrl = "https://game-qa.sw.co.ua/";
//    baseUrl = "https://game-qa.sw.co.ua/#loginCode=5565ae3dfe985738f2e75581___$6$4c29944e-a752-4a$iLbxUryBFPCEMY94dgKpt7QRXszAFdLvRpZY1z/Tm3nNxhKHKvFvM3x6S0BdmwkOvdNgSvhq5Nfp130MNUldH1";
    driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
  }

  @Test
  public void testTutorial() throws Exception {
	  try {
		  Report.WriteToLog("Open URL: "+baseUrl);
		  String NextQuest = "tutorial1";
		  Actions builder = new Actions(driver); 
		  int i = 0;
	driver.manage().window().maximize();
	driver.get(baseUrl + "#loginCode=5565ae3dfe985738f2e75581___$6$4c29944e-a752-4a$iLbxUryBFPCEMY94dgKpt7QRXszAFdLvRpZY1z/Tm3nNxhKHKvFvM3x6S0BdmwkOvdNgSvhq5Nfp130MNUldH1");
	Thread.sleep(2000);

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

Thread.sleep(2000);

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

while (i<100) {
	i++;
//for (int i=1;i<100;i++) {
	Report.WriteToLog("Step#"+i);
	for (int second = 0;; second++) {
		if (second >= 60) fail("timeout");
		if (isElementPresent(By.xpath("//div/div[contains(text(),'\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043E\u0442\u0432\u0435\u0442:')]"))){
			try {driver.findElement(By.xpath("//div/input")).sendKeys("Wolf");
			builder.moveToElement(driver.findElement(By.xpath("//div[text()='\u041E\u041A']//..//div[@class='loader']"))).click().build().perform();} catch (Exception e) {Report.WriteToLog("Missed targetArea"); i=i-1;}
		} else if (isElementPresent(By.cssSelector(".targetArea"))) break;
		Thread.sleep(1000);
	}

	Thread.sleep(1000);
	if (isElementPresent(By.cssSelector("div.questItem"))){
		String s = driver.findElement(By.cssSelector("div.questItem")).getAttribute("data-id");
		if (!s.equals(NextQuest)) {
			NextQuest=s;
			Report.WriteToLog(NextQuest);
		}
		}
	if (isElementPresent(By.xpath("//div[text()='\u0414\u0432\u043E\u0439\u043D\u044B\u043C \u043D\u0430\u0436\u0430\u0442\u0438\u0435\u043C - \u0438\u0441\u043F\u043E\u043B\u044C\u0437\u0443\u0439\u0442\u0435 \u043F\u0440\u0435\u0434\u043C\u0435\u0442']"))) {
		try {builder.moveToElement(driver.findElement(By.cssSelector(".targetArea"))).doubleClick().build().perform();} catch (Exception e) {Report.WriteToLog("Missed targetArea"); i=i-1;}
	}else if(isElementPresent(By.xpath("//div[text()='\u0422\u043E\u0432\u0430\u0440\u044B \u043D\u0430\u0445\u043E\u0434\u044F\u0442\u0441\u044F \u0432 \u0440\u0430\u043C\u043A\u0430\u0445, \u043D\u0438\u0436\u0435 \u043E\u0431\u043E\u0437\u043D\u0430\u0447\u0435\u043D\u0430 \u0438\u0445 \u0446\u0435\u043D\u0430. \u041D\u0430\u0436\u0430\u0442\u0438\u0435\u043C \u043D\u0430 \u0442\u043E\u0432\u0430\u0440 \u0432\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0435\u0433\u043E \u0432 \u043C\u0430\u0433\u0430\u0437\u0438\u043D\u0435']"))){
		try {builder.moveToElement(driver.findElement(By.cssSelector(".targetArea")),50,60).click().build().perform();} catch (Exception e) {Report.WriteToLog("Missed targetArea"); i=i-1;}
	}else if(isElementPresent(By.xpath("//div[text()='\u0412\u044B \u0443\u0434\u0430\u0447\u043D\u043E \u0441\u043E\u0432\u0435\u0440\u0448\u0438\u043B\u0438 \u0441\u0432\u043E\u044E \u043F\u0435\u0440\u0432\u0443\u044E \u043F\u043E\u043A\u0443\u043F\u043A\u0443']"))){
		builder.moveToElement(driver.findElement(By.xpath("//div[text()='\u041E\u041A']//..//div[@class='loader']"))).click().build().perform();
	}else if(isElementPresent(By.xpath("//div[text()='\u041D\u0430\u0436\u0430\u0442\u0438\u0435\u043C \"\u0417\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043E\u0432\u0430\u0442\u044C\u0441\u044F\" \u0432\u044B \u043F\u043E\u0434\u0430\u0434\u0438\u0442\u0435 \u0437\u0430\u044F\u0432\u043A\u0443 \u043D\u0430 \u0443\u0447\u0430\u0441\u0442\u0438\u0435 \u0432 \u0431\u043E\u044F\u0445 \u0441 \u0434\u0440\u0443\u0433\u0438\u043C\u0438 \u0438\u0433\u0440\u043E\u043A\u0430\u043C\u0438']"))){
		break;
	} else {
/*		WebElement element = driver.findElement(By.cssSelector(".dayAvailableQuest + .dayAvailableQuest"));
		Mouse mouse = ((HasInputDevices) driver).getMouse();
		Locatable hoverItem = (Locatable) element;
		Coordinates coor = hoverItem.getCoordinates();
		mouse.mouseMove(coor);
		MoveMouseAction MMA = new MoveMouseAction(mouse,hoverItem);
		MMA.perform();
		builder.click().build().perform();
		ClickAction CA = new ClickAction(mouse,hoverItem);
		CA.perform();
//		System.out.println(element.getLocation().x + ":" + element.getLocation().y);
		mouse.mouseMove(coor);
		builder.click().build().perform();
		System.out.println(element.getLocation().x + ":" + element.getLocation().y);
		builder.moveToElement(element).click().build().perform();*/
//		mouse.mouseMove(hoverItem.getCoordinates());
//		mouse.click(hoverItem.getCoordinates());
//		System.out.println("Click targetArea");
		try {builder.moveToElement(driver.findElement(By.cssSelector(".targetArea"))).click().build().perform();} catch (Exception e) {Report.WriteToLog("Missed targetArea"); i=i-1;}
//		try {driver.findElement(By.cssSelector(".targetArea")).click(); System.out.println("Click targetArea");} catch (Exception e) {Report.WriteToLog("Missed targetArea"); i=i-1;}
	}

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
builder.moveToElement(driver.findElement(By.cssSelector("div.passQuest"))).click().build().perform();
Thread.sleep(3000);
builder.moveToElement(driver.findElement(By.cssSelector("div.npcType"))).click().build().perform();
Thread.sleep(3000);
builder.moveToElement(driver.findElement(By.cssSelector("div.activeQuest"))).click().build().perform();
Thread.sleep(3000);
builder.moveToElement(driver.findElement(By.cssSelector("div#finishQuest"))).click().build().perform();
Thread.sleep(3000);
builder.moveToElement(driver.findElement(By.cssSelector("div#nextQuest"))).click().build().perform();
Thread.sleep(2000);
builder.moveToElement(driver.findElement(By.cssSelector("div#takeQuest"))).click().build().perform();

while (i<150) {
	i++;
//for (int i=1;i<100;i++) {
	Report.WriteToLog("Step#"+i);

	for (int second = 0;; second++) {
		if (second >= 60) fail("timeout");
		if (isElementPresent(By.cssSelector("div.questItem"))){
			String s = driver.findElement(By.cssSelector("div.questItem")).getAttribute("data-id");
			if (!s.equals(NextQuest)) {
				NextQuest=s;
				Report.WriteToLog(NextQuest);
			}
			if (NextQuest.equals("tutorial9")) break;
		}
		if (isElementPresent(By.cssSelector(".targetArea"))) break;
		Thread.sleep(1000);
	}
	if (NextQuest.equals("tutorial9")) break;
	Thread.sleep(1000);

	if (isElementPresent(By.xpath("//div[text()='\u0414\u0432\u043E\u0439\u043D\u044B\u043C \u043D\u0430\u0436\u0430\u0442\u0438\u0435\u043C - \u0438\u0441\u043F\u043E\u043B\u044C\u0437\u0443\u0439\u0442\u0435 \u043F\u0440\u0435\u0434\u043C\u0435\u0442']"))) {
		try {builder.moveToElement(driver.findElement(By.cssSelector(".targetArea"))).doubleClick().build().perform();} catch (Exception e) {Report.WriteToLog("Missed targetArea"); i=i-1;}
	} else {
		try {builder.moveToElement(driver.findElement(By.cssSelector(".targetArea"))).click().build().perform();} catch (Exception e) {Report.WriteToLog("Missed targetArea"); i=i-1;} }

}

if (!NextQuest.equals("tutorial9")) {
	fail("Scenario not finished");
}

	  } catch (Throwable e) {Report.PrintLog(driver,e);}
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
