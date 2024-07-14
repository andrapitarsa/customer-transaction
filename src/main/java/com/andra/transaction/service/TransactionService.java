package com.andra.transaction.service;

import com.andra.transaction.dto.ReqTransactionDto;

public interface TransactionService {

    void createTransaction(String type, ReqTransactionDto reqTransactionDto);
}
