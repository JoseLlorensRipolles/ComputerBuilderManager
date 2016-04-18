/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager.controller;

import computermanager.model.Component;
import computermanager.model.Pc;
import computermanager.model.PcList;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * FXML Controller class
 *
 * @author JoseLlorens
 */
public class LoadViewController implements Initializable {


    @FXML
    private Button LoadButton;
    @FXML
    private Button cancelButton;
    
    private List<Pc> Pclist;
    @FXML
    private TableView<Pc> tableView;
    @FXML
    private TableColumn<Pc, String> nameColumn;
    @FXML
    private Button deleteButton;
    
    private ObservableList<Pc> ObservablePclist;
    
    private TableView<Component> mainMyPcTableView ;
   
    private PcList aux ;
    private Pc newPc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
        File file = new File("pcs.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(PcList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            aux  =(PcList) jaxbUnmarshaller.unmarshal(file);
            Pclist = aux.getPcList();
            ObservablePclist = FXCollections.observableList(Pclist);
            nameColumn.setCellValueFactory(new PropertyValueFactory<Pc,String>("name"));
            tableView.setItems(ObservablePclist);
        }catch(Exception e){
            //System.out.println("Error super chungo de la mort" + e);            
        }

    }

    @FXML
    private void loadButtonClicked(MouseEvent event) throws IOException {
        Pc aux = tableView.getSelectionModel().getSelectedItem();
        //editable = aux.editable;
        ObservableList<Component> aux2 = FXCollections.observableList(aux.getComponentList());
        mainMyPcTableView.setItems(aux2);
        newPc.editable=aux.editable;
        //System.out.println(newPc.editable);
        //System.out.println(aux.editable);
        FXMLLoader loader = new FXMLLoader (getClass().getResource("/computermanager/view/MainView.fxml"));
        HBox root = (HBox) loader.load();
        loader.<MainViewController>getController().init(aux2,aux);
        Scene scene = new Scene(root,900,800);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();

    }

    @FXML
    private void cancelButtonClicked(MouseEvent event) {
        Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();
    }

    @FXML
    private void deleteButtonClicked(MouseEvent event) {
        int index = tableView.getSelectionModel().getSelectedIndex();
        if(index<=3){
        tableView.getItems().remove(index);
        PcList newPcList = new PcList();
        newPcList.setPcList(tableView.getItems());
        
        try{
        File file = new File("pcs.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(PcList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(newPcList, file);
            jaxbMarshaller.marshal(newPcList, System.out);
        
        }catch(Exception e){System.out.print(e);}
        }else {
            //ERROR DE PREDEFINITS
        }
        
    }
    
    public void initList(TableView<Component> aux,Pc p){
        mainMyPcTableView = aux ;
        newPc = p ;
    }
}
