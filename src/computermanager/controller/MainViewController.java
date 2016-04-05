/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager.controller;

import es.upv.inf.Database;
import es.upv.inf.Product;
import es.upv.inf.Product.Category;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author JoseLlorens
 */
public class MainViewController implements Initializable {

    @FXML
    private TableView<Product> mainTableView;
    @FXML
    private TableColumn<Product, String> nombreColumn;
    @FXML
    private TableColumn<Product, Category> categoriaColumn;
    @FXML
    private TableColumn<Product, Double> precioColumn;
    @FXML
    private TableColumn<Product, Integer> disponibilidadColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Product> array = Database.getProductByCategory(Category.RAM);
        ObservableList<Product> myData = FXCollections.observableList(array);
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<Product, Category>("category"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        disponibilidadColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        mainTableView.setItems(myData);
    }    
    
}
