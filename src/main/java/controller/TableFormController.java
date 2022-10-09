package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerTM;

import java.util.Optional;

public class TableFormController {
    public TableView<CustomerTM> tblCustomers;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public Button btnSaveCustomer;
    public Button btnNewCustomer;
    public Button btnDeleteCustomer;

    public void initialize(){
        btnDeleteCustomer.setDisable(true);
        /*Lets map column names*/

        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));


        btnSaveCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String id=txtId.getText();
                String name=txtName.getText();
                String address=txtAddress.getText();
                ObservableList<CustomerTM> items = tblCustomers.getItems();

                if(id.isBlank()){
                    new Alert(Alert.AlertType.ERROR,"Customer ID Can't Empty").showAndWait();
                    txtId.requestFocus();
                    return;
                }else if(txtName.getText().isBlank()){
                    new Alert(Alert.AlertType.ERROR,"Customer Name Can't b e Empty").showAndWait();
                    txtName.requestFocus();
                    return;
                }else if(txtAddress.getText().isBlank()){
                    new Alert(Alert.AlertType.ERROR,"Customer Address Can't b e Empty").showAndWait();
                    txtAddress.requestFocus();
                    return;
                }
                for(CustomerTM customer:items){
                    if(customer.getId().equals(id)){
                        new Alert(Alert.AlertType.ERROR,"Duplicate ID are not Allowed").showAndWait();
                        txtId.requestFocus();
                        return;
                    }
                }
                items.add(new CustomerTM(id,txtName.getText(),txtAddress.getText()));
                txtId.clear();
                txtName.clear();
                txtAddress.clear();
                txtId.requestFocus();

            }
        });





        btnNewCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                txtId.clear();
                txtName.clear();
                txtAddress.clear();
                txtId.requestFocus();
            }
        });

        tblCustomers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends CustomerTM> observableValue,
                                CustomerTM prvCustomer,
                                CustomerTM newCustomer) {

            }
        });

        tblCustomers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends CustomerTM> observableValue,
                                CustomerTM prv,
                                CustomerTM current) {
                if(current==null){
                    btnDeleteCustomer.setDisable(true);
                    return;

                }

                btnDeleteCustomer.setDisable(false);
                txtId.setText(current.getId());
                txtName.setText(current.getName());
                txtAddress.setText(current.getAddress());

            }
        });
        btnDeleteCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Optional<ButtonType> selectedOption = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete Customer?",ButtonType.YES,ButtonType.NO).showAndWait();
                if(selectedOption.get()==ButtonType.YES){
                    ObservableList<CustomerTM> items = tblCustomers.getItems();
                    CustomerTM selectedItem = tblCustomers.getSelectionModel().getSelectedItem();
                    if(selectedItem==null) return;
                    items.remove(selectedItem);
                    txtId.clear();
                    txtName.clear();
                    txtAddress.clear();
                    txtId.requestFocus();
                }


            }
        });








    }


}
