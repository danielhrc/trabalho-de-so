import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;

public class Tests {




    @Test
    public void ioTest() throws IOException {
        LeitorArquivo leitorArquivo = new LeitorArquivo();
        leitorArquivo.lerArquivo("src/docs/test.txt");
        List<Pedido> pedidoList =  leitorArquivo.getPedidoList();

        assertEquals("James Sandero",pedidoList.get(0).getPessoa());
        assertEquals("Jon Sanfdero", pedidoList.get(1).getPessoa());
        assertEquals(5, pedidoList.size());
    }

    @Test
    public void criaPedidoTest(){
    


    }

    @Test
    public void horarioTest(){
        Horario horario = new Horario();
        horario.addTempo(5);
        assertEquals(5.00, horario.getTempo(), 0.1);

        horario.addTempo(0.5);
        assertEquals(5.5, horario.getTempo(), 0.1);

    }



    @Test
    public void checaPrioridadePedido() throws IOException {
        LeitorArquivo leitorArquivo = new LeitorArquivo();
        leitorArquivo.lerArquivo("src/docs/SO_20_DadosEmpacotadeira1.txt");
        List<Pedido> pedidoList =  leitorArquivo.getPedidoList();

        PrioridadeChecker prioridadeChecker = new PrioridadeChecker();
        pedidoList = prioridadeChecker.transformZeroToTenThousand(pedidoList);
        pedidoList = prioridadeChecker.orderByPrazo(pedidoList);

        // Vetor de prazos
        List<Integer> vetorPrioridades = new ArrayList<>();
        // Lê prioridades formando vetor
        pedidoList.forEach(pedido -> {
            if (!vetorPrioridades.contains(pedido.getPrazo())){
                vetorPrioridades.add(pedido.getPrazo());
            }
        });


        AtomicReference<Double> somaHorarios = new AtomicReference<>((double) 0);
        AtomicReference<Double> somaPacotes = new AtomicReference<>((double) 0);
        AtomicReference<Double> resto = new AtomicReference<>((double) 0);
        AtomicReference<Boolean> primeiraRemessa = new AtomicReference<>((boolean) true);

        // Vetor com horarios por prioridade
        List<Horario> horarios = new ArrayList<>();

        // navegar lista pedidos
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
                        }

                        System.out.println("Primeira remessa: foram feitas " + somaPacotes);
                    }else{
                        somaHorarios.set(somaHorarios.get() + horarios.get(horarios.size() - 1).getTempo());
                    }


                }

            });
            System.out.println("Soma pacotes> " + somaPacotes);
            if (resto.get()>0){
                somaPacotes.set(somaPacotes.get() + 5.5);
            }
//            System.out.println(horarios.get(horarios.size()-1).getTempo());
            System.out.println(somaHorarios);

        });



    }







}
