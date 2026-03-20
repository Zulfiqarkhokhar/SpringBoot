package com.example.MyDemoProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDto {

    @JsonProperty("c-name")// used to set name for json body properties
    private String customerName;
    @JsonProperty("p-name")
    private String productName;
    private int quantity;

    public OrderDto() {
    }

    public OrderDto(String productName, String customerName, int quantity) {
        this.productName = productName;
        this.customerName = customerName;
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "customerName='" + customerName + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
