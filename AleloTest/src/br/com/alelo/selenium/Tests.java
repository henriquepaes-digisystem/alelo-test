package br.com.alelo.selenium;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

class Tests {

	@Test
	void testSearchDiretor() {
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
        try {
            driver.get("https://google.com/ncr");
            driver.findElement(By.name("q")).sendKeys("Tim Burton" + Keys.ENTER);
            
            List<WebElement> listaResultados = driver.findElements(By.cssSelector("h3"));
            
            assertNotNull(listaResultados);
        } finally {
            driver.quit();
        }						
	}
	
	@Test
	void testSearchFilmes() {
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
        try {
            driver.get("https://google.com/ncr");
            driver.findElement(By.name("q")).sendKeys("Matrix" + Keys.ENTER);
            
            List<WebElement> listaResultados = driver.findElements(By.cssSelector("h3"));
            
            assertNotNull(listaResultados);
        } finally {
            driver.quit();
        }						
	}	

}