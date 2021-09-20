package com.sample.domain.model;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.Date;

@Entity
public class ProductRate {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @NotNull
    //@Size(min = 1, max = 5 ,message  = "Rate Number Must be between 1 and 5")
    @Range(min=1,max=5)
    private int rate;


    @NotNull
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
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

    @ManyToOne
   // @MapsId("userId")
    @JoinColumn(name = "user_id")
    UserModel user;

}
