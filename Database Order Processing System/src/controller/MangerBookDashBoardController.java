package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.prism.impl.Disposer.Record;

import data.Book;
import data.BookFactory;
import data.Category;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import view.BookView;

public class MangerBookDashBoardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BookView> bookTableView;

    @FXML
    private FlowPane newBkFPane;

    @FXML
    private TableColumn<BookView, String> titleCol, isbnCol, authorsCol,
            yearCol, publisherCol, categoryCol;
    @FXML
    private TableColumn<BookView, Integer> priceCol, copiesCol, thresholdCol;

    private TableColumn removeCol, updateCol;

    private DatePicker newBkDate;

    @FXML
    private Button addBkBtn;

    @FXML
    private ChoiceBox<Category> newBkCategoryCB;

    @FXML
    private TextField newBkAuthorTF, newBkCopiesTF, newBkISBNTF, newBkPriceTF,
            newBkPublisherTF, newBkThresholdTF, newBkTitleTF;

    @FXML
    private Button backBtn;

    @FXML
    private ChoiceBox<Category> srchBkCategoryCB;

    @FXML
    private TextField srchBkAuthorTF, srchBkISBNTF, srchBkPublisherTF,
            srchBkTitleTF;

    @FXML
    private Button srchBtn;

    @FXML
    void initialize() {
        assert addBkBtn != null : "fx:id=\"addBkBtn\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkAuthorTF != null : "fx:id=\"newBkAuthorTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkCategoryCB != null : "fx:id=\"newBkCategoryTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkCopiesTF != null : "fx:id=\"newBkCopiesTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkISBNTF != null : "fx:id=\"newBkISBNTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkPriceTF != null : "fx:id=\"newBkPriceTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkPublisherTF != null : "fx:id=\"newBkPublisherTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkThresholdTF != null : "fx:id=\"newBkThresholdTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert newBkTitleTF != null : "fx:id=\"newBkTitleTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert srchBkAuthorTF != null : "fx:id=\"srchBkAuthorTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert srchBkCategoryCB != null : "fx:id=\"srchBkCategoryCB\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert srchBkISBNTF != null : "fx:id=\"srchBkISBNTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert srchBkPublisherTF != null : "fx:id=\"srchBkPublisherTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert srchBkTitleTF != null : "fx:id=\"srchBkTitleTF\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";
        assert srchBtn != null : "fx:id=\"srchBtn\" was not injected: check your FXML file 'UserDashboardScene.fxml'.";

        ctrl = Controller.getInstance();
        updateView();
    }

    private Controller ctrl;
    private ObservableList<BookView> books = FXCollections
            .observableArrayList();

    @SuppressWarnings("unchecked")
    public void updateView() {
        newBkDate = new DatePicker(LocalDate.now());
        newBkFPane.getChildren().add(newBkDate);
        try {
            for (Category c : ctrl.getCategories()) {
                srchBkCategoryCB.getItems().add(c);
                newBkCategoryCB.getItems().add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        titleCol.setCellValueFactory(
                cellData -> cellData.getValue().getTitleProb());
        yearCol.setCellValueFactory(
                cellData -> cellData.getValue().getYearProb());
        isbnCol.setCellValueFactory(
                cellData -> cellData.getValue().getIsbnProb());
        authorsCol.setCellValueFactory(
                cellData -> cellData.getValue().getAuthorsProb());
        publisherCol.setCellValueFactory(
                cellData -> cellData.getValue().getPublisherProb());
        categoryCol.setCellValueFactory(
                cellData -> cellData.getValue().getCategoryProb());
        priceCol.setCellValueFactory(
                cellData -> cellData.getValue().getPriceProb().asObject());
        copiesCol.setCellValueFactory(
                cellData -> cellData.getValue().getCopiesProb().asObject());
        thresholdCol.setCellValueFactory(
                cellData -> cellData.getValue().getThresholdProb().asObject());

        titleCol.setEditable(true);
        titleCol.setCellFactory(TextFieldTableCell.<BookView> forTableColumn());
        isbnCol.setEditable(true);
        isbnCol.setCellFactory(TextFieldTableCell.<BookView> forTableColumn());
        yearCol.setEditable(true);
        yearCol.setCellFactory(TextFieldTableCell.<BookView> forTableColumn());
        authorsCol.setEditable(true);
        authorsCol
                .setCellFactory(TextFieldTableCell.<BookView> forTableColumn());
        publisherCol.setEditable(true);
        publisherCol
                .setCellFactory(TextFieldTableCell.<BookView> forTableColumn());

        StringConverter<Integer> conv = new StringConverter<Integer>() {

            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        };
        priceCol.setEditable(true);
        priceCol.setCellFactory(
                TextFieldTableCell.<BookView, Integer> forTableColumn(conv));
        copiesCol.setEditable(true);
        copiesCol.setCellFactory(
                TextFieldTableCell.<BookView, Integer> forTableColumn(conv));
        thresholdCol.setEditable(true);
        thresholdCol.setCellFactory(
                TextFieldTableCell.<BookView, Integer> forTableColumn(conv));

        removeCol = new TableColumn<>("");
        updateCol = new TableColumn<>("");
        removeCol.setPrefWidth(125);
        updateCol.setPrefWidth(125);
        bookTableView.getColumns().addAll(removeCol, updateCol);

        removeCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(
                            TableColumn.CellDataFeatures<Record, Boolean> p) {
                        return new SimpleBooleanProperty(p.getValue() != null);
                    }
                });
        removeCol.setCellFactory((p) -> {
            return new RemoveButtonCell();
        });

        updateCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(
                            TableColumn.CellDataFeatures<Record, Boolean> p) {
                        return new SimpleBooleanProperty(p.getValue() != null);
                    }
                });
        updateCol.setCellFactory((p) -> {
            return new UpdateButtonCell();
        });

        bookTableView.setItems(books);
    }

    private class RemoveButtonCell extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Remove");

        RemoveButtonCell() {

            // Action when the button is pressed
            cellButton.setOnAction((t) -> {
                Book book = books.get(getIndex()).toBook();
                try {
                    ctrl.removeBook(book);
                    books.remove(getIndex());
                    bookTableView.refresh();
                    new Alert(Alert.AlertType.INFORMATION, "Book removed successfully.")
                    .showAndWait();
                } catch (Exception e) {
                    ctrl.showErrorDialogue(e.getMessage());
                }
            });
        }

        // Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }

    private class UpdateButtonCell extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Update");

        UpdateButtonCell() {

            // Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    BookView book = books.get(getIndex());
                    try {
                        ctrl.updateBook(book.toBook(), book.getOldIsbn());
                        book.updateOldIsbn();
                        bookTableView.refresh();
                        new Alert(Alert.AlertType.INFORMATION, "Book updated successfully.")
                        .showAndWait();
                    } catch (Exception e) {
                        ctrl.showErrorDialogue(e.getMessage());
                    }
                }
            });
        }

        // Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }

    public void showBooks(List<Book> books) {
        this.books.clear();
        for (Book book : books) {
            BookView bookView = new BookView(book);
            this.books.add(bookView);
        }
        this.bookTableView.refresh();
    }

    @FXML
    protected void handleBkSearchBtnAction(ActionEvent e) {
        // TODO : add only valid vals
        BookFactory bkFactory = new BookFactory();
        bkFactory.setIsbn(srchBkISBNTF.getText());
        bkFactory.setTitle(srchBkTitleTF.getText());
        bkFactory.setAuthors(srchBkAuthorTF.getText());
        if (!srchBkCategoryCB.getSelectionModel().isEmpty()) {
            bkFactory.setCategoryId(srchBkCategoryCB.getValue().getId());
            bkFactory.setCategory(srchBkCategoryCB.getValue().getType());
        }
        bkFactory.setPublisher(srchBkPublisherTF.getText());

        try {
            showBooks(this.ctrl.bookSearch(bkFactory.getBook()));
        } catch (SQLException e1) {
            new Alert(Alert.AlertType.ERROR, e1.getMessage()).showAndWait();
        }
    }

    @FXML
    protected void handleBackBtnAction(ActionEvent e) {
        try {
            ctrl.viewUserDashBoard();
            ctrl.books.clear();
        } catch (Exception ex) {
            ctrl.showErrorDialogue(ex.getMessage());
        }
    }

    @FXML
    protected void handleAddBkBtnAction(ActionEvent e) {
        try {
            BookFactory bkFactory = new BookFactory();
            bkFactory.setTitle(newBkTitleTF.getText());
            bkFactory.setAuthors(newBkAuthorTF.getText());
            if (!newBkCategoryCB.getSelectionModel().isEmpty()) {
                bkFactory.setCategoryId(newBkCategoryCB.getValue().getId());
                bkFactory.setCategory(newBkCategoryCB.getValue().getType());
            } else {
                throw new RuntimeException("You must choose a valid category!");
            }
            bkFactory.setPublisher(newBkPublisherTF.getText());
            bkFactory.setPublishYear(newBkDate.getValue().toString());
            bkFactory.setIsbn(newBkISBNTF.getText());
            bkFactory.setPrice(newBkPriceTF.getText());
            bkFactory.setCopies(newBkCopiesTF.getText());
            bkFactory.setThreshold(newBkThresholdTF.getText());

            ctrl.addBook(bkFactory.getBook());
            new Alert(Alert.AlertType.INFORMATION,
                    "Book added successfully.").showAndWait();
        } catch (Exception ex) {
            ctrl.showErrorDialogue(ex.getMessage());
        }
    }
}
