package com.rayanen.bankAccount.restController;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.dto.ResponseStatus;
import com.rayanen.bankAccount.facade.Facade;
import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;



@RestController
public class TransactionRestController {

    private static Logger logger= LoggerFactory.getLogger(TransactionRestController.class);


Facade facade;

    public TransactionRestController(Facade facade) {
        this.facade = facade;
    }
    //amount meghdar moteghayer hesab dar amountDto ast

    //variz
    @RequestMapping(value = "ws/decreaseTransaction", method = RequestMethod.POST)
    public ResponseDto<Object> decreaseTransaction(@RequestBody TransactionDto transactionDto) throws Exception {
        logger.info("start depositTransaction");
        facade.decreaseTransaction(transactionDto);
        logger.info("end depositTransaction");
        return new ResponseDto<>(ResponseStatus.Ok, null, "واریز وجه با موفقیت انجام شد.", null);
    }

    //bardasht
    @RequestMapping(value = "ws/increaseTransaction", method = RequestMethod.POST)
    public ResponseDto<Object> increaseTransaction(@RequestBody TransactionDto transactionDto) throws Exception {
        logger.info("startIncreaseTransactionRest");
        facade.increaseTransaction(transactionDto);
            logger.info("endIncreaseTransactionRest");
            return new ResponseDto<>(ResponseStatus.Ok, null, "برداشت با موفقیت انجام شد.", null);
    }



    //enteghal vajh
    @RequestMapping(value = "ws/transferTransaction", method = RequestMethod.POST)
    public ResponseDto<Object> transferTransaction(@RequestBody TransactionDto transactionDto) throws Exception {
        logger.info("startTransferTransactionRest");
        increaseTransaction(transactionDto);
         decreaseTransaction(transactionDto);
        logger.info("endTransferTransactionRest");
        return new ResponseDto<>(ResponseStatus.Ok, null, "انتقال وجه با موفقیت انجام شد.", null);


    }




}
