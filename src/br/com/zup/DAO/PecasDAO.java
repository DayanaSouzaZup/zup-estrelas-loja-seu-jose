package br.com.zup.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.zup.POJO.PecasPojo;
import br.com.zup.connectionfactory.ConnectionFactory;

public class PecasDAO {

	private Connection conn;

	public PecasDAO() {
		this.conn = new ConnectionFactory().getConnection();
	}

	public void cadastrarPeca(PecasPojo pecas) {

		String sql = "insert into pecas" + "(codigo_barra, nome, modelo_carro, fabricante, preço_custo,"
				+ " preço_venda, quantidade_estoque, categoria)" + "values(?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, pecas.getCodigoBarra());
			stmt.setString(2, pecas.getNome());
			stmt.setString(3, pecas.getModeloCarro());
			stmt.setString(4, pecas.getFabricante());
			stmt.setFloat(5, pecas.getPrecoCusto());
			stmt.setFloat(6, pecas.getPrecoVenda());
			stmt.setInt(7, pecas.getQuantidadeEstoque());
			stmt.setString(8, pecas.getCategoria());

			stmt.execute();
			stmt.close();
			System.out.println("\nPeça adicionada com sucesso!\n");

		} catch (SQLException e) {

			System.out.println(
					"\nNão foi possível cadastrar a peça. " + "Verifique se o dados foram inseridos corretamente.\n ");
			System.out.println(e.getMessage());
		}
	}

	public List<PecasPojo> consultarPecaPorCodigoBarra(String codigoBarra) {
		List<PecasPojo> pecas = new ArrayList<PecasPojo>();
		String sql = "select * from pecas where codigo_barra = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, codigoBarra);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				PecasPojo peca = new PecasPojo();

				peca.setCodigoBarra(rs.getString("codigo_barra"));
				peca.setNome(rs.getString("nome"));
				peca.setModeloCarro(rs.getString("modelo_carro"));
				peca.setFabricante(rs.getString("fabricante"));
				peca.setPrecoCusto(rs.getFloat("preço_custo"));
				peca.setPrecoVenda(rs.getFloat("preço_venda"));
				peca.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
				peca.setCategoria(rs.getString("categoria"));
				pecas.add(peca);
			}

		} catch (SQLException e) {
			System.out.println("\nO código de barra inválido ou ocorreu um erro na busca");

			System.out.println(e.getMessage());
		}
		return pecas;
	}

	public List<PecasPojo> listarPecasEstoque(int quantidadeEstoque) {
		List<PecasPojo> listaEstoque = new ArrayList<PecasPojo>();
		String sql = "select * from pecas ";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				PecasPojo estoque = new PecasPojo();

				estoque.setCodigoBarra(rs.getString("codigo_barra"));
				estoque.setNome(rs.getString("nome"));
				estoque.setModeloCarro(rs.getString("modelo_carro"));
				estoque.setFabricante(rs.getString("fabricante"));
				estoque.setPrecoCusto(rs.getFloat("preço_custo"));
				estoque.setPrecoVenda(rs.getFloat("preço_venda"));
				estoque.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
				estoque.setCategoria(rs.getString("categoria"));
				listaEstoque.add(estoque);
			}

		} catch (SQLException e) {
			System.out.println("\nOcorreu um erro na busca");

			System.out.println(e.getMessage());
		}

		return listaEstoque;

	}

	public List<PecasPojo> listarPecaPorLetra(String letra) {
		List<PecasPojo> nomePecas = new ArrayList<PecasPojo>();
		String sql = "select * from  pecas where nome like ? ";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, letra + "%");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				PecasPojo pecas = new PecasPojo();

				pecas.setCodigoBarra(rs.getString("codigo_barra"));
				pecas.setNome(rs.getString("nome"));
				pecas.setModeloCarro(rs.getString("modelo_carro"));
				pecas.setFabricante(rs.getString("fabricante"));
				pecas.setPrecoCusto(rs.getFloat("preço_custo"));
				pecas.setPrecoVenda(rs.getFloat("preço_venda"));
				pecas.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
				pecas.setCategoria(rs.getString("categoria"));
				nomePecas.add(pecas);
			}

		} catch (SQLException e) {
			System.out.println("\nOcorreu um erro na busca");

			System.out.println(e.getMessage());
		}

		return nomePecas;

	}

	public List<PecasPojo> listarPecaPorModelo(String modelo) {
		List<PecasPojo> modeloCarro = new ArrayList<PecasPojo>();
		String sql = "select * from  pecas where modelo_carro = ? ";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, modelo);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				PecasPojo pecas = new PecasPojo();

				pecas.setCodigoBarra(rs.getString("codigo_barra"));
				pecas.setNome(rs.getString("nome"));
				pecas.setModeloCarro(rs.getString("modelo_carro"));
				pecas.setFabricante(rs.getString("fabricante"));
				pecas.setPrecoCusto(rs.getFloat("preço_custo"));
				pecas.setPrecoVenda(rs.getFloat("preço_venda"));
				pecas.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
				pecas.setCategoria(rs.getString("categoria"));
				modeloCarro.add(pecas);
			}

		} catch (SQLException e) {
			System.out.println("\nOcorreu um erro na busca");

			System.out.println(e.getMessage());
		}

		return modeloCarro;
	}

	public List<PecasPojo> listarPecaPorCategoria(String categoria) {
		List<PecasPojo> pecaCategoria = new ArrayList<PecasPojo>();
		String sql = "select * from  pecas where categoria = ? ";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, categoria);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				PecasPojo pecas = new PecasPojo();

				pecas.setCodigoBarra(rs.getString("codigo_barra"));
				pecas.setNome(rs.getString("nome"));
				pecas.setModeloCarro(rs.getString("modelo_carro"));
				pecas.setFabricante(rs.getString("fabricante"));
				pecas.setPrecoCusto(rs.getFloat("preço_custo"));
				pecas.setPrecoVenda(rs.getFloat("preço_venda"));
				pecas.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
				pecas.setCategoria(rs.getString("categoria"));
				pecaCategoria.add(pecas);
			}

		} catch (SQLException e) {
			System.out.println("\nOcorreu um erro na busca");

			System.out.println(e.getMessage());
		}

		return pecaCategoria;
	}

	public void removerPeca(String codigoBarra) {

		try {
			String sql = "delete from pecas where codigo_barra = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, codigoBarra);

			stmt.execute();
			stmt.close();
			System.out.println("\nPeça removida com sucesso!");

		} catch (SQLException e) {
			System.out.println("\nNão foi possível remover a peça. Verifique o código de barra informado");
			System.out.println(e.getMessage());
		}

	}

}
