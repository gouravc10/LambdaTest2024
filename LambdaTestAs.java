package com.qa;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class LambdaTestAs {

	public static void main(String[] args) throws InterruptedException {

		UiAutomator2Options options = new UiAutomator2Options();
//	    options.setUdid("QSKBSWLVLZZH5LGM");
//	    options.setUdid("dfed68b50406");
	    
	    options.setCapability("appium:appPackage", "com.android.settings");
	    options.setCapability("appium:appActivity", "com.android.settings.Settings");
	    options.setCapability("platformName", "Android");
	    options.setCapability("appium:platformVersion", "12.0");
	    options.setCapability("appium:automationName", "uiautomator2");
	    AndroidDriver driver;
	    
	    try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(5000));
			
			System.out.println("Scenario 1: - **Objective**: Verify that Airplane Mode is turned off on the OnePlus Nord 2 device.");
			driver.findElement(By.xpath("//*[@text='Connection & sharing']")).click();
			String AirplaneModestatus = driver.findElements(By.className("android.widget.Switch")).get(0).getAttribute("text");
			 if(AirplaneModestatus.equals("On")) {
				System.out.println("Airplane mode is On");
			}
			else if(AirplaneModestatus.equals("Off"))
				System.out.println("Airplane mode is Off");
			 driver.navigate().back();
			 
			 
			 System.out.println("Scenario 2: - **Objective**: In Wi-Fi settings, for the connected SSID, ensure the Proxy setting is set to 'None'.");
				
			 driver.findElement(By.xpath("//*[@text='Wi-Fi']")).click();
			 driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc='Wi-Fi details'])[1]")).click();
			 
	
			 driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Proxy\"))")).click(); 
	    
			 String proxyStatus = driver.findElement(By.xpath("(//android.widget.RadioButton[@resource-id=\"com.oplus.wirelesssettings:id/radio_button\"])[1]")).getAttribute("checked");
			 if(proxyStatus.equals("true")) {
				System.out.println("proxy is none");
			}
			else if(proxyStatus.equals("false"))
				System.out.println("proxy is not none");
			 
			 driver.navigate().back(); 
			 driver.navigate().back(); 
			 driver.navigate().back();
			 
			 
			 System.out.println("Scenario 3: - **Objective**: Verify that user certificates are present under Settings -> Security -> Credential");
				
			 driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Password & security\"))")).click(); 
			
			 
			 System.out.println("Scenario 4: - **Objective**: List all user certificates present in the device.");
				
			 driver.findElement(By.xpath("//*[@text=\"System security\"]")).click();
			 
			 driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Credential storage\"))")).click(); 
			 driver.findElement(By.xpath("//*[@text=\"Trusted certificates\"]")).click();	

			 
			 driver.findElement(By.xpath("//android.widget.TextView[@text=\"User\"]")).click();	
			 
			 
			 if(driver.findElement(By.id("com.android.settings:id/user_empty")).isDisplayed()) {
				 System.out.println("User cetificates list is empty");
			 }
			 else {
				 System.out.println("user list has certificate");
				 
			 }
			 
driver.findElement(By.xpath("//android.widget.TextView[@text=\"System\"]")).click();	
			 
			 
				 List<WebElement> listOfCertificates = driver.findElements(By.xpath("(//android.widget.TextView[@resource-id=\"com.android.settings:id/trusted_credential_subject_primary\"])"));
				 for(int i = 0 ; i<listOfCertificates.size(); i++) {
				 System.out.println( listOfCertificates.get(i).getAttribute("text"));
				
				 
			 }
			 
			
			 
			 
	    
	    } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	  
	}
}