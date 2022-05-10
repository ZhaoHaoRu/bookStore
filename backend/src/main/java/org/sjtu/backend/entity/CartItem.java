package org.sjtu.backend.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "cartitem", schema = "myBookstore")
public class CartItem {
    private int id;
    private int count;
    private Book book;//地址
    private Cart cartId;    //所属的的购物车

    private Timestamp addDate;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int i) { this.id = i; }

    @Basic
    @Column(name = "count", nullable = false)
    public int getCount() { return count; }
    public void setCount(int num) { this.count = num; }

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="bookId")//设置在cartitem表中的关联字段(外键)
    public Book getBook() { return book; }
    public void setBook(Book newBook) { this.book = newBook; }

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="cartId")  //设置在cart表中的关联字段(外键)
    public Cart getCartId() { return cartId; }
    public void setCartId(Cart newCart) { this.cartId = newCart; }

    @Basic
    @Column(name = "addDate")
    public Timestamp getAddDate() { return addDate; }
    public void setAddDate(Timestamp date) { this.addDate = date; }
}
