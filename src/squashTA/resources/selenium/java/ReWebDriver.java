import java.awt.Robot;
import java.awt.event.InputEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;

public class ReWebDriver {
	private WebDriver driver;
	private String browser;
	private Actions builder;
	public ReWebDriver (WebDriver newdriver, String newbrowser) {
		driver = newdriver;
		browser = newbrowser;
		builder = new Actions(driver); 
	}
	public boolean rClick(WebElement el) throws Exception {
		if (browser.equals("firefox")) {
			try{
		int w=el.getSize().width;
		int h=el.getSize().height;
		int x=el.getLocation().x+w/2;
		int y=el.getLocation().y+h/2;
//		System.out.println("w="+w+" h="+h);
//		System.out.println("x1="+el.getLocation().x+" y1="+el.getLocation().y);
//		System.out.println("x2="+x+" y2="+y);
		Robot r= new Robot();
		r.mouseMove(x, y);
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.delay(30);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		return true;
		} catch (Exception e) { 
			//System.out.println(e);
			return false;
			}
		}
		else {
			try{
			builder.moveToElement(el).click().build().perform();
			return true;
		} catch (Exception e) {return false;} }
	}
	public boolean rClick(WebElement el, int nx, int ny) throws Exception {
		if (browser.equals("firefox")) { try{
//		int w=el.getSize().width;
//		int h=el.getSize().height;
		int x=el.getLocation().x+nx;
		int y=el.getLocation().y+ny;
//		System.out.println("w="+w+" h="+h);
//		System.out.println("x1="+el.getLocation().x+" y1="+el.getLocation().y);
//		System.out.println("x2="+x+" y2="+y);
		Robot r= new Robot();
		r.mouseMove(x, y);
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.delay(30);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		return true;
		} catch (Exception e) {return false;}}
		else { try{
			builder.moveToElement(el,nx,ny).click().build().perform();
			return true;
		} catch (Exception e) {return false;} }
	}
	public boolean rDoubleClick(WebElement el) throws Exception {
		if (browser.equals("firefox")) { try{
		int w=el.getSize().width;
		int h=el.getSize().height;
		int x=el.getLocation().x+w/2;
		int y=el.getLocation().y+h/2;
//		System.out.println("w="+w+" h="+h);
//		System.out.println("x1="+el.getLocation().x+" y1="+el.getLocation().y);
//		System.out.println("x2="+x+" y2="+y);
		Robot r= new Robot();
		r.mouseMove(x, y);
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.delay(30);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		r.delay(100);
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.delay(30);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		return true;
		} catch (Exception e) {return false;}}
		else { try{
			builder.moveToElement(el).doubleClick().build().perform();
			return true;
		} catch (Exception e) {return false;} }
	}
}
