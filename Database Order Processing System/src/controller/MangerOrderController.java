package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.prism.impl.Disposer.Record;

import data.Order;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;

public class MangerOrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<OrderView> bookTableView;

    @FXML
    private FlowPane newBkFPane;

    @FXML
    private TableColumn<OrderView, String> isbnCol;
    @FXML
    private TableColumn<OrderView, Integer> copiesCol;

    private TableColumn removeCol;

    private DatePicker newBkDate;

    @FXML
    private Button addBkBtn;

    @FXML
    private TextField newISBNTF, newCopiesTF;

    @FXML
    private Button backBtn;

    @FXML
    void initialize() {
        ctrl = Controller.getInstance();
        updateView();
    }

    private Controller ctrl;
    private ObservableList<OrderView> orders = FXCollections
            .observableArrayList();

    @SuppressWarnings("unchecked")
    public void updateView() {
        
        removeCol = new TableColumn<>();
        
        isbnCol.setCellValueFactory(
                cellData -> cellData.getValue().getIsbnProb());
        copiesCol.setCellValueFactory(
                cellData -> cellData.getValue().getCopiesProb().asObject());

        bookTableView.getColumns().addAll(removeCol);

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
        
        try {
            showOrders(ctrl.getOrders());
        } catch (Exception e) {
//            e.printStackTrace();
        }
        
        bookTableView.setItems(orders);
    }

    public class OrderView {
        private StringProperty isbnProb;
        private IntegerProperty copiesProb;

        public OrderView(Order order) {
            isbnProb = new SimpleStringProperty();
            copiesProb = new SimpleIntegerProperty();

            isbnProb.set(order.getBookIsbn());
            copiesProb.set(order.getOrderedCopies());
        }

        public StringProperty getIsbnProb() {
            return isbnProb;
        }

        public void setIsbnProb(StringProperty isbnProb) {
            this.isbnProb = isbnProb;
        }

        public IntegerProperty getCopiesProb() {
            return copiesProb;
        }

        public void setCopiesProb(IntegerProperty copiesProb) {
            this.copiesProb = copiesProb;
        }
        
        public Order toOrder() {
            return new Order(isbnProb.get(), copiesProb.get());
        }
    }

    private class RemoveButtonCell extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Confirm");

        RemoveButtonCell() {

            // Action when the button is pressed
            cellButton.setOnAction((t) -> {
                Order order = orders.get(getIndex()).toOrder();
                try {
                    ctrl.confirmOrder(order);
                    orders.remove(getIndex());
                    bookTableView.refresh();
                    ctrl.showConfirmDialogue("Order removed succesfully");
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

    public void showOrders(List<Order> orders) {
        this.orders.clear();
        for (Order order : orders) {
            OrderView orderView = new OrderView(order);
            this.orders.add(orderView);
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

    @FXML
    protected void handleAddOrderBtnAction(ActionEvent e) {
        try {
            Order order = new Order(newISBNTF.getText(), Integer.parseInt(newCopiesTF.getText()));
            ctrl.addOrder(order);
            orders.add(new OrderView(order));
            bookTableView.refresh();
        } catch (Exception ex) {
//            ex.printStackTrace();
            ctrl.showErrorDialogue(ex.getMessage());
            bookTableView.refresh();
        }
    }
}
