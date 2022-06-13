package org.sjtu.backend.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.sjtu.backend.entity.Order;
import org.sjtu.backend.entity.*;
@Entity
@JsonIgnoreProperties(value={"orderList"})
@Table(name = "user", schema = "myBookstore")
public class User {
    private int id;
    private String name;
    private String passward;
    private String email;
    private String phone;
    private String address;
    //0：普通用户 1：管理员 2：代表禁用用户
    private int isAdministrators = 0;
    private double consumption = 0;
    private List<Order> orderList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() { return id; }
    public void setId(int i) { this.id = i; }

    @Basic
    @Column(name = "name", nullable = false, length = 10)
    public String getName() { return name; }
    public void setName(String name1) { this.name = name1; }

    @Basic
    @Column(name = "passward", nullable = false, length = 20)
    public String getPassward() { return passward; }
    public void setPassward(String passward) { this.passward = passward; }

    @Basic
    @Column(name = "phone", length = 20)
    public String getPhone() { return phone; }
    public void setPhone(String phone1) { this.phone = phone1; }

    @Basic
    @Column(name = "email", length = 20)
    public String getEmail() { return email; }
    public void setEmail(String newEmail) { this.email = newEmail; }

    @Basic
    @Column(name = "address", nullable = false, length = 100)
    public String getAddress() { return address; }
    public void setAddress(String addr) { this.address = addr; }

    @Basic
    @Column(name = "isAdministrators", nullable = false)
    public int getIsAdministrators() { return isAdministrators; }
    public void setIsAdministrators(int isAdmin){ this.isAdministrators = isAdmin; }

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "buyer",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    public List<Order> getOrderList() { return orderList; }
    public void setOrderList(List<Order> orderList1) { this.orderList = orderList1; }

    @Transient
    public double getConsumption() {return consumption; }
    public void setConsumption(double consumption1) { this.consumption = consumption1; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != that.id) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(passward, that.passward)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(phone, that.phone)) return false;
        if (!Objects.equals(isAdministrators, that.isAdministrators)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (passward != null ? passward.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
