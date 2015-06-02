import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import java.io.File;

public class Tutorial {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

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
		System.setProperty("webdriver.chrome.driver", getClass().getResource("chromedriver").toString().substring(6));
//		System.setProperty("webdriver.chrome.driver", "/src/squashTA/resources/selenium/resources/chromedriver");
	}
	driver = new ChromeDriver();
//	driver = new RemoteWebDriver("http://localhost:9515", DesiredCapabilities.chrome());
//	driver.get("http://www.google.com");
	baseUrl = "https://game-qa.sw.co.ua/";
//    baseUrl = "https://game-qa.sw.co.ua/#loginCode=5565ae3dfe985738f2e75581___$6$4c29944e-a752-4a$iLbxUryBFPCEMY94dgKpt7QRXszAFdLvRpZY1z/Tm3nNxhKHKvFvM3x6S0BdmwkOvdNgSvhq5Nfp130MNUldH1";
    driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
  }

  @Test
  public void testTutorial() throws Exception {
	driver.manage().window().maximize();
	driver.get(baseUrl + "#loginCode=5565ae3dfe985738f2e75581___$6$4c29944e-a752-4a$iLbxUryBFPCEMY94dgKpt7QRXszAFdLvRpZY1z/Tm3nNxhKHKvFvM3x6S0BdmwkOvdNgSvhq5Nfp130MNUldH1");
	Thread.sleep(2000);

driver.get(baseUrl.replace("game","admin") + "#gameCharacter/list/");

for (int second = 0;; second++) {
	if (second >= 60) fail("timeout");
	try { if (isElementPresent(By.id("search-input"))) break; } catch (Exception e) {}
	Thread.sleep(1000);
}

driver.findElement(By.id("search-input")).clear();
driver.findElement(By.id("search-input")).sendKeys("Presetnt");
driver.findElement(By.id("search-input")).sendKeys(Keys.ENTER);

Thread.sleep(2000);

for (int second = 0;; second++) {
	if (second >= 60) fail("timeout");
	try { if (isElementPresent(By.xpath("//td[text()='\"Presetnt\"']//..//button[@data-action='copy']"))) break; } catch (Exception e) {}
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

Thread.sleep(1000);

driver.get(baseUrl.replace("game","admin") + "#location/list/");

Thread.sleep(1000);

for (int second = 0;; second++) {
	if (second >= 60) fail("timeout");
	try { if (isElementPresent(By.xpath("//td[text()='\"rock_messenger\"']//..//button[@data-action='teleport']"))) break; } catch (Exception e) {}
	Thread.sleep(1000);
}

Thread.sleep(1000);

driver.findElement(By.xpath("//td[text()='\"rock_messenger\"']//..//button[@data-action='teleport']")).click();

Thread.sleep(1000);

driver.switchTo().alert().accept();

Thread.sleep(1000);

assertEquals("success", closeAlertAndGetItsText());

Thread.sleep(1000);

driver.get(baseUrl);

Actions builder = new Actions(driver);  
for (int i=1;i<100;i++) {

	if (isElementPresent(By.xpath("//div/div[contains(text(),'\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043E\u0442\u0432\u0435\u0442:')]"))){
		driver.findElement(By.xpath("//div/input")).sendKeys("Wolf");
		builder.moveToElement(driver.findElement(By.xpath("//div[text()='\u041E\u041A']//..//div[@class='loader']"))).click().build().perform();
	}else if(isElementPresent(By.xpath("//div[text()='\u0422\u0440\u0435\u043D\u0438\u0440\u043E\u0432\u043A\u0430 \u0431\u043E\u0435\u043C']"))){
		break;
	} else {
	for (int second = 0;; second++) {
		if (second >= 60) fail("timeout");
		try { if (isElementPresent(By.cssSelector(".targetArea"))) break; } catch (Exception e) {}
		Thread.sleep(1000);
	}
	}
	Thread.sleep(1000);
	try {
			if (isElementPresent(By.xpath("//div[text()='\u0414\u0432\u043E\u0439\u043D\u044B\u043C \u043D\u0430\u0436\u0430\u0442\u0438\u0435\u043C - \u0438\u0441\u043F\u043E\u043B\u044C\u0437\u0443\u0439\u0442\u0435 \u043F\u0440\u0435\u0434\u043C\u0435\u0442']"))) {
				builder.moveToElement(driver.findElement(By.cssSelector(".targetArea"))).doubleClick().build().perform();
			}else if(isElementPresent(By.xpath("//div[text()='\u0422\u043E\u0432\u0430\u0440\u044B \u043D\u0430\u0445\u043E\u0434\u044F\u0442\u0441\u044F \u0432 \u0440\u0430\u043C\u043A\u0430\u0445, \u043D\u0438\u0436\u0435 \u043E\u0431\u043E\u0437\u043D\u0430\u0447\u0435\u043D\u0430 \u0438\u0445 \u0446\u0435\u043D\u0430. \u041D\u0430\u0436\u0430\u0442\u0438\u0435\u043C \u043D\u0430 \u0442\u043E\u0432\u0430\u0440 \u0432\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0435\u0433\u043E \u0432 \u043C\u0430\u0433\u0430\u0437\u0438\u043D\u0435']"))){
				builder.moveToElement(driver.findElement(By.cssSelector(".targetArea")),50,60).click().build().perform();
			}else if(isElementPresent(By.xpath("//div[text()='\u0412\u044B \u0443\u0434\u0430\u0447\u043D\u043E \u0441\u043E\u0432\u0435\u0440\u0448\u0438\u043B\u0438 \u0441\u0432\u043E\u044E \u043F\u0435\u0440\u0432\u0443\u044E \u043F\u043E\u043A\u0443\u043F\u043A\u0443']"))){
				builder.moveToElement(driver.findElement(By.xpath("//div[text()='\u041E\u041A']//..//div[@class='loader']"))).click().build().perform();
		} else {
	builder.moveToElement(driver.findElement(By.cssSelector(".targetArea"))).click().build().perform(); }
	} catch (Exception e) {}
}
if(!isElementPresent(By.xpath("//div[text()='\u0422\u0440\u0435\u043D\u0438\u0440\u043E\u0432\u043A\u0430 \u0431\u043E\u0435\u043C']"))) fail("Scenario not finished");
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
