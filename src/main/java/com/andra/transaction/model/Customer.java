package com.andra.transaction.model;

import com.andra.transaction.model.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends BaseModel {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String username;
    private BigDecimal balance;

    public Customer(Integer id, LocalDateTime createAt, String createBy, boolean isDeleted,
                    LocalDateTime modifyAt, String modifyBy, BigDecimal balance,
                    String email, String fullName, String phoneNumber, String username) {
        super(id, createAt, modifyAt, createBy, modifyBy, isDeleted);
        this.balance = balance;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }
}
