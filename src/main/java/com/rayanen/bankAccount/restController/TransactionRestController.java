package com.rayanen.bankAccount.restController;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.dto.ResponseStatus;
import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


@RestController
public class TransactionRestController {

    private static Logger logger= LoggerFactory.getLogger(TransactionRestController.class);
    private BankAccountDao bankAccountDao;

    public TransactionRestController(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
}



    //amount meghdar moteghayer hesab dar amountDto ast

    //variz
    @RequestMapping(value = "ws/decreaseTransaction", method = RequestMethod.POST)
    public ResponseDto<Object> decreaseTransaction(@RequestBody TransactionDto transactionDto) {
        Boolean report = bankAccountDao.existsByAccountNumber(transactionDto.getPayeeAccountNumber());
        logger.info("start depositTransaction");
        if (!report ) {
            return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("حسابی یافت نشد"));
        }
        BankAccount account = bankAccountDao.findBankAccountByAccountNumber(transactionDto.getPayeeAccountNumber());
        account.setInventory(account.getInventory().add(transactionDto.getAmount()));

        logger.info("end depositTransaction");
        return new ResponseDto<>(ResponseStatus.Ok, null, "واریز وجه با موفقیت انجام شد.", null);
    }

    //bardasht
    @RequestMapping(value = "ws/increaseTransaction", method = RequestMethod.POST)
    public ResponseDto<Object> increaseTransaction(@RequestBody TransactionDto transactionDto) {
        Boolean report = bankAccountDao.existsByAccountNumber(transactionDto.getPayerAccountNumber());
        logger.info("start  increaseTransaction");
        if (report) {
            BankAccount account = bankAccountDao.findBankAccountByAccountNumber(transactionDto.getPayerAccountNumber());
            if (account.getInventory().compareTo(transactionDto.getAmount())>0 ){
                return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("موجودی کم است"));
            }
            account.setInventory(account.getInventory().subtract(transactionDto.getAmount()) );
            logger.info("end   increaseTransaction");
            return new ResponseDto<>(ResponseStatus.Ok, null, "برداشت با موفقیت انجام شد.", null);
        }else{
            logger.error(" error finding account ");
            return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("حساب موجود نیست"));
        }
    }


//
//    @RequestMapping(value = "ws/findAccountNumber", method = RequestMethod.POST)
//    public ResponseDto<RealPerson> findAccountNumber(@RequestParam String nationalCode) {
//
//        logger.info("start finding Real");
//        if (Objects.isNull(realPersonDto)) {
//            logger.error("real wasn't found");
//            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("not find"));
//        }
//        logger.info("real was found");
//        return new ResponseDto(ResponseStatus.Ok, realPersonDto, null, null);
//    }



    //enteghal vajh
    @RequestMapping(value = "ws/transferTransaction", method = RequestMethod.POST)
    public ResponseDto<Object> transferTransaction(@RequestBody TransactionDto transactionDto) {
         decreaseTransaction(transactionDto);
         increaseTransaction(transactionDto);
        return new ResponseDto<>(ResponseStatus.Ok, null, "انتقال وجه با موفقیت انجام شد.", null);


    }


//    private void sodem(){
//
//    }



}
