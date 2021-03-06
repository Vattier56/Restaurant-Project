package restaurant.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import restaurant.database.OrderRepository;
import restaurant.exception.SessionNotSet;
import restaurant.misc.Builder;
import restaurant.misc.ContextWrapper;
import restaurant.misc.Money;
import restaurant.misc.Session;
import restaurant.model.Order;
import restaurant.model.Product;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

import java.net.URL;
import java.util.ResourceBundle;

public class History extends DraggableWindow {

    @FXML
    private VBox orderWrapper;

    @FXML
    private void onBackClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/dashboard.fxml"));
    }

    private void showOrder(Order order) {
        VBox orderContainer = new VBox();
        orderContainer.getStyleClass().add("order-item");
        orderContainer.setSpacing(32.0);

        Label orderHeader = new Label("Zamowienie nr " + order.getId() + " - " + order.getStatus());
        orderHeader.setFont(Font.font(32.0));
        orderHeader.getStyleClass().add("order-product-header");
        orderContainer.getChildren().add(orderHeader);

        CurrentOrder.showProducts(order, orderContainer);

        orderWrapper.getChildren().add(orderContainer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggingEvents();

        try {
            for (Order order : Session.getClient().getOrderList()) {
                if (order.getStatus() == Order.Status.ZREALIZOWANE) {
                    showOrder(order);
                }
            }
        } catch (SessionNotSet sessionNotSet) {
            throw new IllegalStateException();
        }
    }
}
