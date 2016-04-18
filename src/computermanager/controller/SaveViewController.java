/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager.controller;

import computermanager.model.Component;
import computermanager.model.Pc;
import computermanager.model.PcList;
import es.upv.inf.Product;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * FXML Controller class
 *
 * @author JoseLlorens
 */
public class SaveViewController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private ObservableList<Component> list;

    @FXML
    private TableView<Component> tableView;
    @FXML
    private TableColumn<Component,String> componentColumn;
    @FXML
    private TableColumn<Component,Integer> cuantityColumn;
    @FXML
    private TableColumn<Component,Double> priceColumn;
    @FXML
    private TableColumn<Component, Product.Category> categoryColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveButtonClicked(MouseEvent event) {
        Pc pc = new Pc(nameTextField.getText(), list);
        pc.editable =  true;
        /*
        PcList theList = new PcList();
        List<Pc> arrayList = new ArrayList<Pc>();
        arrayList.add(pc);
        theList.setPcList(arrayList);
        */
        //Carga
        try {
        File file = new File("pcs.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(PcList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            PcList aux  =(PcList) jaxbUnmarshaller.unmarshal(file);
            aux.getPcList().add(pc);
        
        
        
        
           
            //File file = new File("pcs.xml");
            //JAXBContext jaxbContext = JAXBContext.newInstance(PcList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(aux, file);
            jaxbMarshaller.marshal(aux, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void CancelButtonClicked(MouseEvent event) {
        Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();
    }
    
    public void initList(ObservableList<Component> list){
        this.list = list;
        componentColumn.setCellValueFactory(new PropertyValueFactory<Component, String>("description"));
        cuantityColumn.setCellValueFactory(new PropertyValueFactory<Component, Integer>("cuantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Component, Double>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Component, Product.Category>("category"));
        tableView.setItems(list);

        
        
    }
    
}
