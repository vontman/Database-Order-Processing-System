package data;

import java.util.ArrayList;

public class BookFactory {

    private Book book = new Book();

    public void setIsbn(String val) {
        val = val.trim();
        if (!val.isEmpty())
            book.setProperty("isbn", val);
        else
            book.remove("isbn");
    }

    public void setTitle(String val) {
        val = val.trim();
        if (!val.isEmpty())
            book.setProperty("title", val);
        else
            book.remove("title");
    }

    public void setPrice(int val) {
        book.setProperty("price", Integer.toString(val));
    }

    public void setPrice(String val) {
        try {
            setPrice(Integer.parseInt(val));
        } catch (NumberFormatException e) {
            book.remove("price");
            throw new RuntimeException("invalid number");
        }
    }

    public void setCopies(String val) {
        try {
            setCopies(Integer.parseInt(val));
        } catch (NumberFormatException e) {
            book.remove("copies");
            throw new RuntimeException("invalid number");
        }
    }

    public void setCopies(int val) {
        book.setProperty("copies", Integer.toString(val));
    }

    public void setPublishYear(String val) {
        val = val.trim();
        if (!val.isEmpty())
            book.setProperty("publication_year", val);
        else
            book.remove("publication_year");
    }

    public void setCategoryId(int id) {
        try {
            book.setProperty("category_id", Integer.toString(id));
        } catch (NumberFormatException e) {
            book.remove("category_id");
            throw new RuntimeException("invalid number");
        }
    }

    public void setCategory(String val) {
        val = val.trim();
        if (!val.isEmpty())
            book.setProperty("category", val);
        else
            book.remove("category");
    }

    public void setThreshold(int val) {
        book.setProperty("threshold", Integer.toString(val));
    }
    
    public void setThreshold(String val) throws RuntimeException {
        try {
            setThreshold(Integer.parseInt(val));
        } catch (NumberFormatException e) {
            book.remove("threshold");
            throw new RuntimeException("invalid number");
        }
    }

    public void setAuthors(String[] vals) {
        ArrayList<String> valList = new ArrayList<>();
        for (String s : vals) {
            String trimed = s.trim();
            if (!trimed.isEmpty())
                valList.add(trimed);
        }
        String val = String.join(",", valList);
        if (!val.isEmpty())
            book.setProperty("authors", val);
        else
            book.remove("authors");
    }

    public void setAuthors(String val) {
        setAuthors(splitAuthorsString(val));
    }

    public void setPublisher(String val) {
        book.setProperty("publisher_name", val);
        val = val.trim();
        if (!val.isEmpty())
            book.setProperty("publisher_name", val);
        else
            book.remove("publisher_name");
    }

    public static String[] splitAuthorsString(String authors) {
        return authors.split("\\s*,\\s*");
    }

    public Book getBook() {
        return book;
    }
}
