package org.sjtu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.boot.SpringApplication;
import org.sjtu.backend.entity.OrderItem;
import javax.management.Descriptor;
import javax.persistence.*;
import javax.swing.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIgnoreProperties(value={"orderItemList", "cartItemList"})
@Table(name = "book", schema = "myBookstore")
public class Book {
    private int id;
    private int isbn;
    private String name;
    private String type;
    private String author;
    private double price;
    private String descript;
    //inventory = -1：deleted
    private int inventory;
    private String image;
    private int sales;
    private List<OrderItem> orderItemList;
    private List<CartItem> cartItemList;
//    @OneToOne(mappedBy = "orderitem", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
//    private OrderItem orderItem;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int i){ this.id = i; }
//    @Basic
//    @Column(name = "isbn", nullable = false)
//    public int getIsbn() {
//        return id;
//    }

    @Basic
    @Column(name = "isbn")
    public int getIsbn() { return isbn; }
    public void setIsbn(int isbn1) { this.isbn = isbn1; }

    @Basic
    @Column(name = "name", nullable = false, length = 40)
    public String getName() { return name; }
    public void setName(String bookName) { this.name = bookName; }

    @Basic
    @Column(name = "type", length = 10)
    public String getType(){ return type; }
    public void setType(String type){ this.type = type; }

    @Basic
    @Column(name = "author", nullable = false, length = 20)
    public String getAuthor(){ return author; }
    public void setAuthor(String auth) { this.author = auth; }

    @Basic
    @Column(name = "price", nullable = false)
    public double getPrice(){ return price; }
    public void setPrice(double pric) { this.price = pric; }

    @Basic
    @Column(name = "description")
    public String getDescript(){ return descript; }
    public void setDescript(String desci){ this.descript = desci; }

    @Basic
    @Column(name = "inventory")
    public int getInventory(){ return inventory; }
    public void setInventory(int inventory1){ this.inventory = inventory1; }

    @Basic
    @Column(name = "image")
    public String getImage(){ return image; }
    public void setImage(String img){ this.image = img; }

    public int getSales(){ return sales; }
    public void setSales(int sale){ this.sales = sale; }

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "book",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public List<OrderItem> getOrderItemList() { return orderItemList; }
    public void setOrderItemList(List<OrderItem> orderItemList1) { this.orderItemList = orderItemList1; }

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "book",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public List<CartItem> getCartItemList() { return cartItemList; }
    public void setCartItemList(List<CartItem> cartItems) { this.cartItemList = cartItems; }

}
