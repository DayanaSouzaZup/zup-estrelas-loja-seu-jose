package br.com.zup.vendasDAO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import br.com.zup.DAO.PecasDAO;
import br.com.zup.POJO.Pecas;

public class Vendas {

	
	
	List<String> relatorioDeVendas = new ArrayList<String>();

	PecasDAO pecas = new PecasDAO();

	double valorTotalVenda;

	public void realizaVenda(Scanner teclado, int quantidadeDesejada, String codigoBarra) {

		Pecas pecaVendida = new Pecas();

		pecaVendida = pecas.consultarPecaPorCodigoBarra(codigoBarra);

		int estoqueAntesVenda = pecaVendida.getQuantidadeEstoque();

		while (pecaVendida.getQuantidadeEstoque() < quantidadeDesejada) {
			System.out.print("\nO estoque atual é de: " + estoqueAntesVenda + " unidades.\n"
					+ "Informe uma nova quantidade válida: ");
			quantidadeDesejada = teclado.nextInt();

		}

		System.out.println("\nVenda realizada com sucesso!");
		valorTotalVenda += quantidadeDesejada * pecaVendida.getPrecoVenda();
		relatorioDeVendas.add("Codigo de Barras = " + pecaVendida.getCodigoBarra() + " | Nome = "
				+ pecaVendida.getNome() + " | Quantidade = " + quantidadeDesejada + " | Valor =  "
				+ pecaVendida.getPrecoVenda() * quantidadeDesejada);

		int estoqueAtualizado = estoqueAntesVenda - quantidadeDesejada;

		pecas.atualizaEstoque(codigoBarra, estoqueAtualizado);

	}

	public void imprimeRelatorioVendas() {

		System.out.println("\nRelatório de vendas dia " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + ":\n");

		for (String vendas : relatorioDeVendas) {

			System.out.println(vendas);

		}

		System.out.printf("\nValor total de vendas: %.2f\n\n", valorTotalVenda);

	}

	public void arquivoVendas() {

		String nomeArquivo = "relatorioVendas_dia_" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + ".txt";

		try {
			FileWriter writer = new FileWriter(nomeArquivo);

			writer.write("Vendas - Dia " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			writer.write("\n\n");

			for (String  pecas : relatorioDeVendas) {

				writer.append(String.format(pecas));

				writer.write("\n");

			}

			writer.append(String.format("\nTotal de vendas: %.2f", valorTotalVenda));

			writer.close();

			System.out.println("\nArquivo registrado com sucesso!\n");

		} catch (IOException e) {
			System.out.println("\nNão foi possível registrar as informações em Arquivo. Tente novamente!");
			System.out.println(e.getMessage());
		}

	}

}
