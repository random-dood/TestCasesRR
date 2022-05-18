package com.midterm.demo;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;



public class MidTermDemo {
	
	public class Demos {
		private WebDriver driver;
		
		@Parameters({ "browser" })
		@BeforeMethod(alwaysRun = true)
		private void setUp(@Optional("firefox") String browser) {
			
			
//			Create driver
			switch (browser) {
			
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
				 driver = new FirefoxDriver();
				break;
			
			
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
				ChromeOptions options = new ChromeOptions ();

				options.addExtensions (new File("src/main/resources/extension_3_0_11_0.crx"));

				DesiredCapabilities capabilities = new DesiredCapabilities ();

				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				
				 driver = new ChromeDriver(capabilities);
				break;

			case "msedge":
				System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
				EdgeOptions op=new EdgeOptions();
				 
				op.addExtensions(new File("src/main/resources/16e681ed-f6ce-4c04-801a-47fe3cbd78ec.crx"));
				 
				 DesiredCapabilities cap = new DesiredCapabilities();
				 
				 cap.setCapability(EdgeOptions.CAPABILITY, op);
				 
				 op.merge(cap);
				
				 driver = new EdgeDriver(op);
				break;
				
				
			default:
				System.out.println("What are you doing? This is clearly not Firefox or Chrome. This is " + browser + 
						". This is not in the test cases. I'll just show you a chrome one instead by default.");
				System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
				
				ChromeOptions opt = new ChromeOptions ();

				opt.addExtensions (new File("src/main/resources/extension_3_0_11_0.crx"));

				DesiredCapabilities capa = new DesiredCapabilities ();

				capa.setCapability(ChromeOptions.CAPABILITY, opt);
				
				 driver = new ChromeDriver(capa);
				break;
				
				//This block of code can probably be done better but it gave me some weird synthax
				//Like I tried putting options then it told me it wasn't instantiated
				//Then I tried to instantiate it then it told me it was a duplicate, so wut??
			}
			
			driver.manage().window().maximize();

        }
		
		
		
	@Test
		public void TC_UpdateProject_04() {
			
		String url = "https://fierce-shelf-03672.herokuapp.com/";
		driver.get(url);
		
		System.out.println("Page is opened");
		
		WebElement createProjectButton = driver.findElement(By.xpath("//a[@class='btn btn-lg btn-info']"));
		createProjectButton.click();
		
		System.out.println("A project template has been created");
		
		WebElement projectName = driver.findElement(By.name("projectName"));
		projectName.sendKeys("Amazing new project");
		
		WebElement projectId = driver.findElement(By.name("projectIdentifier"));
		projectId.sendKeys("12345");
		
		WebElement projectDescript = driver.findElement(By.name("description"));
		projectDescript.sendKeys("An incredible project collaboration between the HCL testing team");
		
		WebElement startDate = driver.findElement(By.name("start_date"));
		startDate.sendKeys("05/13/2022");
		
		WebElement endDate = driver.findElement(By.name("end_date"));
		endDate.sendKeys("05/24/2022");
		
		WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
		submitButton.click();
		
		sleep(3000);
		
		WebElement updateButton = driver.findElement(By.xpath("//a[@href='/updateProject/12345']"));
		updateButton.click();
		
		sleep(2000);
		
		WebElement updateDescript = driver.findElement(By.name("description"));
		// The next two lines are to clear the input field
		updateDescript.sendKeys(Keys.CONTROL + "a");
		updateDescript.sendKeys(Keys.DELETE);
		updateDescript.sendKeys("Update Description Test");
		
		sleep(3000);
		
		WebElement submitUpdateButton = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
		submitUpdateButton.click();
		
		sleep(3000);
		
		
		WebElement deleteProject = driver.findElement(By.xpath("//li[@class='list-group-item delete']"));
		deleteProject.click();
		
		// Switching to Alert        
        Alert alert = driver.switchTo().alert();		
        		
        // Capturing alert message.    
        String alertMessage= driver.switchTo().alert().getText();		
        		
        // Displaying alert message		
        System.out.println(alertMessage);	
        sleep(3000);
        		
        // This makes it click OK, I believe alert.dismiss() makes it hit Cancel		
        alert.accept();		
        
        sleep(2000);
		
		
		
		
		
		
					
		
	}
		
