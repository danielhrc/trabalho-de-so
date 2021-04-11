import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void ioTest() throws IOException {
        LeitorArquivo leitorArquivo = new LeitorArquivo();
        leitorArquivo.lerArquivo("/Users/daniel/repos/trabalho-de-so/docs/test.txt");
        List<Pedido> pedidoList =  leitorArquivo.getPedidoList();

        assertEquals("James Sandero",pedidoList.get(0).getPessoa());
        assertEquals("Jon Sanfdero", pedidoList.get(1).getPessoa());
        assertEquals(5, pedidoList.size());
    }


    @Test
    public void esteiraTest(){

    }


    @Test
    public void checaPrioridadePedido(){

    }





}
