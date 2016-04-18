/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager.controller;

import computermanager.model.Component;
import es.upv.inf.Product;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author JoseManuel
 */
public class PreViewController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Button imprimirButton;
    @FXML
    private Label dateLabel;
    @FXML
    private Label precioLabel;
    @FXML
    private TableView<Component> tableView;
    @FXML
    private TableColumn<Component, String> descriptionColumn;
    @FXML
    private TableColumn<Component, Product.Category> categoryColumn;
    @FXML
    private TableColumn<Component, Integer> cuantityColumn;
    @FXML
    private TableColumn<Component, Double> priceColumn;
    @FXML
    private TableColumn<Component, Double> totalColumn;
    
    private ObservableList<Component> lista ;
    
    private Double totalPrice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("/computermanager/resources/LogicalIncrementsLogo.png");
        imageView.setImage(image);
        
    }    

    @FXML
    private void imprimirButtonClicked(MouseEvent event) {
    }
    
    public void setList(ObservableList<Component> list, Double total){
        lista = list;
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Component,String>("description"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Component,Product.Category>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Component,Double>("price"));
        cuantityColumn.setCellValueFactory(new PropertyValueFactory<Component,Integer>("cuantity"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<Component,Double>("total"));
        tableView.setItems(lista);
        totalPrice=total;
        DecimalFormat decimalformatter = new DecimalFormat(".##");
        precioLabel.setText(precioLabel.getText()+decimalformatter.format(totalPrice));
        
    }
    
}
