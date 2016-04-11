/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JoseLlorens
 */
@XmlRootElement(name = "PcList",namespace="")
public class PcList {
    
    private List<Pc> pcList;
    public PcList(){}
    
    @XmlElement(name="Pc")
    public List<Pc> getPcList(){
        return pcList;
    }
    public void setPcList(List<Pc> list){
        pcList = list;
    }
    
}
