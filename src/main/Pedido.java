public class Pedido {

    private Integer totalProdutos;
    private String pessoa;
    private Integer prazo;
    private Integer numPacotes;


    public Pedido(String pessoa, Integer totalProdutos, Integer prazo ){
        this.setPessoa(pessoa);
        this.setTotalProdutos(totalProdutos);
        this.setPrazo(prazo);

    }

    public Integer getTotalProdutos() {
        return totalProdutos;
    }

    public void setTotalProdutos(Integer totalProdutos) {
        this.totalProdutos = totalProdutos;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }

    public Integer getNumPacotes() {

        return (250 * numPacotes <= 5000) ? 1 : ((250 * numPacotes)/5000)+1;
    }

    public void setNumPacotes(Integer numPacotes) {
        this.numPacotes = numPacotes;
    }
}
