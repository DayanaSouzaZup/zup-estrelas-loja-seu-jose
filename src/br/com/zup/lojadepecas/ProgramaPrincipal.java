package br.com.zup.lojadepecas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import br.com.zup.DAO.PecasDAO;
import br.com.zup.POJO.PecasPojo;
import br.com.zup.connectionfactory.ConnectionFactory;

public class ProgramaPrincipal {

	public static void cabecalho() {
		System.out.println("-----------------------------------------");
		System.out.println("*      Loja de peças do Seu José        *");
		System.out.println("-----------------------------------------");
	}

	public static void menuPrincipal() {

		System.out.println("\n(A) - Gestão de peças");
		System.out.println("(B) - Gestão de vendas");
		System.out.println("(C) - Finalizar programa");

	}

	public static void menuPecas() {

		System.out.println("\n(1) - Cadastrar nova peça");
		System.out.println("(2) - Consultar peça");
		System.out.println("(3) - Listar peças em estoque");
		System.out.println("(4) - Listar peças iniciadas com a mesma letra");
		System.out.println("(5) - Listar peças por um modelo de carro");
		System.out.println("(6) - Listar peças por categoria");
		System.out.println("(7) - Remover peça do estoque");
		System.out.println("(0) - Voltar para o menu principal");

	}

	public static void menuVendas() {

		System.out.println("\n(1) - Realizar venda");
		System.out.println("(2) - Relatório de vendas");
		System.out.println("(0) - Voltar para o menu principal");

	}

	public static void cadastrarNovaPeca(Scanner teclado, PecasDAO pecasDao) {

		System.out.print("\nDigite o código de barras: ");
		String codigoDeBarras = teclado.next();

		teclado.nextLine();
		System.out.print("\nDigite o nome da peça: ");
		String nomePeca = teclado.nextLine();

		System.out.print("\nDigite o modelo do carro: ");
		String modeloCarro = teclado.next();

		System.out.print("\nDigite o fabricante: ");
		String fabricante = teclado.next();

		System.out.print("\nDigite o preço de custo: ");
		float precoCusto = teclado.nextFloat();

		System.out.print("\nDigite o preço de venda: ");
		float precoVenda = teclado.nextFloat();

		System.out.print("\nDigite a quantidade em estoque: ");
		int quantidadeEstoque = teclado.nextInt();

		teclado.nextLine();
		System.out.print("\nDigite a categoria: ");
		String categoria = teclado.nextLine();

		PecasPojo peca = new PecasPojo(codigoDeBarras, nomePeca, modeloCarro, fabricante, precoCusto, precoVenda,
				quantidadeEstoque, categoria);

		pecasDao.cadastrarPeca(peca);

	}

	public static void consultarPecaPorCodigoBarra(Scanner teclado, PecasDAO pecasDao) {

		System.out.print("\nInforme o número do código de barras: ");
		String codigoBarra = teclado.next();

		PecasPojo pecas = pecasDao.consultarPecaPorCodigoBarra(codigoBarra);

		System.out.printf("\nRelação das peças com código de barras %s: \n\n", codigoBarra);

		System.out.println("Código de Barras = " + pecas.getCodigoBarra() + " Nome = " + pecas.getNome()
				+ " Modelo do carro = " + pecas.getModeloCarro() + " Fabricante = " + pecas.getFabricante()
				+ " Preço de Custo = R$" + pecas.getPrecoCusto() + " Preço de Venda = R$" + pecas.getPrecoVenda()
				+ " Quantidade Estoque = " + pecas.getQuantidadeEstoque() + " Categoria = " + pecas.getCategoria());

	}

	public static void listarPecasEstoque(Scanner teclado, PecasDAO pecasDao) {

		List<PecasPojo> estoqueBd = pecasDao.listarPecasEstoque();
		
		System.out.println("\nRelação das peças em estoque: \n");

		for (PecasPojo listaEstoque : estoqueBd) {
			System.out.println(listaEstoque);
		}

	}

	public static void listarPecasPorLetra(Scanner teclado, PecasDAO pecasDao) {

		System.out.print("\nDigite uma letra para buscar uma peça:");
		String letra = teclado.next().toUpperCase();

		List<PecasPojo> pecasLetra = pecasDao.listarPecaPorLetra(letra);

		System.out.printf("\nRelação das peças inciadas por %s: \n\n", letra);

		for (PecasPojo nomePecas : pecasLetra) {
			System.out.println(nomePecas);

		}
	}

	public static void listarPecasPorModelo(Scanner teclado, PecasDAO pecasDao) {

		System.out.print("\nDigite o modelo do carro: ");
		String modelo = teclado.next();

		List<PecasPojo> pecasModelo = pecasDao.listarPecaPorModelo(modelo);

		System.out.printf("\nRelação das peças para o modelo %s: \n\n", modelo);

		for (PecasPojo modelos : pecasModelo) {
			System.out.println(modelos);
		}

	}

	public static void listarPecasPorCategoria(Scanner teclado, PecasDAO pecasDao) {

		String opcao = "";
		String categoria = "";

		System.out.println("\n(1) - Som e vídeo");
		System.out.println("(2) - Personalização");
		System.out.println("(3) - Performance");
		System.out.print("\nEscolha uma das opções acima: ");
		opcao = teclado.next();

		switch (opcao) {
		
		case "1":
			
			categoria = "Som e vídeo";
			
			List<PecasPojo> pecasCategoria1 = pecasDao.listarPecaPorCategoria(categoria);
			
			System.out.printf("\nRelação das peças para a categoria %s: \n\n", categoria);
			
			for (PecasPojo categorias : pecasCategoria1) {
				System.out.println(categorias);
			}

			break;

		case "2":
			
			categoria = "Personalização";
			
			List<PecasPojo> pecasCategoria2 = pecasDao.listarPecaPorCategoria(categoria);
			
			System.out.printf("\nRelação das peças para a categoria %s: \n\n", categoria);
			
			for (PecasPojo categorias : pecasCategoria2) {
				System.out.println(categorias);
			}

			break;

		case "3":
			
			categoria = "Perfomance";
			
			List<PecasPojo> pecasCategoria3 = pecasDao.listarPecaPorCategoria(categoria);
			
			System.out.printf("\nRelação das peças para a categoria %s: \n\n", categoria);
			
			for (PecasPojo categorias : pecasCategoria3) {
				System.out.println(categorias);
			}

			break;
			
		}

	}