		@Test
		public void TC_Delete_01() {
			
		System.out.println("Testing to see if Delete project button is working");
			
		String url = "https://fierce-shelf-03672.herokuapp.com/";
		driver.get(url);
		
		System.out.println("Page is opened");
		
		sleep(3000);
		
		WebElement createProjectButton = driver.findElement(By.xpath("//a[@class='btn btn-lg btn-info']"));
		createProjectButton.click();
		
		System.out.println("A project template has been created");
		
		WebElement projectName = driver.findElement(By.name("projectName"));
		projectName.sendKeys("Test Deletion");
		
		WebElement projectId = driver.findElement(By.name("projectIdentifier"));
		projectId.sendKeys("12345");
		
		WebElement projectDescript = driver.findElement(By.name("description"));
		projectDescript.sendKeys("Testing if deletion is working properly");
		
		WebElement startDate = driver.findElement(By.name("start_date"));
		startDate.sendKeys("05/13/2022");
		
		WebElement endDate = driver.findElement(By.name("end_date"));
		endDate.sendKeys("05/24/2022");
		
		WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
		submitButton.click();
		
		sleep(3000);
		
		WebElement updateButton = driver.findElement(By.xpath("//a[@href='/updateProject/12345']"));
		Assert.assertTrue(updateButton.isDisplayed(), "The project is not visible");
		
		System.out.println("The project is visible");
		
		WebElement deleteProject = driver.findElement(By.xpath("//li[@class='list-group-item delete']"));
		deleteProject.click();
		
		// Switching to Alert        
        Alert alert = driver.switchTo().alert();		
        		
        // Capturing alert message.    
        String alertMessage= driver.switchTo().alert().getText();		
        		
        // Displaying alert message		
        System.out.println(alertMessage);	
        sleep(3000);
        		
        // This makes it click OK, I believe alert.dismiss() makes it hit Cancel		
        alert.accept();		
        
        sleep(3000);
		
        try {
		Assert.assertFalse(driver.findElement(By.xpath("//a[@href='/updateProject/12345']")).isDisplayed(), "The project is still there");
		}
        catch(NoSuchElementException e) {
        	
        	System.out.println("The project is no longer there");
        	
        }
        
		
		}
		@Test
		public void TC_Delete_02() {
			
		System.out.println("Testing to see if Delete project button is working");
			
		String url = "https://fierce-shelf-03672.herokuapp.com/";
		driver.get(url);
		
		System.out.println("Page is opened");
		
		sleep(3000);
		
		WebElement createProjectButton = driver.findElement(By.xpath("//a[@class='btn btn-lg btn-info']"));
		createProjectButton.click();
		
		System.out.println("A project template has been created");
		
		WebElement projectName = driver.findElement(By.name("projectName"));
		projectName.sendKeys("Test Deletion");
		
		WebElement projectId = driver.findElement(By.name("projectIdentifier"));
		projectId.sendKeys("12345");
		
		WebElement projectDescript = driver.findElement(By.name("description"));
		projectDescript.sendKeys("Testing if deletion is working properly");
		
		WebElement startDate = driver.findElement(By.name("start_date"));
		startDate.sendKeys("05/13/2022");
		
		WebElement endDate = driver.findElement(By.name("end_date"));
		endDate.sendKeys("05/24/2022");
		
		WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
		submitButton.click();
		
		sleep(3000);
		
		WebElement updateButton = driver.findElement(By.xpath("//a[@href='/updateProject/12345']"));
		Assert.assertTrue(updateButton.isDisplayed(), "The project is not visible");
		
		System.out.println("The project is visible");
		
		WebElement deleteProject = driver.findElement(By.xpath("//li[@class='list-group-item delete']"));
		deleteProject.click();
		
		// Switching to Alert        
        Alert alert = driver.switchTo().alert();		
        		
        // Capturing alert message.    
        String alertMessage= driver.switchTo().alert().getText();		
        		
        // Displaying alert message		
        System.out.println(alertMessage);	
        sleep(3000);
        		
        // This makes it click OK, I believe alert.dismiss() makes it hit Cancel		
        alert.dismiss();
        
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='/updateProject/12345']")).isDisplayed(), "The project is not there");
        System.out.println("The project is still there");
        
