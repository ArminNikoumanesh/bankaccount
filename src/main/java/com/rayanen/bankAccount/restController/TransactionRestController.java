package com.rayanen.bankAccount.restController;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.dto.ResponseStatus;
import com.rayanen.bankAccount.facade.Facade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;



@RestController
public class TransactionRestController {

    private static Logger logger= LoggerFactory.getLogger(TransactionRestController.class);

private Environment environment;
private Facade facade;

    public TransactionRestController(Environment environment, Facade facade) {
        this.environment = environment;
        this.facade = facade;
    }


    //variz
    @RequestMapping(value = "ws/decreaseTransaction", method = RequestMethod.POST)
    public ResponseDto<Object> decreaseTransaction(@RequestBody TransactionDto transactionDto) throws Exception {
        logger.info("start depositTransaction");
        facade.decreaseTransaction(transactionDto);
        logger.info("end depositTransaction");
        return new ResponseDto<>(ResponseStatus.Ok, null, environment.getProperty("app.message.decreaseTransaction"), null);
    }

    //bardasht
    @RequestMapping(value = "ws/increaseTransaction", method = RequestMethod.POST)
    public ResponseDto<Object> increaseTransaction(@RequestBody TransactionDto transactionDto) throws Exception {
        logger.info("startIncreaseTransactionRest");
        facade.increaseTransaction(transactionDto);
            logger.info("endIncreaseTransactionRest");
            return new ResponseDto<>(ResponseStatus.Ok, null, environment.getProperty("app.message.increaseTransaction"), null);
    }



    //enteghal vajh
    @RequestMapping(value = "ws/transferTransaction", method = RequestMethod.POST)
    public ResponseDto<Object> transferTransaction(@RequestBody TransactionDto transactionDto) throws Exception {
        logger.info("startTransferTransactionRest");
         increaseTransaction(transactionDto);
         decreaseTransaction(transactionDto);
        logger.info("endTransferTransactionRest");
        return new ResponseDto<>(ResponseStatus.Ok, null, environment.getProperty("app.message.transferTransaction"), null);


    }




}
