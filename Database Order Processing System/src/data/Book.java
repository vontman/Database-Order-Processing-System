package data;

import java.util.Properties;

public class Book extends Properties {

    public String getIsbn() {
        return getProperty("isbn");
    }

    public String getTitle() {
        return getProperty("title");
    }

    public int getPrice() {
        return Integer.parseInt(getProperty("price"));
    }

    public int getCopies() {
        return Integer.parseInt(getProperty("copies"));
    }

    public String getPublishYear() {
        return getProperty("publication_year");
    }

    public int getCategoryId() {
        return Integer.parseInt(getProperty("category_id"));
    }

    public String getCategory() {
        return getProperty("category");
    }

    public int getThreshold() {
        return Integer.parseInt(getProperty("threshold"));
    }

    public String[] getAuthors() {
        return getProperty("authors").split(",");
    }

    public String getPublisher() {
        return getProperty("publisher_name");
    }
}
