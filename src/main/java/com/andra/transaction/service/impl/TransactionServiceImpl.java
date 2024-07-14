package com.andra.transaction.service.impl;

import com.andra.transaction.constant.TransactionType;
import com.andra.transaction.dto.ReqTransactionDto;
import com.andra.transaction.exception.BadRequestException;
import com.andra.transaction.exception.NotFoundException;
import com.andra.transaction.model.Customer;
import com.andra.transaction.model.Transaction;
import com.andra.transaction.repository.CustomerRepository;
import com.andra.transaction.repository.TransactionRepository;
import com.andra.transaction.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private CustomerRepository customerRepository;

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    @Override
    public void createTransaction(String type, ReqTransactionDto reqTransactionDto) {
        Optional<Customer> customerOpt = customerRepository.findByIdWithLock(reqTransactionDto.getCustomerId());
        if (!customerOpt.isPresent()) {
            throw new NotFoundException("Customer not found");
        }
        Customer customer = customerOpt.get();

        switch (TransactionType.valueOf(type.toUpperCase())) {
            case DEBIT:
                if (customer.getBalance().compareTo(reqTransactionDto.getAmount()) < 0) {
                    throw new BadRequestException("Insufficient balance");
                }
                customer.setBalance(customer.getBalance().subtract(reqTransactionDto.getAmount()));
                break;
            case TOP_UP:
            case REFUND:
                customer.setBalance(customer.getBalance().add(reqTransactionDto.getAmount()));
                break;
            default:
                throw new BadRequestException("Invalid transaction type");
        }

        Transaction transaction = new Transaction();
        transaction.setCustomer(customer);
        transaction.setAmount(reqTransactionDto.getAmount());
        transaction.setType(TransactionType.valueOf(type.toUpperCase()));

        transactionRepository.save(transaction);
        customerRepository.save(customer);
    }
}
