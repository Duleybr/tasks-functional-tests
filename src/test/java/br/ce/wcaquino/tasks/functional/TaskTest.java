package br.ce.wcaquino.tasks.functional;

import java.time.Clock;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TaskTest {
	
	
	public WebDriver openApp() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://mo76was1.fyre.ibm.com:8080/tasks");
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	@SuppressWarnings("deprecation")
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
	
	
	
	@SuppressWarnings("deprecation")
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

	@SuppressWarnings("deprecation")
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
	
	@SuppressWarnings("deprecation")
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
