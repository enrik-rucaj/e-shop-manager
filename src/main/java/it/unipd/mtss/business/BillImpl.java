////////////////////////////////////////////////////////////////////
// [Lorenzo] [Salami] [1187478]
// [Enrik] [Rucaj] [2016131]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.List;
import java.time.LocalTime;
import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillImpl implements Bill{
    public double getOrderPrice(List<EItem> itemsOrdered, User user, LocalTime orario) throws BillException {
        double total = 0.0;
        double prezzoProcessoreMinimo = 10000;
        double prezzoMouseMinimo = 10000;
        int nProcessori = 0;
        int nMouse = 0;

        if(itemsOrdered == null) {
            throw new BillException("Items Ordered must not be null");
        }
        if(itemsOrdered.size() == 0) {
            throw new BillException("Item Ordered must not be empty");
        }
        if(user == null) {
            throw new BillException("User must not be null");
        }

        for (EItem item : itemsOrdered) {
            double prezzoItem = item.getPrice();
            //Utile per il punto 2.
            if (item.getItemType().equals(EItem.type.Processor)) {
                nProcessori++;
                if (prezzoItem < prezzoProcessoreMinimo) {
                    prezzoProcessoreMinimo = prezzoItem;
                }
            }
            //Utile per il punto 3.
            if (item.getItemType().equals(EItem.type.Mouse)){
                nMouse++;
                if (prezzoItem < prezzoMouseMinimo){
                    prezzoMouseMinimo = prezzoItem;
                }
            }

            total += prezzoItem;
        }

        //Utile per il punto 2.
        if (nProcessori > 5){
            total = total - (prezzoProcessoreMinimo*0.5);
        }
        //Utile per il punto 3.
        if (nMouse > 10){
            total = total - prezzoMouseMinimo;
        }

        return total;
    }
}
