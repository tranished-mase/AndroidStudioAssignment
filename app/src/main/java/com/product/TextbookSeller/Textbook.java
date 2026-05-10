package com.product.TextbookSeller;

/**
 * A concrete class representing a Textbook.
 * It extends the Abstract class 'Product'.
 */
public class Textbook extends Product {
    private int numCopies;
    private double price;

    
    private String accountNumber;
    private String bankName;
    private String accountType;

    
    public Textbook(String title, String sellerName, int numCopies, double price,
                    String accountNumber, String bankName, String accountType) {

        // 'super'  passes the title and sellerName to the Abstract Product class
        super(title, sellerName);

        this.numCopies = numCopies;
        this.price = price;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.accountType = accountType;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void displayDetails() {
        
        System.out.println("Book: " + title + " | Seller: " + sellerName +
                " | Price: R" + price + " | Bank: " + bankName);
    }

    // New specific getters for the listing requirements
    public String getAccountNumber() { return accountNumber; }
    public String getBankName() { return bankName; }
    public String getAccountType() { return accountType; }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Textbook)) return false;

        Textbook other = (Textbook) o;

        // Duplicate prevention logic remains the same: same title and seller
        return this.title.equalsIgnoreCase(other.title) &&
                this.sellerName.equalsIgnoreCase(other.sellerName);
    }
}
