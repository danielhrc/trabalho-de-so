import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String [] args) throws IOException, InterruptedException {

        LeitorArquivo leitorArquivo = new LeitorArquivo();
        leitorArquivo.lerArquivo("src/docs/SO_20_DadosEmpacotadeira1.txt");
        List<Pedido> pedidoList =  leitorArquivo.getPedidoList();

        PrioridadeChecker prioridadeChecker = new PrioridadeChecker();
        pedidoList = prioridadeChecker.transformZeroToTenThousand(pedidoList);
        pedidoList = prioridadeChecker.orderByPrazo(pedidoList);


        List<Integer> vetorPrioridades = new ArrayList<>();

        pedidoList.forEach(pedido -> {
            if (!vetorPrioridades.contains(pedido.getPrazo())){
                vetorPrioridades.add(pedido.getPrazo());
            }
        });


        AtomicReference<Double> somaHorarios = new AtomicReference<>((double) 0);
        AtomicReference<Double> somaPacotes = new AtomicReference<>((double) 0);
        AtomicReference<Double> resto = new AtomicReference<>((double) 0);
        AtomicReference<Boolean> primeiraRemessa = new AtomicReference<>((boolean) true);


        List<Horario> horarios = new ArrayList<>();


        List<Pedido> finalPedidoList = pedidoList;
        vetorPrioridades.forEach(prioridade -> {
            horarios.add(new Horario());
            finalPedidoList.forEach(pedido -> {
                if (pedido.getPrazo() == prioridade){

                    if (resto.get() > 0 ){
                        pedido.setTotalProdutos((int) (pedido.getTotalProdutos() + resto.get()));
                        resto.set((double) 0);
                    }

                    somaPacotes.set(somaPacotes.get() + (Double.parseDouble(pedido.getTotalProdutos().toString())/20));

                    resto.set((Double.parseDouble(pedido.getTotalProdutos().toString())%20));

                    // Add o tempo
                    horarios.get(horarios.size() -1).addTempo(5.5 * (Double.parseDouble(pedido.getTotalProdutos().toString())/20) - 0.5);

                    if(somaHorarios.get() >= 7200 && primeiraRemessa.get()){
                        primeiraRemessa.set(false);
                        if(somaHorarios.get() > 7200){

                            resto.set(resto.get() + ((somaHorarios.get() - 7200)/5.5 ));
                            somaPacotes.set(somaPacotes.get()-resto.get());
                        }

                        System.out.println("Primeira remessa: foram feitas " + somaPacotes);
                    }else{
                        somaHorarios.set(somaHorarios.get() + horarios.get(horarios.size() - 1).getTempo());
                    }


                }

            });
            System.out.println(" ---------------------------------------------" );
            System.out.println("Prioridade: " + prioridade + " min ");
            System.out.println("Soma pacotes> " + somaPacotes);

            if (resto.get()>0){
                somaPacotes.set(somaPacotes.get() + 5.5);
            }
           System.out.println("Horario por prazo: " + horarios.get(horarios.size()-1).getTempo() + " s ");
            System.out.println("Tempo gasto: " + somaHorarios + " s ");


        });


        System.out.println("Média de pacotes por hora: " + somaPacotes.get()/(somaHorarios.get()/3600));
}
}
