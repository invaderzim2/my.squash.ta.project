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

public class InOutChrome {
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
			System.setProperty("webdriver.chrome.driver", getClass().getResource("chromedriver").toString().substring(5));
//			System.setProperty("webdriver.chrome.driver", "/src/squashTA/resources/selenium/resources/chromedriver");
		}
	driver = new ChromeDriver();
//	driver = new RemoteWebDriver("http://localhost:9515", DesiredCapabilities.chrome());
//	driver.get("http://www.google.com");
    baseUrl = "https://www-uat.sw.co.ua/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testInOutChrome() throws Exception {
	driver.manage().window().maximize();
	driver.get(baseUrl);
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.id("open-login"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    Thread.sleep(5000);
    driver.findElement(By.id("open-login")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("TopLoginForm_email")).clear();
    driver.findElement(By.id("TopLoginForm_email")).sendKeys("7@7.ru");
    driver.findElement(By.id("TopLoginForm_password")).clear();
    driver.findElement(By.id("TopLoginForm_password")).sendKeys("123456");
    driver.findElement(By.id("login")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("7@7.ru".equals(driver.findElement(By.cssSelector("div.email")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    Thread.sleep(3000);
    driver.findElement(By.id("open-register")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("div.buttonExit"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("div.buttonExit")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("7@7.ru".equals(driver.findElement(By.cssSelector("div.email")).getText())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    Thread.sleep(3000);
    driver.findElement(By.id("close-login")).click();
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
