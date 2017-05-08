package view;

import data.Book;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class BookView extends Book {

    StringProperty titleProb, isbnProb, authorsProb, publisherProb, yearProb;
    IntegerProperty priceProb;

    public StringProperty getTitleProb() {
        return titleProb;
    }

    public void setTitleProb(StringProperty titleProb) {
        this.titleProb = titleProb;
    }

    public StringProperty getIsbnProb() {
        return isbnProb;
    }

    public void setIsbnProb(StringProperty isbnProb) {
        this.isbnProb = isbnProb;
    }

    public StringProperty getAuthorsProb() {
        return authorsProb;
    }

    public void setAuthorsProb(StringProperty authorsProb) {
        this.authorsProb = authorsProb;
    }

    public StringProperty getPublisherProb() {
        return publisherProb;
    }

    public void setPublisherProb(StringProperty publisherProb) {
        this.publisherProb = publisherProb;
    }

    public StringProperty getYearProb() {
        return yearProb;
    }

    public void setYearProb(StringProperty yearProb) {
        this.yearProb = yearProb;
    }

    public IntegerProperty getPriceProb() {
        return priceProb;
    }

    public void setPriceProb(IntegerProperty priceProb) {
        this.priceProb = priceProb;
    }

}