        sleep(3000);
        
        WebElement deleteProject1 = driver.findElement(By.xpath("//li[@class='list-group-item delete']"));
		deleteProject1.click();
		
		// Capturing alert message.    
        String alertMessage1= driver.switchTo().alert().getText();		
        		
        // Displaying alert message		
        System.out.println(alertMessage1);	
        sleep(3000);
        		
        // This makes it click OK, I believe alert.dismiss() makes it hit Cancel		
        alert.accept();
        
		}
		
		@Test
		public void TC_URL_03() {
			
			String url = "https://fierce-shelf-03672.herokuapp.com/";
			driver.get(url);
		
		System.out.println("Page is opened");
		
		String expectedUrl1 = "https://fierce-shelf-03672.herokuapp.com/";
		String actualUrl1 = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl1, expectedUrl1, "This is not actually the web page that it was asserted it be");
		System.out.println("dashboard url");
		
		sleep(3000);
		
		WebElement registerButton = driver.findElement(By.xpath("//a[@href='register.html']"));
		registerButton.click();
		
		String expectedUrl = "https://fierce-shelf-03672.herokuapp.com/register.html";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "This is not actually the web page that it was asserted it be");
		System.out.println("Sign up url");
		
		sleep(3000);
		
		}
		
		@Test
		public void TC_URL_04() {
			
			String url = "https://fierce-shelf-03672.herokuapp.com/";
			driver.get(url);
		
		System.out.println("Page is opened");
		
		String expectedUrl1 = "https://fierce-shelf-03672.herokuapp.com/";
		String actualUrl1 = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl1, expectedUrl1, "This is not actually the web page that it was asserted it be");
		System.out.println("dashboard url");
		
		sleep(3000);
		
		WebElement loginButton = driver.findElement(By.xpath("//a[@href='login.html']"));
		loginButton.click();
		
		String expectedUrl = "https://fierce-shelf-03672.herokuapp.com/login.html";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "This is not actually the web page that it was asserted it be");
		System.out.println("Login url");
		
		sleep(3000);
		
		}
		
		@Test
		public void TC_URL_05() {
			
			String url = "https://fierce-shelf-03672.herokuapp.com/";
			driver.get(url);
		
		System.out.println("Page is opened");
		
		String expectedUrl1 = "https://fierce-shelf-03672.herokuapp.com/";
		String actualUrl1 = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl1, expectedUrl1, "This is not actually the web page that it was asserted it be");
		System.out.println("dashboard url");
		
		sleep(3000);
		
		WebElement createProjectButton = driver.findElement(By.xpath("//a[@href='/addProject']"));
		createProjectButton.click();
		
		String expectedUrl = "https://fierce-shelf-03672.herokuapp.com/addProject";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "This is not actually the web page that it was asserted it be");
		System.out.println("Create project url");
		
		sleep(3000);
		
		}
		
	@Test
		public void TC_URL_02() {
			
			String url = "https://fierce-shelf-03672.herokuapp.com/";
			driver.get(url);
		
		System.out.println("Page is opened");
		
		WebElement loginButton = driver.findElement(By.xpath("//a[@href='/addProject']"));
		loginButton.click();
		
		String expectedUrl1 = "https://fierce-shelf-03672.herokuapp.com/addProject";
		String actualUrl1 = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl1, expectedUrl1, "This is not actually the web page that it was asserted it be");
		System.out.println("create project url");
		
		sleep(3000);
		
		WebElement dashboardButton = driver.findElement(By.xpath("//a[@href='/dashboard']"));
		dashboardButton.click();
		
		String expectedUrl = "https://fierce-shelf-03672.herokuapp.com/dashboard";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "This is not actually the web page that it was asserted it be");
		System.out.println("dashboard url");
		
		sleep(3000);
		
		}
		
		@Test
		public void TC_URL_01() {
			
			String url = "https://fierce-shelf-03672.herokuapp.com/";
			driver.get(url);
		
		System.out.println("Page is opened");
		
		String expectedUrl1 = "https://fierce-shelf-03672.herokuapp.com/";
		String actualUrl1 = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl1, expectedUrl1, "This is not actually the web page that it was asserted it be");
		System.out.println("dashboard url");
		
		sleep(3000);
		
		WebElement createProjectButton = driver.findElement(By.xpath("//a[@href='Dashboard.html']"));
		createProjectButton.click();
		
		String expectedUrl = "https://fierce-shelf-03672.herokuapp.com/Dashboard.html";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "This is not actually the web page that it was asserted it be");
		System.out.println("Personal Project management url");
		
		sleep(3000);
		
		}
		
		@Test
		public void TC_UpdateProject_06() {
			
		String url = "https://fierce-shelf-03672.herokuapp.com/";
		driver.get(url);
		
		System.out.println("Page is opened");
		
		WebElement createProjectButton = driver.findElement(By.xpath("//a[@class='btn btn-lg btn-info']"));
		createProjectButton.click();
		
		System.out.println("A project template has been created");
		
		WebElement projectName = driver.findElement(By.name("projectName"));
		projectName.sendKeys("Amazing new project");
		
		WebElement projectId = driver.findElement(By.name("projectIdentifier"));
		projectId.sendKeys("12345");
		
		WebElement projectDescript = driver.findElement(By.name("description"));
		projectDescript.sendKeys("An incredible project collaboration between the HCL testing team");
		
		WebElement startDate = driver.findElement(By.name("start_date"));
		startDate.sendKeys("05/13/2022");
		
		WebElement endDate = driver.findElement(By.name("end_date"));
		endDate.sendKeys("05/24/2022");
		
		WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
		submitButton.click();
		
		sleep(3000);
		
		WebElement updateButton = driver.findElement(By.xpath("//a[@href='/updateProject/12345']"));
		updateButton.click();
		
		sleep(2000);
		
		WebElement updateEndDate = driver.findElement(By.name("end_date"));
		// The next two lines are to clear the input field
		updateEndDate.sendKeys(Keys.CONTROL + "a");
		updateEndDate.sendKeys(Keys.DELETE);
		updateEndDate.sendKeys("06/23/2022");
		
		sleep(3000);
		
		WebElement submitUpdateButton = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
		submitUpdateButton.click();
		
		sleep(3000);
		
		WebElement deleteProject = driver.findElement(By.xpath("//li[@class='list-group-item delete']"));
		deleteProject.click();
		
		// Switching to Alert        
        Alert alert = driver.switchTo().alert();		
        		
        // Capturing alert message.    
        String alertMessage= driver.switchTo().alert().getText();		
        		
        // Displaying alert message		
        System.out.println(alertMessage);	
        sleep(3000);
        		
        // This makes it click OK, I believe alert.dismiss() makes it hit Cancel		
        alert.accept();		
        
        sleep(3000);
		
		}
		
		@Test
		public void TC_UpdateProject_05() {
			
		String url = "https://fierce-shelf-03672.herokuapp.com/";
		driver.get(url);
		
		System.out.println("Page is opened");
		
		WebElement createProjectButton = driver.findElement(By.xpath("//a[@class='btn btn-lg btn-info']"));
		createProjectButton.click();
		
		System.out.println("A project template has been created");
		
		WebElement projectName = driver.findElement(By.name("projectName"));
		projectName.sendKeys("Amazing new project");
		
		WebElement projectId = driver.findElement(By.name("projectIdentifier"));
		projectId.sendKeys("12345");
		
		WebElement projectDescript = driver.findElement(By.name("description"));
		projectDescript.sendKeys("An incredible project collaboration between the HCL testing team");
		
		WebElement startDate = driver.findElement(By.name("start_date"));
		startDate.sendKeys("05/13/2022");
		
		WebElement endDate = driver.findElement(By.name("end_date"));
		endDate.sendKeys("05/24/2022");
		
		WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
		submitButton.click();
		
		sleep(3000);
		
		WebElement updateButton = driver.findElement(By.xpath("//a[@href='/updateProject/12345']"));
		updateButton.click();
		
		sleep(2000);
		
		WebElement updateStartDate = driver.findElement(By.name("start_date"));
		// The next two lines are to clear the input field
		updateStartDate.sendKeys(Keys.CONTROL + "a");
		updateStartDate.sendKeys(Keys.DELETE);
		updateStartDate.sendKeys("07/04/2022");
		
		sleep(3000);
		
		WebElement submitUpdateButton = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
		submitUpdateButton.click();
		
		sleep(3000);
		
		WebElement deleteProject = driver.findElement(By.xpath("//li[@class='list-group-item delete']"));
		deleteProject.click();
		
		// Switching to Alert        
        Alert alert = driver.switchTo().alert();		
        		
        // Capturing alert message.    
        String alertMessage= driver.switchTo().alert().getText();		
        		
        // Displaying alert message		
        System.out.println(alertMessage);	
        sleep(3000);
        		
        // This makes it click OK, I believe alert.dismiss() makes it hit Cancel		
        alert.accept();		
        
        sleep(3000);
		
		}
		
