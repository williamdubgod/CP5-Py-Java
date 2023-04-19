public class Pedido {
    private String nomeProduto;
    private int quantidade;
    private double precoUnitario;

    public Pedido(String nomeProduto, int quantidade, double precoUnitario) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double getTotal() {
        return quantidade * precoUnitario;
    }
}
