package data;

import java.util.Properties;

public class Order extends Properties {

    public Order() {
        super();
    }

    public Order(String isbn, Integer copies) {
        super();
        setProperty("book_isbn", isbn);
        setProperty("copies", copies.toString());
    }

    public String getBookIsbn() {
        return getProperty("book_isbn");
    }

    public int getOrderedCopies() {
        return Integer.parseInt(getProperty("copies"));
    }
}
