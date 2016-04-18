/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager.model;

import es.upv.inf.Product;
import es.upv.inf.Product.Category;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JoseLlorens
 */
public class Component {
    
    private Product product;
    private Integer cuantity;
    private String description;
    private Double price ;
    private Integer stock;
    private Category category;
    private Double total;
    
    public Component(Product p){
        this.category=p.getCategory();
        this.description=p.getDescription();
        this.price=p.getPrice();
        this.stock=p.getStock();
        this.cuantity = 1;
        this.product = p;
        this.total=this.cuantity*this.price;
    }
    public Component(){}
    
    public Category getCategory(){
        return this.category;
    }
    public String getDescription(){
        return this.description;
    }
    public Double getPrice(){
        return this.price;
    }
    public Integer getCuantity(){
        return this.cuantity;
    }
    @XmlTransient
    public Product getProduct(){
        return product;
    }
    public Integer getStock(){
        return this.stock;
    }
    public Double getTotal(){
        return this.total;
    }

        
    
    public void setCategory(Product.Category cat){
        this.category=cat;
    }
    public void setDescription(String s){
        this.description=s;
    }
    public void setPrice(Double d){
        this.price = d;
    }
    public void setCuantity(Integer i){
        this.cuantity=i;
        this.total = this.price*this.cuantity;
    }
    public void setStock(Integer s){
        this.stock=s;
    }
    
    public void setTotal(Double d){
        this.total=d;
    }

    public void incCuantity(){
        this.cuantity ++ ;
        this.total += this.price;
    }
    public void decCuantity(){
        this.cuantity -- ;
                this.total -= this.price;

    }
    public void setProduct(Product p){
        this.product=p;
    }
    

    
}
