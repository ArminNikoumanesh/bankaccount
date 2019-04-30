package com.rayanen.bankAccount.facade;

import com.rayanen.bankAccount.dto.*;

import com.rayanen.bankAccount.model.entity.LegalPerson;
import com.rayanen.bankAccount.model.entity.RealPerson;
import com.rayanen.bankAccount.model.entity.Transaction;
import com.rayanen.bankAccount.restController.LegalPersonRestController;
import com.rayanen.bankAccount.serviceController.impl.LegalPersonServiceImpl;
import com.rayanen.bankAccount.serviceController.impl.RealPersonServiceImpl;
import com.rayanen.bankAccount.serviceController.impl.TransactionServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Component
public class Facade {
    private static Logger logger = LoggerFactory.getLogger(LegalPersonRestController.class);


    ModelMapper modelMapper = new ModelMapper();

    RealPersonServiceImpl realPersonService;
    LegalPersonServiceImpl legalPersonService;
    TransactionServiceImpl transactionService;

    public Facade(RealPersonServiceImpl realPersonService, LegalPersonServiceImpl legalPersonService, TransactionServiceImpl transactionService) {
        this.realPersonService = realPersonService;
        this.legalPersonService = legalPersonService;
        this.transactionService = transactionService;
    }

    @Transactional(rollbackOn = Exception.class)
    public void saveLegal( LegalPersonDto legalPersonDto) throws Exception {
        logger.info("startSaveLegalFacade");
        LegalPerson legalPerson = modelMapper.map(legalPersonDto, LegalPerson.class);
        legalPersonService.saveLegal(legalPerson);
        logger.info("endSaveLegalFacade");
    }


    @Transactional(rollbackOn = Exception.class)
    public void updateLegal( LegalPersonDto legalPersonDto) throws Exception {
        logger.info("startUpdateLegalFacade");
        LegalPerson legalPerson = modelMapper.map(legalPersonDto, LegalPerson.class);
        legalPersonService.updateLegal(legalPerson);
        logger.info("endUpdateLegalFacade");
    }


    @Transactional(rollbackOn = Exception.class)
    public LegalPersonDto findLegal(String companyCode) throws Exception {
       logger.info("FindingLegalFacade");
        return modelMapper.map(legalPersonService.findByCompanyCode(companyCode), LegalPersonDto.class);

    }



    @Transactional(rollbackOn = Exception.class)
    public List<LegalPersonDto> findLegalAll( LegalPersonDto legalPersonDto) throws Exception {
        logger.info("startFindingAllLegalFacade");
        LegalPerson legalPerson = modelMapper.map(legalPersonDto, LegalPerson.class);
        legalPersonService.findLegalAll(legalPerson);
        List<LegalPersonDto> legalPersonDtoListResponse=new ArrayList<>();
        List<LegalPerson> legalPersonList = legalPersonService.findLegalAll(legalPerson);
        for (LegalPerson person : legalPersonList) {
            LegalPersonDto legalPersonDtoResponse = modelMapper.map(person, LegalPersonDto.class);
            legalPersonDtoListResponse.add(legalPersonDtoResponse);
        }
        logger.info("endFindingAllLegalFacade");
        return legalPersonDtoListResponse;
    }






    @Transactional(rollbackOn = Exception.class)
    public void saveReal( RealPersonDto realPersonDto) throws Exception {
        logger.info("startRealSaveFacade");
        RealPerson realPerson = modelMapper.map(realPersonDto, RealPerson.class);
        realPersonService.saveReal(realPerson);
        logger.info("endRealSaveFacade");

    }


    public void updateReal( RealPersonDto realPersonDto) throws Exception {
        logger.info("startUpdateRealFacade");
        RealPerson realPerson = modelMapper.map(realPersonDto, RealPerson.class);
        realPersonService.updateReal(realPerson);
        logger.info("endUpdateRealFacade");
    }


    @Transactional(rollbackOn = Exception.class)
    public RealPersonDto findByNationalCode(String nationalCode) throws Exception {
        return modelMapper.map(realPersonService.findByNationalCode(nationalCode), RealPersonDto.class);
    }



    @Transactional(rollbackOn = Exception.class)
    public List<RealPersonDto> findRealAll( RealPersonDto realPersonDto) throws Exception {
        logger.info("startFindingRealFacade");
        RealPerson realPerson = modelMapper.map(realPersonDto, RealPerson.class);
        realPersonService.findRealAll(realPerson);
        List<RealPersonDto> realPersonListResponse = new ArrayList<>();
        List<RealPerson> realPersonList = realPersonService.findRealAll(realPerson);
        for (RealPerson person : realPersonList) {
            RealPersonDto realPersonDtoResponse = modelMapper.map(person, RealPersonDto.class);
            realPersonListResponse.add(realPersonDtoResponse);

        }
        logger.info("endRealFindingFacade");
        return realPersonListResponse;
    }


    @Transactional(rollbackOn = Exception.class)
    public void decreaseTransaction( TransactionDto transactionDto) throws Exception {
        logger.info("startDepositTransactionFacade");
         Transaction transaction=modelMapper.map(transactionDto,Transaction.class);
         transactionService.decreaseTransaction(transaction);
        logger.info("endDepositTransactionFacade");
    }

    //bardasht

    @Transactional(rollbackOn = Exception.class)
    public void increaseTransaction( TransactionDto transactionDto) throws Exception {
        logger.info("startIncreaseTransactionFacade");
        Transaction transaction=modelMapper.map(transactionDto,Transaction.class);
        transactionService.increaseTransaction(transaction);

    }


    //enteghal vajh
    @Transactional(rollbackOn = Exception.class)
    public void transferTransaction( TransactionDto transactionDto)throws Exception {
        logger.info("startTransferTransactionFacade");
        Transaction transaction=modelMapper.map(transactionDto,Transaction.class);
        transactionService.transferTransaction(transaction);
        logger.info("endTransferTransactionFacade");

    }


}
