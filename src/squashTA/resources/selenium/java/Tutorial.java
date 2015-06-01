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
	System.setProperty("webdriver.chrome.driver", getClass().getResource("chromedriver.exe").toString().substring(6));
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

	if (isElementPresent(By.xpath("//div/div[contains(text(),'Введите ответ:')]"))){
		driver.findElement(By.xpath("//div/input")).sendKeys("Wolf");
		builder.moveToElement(driver.findElement(By.xpath("//div[text()='ОК']//..//div[@class='loader']"))).click().build().perform();
	}else if(isElementPresent(By.xpath("//div[text()='Тренировка боем']"))){
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
			if (isElementPresent(By.xpath("//div[text()='Двойным нажатием - используйте предмет']"))) {
				builder.moveToElement(driver.findElement(By.cssSelector(".targetArea"))).doubleClick().build().perform();
			}else if(isElementPresent(By.xpath("//div[text()='Товары находятся в рамках, ниже обозначена их цена. Нажатием на товар выберите его в магазине']"))){
				builder.moveToElement(driver.findElement(By.cssSelector(".targetArea")),50,60).click().build().perform();
			}else if(isElementPresent(By.xpath("//div[text()='Вы удачно совершили свою первую покупку']"))){
				builder.moveToElement(driver.findElement(By.xpath("//div[text()='ОК']//..//div[@class='loader']"))).click().build().perform();
			}else if(isElementPresent(By.xpath("//div/div[contains(text(),'Введите ответ:')]"))){
				driver.findElement(By.xpath("//div/input")).sendKeys("Wolf");
				builder.moveToElement(driver.findElement(By.xpath("//div[text()='ОК']//..//div[@class='loader']"))).click().build().perform();
		} else {
	builder.moveToElement(driver.findElement(By.cssSelector(".targetArea"))).click().build().perform(); }
	} catch (Exception e) {}
}
if(!isElementPresent(By.xpath("//div[text()='Тренировка боем']"))) fail("Scenario not finished");
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
