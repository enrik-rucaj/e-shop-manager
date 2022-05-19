////////////////////////////////////////////////////////////////////
// [Lorenzo] [Salami] [1187478]
// [Enrik] [Rucaj] [2016131]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class User {
    private int id;
    private boolean isAMinor;
    private String name, surname, address;

    public User(int id, boolean isAMinor, String name, String surname, String address) {
        this.id = id;
        this.isAMinor = isAMinor;
        if ((name.length() > 0 && name.length() < 15) && (surname.length() > 0 && surname.length() < 15)) {
            this.name = name;
            this.surname = surname;
        }
        else {
            throw new IllegalArgumentException("Name or Surname must have less than 16 characters and must not be null");
        }
        if (address.length() > 0) {
            this.address = address;
        }
        else {
            throw new IllegalArgumentException("The address must not be null");
        }
    }

    public int getId() {
        return id;
    }

    public boolean isAMinor() {
        return isAMinor;
    }
}
