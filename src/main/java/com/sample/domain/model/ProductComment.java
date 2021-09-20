package com.sample.domain.model;

import com.sun.istack.NotNull;
import org.springframework.security.core.parameters.P;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.Date;

@Entity
public class ProductComment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @NotNull
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @NotNull
    private Date createDate;

    @ManyToOne
   // @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
   // @MapsId("userId")
    @JoinColumn(name = "user_id")
    UserModel user;

}
