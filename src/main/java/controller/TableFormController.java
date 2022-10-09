package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.customerTM;

public class TableFormController {
    public TableView<customerTM> tblCustomers;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public Button btnSaveCustomer;

    public void initialize(){
        /*Lets map column names*/

        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        ObservableList<customerTM> tblRows = tblCustomers.getItems();
        customerTM sandaru = new customerTM("c001", "Sandaru", "Kirindiwela");
        customerTM ruwan = new customerTM("c002", "Ruwan", "Panadura");

        tblRows.add(sandaru);
        tblRows.add(ruwan);

        btnSaveCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<customerTM> items = tblCustomers.getItems();
                items.add(new customerTM(txtId.getText(),txtName.getText(),txtAddress.getText()));
                txtId.clear();
                txtName.clear();
                txtAddress.clear();
                txtId.requestFocus();

            }
        });


    }

    public void btnSaveCustomer(ActionEvent actionEvent) {
    }
}