	public static void removerPeca(Scanner teclado, PecasDAO pecasDao) {
		
		System.out.print("\nDigite o código de barras da peça que deseja excluir: ");
		String codigoDeBarra = teclado.next();
		
		pecasDao.removerPeca(codigoDeBarra);

	}

	public static List<PecasPojo> realizaVenda(Scanner teclado, PecasDAO pecasDao, List<PecasPojo> relatorioVendas, PecasPojo pecasPojo) {
		
		System.out.print("\nDigite o código de barras: ");
		String codigoBarra = teclado.next();
		
		System.out.print("Digite a quantidade desejada: ");
		int quantidadeDesejada = teclado.nextInt();

		pecasPojo = pecasDao.consultarPecaPorCodigoBarra(codigoBarra);

		while (pecasPojo.getQuantidadeEstoque() < quantidadeDesejada) {
			System.out.println("\nNão temos a quantidade desejada em estoque. \nA quantidade disponível em estoque é de: "
					+ pecasPojo.getQuantidadeEstoque());
			System.out.print("\nInfome novamente a quantidade desejada: ");
			quantidadeDesejada = teclado.nextInt();
			pecasPojo = pecasDao.consultarPecaPorCodigoBarra(codigoBarra);

		}

		pecasDao.realizaVenda(pecasPojo, quantidadeDesejada);

		pecasPojo.setQuantidadeEstoque(quantidadeDesejada);
		pecasPojo.setPrecoVenda(pecasPojo.getPrecoVenda() * quantidadeDesejada);

		relatorioVendas.add(pecasPojo);
		
		return relatorioVendas;

	}

	public static void imprimeRelatorioVendas(PecasDAO pecasDao, List<PecasPojo> relatorioVendas) {
		
		double valorTotal = 0;
		
		System.out.println("\nRelatório de vendas dia " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + ":\n");
		
		for (PecasPojo vendas : relatorioVendas) {
		
			System.out.println("Codigo de Barras = " + vendas.getCodigoBarra() + " Nome = " + vendas.getNome()
					+ " Quantidade = " + vendas.getQuantidadeEstoque() + " Valor =  " + vendas.getPrecoVenda());
			valorTotal += vendas.getPrecoVenda();
			
		}
		
		System.out.printf("\nValor total de vendas: %.2f\n\n", valorTotal);

	}

	public static void main(String[] args) throws SQLException {
		Connection conn = new ConnectionFactory().getConnection();
		List<PecasPojo> relatorioVendas = new ArrayList<PecasPojo>();
		PecasPojo pecasPojo = new PecasPojo();
		PecasDAO pecasDao = new PecasDAO();
		Scanner teclado = new Scanner(System.in);
		String opcaoPrimaria = "";
		String opcaoPecas = "";
		String opcaoVendas = "";

		cabecalho();

		do {
			menuPrincipal();

			System.out.print("\nEscolha uma das opções acima: ");
			opcaoPrimaria = teclado.next().toUpperCase();

			switch (opcaoPrimaria) {

			case "A":

				do {

					menuPecas();
					System.out.print("\nDigite uma das opções acima: ");
					opcaoPecas = teclado.next();

					switch (opcaoPecas) {

					case "1":

						cadastrarNovaPeca(teclado, pecasDao);

						break;

					case "2":

						consultarPecaPorCodigoBarra(teclado, pecasDao);

						break;

					case "3":

						listarPecasEstoque(teclado, pecasDao);

						break;

					case "4":

						listarPecasPorLetra(teclado, pecasDao);

						break;

					case "5":

						listarPecasPorModelo(teclado, pecasDao);

						break;

					case "6":

						listarPecasPorCategoria(teclado, pecasDao);

						break;

					case "7":

						removerPeca(teclado, pecasDao);

						break;

					case "0":

						break;

					default:

						System.out.println("\nOpção inválida.Tente uma opção de '0' a '7'.");

						break;
					}

				} while (!opcaoPecas.equals("0"));

			case "B":

				do {

					menuVendas();
					System.out.print("\nEscolha uma das opções acima: ");
					opcaoVendas = teclado.next();

					switch (opcaoVendas) {

					case "1":

						relatorioVendas = realizaVenda(teclado, pecasDao, relatorioVendas, pecasPojo);

						break;

					case "2":

						imprimeRelatorioVendas(pecasDao, relatorioVendas);

						pecasDao.arquivoVendas(relatorioVendas);

						break;

					case "0":

						break;

					default:

						System.out.println("\nOpção inválida.Tente uma opção de '0' a '2'.");

						break;
					}

				} while (!opcaoVendas.equals("0"));

				break;

			case "C":

				System.out.println("\nObrigada por utilizar nosso sistema! =D ");

				break;

			default:
				System.out.println("\nOpção inválida. Tente uma opção de 'A' a 'C'.");
				break;
			}

		} while (!opcaoPrimaria.equals("C"));

		teclado.close();
		conn.close();

	}

}