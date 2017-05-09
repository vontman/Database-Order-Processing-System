package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.prism.impl.Disposer.Record;

import data.Book;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import view.BookView;

public class CartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BookView> bookTableView;

    @FXML
    private TableColumn<BookView, String> titleCol, isbnCol, authorsCol,
            yearCol, publisherCol, categoryCol;
    @FXML
    private TableColumn<BookView, Integer> priceCol, itemsCol;

    private TableColumn removeCol;

    @FXML
    private Button backBtn;

    @FXML
    void initialize() {
        ctrl = Controller.getInstance();
    }

    private Controller ctrl;

    @SuppressWarnings("unchecked")
    public void setCart() {
        titleCol.setCellValueFactory(
                cellData -> cellData.getValue().getTitleProb());
        isbnCol.setCellValueFactory(
                cellData -> cellData.getValue().getIsbnProb());
        authorsCol.setCellValueFactory(
                cellData -> cellData.getValue().getAuthorsProb());
        publisherCol.setCellValueFactory(
                cellData -> cellData.getValue().getPublisherProb());
        yearCol.setCellValueFactory(
                cellData -> cellData.getValue().getYearProb());
        categoryCol.setCellValueFactory(
                cellData -> cellData.getValue().getCategoryProb());
        priceCol.setCellValueFactory(
                cellData -> cellData.getValue().getPriceProb().asObject());
        itemsCol.setCellValueFactory(
                cellData -> cellData.getValue().getRequestedProb().asObject());
        removeCol = new TableColumn<>("");
        removeCol.setPrefWidth(125);
        bookTableView.getColumns().add(removeCol);

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

        bookTableView.setItems(ctrl.cartBooks);
    }

    private class RemoveButtonCell extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Remove");

        RemoveButtonCell() {
            // Action when the button is pressed
            cellButton.setOnAction((t) -> {
                Book book = ctrl.cartBooks.get(getIndex()).toBook();
                try {
                    ctrl.removeFromCart(book);
                    bookTableView.refresh();
                    ctrl.showConfirmDialogue(
                            "Book from cart removed succesfully");
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

    public void showBooks(List<Book> books) {
        this.ctrl.cartBooks.clear();
        for (Book book : books) {
            BookView bookView = new BookView(book);
            this.ctrl.cartBooks.add(bookView);
        }
        this.bookTableView.refresh();
    }

    @FXML
    protected void handleBackBtnAction(ActionEvent e) {
        try {
            ctrl.viewUserDashBoard();
        } catch (Exception ex) {
            ctrl.showErrorDialogue(ex.getMessage());
        }
    }
}
