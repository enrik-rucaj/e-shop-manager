////////////////////////////////////////////////////////////////////
// [Lorenzo] [Salami] [1187478]
// [Enrik] [Rucaj] [2016131]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class EItem {
    public enum type {Processor, Motherboard, Mouse, Keyboard}
    private type itemType;
    private String name;
    private double price;

    public EItem(type itemType, String name, double price) {
        this.itemType = itemType;
        this.name = name;
        if (price >= 0) {
            this.price = price;
        }
        else {
            throw new IllegalArgumentException("Price must be positive");
        }
    }

    public type getItemType() { return itemType; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}
