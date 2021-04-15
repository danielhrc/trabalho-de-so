import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LeitorArquivo {

    private Integer quantidade;
    private List<Pedido> pedidoList = new ArrayList<>();

    public LeitorArquivo(){};

    public void lerArquivo (String diretorioArquivo) throws IOException {

        try (
                FileReader arquivo = new FileReader(diretorioArquivo);
                BufferedReader arquivoBuff  = new BufferedReader(arquivo);

        ) {

            String linha = arquivoBuff.readLine();
            this.setQuantidade(Integer.parseInt(linha));
            linha = arquivoBuff.readLine();

           while ( linha != null && !linha.isEmpty()){

               String [] fromArquivoLnToPedidosList = linha.split(";");

               addPedidoList(fromArquivoLnToPedidosList);
               linha = arquivoBuff.readLine();

           }

        }
    }


    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void addPedidoList(String[] fromArquivoLnToPedidosList) {

        if (fromArquivoLnToPedidosList[0].equals( "null")) {

        }else {
          Pedido pedido = new Pedido(fromArquivoLnToPedidosList[0],
                   Integer.parseInt(fromArquivoLnToPedidosList[1]),
                   Integer.parseInt(fromArquivoLnToPedidosList[2] ));
           pedidoList.add(pedido);


       };



    }

    public Integer getQuantidade(){
        return this.quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
