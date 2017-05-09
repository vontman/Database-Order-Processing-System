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
        if (getProperty("category_id") != null)
            return Integer.parseInt(getProperty("category_id"));
        return 0;
    }

    public String getCategory() {
        return getProperty("category");
    }

    public int getThreshold() {
        if(getProperty("threshold") != null)
            return Integer.parseInt(getProperty("threshold"));
        return Integer.MIN_VALUE;
    }

    public String[] getAuthors() {
        return getProperty("authors").split(",");
    }

    public String getPublisher() {
        return getProperty("publisher_name");
    }
}
