/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager.controller;

import computermanager.model.Component;
import computermanager.model.Pc;
import es.upv.inf.Database;
import es.upv.inf.Product;
import es.upv.inf.Product.Category;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML
    private ChoiceBox<Product.Category> categoryChoiceBox;
    @FXML
    private Button BuscarButton;
    @FXML
    private Button addButton;
    @FXML
    private TableColumn<Component, String> nombreColumnMyPc;
    @FXML
    private TableColumn<Component, Category> categoryColumnMyPc;
    @FXML
    private TableColumn<Component, Double> precioColumnMyPc;
    @FXML
    private TableView<Component> myPcTableView;

    private TableColumn<Component,Integer> cuantityColumn;
    
    ObservableList<Component> myDataPc ;
    List<Component> myComponentsPc;
    
    List<Component> arrayMiPc;
    @FXML
    private TableColumn<Component, Integer> cuantityColumnMyPc;
    
    
    private Pc myPc ;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button newButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button previewButton;
    @FXML
    private Button clear;
    @FXML
    private TableColumn<Component, Integer> stockColumn;
    
    List<Product> array ;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Product> array = Database.getProductByCategory(Category.CPU);
        //List<Product> array = new ArrayList<Product>();
        ObservableList<Product> myData = FXCollections.observableList(array);
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<Product, Category>("category"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        disponibilidadColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        mainTableView.setItems(myData);
        
        
        List<Product.Category> categoryList = new ArrayList<Product.Category>();
        categoryList.add(Product.Category.CPU);
        categoryList.add(Product.Category.RAM);
        categoryList.add(Product.Category.HDD);
        categoryList.add(Product.Category.HDD_SSD);
        categoryList.add(Product.Category.POWER_SUPPLY);
        categoryList.add(Product.Category.MOTHERBOARD);
        categoryList.add(Product.Category.KEYBOARD);
        categoryList.add(Product.Category.MOUSE);
        categoryList.add(Product.Category.SCREEN);
        categoryList.add(Product.Category.GPU);
        categoryList.add(Product.Category.CASE);
        categoryList.add(Product.Category.DVD_WRITER);
        categoryList.add(Product.Category.FAN);
        categoryList.add(Product.Category.MULTIREADER);
        categoryList.add(Product.Category.SPEAKER);
        ObservableList<Product.Category> observableCategoryList = FXCollections.observableList(categoryList);
        categoryChoiceBox.setItems(observableCategoryList);
        categoryChoiceBox.getSelectionModel().select(0);
        
        arrayMiPc = new ArrayList<Component>();
        
        myDataPc = FXCollections.observableList(arrayMiPc);

        myPcTableView.setItems(myDataPc);
        
        
        /*array = new ArrayList<Product>();
        myData = FXCollections.observableList(array);
        */
        cuantityColumnMyPc.setCellValueFactory(new PropertyValueFactory<Component,Integer>("cuantity"));
        nombreColumnMyPc.setCellValueFactory(new PropertyValueFactory<Component,String>("description"));
        precioColumnMyPc.setCellValueFactory(new PropertyValueFactory<Component,Double>("price"));
        categoryColumnMyPc.setCellValueFactory(new PropertyValueFactory<Component,Category>("category"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<Component,Integer>("stock"));
        //mainTableView.setItems(myData);
        
        categoryChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
        new ChangeListener<Number>(){
            public void changed(ObservableValue ov, Number value, Number new_value){
                int index = categoryChoiceBox.getSelectionModel().getSelectedIndex();
                Product.Category aux = categoryChoiceBox.getItems().get(index);
                List<Product> newList = Database.getProductByCategory(aux);
                ObservableList<Product> myNewData = FXCollections.observableList(newList);
                mainTableView.setItems(myNewData);
            }
        });
        
        
    }    

    private void newClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass().getResource("/computermanager/view/MainView.fxml"));
        HBox root =(HBox)loader.load();
        Scene scene = new Scene(root,800,800);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle("Hola");
        newStage.show();
    }

    @FXML
    private void buscarButtonClicked(MouseEvent event) {
        
        Product.Category aux1 = categoryChoiceBox.getValue();
        List<Product> array = Database.getProductByCategory(aux1);
        ObservableList<Product> myData = FXCollections.observableList(array);
        /*cuantityColumnMyPc.setCellValueFactory(new PropertyValueFactory<Component,Integer>("cuantity"));
        nombreColumnMyPc.setCellValueFactory(new PropertyValueFactory<Component,String>("description"));
        precioColumnMyPc.setCellValueFactory(new PropertyValueFactory<Component,Double>("price"));
        categoryColumnMyPc.setCellValueFactory(new PropertyValueFactory<Component,Category>("category"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<Component,Integer>("stock"));
        */
        mainTableView.setItems(myData);
        
    }

    @FXML
    private void addButtonClicked(MouseEvent event) {
        Product aux = mainTableView.getSelectionModel().getSelectedItem();
        boolean setted = false;
        boolean outOfStock = false;
        for(int i = 0; i<myPcTableView.getItems().size();i++){
            if(myPcTableView.getItems().get(i).getProduct() == aux){
                if(myPcTableView.getItems().get(i).getCuantity() != aux.getStock()){
                myPcTableView.getItems().get(i).incCuantity();
                myPcTableView.refresh();
                setted = true;
                break;
                }else{
                    outOfStock=true;
                }
            }
        }
        if(!setted && !outOfStock){
            Component newComponent = new Component(aux);
            myPcTableView.getItems().add(newComponent);
        }
        
    }

    @FXML
    private void deleteButtonClicked(MouseEvent event) {
        myPcTableView.getItems().remove(myPcTableView.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void editButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass().getResource("/computermanager/view/EditView.fxml"));
        VBox root = (VBox) loader.load();
        loader.<EditViewController>getController().initComponent(myPcTableView.getSelectionModel().getSelectedItem(),myPcTableView);

        Stage stage = new Stage();
        Scene scene = new Scene(root,400,300);
        stage.setScene(scene);
        stage.setTitle("Edit view");
        stage.show();
        
    }

    @FXML
    private void newButtonClicked(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader  = new FXMLLoader (getClass().getResource("/computermanager/view/MainView.fxml"));
        HBox root = (HBox) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Main view");
        stage.show();
    }

    @FXML
    private void saveButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader loader  = new FXMLLoader (getClass().getResource("/computermanager/view/SaveView.fxml"));
        VBox root = (VBox) loader.load();
        loader.<SaveViewController>getController().initList(myDataPc);
        Stage stage = new Stage();
        Scene scene = new Scene (root,400,400);
        stage.setScene(scene);
        stage.setTitle("Save view");
        stage.show();
    }

    @FXML
    private void loadButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass().getResource("/computermanager/view/LoadView.fxml"));
        VBox root = (VBox) loader.load();
        loader.<LoadViewController>getController().initList(myPcTableView);
        Stage stage = new Stage();
        Scene scene = new Scene (root,400,400);
        stage.setScene(scene);
        stage.setTitle("Load view");
        stage.show();
    }

    @FXML
    private void previewButtonClicked(MouseEvent event) {
    }

    @FXML
    private void clearButtonClicked(MouseEvent event) {
        myDataPc.remove(0,myDataPc.size());
        myPcTableView.refresh();
        System.out.println("Hola, soy el cabezón");
    }
    
}
