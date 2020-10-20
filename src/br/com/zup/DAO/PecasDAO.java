package br.com.zup.DAO;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.zup.POJO.Pecas;
import br.com.zup.connectionfactory.ConnectionFactory;

public class PecasDAO {

	EntityManager manager;

	public PecasDAO() {

		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("pecas");
		this.manager = managerFactory.createEntityManager();
	}

	public void cadastrarPeca(Pecas pecas) {

		manager.getTransaction().begin();
		manager.persist(pecas);
		manager.getTransaction().commit();
	}

	public Pecas consultarPecaPorCodigoBarra(String codigoBarra) {

		Pecas pecasEstoque = manager.find(Pecas.class, codigoBarra);

		return pecasEstoque;
	}

	public List<Pecas> listarPecasEstoque() {

		Query query = manager.createQuery("select p from Pecas as p");

		List<Pecas> listaPecas = query.getResultList();

		return listaPecas;
	}

	public List<Pecas> listarPecaPorLetra(String letra) {

		Query query = manager.createQuery("select p from Pecas as p where p.nome like CONCAT(:parametro, '%')");

		query.setParameter("parametro", letra);

		List<Pecas> listaPecasPorLetra = query.getResultList();

		return listaPecasPorLetra;
	}

	public List<Pecas> listarPecaPorModelo(String modelo) {

		Query query = manager.createQuery("SELECT p FROM Pecas AS p WHERE p.modeloCarro = :modeloCarro");

		query.setParameter("modeloCarro", modelo);

		List<Pecas> listaPecasPorModelo = query.getResultList();

		return listaPecasPorModelo;
	}

	public List<Pecas> listarPecaPorCategoria(String categoria) {

		Query query = manager.createQuery("select p from Pecas as p where p.categoria =: categoria");

		query.setParameter("categoria", categoria);

		List<Pecas> listaPecasPorCategoria = query.getResultList();

		return listaPecasPorCategoria;

	}

	public void removerPeca(String codigoBarra) {

		Pecas removePeca = manager.find(Pecas.class, codigoBarra);

		manager.getTransaction().begin();
		manager.remove(removePeca);
		manager.getTransaction().commit();
	}

	public void atualizaEstoque(String codigoBarra, int estoqueAtualizado) {

		Pecas peca = manager.find(Pecas.class, codigoBarra);

		peca.setQuantidadeEstoque(estoqueAtualizado);

		manager.getTransaction().begin();
		manager.merge(peca);
		manager.getTransaction().commit();

	}

}

