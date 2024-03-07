package com.vidracariaCampos.model.entity;
import lombok.Data;

@Data
public class BudgetItem {
    private int quantity;
    private float price;

    public float getSubtotal() {
        return price * quantity;
    }
}
