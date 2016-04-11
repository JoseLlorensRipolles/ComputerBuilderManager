/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager.controller;

import computermanager.model.Component;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author JoseLlorens
 */
public class EditViewController implements Initializable {

    @FXML
    private Label categoryLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private TextField cuantityTextField;
    @FXML
    private Button moreButton;
    @FXML
    private Button lessButton;
    
    private Component component;
    @FXML
    private Label descriptionLabel;
    
    private TableView<Component> list;
    @FXML
    private Button CloseButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cuantityTextField.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal) {

                String aux = cuantityTextField.getText();
                // asegurase de que no es "" que es lo que torna quan esta buid
                //Pero segurament fer-ho que se tinga que apretar enter
                Integer res = Integer.parseInt(aux);
                if(res >0 && res <= component.getStock()){
                component.setCuantity(res);
                list.refresh();
                }else{
                    System.out.println("Aqui va un error");
                    cuantityTextField.setText(oldVal.toString());
                }
            }
        });

    }    

    @FXML
    private void moreButtonClicked(MouseEvent event) {
        component.incCuantity();
        cuantityTextField.setText(""+component.getCuantity());
        list.refresh();
    }

    @FXML
    private void lessButtonClicked(MouseEvent event) {
        component.decCuantity();
        cuantityTextField.setText(""+component.getCuantity());
        list.refresh();
    }
    
    public void initComponent(Component c, TableView<Component> list){
        this.component = c;
        this.list=list;
        priceLabel.setText("Price: "+component.getPrice());
        categoryLabel.setText("Category: "+component.getCategory().toString());
        descriptionLabel.setText(component.getDescription());
        cuantityTextField.setText("" + component.getCuantity());
    }

    @FXML
    private void closeButtonClicked(MouseEvent event) {
        Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();
    }

    @FXML
    private void cuantityTextFieldKeyPressed(KeyEvent event) {
        KeyCode aux = event.getCode();
        if (aux.equals(KeyCode.ENTER)) {
            Node n = (Node) event.getSource();
            n.getScene().getWindow().hide();
        }
    }

}
