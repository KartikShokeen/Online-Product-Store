package com.product.productstore.model;


import javax.persistence.*;
import com.product.productstore.model.Charges;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "products")
public class Product {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_type")
    private String productType;
    @Column(name = "product_category")
    private String productCategory;
    @Column(name = "product_price")
    private String productPrice;

    @Column(name = "product_total_price")
    private String totalPrice;


    public Product(){

    }

    public Product(String productName, String productType, String productCategory, String productPrice,String totalPrice) {
        this.productName = productName;
        this.productType = productType;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.totalPrice= totalPrice;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {

        this.productPrice = productPrice;
    }


    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @ManyToOne
    @JoinColumn(name="product_category", nullable=false,insertable = false, updatable = false)
    private Charges charges;

    public String calculate(String Price){
        int basePrice=Integer.parseInt(Price);
        basePrice -=(basePrice* charges.getDiscount()*0.01);
        double finalPrice= basePrice+ (basePrice*charges.getGST()*0.01) + (charges.getDeliveryCharge()*1.00);
        System.out.println(finalPrice);
        setTotalPrice(Double.toString(finalPrice));
        return Double.toString(finalPrice);

    }

}

