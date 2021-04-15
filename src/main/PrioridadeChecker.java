import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrioridadeChecker {

    public PrioridadeChecker(){};

    public List<Pedido> transformZeroToTenThousand(List<Pedido> pedidoList) {
        pedidoList.forEach(pedido ->{
           if (pedido.getPrazo().equals(0))
               pedido.setPrazo(10000);
        });

        return pedidoList;
    }

    public List<Pedido> orderByPrazo(List<Pedido> pedidoList) {
        Collections.sort(pedidoList, Comparator.comparing(Pedido::getPrazo));
        return pedidoList;
    }
}
