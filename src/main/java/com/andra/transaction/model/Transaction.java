package com.andra.transaction.model;

import com.andra.transaction.constant.TransactionType;
import com.andra.transaction.model.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
}
