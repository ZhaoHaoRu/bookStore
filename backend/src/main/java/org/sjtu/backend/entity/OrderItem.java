package org.sjtu.backend.entity;



import javax.persistence.*;


@Entity
@Table(name = "orderitem", schema = "myBookstore")
public class OrderItem {
    private int id;
    private int count;
    private Order orderId;//所属的的订单
    //这里先设置一个persist，总感觉不太合适……
    private Book book;//地址


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
    public void setCount(int c) { this.count = c; }

    @ManyToOne(cascade=CascadeType.ALL,optional=false)
    @JoinColumn(name="orderId")//设置在order表中的关联字段(外键)
    public Order getOrderId() { return orderId; }
    public void setOrderId(Order order){ this.orderId = order; }

    @ManyToOne(cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "bookId")//people中的address_id字段参考address表中的id字段
    public Book getBook(){ return book; }
    public void setBook(Book b) { this.book = b; }
}
