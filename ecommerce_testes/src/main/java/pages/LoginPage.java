package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;

	private By email = By.id("email");

	private By password = By.id("passwd");

	private By botaoSignIn = By.id("SubmitLogin");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void preeencherEmail(String texto) {
		driver.findElement(email).sendKeys(texto);
	}

	public void preeencherPassword(String texto) {
		driver.findElement(password).sendKeys(texto);
	}

	public void clicarBotaoSignIn() {
		driver.findElement(botaoSignIn).click();
	}

}
