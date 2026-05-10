package com.product.TextbookSeller;

/**
 * A concrete class representing a Textbook.
 * Extends the abstract Product class.
 */
public class Textbook extends Product {
    private int numCopies;
    private double price;
    private String accountNumber;
    private String bankName;
    private String accountType;
    private boolean sold = false;   

    public Textbook(String title, String sellerName, int numCopies, double price,
                    String accountNumber, String bankName, String accountType) {
        super(title, sellerName);
        this.numCopies     = numCopies;
        this.price         = price;
        this.accountNumber = accountNumber;
        this.bankName      = bankName;
        this.accountType   = accountType;
    }

    @Override public double getPrice() { return price; }

    @Override
    public void displayDetails() {
        System.out.println("Book: " + title + " | Seller: " + sellerName
                + " | Price: R" + price + " | Bank: " + bankName);
    }

    public String getAccountNumber() { return accountNumber; }
    public String getBankName()      { return bankName; }
    public String getAccountType()   { return accountType; }
    public boolean isSold()          { return sold; }
    public void markSold()           { this.sold = true; }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Textbook)) return false;
        Textbook other = (Textbook) o;
        return this.title.equalsIgnoreCase(other.title)
                && this.sellerName.equalsIgnoreCase(other.sellerName);
    }
}
