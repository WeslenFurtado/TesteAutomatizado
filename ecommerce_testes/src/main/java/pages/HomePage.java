package pages;

//import static base.BaseTests.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private WebDriver driver;

	List<WebElement> listaProdutos = new ArrayList();

	private By textoProdutosNoCarrinho = By.className("ajax_cart_no_product");

	private By produtos = By.className("product-container");
	
	private By descricoesDosProdutos = By.cssSelector("a.product-name");
	
	private By precoDosProdutos = By.cssSelector("span.price.product-price");
	
	private By botaoSignIn = By.xpath("//a[@class='login']");

	private By usuarioLogado = By.cssSelector(".header_user_info span");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public int contarProdutos() {
		carregarListaProdutos();
		return listaProdutos.size();
	}
	
	private void carregarListaProdutos() {
		listaProdutos = driver.findElements(produtos);
	}


	public String obterQuantidadeProdutosNoCarrinho() {
		String quantidadeProdutosNoCarrinho = driver.findElement(textoProdutosNoCarrinho).getText();
		quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace("(", "");
		quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho.replace(")", "");

		String qtdProdutosNoCarrinho = quantidadeProdutosNoCarrinho;

		return qtdProdutosNoCarrinho;
	}

	public String obterNomeProduto(int indice) {
		return driver.findElements(descricoesDosProdutos).get(indice).getText();
	}
	
	public String obterPrecoProduto(int indice ) {
		return driver.findElements(precoDosProdutos).get(indice).getText();
	}
	
	public ProdutoPage clicarProduto( int indice) {
		driver.findElements(descricoesDosProdutos).get(indice).click();
		return new ProdutoPage(driver);
	}
		
	public LoginPage clicarBotaoSignIn() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);// instrução para fazer o cenário esperar 5
																		// segundos antes de clicar em Sign in
		driver.findElement(botaoSignIn).click();
		return new LoginPage(driver);
	}

	public boolean estaLogado(String texto) {
		return texto.contentEquals(driver.findElement(usuarioLogado).getText());
	}
	
}
