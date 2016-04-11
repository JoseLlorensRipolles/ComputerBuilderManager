/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager.model;

import java.util.List;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JoseLlorens
 */
@XmlRootElement
public class Pc {
    
    private String name;
    private List<Component> componentList ;
    
    
    public Pc(){}
    
    public Pc(String s,List<Component> l){
        this.name=s;
        this.componentList=l;
    }
    
    @XmlElement(name = "Component")
    public List<Component> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<Component> list) {
        componentList = list;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name=name;
    }
    public String toString(){
        String aux = "";
        aux+=name;
        return aux ;
    }
}
