package br.ce.wcaquino.tasks.functional;

import java.time.Clock;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TaskTest {
	
	
    public WebDriver openApp() {
        // Set up ChromeOptions with the desired preferences
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.images", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-setuid-sandbox");
        chromeOptions.addArguments("--remote-debugging-port=9222");
        chromeOptions.addArguments("--disable-dev-shm-using");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--headless");


        // Set up WebDriver with ChromeOptions
        WebDriver driver = new ChromeDriver(chromeOptions);
        
        // Navigate to the specified URL
        driver.navigate().to("http://mo76was1.fyre.ibm.com:8080/tasks");
        
        // Set implicit wait using WebDriverWait (you can adjust the timeout as needed)
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        return driver;
    }

	@Test
	public void saveTaskSuccesful() {
		WebDriver driver = openApp();
		try {
		
		//Clicar em ADD TO DO
		driver.findElement(By.id("addTodo")).click();
		
		//Escrever a descrição
		driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		
		//Escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		//Clicar em Salvar
		driver.findElement(By.id("saveButton")).click();
		
		//Validar a mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", message);
		
	} finally {
			//fechar o browser
			driver.quit();
	} 	
	}
	
	
	
	@Test
	public void blankTaskField() {
		WebDriver driver = openApp();
		try {
		
		//Clicar em ADD TO DO
		driver.findElement(By.id("addTodo")).click();
		
		//Escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		
		//Clicar em Salvar
		driver.findElement(By.id("saveButton")).click();
		
		//Validar a mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description", message);
		
	} finally {
		//fechar o browser
		driver.quit();
	} 	
	}


	@Test
	public void blankDateField() {
		WebDriver driver = openApp();
		try {
		
		//Clicar em ADD TO DO
		driver.findElement(By.id("addTodo")).click();
		
		//Escrever a data
		driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		
		//Clicar em Salvar
		driver.findElement(By.id("saveButton")).click();
		
		//Validar a mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", message);
		
	} finally {
		//fechar o browser
		driver.quit();
	} 	
	}
	
	
	@Test
	public void dateFieldIncorrect() {
		WebDriver driver = openApp();
		try {
		
		//Clicar em ADD TO DO
		driver.findElement(By.id("addTodo")).click();
		
		//Escrever a descrição
		driver.findElement(By.id("task")).sendKeys("Teste via selenium");
		
		//Escrever a data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
		
		//Clicar em Salvar
		driver.findElement(By.id("saveButton")).click();
		
		//Validar a mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", message);
		
	} finally {
		//fechar o browser
		driver.quit();
	} 	
	}

	
}
