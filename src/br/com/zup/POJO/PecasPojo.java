package br.com.zup.POJO;

public class PecasPojo {

	private String codigoBarra;
	private String nome;
	private String modeloCarro;
	private String fabricante;
	private float precoCusto;
	private float precoVenda;
	private int quantidadeEstoque;
	private String categoria;

	public PecasPojo(String codigoBarra, String nome, String modeloCarro, String fabricante, float precoCusto,
			float precoVenda, int quantidadeEstoque, String categoria) {

		this.codigoBarra = codigoBarra;
		this.nome = nome;
		this.modeloCarro = modeloCarro;
		this.fabricante = fabricante;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.quantidadeEstoque = quantidadeEstoque;
		this.categoria = categoria;
	}

	public PecasPojo() {

	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public float getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(float precoCusto) {
		this.precoCusto = precoCusto;
	}

	public float getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String toString() {
		return "Nome = " + nome + ", Modelo do Carro = " + modeloCarro + ", Fabricante = " + fabricante
				+ ", Preço de custo = " + precoCusto + ", Preço de venda = " + precoVenda + ", Quantidade em estoque = "
				+ quantidadeEstoque + ", Categoria = " + categoria + ", Código de barra = " + codigoBarra + "";
	}

}
