package controller;

import java.util.List;
import java.util.Properties;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class CardSceneController {
	@FXML
	private TableView<CardView> bookTableView;
	@FXML
	private TableColumn<CardView, String> cardNumCol;
	@FXML
	private TableColumn<CardView, String> balanceCol;
	@FXML
	private TableColumn<CardView, String> expDateCol;
	@FXML
	private FlowPane newBkFPane;
	@FXML
	private TextField cardNumTF;
	@FXML
	private TextField pinTF;
	@FXML
	private TextField balanceTF;
	@FXML
	private DatePicker expDateTF;
	@FXML
	private Button addCreditBtn;
	@FXML
	private Button backBtn;

	private Controller ctrl;
	private ObservableList<CardView> cards = FXCollections.observableArrayList();

	@FXML
	void initialize() {
		ctrl = Controller.getInstance();
		updateView();
	}

	public void showOrders(List<Properties> cards) {
		this.cards.clear();
		for (Properties p : cards) {
			CardView cardView = new CardView(p);
			this.cards.add(cardView);
		}
		this.bookTableView.refresh();
	}

	public void updateView() {

		// removeCol = new TableColumn<>();

		balanceCol.setCellValueFactory(cellData -> cellData.getValue().getBalanceProb());
		cardNumCol.setCellValueFactory(cellData -> cellData.getValue().getCardNumProb());
		expDateCol.setCellValueFactory(cellData -> cellData.getValue().getExpDateProb());

		// bookTableView.getColumns().addAll(removeCol);

		try {
			showOrders(ctrl.getCards());
		} catch (Exception e) {
			// e.printStackTrace();
		}

		bookTableView.setItems(cards);
	}

	public class CardView {
		private StringProperty cardNumProb, balanceProb, expDateProb;

		public StringProperty getCardNumProb() {
			return cardNumProb;
		}

		public void setCardNumProb(StringProperty cardNumProb) {
			this.cardNumProb = cardNumProb;
		}

		public StringProperty getBalanceProb() {
			return balanceProb;
		}

		public void setBalanceProb(StringProperty balanceProb) {
			this.balanceProb = balanceProb;
		}

		public StringProperty getExpDateProb() {
			return expDateProb;
		}

		public void setExpDateProb(StringProperty expDateProb) {
			this.expDateProb = expDateProb;
		}

		public CardView(String cardNum, String expDate, String balance) {
			cardNumProb = new SimpleStringProperty();
			balanceProb = new SimpleStringProperty();
			expDateProb = new SimpleStringProperty();

			cardNumProb.set(cardNum);
			balanceProb.set(expDate);
			expDateProb.set(balance);
		}

		public CardView(Properties card) {
			this(card.getProperty("number"), card.getProperty("balance"), card.getProperty("expirationdate"));
		}
	}

	// Event Listener on Button[#addCreditBtn].onAction
	@FXML
	public void handleAddCrardBtnAction(ActionEvent event) {
		try {
			ctrl.addCreditCard(cardNumTF.getText(), pinTF.getText(), expDateTF.getValue().toString(),
					balanceTF.getText());
			cards.add(new CardView(cardNumTF.getText(), expDateTF.getValue().toString(), balanceTF.getText()));
			bookTableView.refresh();
			ctrl.showConfirmDialogue("Credit Card added successfully");
		} catch (Exception ex) {
			// ex.printStackTrace();
			ctrl.showErrorDialogue(ex.getMessage());
		}
	}

	// Event Listener on Button[#backBtn].onAction
	@FXML
	public void handleBackBtnAction(ActionEvent event) {
		try {
			ctrl.viewUserDashBoard();
		} catch (Exception ex) {
			ctrl.showErrorDialogue(ex.getMessage());
		}
	}
}