//}
/*
 * private Connection conn; public PecasDAO() { this.conn = new
 * ConnectionFactory().getConnection(); }
 * 
 * public void cadastrarPeca(Pecas pecas) {
 * 
 * String sql = "insert into pecas" +
 * "(codigo_barra, nome, modelo_carro, fabricante, preço_custo," +
 * " preço_venda, quantidade_estoque, categoria)" +
 * "values(?, ?, ?, ?, ?, ?, ?, ?)";
 * 
 * try { PreparedStatement stmt = conn.prepareStatement(sql);
 * 
 * stmt.setString(1, pecas.getCodigoBarra()); stmt.setString(2,
 * pecas.getNome()); stmt.setString(3, pecas.getModeloCarro());
 * stmt.setString(4, pecas.getFabricante()); stmt.setFloat(5,
 * pecas.getPrecoCusto()); stmt.setFloat(6, pecas.getPrecoVenda());
 * stmt.setInt(7, pecas.getQuantidadeEstoque()); stmt.setString(8,
 * pecas.getCategoria());
 * 
 * stmt.execute(); stmt.close();
 * System.out.println("\nPeça adicionada com sucesso!\n");
 * 
 * } catch (SQLException e) {
 * 
 * System.out.println(
 * "\nNão foi possível cadastrar a peça. Verifique se o dados foram inseridos corretamente.\n "
 * ); System.out.println(e.getMessage()); } }
 * 
 * public Pecas consultarPecaPorCodigoBarra(String codigoBarra) {
 * 
 * Pecas peca = new Pecas();
 * 
 * String sql = "select * from pecas where codigo_barra = ?";
 * 
 * try { PreparedStatement stmt = conn.prepareStatement(sql);
 * 
 * stmt.setString(1, codigoBarra);
 * 
 * ResultSet rs = stmt.executeQuery();
 * 
 * while (rs.next()) {
 * 
 * peca.setCodigoBarra(rs.getString("codigo_barra"));
 * peca.setNome(rs.getString("nome"));
 * peca.setModeloCarro(rs.getString("modelo_carro"));
 * peca.setFabricante(rs.getString("fabricante"));
 * peca.setPrecoCusto(rs.getFloat("preço_custo"));
 * peca.setPrecoVenda(rs.getFloat("preço_venda"));
 * peca.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
 * peca.setCategoria(rs.getString("categoria")); }
 * 
 * } catch (SQLException e) {
 * 
 * System.out.
 * println("\nO código de barras informado é inválido ou ocorreu um erro na busca\n"
 * ); System.out.println(e.getMessage()); } return peca; }
 * 
 * public List<Pecas> listarPecasEstoque() {
 * 
 * List<Pecas> listaEstoque = new ArrayList<Pecas>();
 * 
 * String sql = "select * from pecas ";
 * 
 * try { PreparedStatement stmt = conn.prepareStatement(sql);
 * 
 * ResultSet rs = stmt.executeQuery();
 * 
 * while (rs.next()) {
 * 
 * Pecas estoque = new Pecas();
 * 
 * estoque.setCodigoBarra(rs.getString("codigo_barra"));
 * estoque.setNome(rs.getString("nome"));
 * estoque.setModeloCarro(rs.getString("modelo_carro"));
 * estoque.setFabricante(rs.getString("fabricante"));
 * estoque.setPrecoCusto(rs.getFloat("preço_custo"));
 * estoque.setPrecoVenda(rs.getFloat("preço_venda"));
 * estoque.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
 * estoque.setCategoria(rs.getString("categoria")); listaEstoque.add(estoque); }
 * 
 * } catch (SQLException e) {
 * 
 * System.out.println("\nErro ao listar as peças em estoque. Tente novamente!\n"
 * ); System.out.println(e.getMessage()); }
 * 
 * return listaEstoque;
 * 
 * }
 * 
 * public List<Pecas> listarPecaPorLetra(String letra) { List<Pecas> nomePecas =
 * new ArrayList<Pecas>(); String sql =
 * "select * from  pecas where nome like ? ";
 * 
 * try { PreparedStatement stmt = conn.prepareStatement(sql); stmt.setString(1,
 * letra + "%");
 * 
 * ResultSet rs = stmt.executeQuery();
 * 
 * while (rs.next()) {
 * 
 * Pecas pecas = new Pecas();
 * 
 * pecas.setCodigoBarra(rs.getString("codigo_barra"));
 * pecas.setNome(rs.getString("nome"));
 * pecas.setModeloCarro(rs.getString("modelo_carro"));
 * pecas.setFabricante(rs.getString("fabricante"));
 * pecas.setPrecoCusto(rs.getFloat("preço_custo"));
 * pecas.setPrecoVenda(rs.getFloat("preço_venda"));
 * pecas.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
 * pecas.setCategoria(rs.getString("categoria")); nomePecas.add(pecas); }
 * 
 * } catch (SQLException e) {
 * 
 * System.out.
 * println("\nErro na busca por peça por letra inicial. Tente novamente!\n");
 * System.out.println(e.getMessage()); }
 * 
 * return nomePecas;
 * 
 * }
 * 
 * public List<Pecas> listarPecaPorModelo(String modelo) {
 * 
 * List<Pecas> modeloCarro = new ArrayList<Pecas>();
 * 
 * String sql = "select * from  pecas where modelo_carro = ? ";
 * 
 * try { PreparedStatement stmt = conn.prepareStatement(sql);
 * 
 * stmt.setString(1, modelo);
 * 
 * ResultSet rs = stmt.executeQuery();
 * 
 * while (rs.next()) {
 * 
 * Pecas pecas = new Pecas();
 * 
 * pecas.setCodigoBarra(rs.getString("codigo_barra"));
 * pecas.setNome(rs.getString("nome"));
 * pecas.setModeloCarro(rs.getString("modelo_carro"));
 * pecas.setFabricante(rs.getString("fabricante"));
 * pecas.setPrecoCusto(rs.getFloat("preço_custo"));
 * pecas.setPrecoVenda(rs.getFloat("preço_venda"));
 * pecas.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
 * pecas.setCategoria(rs.getString("categoria")); modeloCarro.add(pecas); }
 * 
 * } catch (SQLException e) {
 * System.out.println("\nErro na busca por modelo. Tente novamente!\n");
 * 
 * System.out.println(e.getMessage()); }
 * 
 * return modeloCarro; }
 * 
 * public List<Pecas> listarPecaPorCategoria(String categoria) {
 * 
 * List<Pecas> pecaCategoria = new ArrayList<Pecas>();
 * 
 * String sql = "select * from  pecas where categoria = ? ";
 * 
 * try { PreparedStatement stmt = conn.prepareStatement(sql);
 * 
 * stmt.setString(1, categoria);
 * 
 * ResultSet rs = stmt.executeQuery();
 * 
 * while (rs.next()) {
 * 
 * Pecas pecas = new Pecas();
 * 
 * pecas.setCodigoBarra(rs.getString("codigo_barra"));
 * pecas.setNome(rs.getString("nome"));
 * pecas.setModeloCarro(rs.getString("modelo_carro"));
 * pecas.setFabricante(rs.getString("fabricante"));
 * pecas.setPrecoCusto(rs.getFloat("preço_custo"));
 * pecas.setPrecoVenda(rs.getFloat("preço_venda"));
 * pecas.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
 * pecas.setCategoria(rs.getString("categoria")); pecaCategoria.add(pecas); }
 * 
 * } catch (SQLException e) {
 * 
 * System.out.
 * println("\nErro ao buscar a categoria informada. Tente novamente!\n");
 * System.out.println(e.getMessage()); }
 * 
 * return pecaCategoria; }
 * 
 * public void removerPeca(String codigoBarra) {
 * 
 * try { String sql = "delete from pecas where codigo_barra = ?";
 * 
 * PreparedStatement stmt = conn.prepareStatement(sql);
 * 
 * stmt.setString(1, codigoBarra);
 * 
 * stmt.execute(); stmt.close();
 * 
 * System.out.println("\nPeça removida com sucesso!");
 * 
 * } catch (SQLException e) { System.out.
 * println("\nNão foi possível remover a peça. Verifique o código de barras informado\n"
 * ); System.out.println(e.getMessage()); }
 * 
 * }
 * 
 * public boolean realizaVenda(Pecas pecasPojo, int quantidadeDesejada) {
 * 
 * String sql =
 * "update pecas set quantidade_estoque = ? where codigo_barra = ?";
 * 
 * try { PreparedStatement stmt = conn.prepareStatement(sql);
 * 
 * stmt.setInt(1, pecasPojo.getQuantidadeEstoque() - quantidadeDesejada);
 * stmt.setString(2, pecasPojo.getCodigoBarra());
 * 
 * stmt.execute(); stmt.close();
 * 
 * System.out.println("\nVenda realizada com sucesso!\n");
 * 
 * } catch (SQLException e) { System.out.
 * println("\nErro ao realizar venda. Verifique os dados informados\n");
 * System.out.println(e.getMessage()); return false; } return true; }
 * 
 * public void arquivoVendas(List<Pecas> relatorioVendas) {
 * 
 * double totalVendas = 0; String nomeArquivo = "relatorioVendas_dia_" +
 * Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + ".txt";
 * 
 * try { FileWriter writer = new FileWriter(nomeArquivo);
 * 
 * writer.write("Vendas - Dia " +
 * Calendar.getInstance().get(Calendar.DAY_OF_MONTH)); writer.write("\n\n");
 * 
 * for (Pecas pecasPojo : relatorioVendas) {
 * 
 * writer.append(String.format(" Codigo de Barras = " +
 * pecasPojo.getCodigoBarra() + "/ Nome = " + pecasPojo.getNome() +
 * "/ Quantidade = " + pecasPojo.getQuantidadeEstoque() + "/ Valor =  " +
 * pecasPojo.getPrecoVenda()));
 * 
 * writer.write("\n");
 * 
 * totalVendas += pecasPojo.getPrecoVenda(); }
 * 
 * writer.append(String.format("\nTotal de vendas: %.2f", totalVendas));
 * 
 * writer.close();
 * 
 * System.out.println("\nArquivo registrado com sucesso!\n");
 * 
 * } catch (IOException e) { System.out.
 * println("\nNão foi possível registrar as informações em Arquivo. Tente novamente!"
 * ); System.out.println(e.getMessage()); }
 * 
 * 
 * }
 */
