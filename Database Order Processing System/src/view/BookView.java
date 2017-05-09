package view;

import data.Book;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookView extends Book {

    StringProperty titleProb, isbnProb, authorsProb, publisherProb, yearProb, categoryProb;
    IntegerProperty priceProb, copiesProb;
	public BookView(Book book) {
		titleProb = new SimpleStringProperty();
		isbnProb  = new SimpleStringProperty(); 
		authorsProb = new SimpleStringProperty();
		publisherProb = new SimpleStringProperty();
		yearProb = new SimpleStringProperty();
		categoryProb = new SimpleStringProperty();
	    priceProb  = new SimpleIntegerProperty();
	    copiesProb  = new SimpleIntegerProperty();
	    
	    this.putAll(book);
	    
	    titleProb.set(book.getTitle());
		isbnProb.set(book.getIsbn()); 
		authorsProb.set(String.join(", ", book.getAuthors()));
		publisherProb.set(book.getPublisher());
		yearProb.set(book.getPublishYear());
	    priceProb.set(book.getPrice());
	    copiesProb.set(book.getCopies());
	    categoryProb.set(book.getCategory());
	    
	}
    public StringProperty getCategoryProb() {
        return categoryProb;
    }

    public void setCategoryProb(StringProperty categoryProb) {
        this.categoryProb= categoryProb;
    }
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
    
    public IntegerProperty getCopiesProb() {
    	return copiesProb;
    }
    
    public void setCopiesProb(IntegerProperty copiesProb) {
    	this.copiesProb = copiesProb;
    }

}
