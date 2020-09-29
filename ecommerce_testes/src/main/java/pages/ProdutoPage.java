package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProdutoPage {

	private WebDriver driver;

	private By nomeProduto = By.cssSelector("div.pb-center-column.col-xs-12.col-sm-4 h1");

	private By precoProduto = By.cssSelector("span#our_price_display");

	private By tamanhoProduto = By.id("group_1");
	
	private By corAzul = By.id("color_14");
	
	private By quantidadeProduto = By.id("quantity_wanted");
	
	private By botaoAddToCart = By.id("add_to_cart");

	public ProdutoPage(WebDriver driver) {
		this.driver = driver;
	}

	public String obterNomeProduto() {
		return driver.findElement(nomeProduto).getText();
	}

	public String obterPrecoProduto() {
		return driver.findElement(precoProduto).getText();
	}
	
	public void selecionarOpcaoDropDown(String opcao) {
		encontrarDropdownSize().selectByVisibleText(opcao);
	}
	
	public List<String> obterOpcoesSelecionadas(){
		List<WebElement> elementosSelecionados = 
		encontrarDropdownSize().getAllSelectedOptions();
		
		List<String> listaOpcoesSelecionadas = new ArrayList();
		for ( WebElement elemento : elementosSelecionados ) {
			listaOpcoesSelecionadas.add(elemento.getText());	
		}
		return listaOpcoesSelecionadas;
		
	}

	public Select encontrarDropdownSize() {
		return new Select(driver.findElement(tamanhoProduto));
	}
	
	public void selecionarCorAzul() {
		driver.findElement(corAzul).click();
	}
	
	public void alterarQuantidade(int quantidade) {
		driver.findElement(quantidadeProduto).clear();
		driver.findElement(quantidadeProduto).sendKeys(Integer.toString(quantidade));		
	}
	
	public ModalProdutoPage clicarBotaoAddToCart() {
		driver.findElement(botaoAddToCart).click();
		return new ModalProdutoPage(driver);
	}
}
