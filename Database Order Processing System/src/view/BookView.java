package view;

import data.Book;
import data.BookFactory;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookView {

    private StringProperty titleProb, isbnProb, authorsProb, publisherProb,
            yearProb, categoryProb;
    private IntegerProperty priceProb, copiesProb, thresholdProb,
            categoryIDProb, requestedProb;

    private String oldIsbn;

    public BookView(Book book) {

        titleProb = new SimpleStringProperty();
        isbnProb = new SimpleStringProperty();
        authorsProb = new SimpleStringProperty();
        publisherProb = new SimpleStringProperty();
        yearProb = new SimpleStringProperty();
        categoryProb = new SimpleStringProperty();
        priceProb = new SimpleIntegerProperty();
        copiesProb = new SimpleIntegerProperty();
        thresholdProb = new SimpleIntegerProperty();
        categoryIDProb = new SimpleIntegerProperty();
        requestedProb = new SimpleIntegerProperty(0);

        titleProb.set(book.getTitle());
        isbnProb.set(book.getIsbn());
        authorsProb.set(String.join(", ", book.getAuthors()));
        publisherProb.set(book.getPublisher());
        yearProb.set(book.getPublishYear());
        priceProb.set(book.getPrice());
        copiesProb.set(book.getCopies());
        thresholdProb.set(book.getThreshold());
        categoryProb.set(book.getCategory());
        categoryIDProb.set(book.getCategoryId());

        oldIsbn = book.getIsbn();

    }

    public IntegerProperty getCategoryIDProb() {
        return categoryIDProb;
    }

    public void setCategoryIDProb(IntegerProperty categoryIDProb) {
        this.categoryIDProb = categoryIDProb;
    }

    public void setThresholdProb(IntegerProperty thresholdProb) {
        this.thresholdProb = thresholdProb;
    }

    public StringProperty getCategoryProb() {
        return categoryProb;
    }

    public void setCategoryProb(StringProperty categoryProb) {
        this.categoryProb = categoryProb;
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

    public IntegerProperty getThresholdProb() {
        return thresholdProb;
    }

    public void setThreasholdProb(IntegerProperty copiesProb) {
        this.thresholdProb = copiesProb;
    }

    public void updateOldIsbn() {
        this.oldIsbn = this.getIsbnProb().getValue();
    }

    public String getOldIsbn() {
        return oldIsbn;
    }

    public Book toBook() {
        BookFactory bkFactory = new BookFactory();
        bkFactory.setAuthors(this.getAuthorsProb().get());
        bkFactory.setCategory(this.getCategoryProb().getValue());
        bkFactory.setCategoryId(this.getCategoryIDProb().getValue());
        bkFactory.setCopies(this.getCopiesProb().getValue());
        bkFactory.setIsbn(this.getIsbnProb().getValue());
        bkFactory.setPrice(this.getPriceProb().getValue());
        bkFactory.setPublisher(this.getPublisherProb().getValue());
        bkFactory.setPublishYear(this.getYearProb().getValue());
        bkFactory.setThreshold(this.getThresholdProb().getValue());
        bkFactory.setTitle(this.getTitleProb().getValue());

        return bkFactory.getBook();
    }

    public IntegerProperty getRequestedProb() {
        return requestedProb;
    }

    public void setRequestedProb(IntegerProperty requestedProb) {
        this.requestedProb = requestedProb;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((isbnProb == null) ? 0 : isbnProb.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookView other = (BookView) obj;
        if (isbnProb == null) {
            if (other.isbnProb != null)
                return false;
        } else if (!isbnProb.equals(other.isbnProb))
            return false;
        return true;
    }
}
