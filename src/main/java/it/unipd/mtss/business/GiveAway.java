////////////////////////////////////////////////////////////////////
// [Lorenzo] [Salami] [1187478]
// [Enrik] [Rucaj] [2016131]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.List;
import java.time.LocalTime;
import java.util.Random;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import java.util.ArrayList;

public class GiveAway {
    static List<User> users = new ArrayList<User>();
    static int nGiveAway=0;
    //I dati utili per creare la Bill.
    private List<EItem> itemsOrdered;
    private User user;
    private LocalTime orario;

    public GiveAway(List<EItem> itemsOrdered, User user, LocalTime orario) {
        this.itemsOrdered = itemsOrdered;
        this.user = user;
        this.orario = orario;
    }

    boolean canGiveAway() {
        if (!users.contains(user)) {
            if (orario.isAfter(LocalTime.of(18, 00, 00)) && orario.isBefore(LocalTime.of(19, 00, 00))) {
                if (nGiveAway < 10 && user.isAMinor()) {
                    return true;
                }
            }
        }
        return false;
    }

    //Viene scelto in modo random uno degli item presenti in itemsOrdered e ne viene regalato uno nuovo tra quelli.
    boolean GiveAwayAFreeItem() {
        if (canGiveAway()){
            Random rand = new Random();
            int upperbound = itemsOrdered.size();
            int int_random = rand.nextInt(upperbound);

            users.add(user);
            nGiveAway++;
            EItem item = itemsOrdered.get(int_random);
            itemsOrdered.add(item);
            return true;
        }
        return false;
    }
}
