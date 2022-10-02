package com.product.productstore.model;


import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "charges")
public class Charges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category")
    private String productName;
    @Column(name = "category_id")
    private long id;
    @Column(name = "discount")
    private long discount;
    @Column(name = "GST")
    private long GST;
    @Column(name = "delivery_charges")
    private long deliveryCharge;

    public Charges(){

    }
    public Charges(String productName, long discount, long GST, long deliveryCharge) {
        this.productName = productName;
        this.discount = discount;
        this.GST = GST;
        this.deliveryCharge = deliveryCharge;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public long getGST() {
        return GST;
    }

    public void setGST(long GST) {
        this.GST = GST;
    }

    public long getDeliveryCharge() {

        return deliveryCharge;
    }

    public void setDeliveryCharge(long deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy="charges")
    private Set<Product> product;

}
