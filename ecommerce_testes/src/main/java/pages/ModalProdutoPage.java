package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ModalProdutoPage {
	
	private WebDriver  driver;
	
	private By mensagemProdutoAdicionado = By.cssSelector("//div[@class='layer_cart_product col-xs-12 col-md-6']/h2[1]");
	
	public ModalProdutoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String obterMensagemProdutoAdicionado() {
		FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(mensagemProdutoAdicionado));
		
		return driver.findElement(mensagemProdutoAdicionado).getText();
	}

}
