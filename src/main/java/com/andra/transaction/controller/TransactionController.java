package com.andra.transaction.controller;

import com.andra.transaction.dto.ReqTransactionDto;
import com.andra.transaction.dto.base.ResponseWrapper;
import com.andra.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("")
    public ResponseEntity<ResponseWrapper<Object>> handleTransaction(@RequestParam String type, @RequestBody @Valid ReqTransactionDto reqTransactionDto) {
        transactionService.createTransaction(type, reqTransactionDto);
        return ResponseEntity.ok(ResponseWrapper.success());
    }
}