//		@Test
		public void ExpandTextArea() {
			
		String url = "https://fierce-shelf-03672.herokuapp.com/";
		driver.get(url);
		
		System.out.println("Page is opened");
		
		WebElement createProjectButton = driver.findElement(By.xpath("//a[@class='btn btn-lg btn-info']"));
		createProjectButton.click();
		
		System.out.println("A project template has been created");
		
		WebElement projectName = driver.findElement(By.name("projectName"));
		projectName.sendKeys("Amazing new project");
		
		WebElement projectId = driver.findElement(By.name("projectIdentifier"));
		projectId.sendKeys("12345");
		
		WebElement projectDescript = driver.findElement(By.name("description"));
		projectDescript.sendKeys("An incredible project collaboration between the HCL testing team");
		
		WebElement startDate = driver.findElement(By.name("start_date"));
		startDate.sendKeys("05/13/2022");
		
		WebElement endDate = driver.findElement(By.name("end_date"));
		endDate.sendKeys("05/24/2022");
		
		WebElement submitButton = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
		submitButton.click();
		
		sleep(3000);
		
		WebElement updateButton = driver.findElement(By.xpath("//a[@href='/updateProject/12345']"));
		updateButton.click();
		
		sleep(2000);
		
		WebElement resizeThis = driver.findElement(By.xpath("//textarea[@placeholder='Project Description']"));
		
		
		
		Actions action = new Actions(driver);
		action.moveToElement(resizeThis, 360, 40); //moves to bottom right corner
		action.clickAndHold();
		action.moveByOffset(0, -100); 
		action.release();
		action.perform();
		sleep(3000);
		
		WebElement submitUpdateButton = driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block mt-4']"));
		submitUpdateButton.click();
		
		WebElement deleteProject = driver.findElement(By.xpath("//li[@class='list-group-item delete']"));
		deleteProject.click();
		
		// Switching to Alert        
        Alert alert = driver.switchTo().alert();		
        		
        // Capturing alert message.    
        String alertMessage= driver.switchTo().alert().getText();		
        		
        // Displaying alert message		
        System.out.println(alertMessage);	
        sleep(3000);
        		
        // This makes it click OK, I believe alert.dismiss() makes it hit Cancel		
        alert.accept();		
        
        sleep(3000);
		
		}
		
		
		
		private void sleep(long m) {
			try {
				Thread.sleep(m);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@AfterMethod(alwaysRun = true)
		private void tearDown() {
			// Close browser
			driver.quit();
		}
  }
	
}
