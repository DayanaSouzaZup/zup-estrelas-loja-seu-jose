package br.com.zup.lojadepecas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
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

	public static void menuSecundario() {
		System.out.println("\n(1) - Cadastrar nova peça");
		System.out.println("(2) - Consultar peça");
		System.out.println("(3) - Listar peças em estoque");
		System.out.println("(4) - Listar peças iniciadas com a mesma letra");
		System.out.println("(5) - Listar peças por um modelo de carro");
		System.out.println("(6) - Listar peças por categoria");
		System.out.println("(7) - Remover peça do estoque");
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
		List<PecasPojo> pecasBd = pecasDao.consultarPecaPorCodigoBarra(codigoBarra);
		System.out.printf("\nRelação das peças com código de barras %s: \n\n" , codigoBarra);
		for (PecasPojo pecas : pecasBd) {
			System.out.println(pecas);
		}
	}

	public static void main(String[] args) throws SQLException {
		Connection conn = new ConnectionFactory().getConnection();
		Scanner teclado = new Scanner(System.in);
		PecasDAO pecasDao = new PecasDAO();
		String opcaoPrimaria = "";

		cabecalho();

		do {
			menuPrincipal();
			System.out.print("\nEscolha uma das opções acima: ");
			opcaoPrimaria = teclado.next().toUpperCase();

			switch (opcaoPrimaria) {
			case "A":

				String opcaoSecundaria = "";
				menuSecundario();
				System.out.print("\nDigite uma das opções acima: ");
				opcaoSecundaria = teclado.next();

				do {
					switch (opcaoSecundaria) {

					case "1":
						cadastrarNovaPeca(teclado, pecasDao);

						break;

					case "2":
						consultarPecaPorCodigoBarra(teclado, pecasDao);

						break;

					case "3":

						break;

					case "4":

						break;

					case "5":

						break;

					case "6":

						break;

					case "7":

						break;

					case "0":

						break;

					default:
						System.out.println("Opção inválida.Tente uma opção de '0' a '7'.");
						break;
					}

					break;
				} while (!opcaoSecundaria.equals("0"));

			case "B":

				break;

			case "C":
				System.out.println("Obrigada por utilizar nosso sistema! =D ");

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
