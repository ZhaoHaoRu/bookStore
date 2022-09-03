package org.sjtu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(value={"CartItemList"})
@Table(name = "cart", schema = "myBookstore")
public class Cart {
    private int id;
    private User owner;//用户
    private List<CartItem> CartItemList;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int i) { this.id = i; }

    @OneToOne(cascade=CascadeType.ALL)//People是关系的维护端，当删除 people，会级联删除 address
    @JoinColumn(name = "ownerId", referencedColumnName = "id")//people中的address_id字段参考address表中的id字段
    public User getOwner() { return owner;}
    public void setOwner(User newUser) { this.owner = newUser; }

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "cartId",cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY)
    public List<CartItem> getCartItemList() { return this.CartItemList; }
    public void setCartItemList(List<CartItem> CartItems) { this.CartItemList = CartItems; }

}
