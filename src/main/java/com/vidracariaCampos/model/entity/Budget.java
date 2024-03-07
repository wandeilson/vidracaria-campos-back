package com.vidracariaCampos.model.entity;
import java.math.BigDecimal;
import java.util.Date;
import java.io.File;

import com.vidracariaCampos.model.enums.BudgetStatus;
import com.vidracariaCampos.model.enums.PaymentMethod;
import lombok.Data;

@Data
public class Budget {
    private BudgetStatus status;
    private PaymentMethod paymentMethod;
    private Date creationDate;
    private Date contractingDate;
    private Date completionDate;
    private BigDecimal downPayment;
    private BigDecimal discount;
    private File file;

    public float getTotal() {
        return 0.0f;
    }
}
