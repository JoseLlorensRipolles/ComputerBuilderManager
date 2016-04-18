/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author JoseManuel
 */
public class NeededViewController implements Initializable {

    @FXML
    private Button okButton;
    
    boolean[] needed ;
    @FXML
    private Label placaLabel;
    @FXML
    private Label cpuLabel;
    @FXML
    private Label ramLabel;
    @FXML
    private Label gpuLabel;
    @FXML
    private Label discoLabel;
    @FXML
    private Label torreLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void okButtonClicked(MouseEvent event) {
        Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();
        
    }
    public void initNeeded(boolean[] need) {
        this.needed = need;
        if (!needed[0]) {
            placaLabel.setTextFill(Color.web("#FF0000"));

        } else {
            placaLabel.setTextFill(Color.web("#008000"));

        }
        if (!needed[1]) {
            cpuLabel.setTextFill(Color.web("#FF0000"));

        } else {
            cpuLabel.setTextFill(Color.web("#008000"));

        }
        if (!needed[2]) {
            ramLabel.setTextFill(Color.web("#FF0000"));

        } else {
            ramLabel.setTextFill(Color.web("#008000"));

        }
        if (!needed[3]) {
            gpuLabel.setTextFill(Color.web("#FF0000"));
        } else {
            gpuLabel.setTextFill(Color.web("#008000"));

        }
        if (!needed[4]) {
            discoLabel.setTextFill(Color.web("#FF0000"));
        } else {
            discoLabel.setTextFill(Color.web("#008000"));

        }
        if (!needed[5]) {
            torreLabel.setTextFill(Color.web("#FF0000"));
        } else {
            torreLabel.setTextFill(Color.web("#008000"));

        }
    }

}
