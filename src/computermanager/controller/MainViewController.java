/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager.controller;

//import computermanager.controller.NeededViewController ;
import computermanager.model.Component;
import computermanager.model.Pc;
import es.upv.inf.Database;
import es.upv.inf.Product;
import es.upv.inf.Product.Category;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    @FXML
    private TextField minTextField;
    @FXML
    private TextField maxTextField;
    @FXML
    private Button filterButton;
    @FXML
    private TextField nameFilterTextField;
    @FXML
    private CheckBox onlyAvailableCheckBox;
    @FXML
    private Button clearFilterButton;
    @FXML
    private TableColumn<Component, Double> totalPriceColumn;
    
    //private boolean editable = true ;
    private Pc myeditablePc ;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label ivaLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private Button lessButton;
    @FXML
    private Button moreButton;
    
    

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
       totalPriceColumn.setCellValueFactory(new PropertyValueFactory<Component,Double>("total"));
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
        
        myeditablePc = new Pc();
        myeditablePc.editable=true;
        myeditablePc.totalPrice=0.0;
        
        
        //subtotalLabel.textProperty().bind(Bindings.concat("Subtotal:"+myeditablePc.totalPrice));
        
        
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
    private void filterButtonClicked(MouseEvent event) {

        Product.Category category = categoryChoiceBox.getValue();
        Double min;
        if (!minTextField.getText().equals("")) {
            min = Double.parseDouble(minTextField.getText());
        } else {
            min = 0.0;
        }
        Double max;
        if (!maxTextField.getText().equals("")) {
            max = Double.parseDouble(maxTextField.getText());
        } else {
            max = Double.MAX_VALUE;
        }
        boolean onlyAvailable = onlyAvailableCheckBox.isSelected();
        String descriptionFilter = nameFilterTextField.getText();
        
        List<Product> auxiliarList ;
        if(descriptionFilter.equals("")){
            if(min == 0 && max == 0){
                //Busqueda por categoria solo
                auxiliarList = Database.getProductByCategory(category);
            }else{
                //Busqueda por categoria y precio.
                auxiliarList = Database.getProductByCategoryAndPrice(category, min, max, onlyAvailable);
            }
        }else{
            if(min == 0 && max == 0){
                //Busqueda por categoria y descripcion
                auxiliarList = Database.getProductByCategoryAndDescription(category, descriptionFilter, onlyAvailable);
            }else{
                //Busqueda por categoria precio y descipcion.
                auxiliarList = Database.getProductByCategoryDescriptionAndPrice(category, descriptionFilter, min, max, onlyAvailable);
            }
        }

        /*System.out.println(category);
        System.out.println(min);
        System.out.println(max);
        System.out.println(onlyAvailable);
        System.out.println(descriptionFilter);
        */

        ObservableList<Product> myData = FXCollections.observableList(auxiliarList);
        mainTableView.setItems(myData);

    }

    @FXML
    private void addButtonClicked(MouseEvent event) {
        //System.out.println(myeditablePc.editable);

        if(!myeditablePc.editable){
            //Error por no editable
            //System.out.println("return");
            return;
        }
        Product aux = mainTableView.getSelectionModel().getSelectedItem();
        boolean setted = false;
        boolean outOfStock = false;
        for(int i = 0; i<myPcTableView.getItems().size();i++){
            if(myPcTableView.getItems().get(i).getProduct() == aux){
                if(myPcTableView.getItems().get(i).getCuantity() != aux.getStock()){
                myPcTableView.getItems().get(i).incCuantity();
                myPcTableView.refresh();
                myeditablePc.totalPrice = myeditablePc.totalPrice + myPcTableView.getItems().get(i).getPrice();
                refreshLabels();
                //System.out.println(myeditablePc.totalPrice);
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
            myeditablePc.totalPrice = myeditablePc.totalPrice + newComponent.getPrice();
               refreshLabels();
        }
        
    }

    @FXML
    private void deleteButtonClicked(MouseEvent event) {
        if(!myeditablePc.editable){
            //Error por no editable
            return;
        }
        Component aux = myPcTableView.getItems().remove(myPcTableView.getSelectionModel().getSelectedIndex());
        Double less = aux.getPrice()*aux.getCuantity();
        myeditablePc.totalPrice = myeditablePc.totalPrice - less ;
        refreshLabels();
    }

    /*private void editButtonClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader (getClass().getResource("/computermanager/view/EditView.fxml"));
        VBox root = (VBox) loader.load();
        loader.<EditViewController>getController().initComponent(myPcTableView.getSelectionModel().getSelectedItem(),myPcTableView);

        Stage stage = new Stage();
        Scene scene = new Scene(root,400,300);
        stage.setScene(scene);
        stage.setTitle("Edit view");
        stage.show();
        
    }*/

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
        loader.<LoadViewController>getController().initList(myPcTableView,myeditablePc);
        Stage stage = new Stage();
        Scene scene = new Scene (root,400,400);
        stage.setScene(scene);
        stage.setTitle("Load view");
        stage.show();
                Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();
        
    }

    @FXML
    private void previewButtonClicked(MouseEvent event) throws IOException {
        boolean[] needed = new boolean[6];
        boolean all = true;
        for(int j=0;j<6;j++){
            needed[j]=false;
        }
        for(int i  = 0 ; i < myPcTableView.getItems().size();i++){
            Component aux = myPcTableView.getItems().get(i);
            switch(aux.getCategory()){
                case MOTHERBOARD : needed[0] = true; break ;
                case CPU :needed[1] = true; break ;
                case RAM :needed[2] = true; break ;
                case GPU :needed[3] = true; break ;
                case HDD :needed[4] = true; break ;
                case HDD_SSD :needed[4] = true; break ;
                case CASE :needed[5] = true; break ;
            }
        }
        for(int i = 0;i<6;i++){
                if(!needed[i]){
                    all=false;
                }

        }
        if(!all){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/computermanager/view/NeededView.fxml"));
        VBox root = (VBox) loader.load();
        loader.<NeededViewController>getController().initNeeded(needed);
        Scene scene = new Scene(root,400,400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/computermanager/view/PreView.fxml"));
            BorderPane root = (BorderPane) loader.load();
            loader.<PreViewController>getController().setList(myPcTableView.getItems(),myeditablePc.totalPrice);
                    //System.out.println(myPcTableView.getItems());

            Scene scene = new Scene(root,600, 630);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void clearButtonClicked(MouseEvent event) {
        if(myeditablePc.editable){
        myDataPc.remove(0,myDataPc.size());
        myPcTableView.refresh();
        myeditablePc.totalPrice=0.0;
        refreshLabels();
        }else{
            //Error por no editable
        }
    }

    @FXML
    private void clearFilterButtonClicked(MouseEvent event) {
        minTextField.setText("");
        maxTextField.setText("");
        onlyAvailableCheckBox.setSelected(false);
        nameFilterTextField.setText("");
        Category category = categoryChoiceBox.getValue();
        List<Product> aux = Database.getProductByCategory(category);
        ObservableList<Product> observableaux = FXCollections.observableList(aux);
        mainTableView.setItems(observableaux);
        
    }
    
    private void refreshLabels(){
        DecimalFormat decimalFormater = new DecimalFormat(".##");
        subtotalLabel.setText("Subtotal(sin IVA):"+decimalFormater.format(myeditablePc.totalPrice));
        ivaLabel.setText("IVA:"+decimalFormater.format(myeditablePc.totalPrice*0.21));
        totalLabel.setText("Total:" + decimalFormater.format(myeditablePc.totalPrice + myeditablePc.totalPrice * 0.21));
    }

    @FXML
    private void lessButtonClicked(MouseEvent event) {
        if(!myeditablePc.editable){
            //Error por no editable
            return;
        }
        Component aux = myPcTableView.getSelectionModel().getSelectedItem();
        if (aux.getCuantity() == 1) {
            int index = myPcTableView.getSelectionModel().getSelectedIndex();
            myPcTableView.getItems().remove(index);
            myeditablePc.totalPrice = myeditablePc.totalPrice - aux.getPrice();
            refreshLabels();
            myPcTableView.refresh();

        } else {
            aux.decCuantity();
            myeditablePc.totalPrice = myeditablePc.totalPrice - aux.getPrice();
            refreshLabels();
            myPcTableView.refresh();
        }

    }

    @FXML
    private void moreButtonClicked(MouseEvent event) {
        if(!myeditablePc.editable){
            //Error por no editable
            return;
        }
        Component aux = myPcTableView.getSelectionModel().getSelectedItem();
        if (aux.getCuantity() < aux.getStock()) {
            aux.incCuantity();
            myeditablePc.totalPrice = myeditablePc.totalPrice + aux.getPrice();
            refreshLabels();
            myPcTableView.refresh();
        }else{
            //OutOfStockError
        }
    }
    public void recalculePrice(){
        ObservableList<Component> aux = myPcTableView.getItems();
        myeditablePc.totalPrice=0.0;
        for(int i=0; i<aux.size();i++){
            myeditablePc.totalPrice = myeditablePc.totalPrice + (aux.get(i).getPrice()*aux.get(i).getCuantity());
        }
        refreshLabels();
    }
    
    public void init(ObservableList<Component> list,Pc pc){
        myPcTableView.setItems(list);
        myeditablePc=pc;
        myeditablePc.totalPrice=0.0;
        for(int i=0; i<list.size();i++){
            myeditablePc.totalPrice = myeditablePc.totalPrice + (list.get(i).getPrice()*list.get(i).getCuantity());
        }
        refreshLabels();
    }

}
