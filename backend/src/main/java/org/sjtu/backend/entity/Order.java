package org.sjtu.backend.entity;



import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.sjtu.backend.entity.User;

@Entity
@JsonIgnoreProperties(value={"orderItemList"})
@Table(name = "ordertable", schema = "myBookstore")
public class Order {
    private int id;

    private User buyer;

    private String recipient;
    private String phone;
    private String address;
    private Timestamp addDate;


    private List<OrderItem> orderItemList;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int i) { this.id = i; }

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "buyerId")//people中的address_id字段参考address表中的id字段
    public User getBuyer(){ return buyer; }
    public void setBuyer(User person){ this.buyer = person; }

    @Basic
    @Column(name = "recipient", length = 10)
    public String getRecipient() { return recipient; }
    public void setRecipient(String reci) { this.recipient = reci; }

    @Basic
    @Column(name = "phone", length = 20)
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Basic
    @Column(name = "address", length = 100)
    public String getAddress() { return address; }
    public void setAddress(String addr) { this.address = addr; }

    @Basic
    @Column(name = "addDate")
    public Timestamp getAddDate() { return addDate; }
    public void setAddDate(Timestamp date) { this.addDate = date; }

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "orderId",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public List<OrderItem> getOrderItemList() { return orderItemList; }
    public void setOrderItemList(List<OrderItem> orderItems) { this.orderItemList = orderItems; }
}
