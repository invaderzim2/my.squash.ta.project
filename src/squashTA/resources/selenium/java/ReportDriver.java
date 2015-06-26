import java.io.*;
import org.apache.commons.io.FileUtils;
import java.util.Scanner;
import org.openqa.selenium.*;
import java.util.Date;

public class ReportDriver {
	private String MyLog = "Test log:";
	public void PrintLog(WebDriver driver, Throwable e) throws Exception {
		try {File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("target/squashTA/screenshots/screenshot.png"));
		  FileOutputStream fos = new FileOutputStream("target/squashTA/screenshots/index.html"); 
		  Writer out = new OutputStreamWriter(fos, "UTF-8");
		  out.write("<!DOCTYPE HTML><html><head><meta charset=\"utf-8\"><title>Test Report</title></head><body><pre>");
		  out.write("Error and trace:\n");
		  e.printStackTrace(new PrintWriter(out));
		  out.write("</pre> <br> <p>");
		  out.write(MyLog+"<br> -----------------------FAIL-----------------------");
		  out.write("</p> <br> <p>Screenshot on fail: <br> <a href=\"lorem.html\"><img src=\"screenshot.png\" alt=\"lorem\"></a></p></body></html>");
		  out.close(); } catch (Exception ex) {System.out.println("Can't pint log: " + ex);}
		  throw new Exception ("<a href=\"../../../../Screenshots/index.html\">See_Full_Test_Result</a>", e);

	}
	public void WriteToLog(String s) {
		  System.out.println(new Date().toString().substring(10,19)+": "+s);
		  MyLog=MyLog+"<br>"+new Date().toString().substring(10,19)+": "+s;
	}
}