package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;

public class HomePageTests extends BaseTests {

	@Test
	public void testContarProdutos_catorzeProdutoDiferentes() {
		carregarPaginaInicial();
		assertThat(homePage.contarProdutos(), is(14));
	}

	@Test
	public void testValidarCarrinhoZerado_ZeroItensNoCarrinho() {
		String produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
		/*System.out.println(produtosNoCarrinho);*/
		assertThat(produtosNoCarrinho, is("empty"));
	}

	
	LoginPage loginPage;
	@Test
	public void testLoginComSucesso_UsuarioLogado() {
		// Clicar no bot�o Sign In na home page
		
		loginPage = homePage.clicarBotaoSignIn();

		// Preencher usu�rio e Senha
		loginPage.preeencherEmail("weslen@teste.com");
		loginPage.preeencherPassword("teste12345");

		// Clicar no bot�o Sing In para logar
		loginPage.clicarBotaoSignIn();

		// Validar se o usu�rio est� logado de fato
		assertThat(homePage.estaLogado("weslen teste"), is(true));
		
		carregarPaginaInicial();
	}
	
	
	ProdutoPage produtoPage;
	@Test
	public void TestValidarDetalhesDoProduto_DescricaoEValorIguais() {
		int indice = 0;
		
		String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
		String precoProduto_HomePage = homePage.obterPrecoProduto(indice);
		
		  System.out.println(nomeProduto_HomePage);
	      System.out.println(precoProduto_HomePage);	
	      
	     produtoPage = homePage.clicarProduto(indice);
	     
	     String nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
	     String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();
	     
		 System.out.println(nomeProduto_ProdutoPage);
		 System.out.println(precoProduto_ProdutoPage);	  		  
	}
	

	@Test
	public void incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso() {
		
		//Pr�-condi��o
		//usu�rio logado
		if(!homePage.estaLogado("weslen teste")) {
			testLoginComSucesso_UsuarioLogado();
		}
		
		//--Teste
		//Selecionar Produto
		TestValidarDetalhesDoProduto_DescricaoEValorIguais();
		
		//Selecionar tamanho
		List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		
		System.out.println(listaOpcoes.get(0));
		System.out.println("Tamanho da Lista : " + listaOpcoes.size());
		
		produtoPage.selecionarOpcaoDropDown("M");
		
		listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		
		System.out.println(listaOpcoes.get(0));
		System.out.println("Tamanho da Lista : " + listaOpcoes.size());
		
		//Selecionar Cor
		produtoPage.selecionarCorAzul();
		
		//Selecionar quantidade
		produtoPage.alterarQuantidade(2);
		
		//Adicionar no carrinho
		ModalProdutoPage modalProdutoPage = produtoPage.clicarBotaoAddToCart();
		
		//assertThat(modalProdutoPage.obterMensagemProdutoAdicionado(), is ("Product successfully added to your shopping cart"));	
		
		//assertTrue(modalProdutoPage.obterMensagemProdutoAdicionado().endsWith("Product successfully added to your shopping cart"));
	}
}
