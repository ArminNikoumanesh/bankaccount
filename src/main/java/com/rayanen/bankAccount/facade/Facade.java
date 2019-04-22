package com.rayanen.bankAccount.facade;

import com.rayanen.bankAccount.dto.*;

import com.rayanen.bankAccount.model.entity.LegalPerson;
import com.rayanen.bankAccount.model.entity.RealPerson;
import com.rayanen.bankAccount.model.entity.Transaction;
import com.rayanen.bankAccount.restController.LegalPersonRestController;
import com.rayanen.bankAccount.serviceController.impl.LegalPersonServiceImpl;
import com.rayanen.bankAccount.serviceController.impl.RealPersonServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import javax.transaction.Transactional;
import java.util.List;




@Component
public class Facade {
    private static Logger logger= LoggerFactory.getLogger(LegalPersonRestController.class);



    ModelMapper modelMapper = new ModelMapper();





RealPersonServiceImpl realPersonService;

    public Facade(RealPersonServiceImpl realPersonService) {
        this.realPersonService = realPersonService;
    }



    LegalPersonServiceImpl legalPersonService;

    public Facade(LegalPersonServiceImpl legalPersonService) {
        this.legalPersonService = legalPersonService;
    }





    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> saveLegal(@RequestBody LegalPersonDto legalPersonDto) {
        logger.info("startSaveLegalFacade");

       LegalPerson legalPerson=modelMapper.map(legalPersonDto,LegalPerson.class);
       legalPersonService.saveLegal(legalPerson);

        logger.info("endSaveLegalFacade");
        return new ResponseDto<>(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
    }



    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> updateLegal(@RequestBody LegalPersonDto legalPersonDto) {
        logger.info("startUpdateLegalFacade");

        LegalPerson legalPerson=modelMapper.map(legalPersonDto,LegalPerson.class);
        legalPersonService.updateLegal(legalPerson);

        logger.info("endUpdateLegalFacade");
        return new ResponseDto<>(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);    }



    public ResponseDto<LegalPerson> findLegal(@RequestBody String companyCode) {
        logger.info("startFindingLegalFacade");

        LegalPerson legalPerson=modelMapper.map(companyCode,LegalPerson.class);

        legalPersonService.findLegal(companyCode);

        LegalPersonDto legalPersonDto=modelMapper.map(legalPerson,LegalPersonDto.class);
        logger.info("endFindingLegalFacade");
        return new ResponseDto(ResponseStatus.Ok, legalPersonDto, null, null);
    }



    public ResponseDto<List<LegalPersonDto>> findLegalAll(@RequestBody LegalPersonDto legalPersonDto) {
        logger.info("startFindingAllLegalFacade");

        LegalPerson legalPerson=modelMapper.map(legalPersonDto,LegalPerson.class);
        legalPersonService.findLegalAll(legalPerson);
        LegalPersonDto legalPersonDtoResponse=modelMapper.map(legalPerson,LegalPersonDto.class);
        logger.info("endFindingAllLegalFacade");
        return new ResponseDto(ResponseStatus.Ok, legalPersonDtoResponse, null, null);
    }



    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> saveReal(@RequestBody RealPersonDto realPersonDto) {
        logger.info("startRealSaveFacade");

        RealPerson realPerson=modelMapper.map(realPersonDto,RealPerson.class);
        realPersonService.saveReal(realPerson);

        logger.info("endRealSaveFacade");
        return new ResponseDto(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
    }


    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> updateReal(@RequestBody RealPersonDto realPersonDto) {
        logger.info("startUpdateRealFacade");
        RealPerson realPerson=modelMapper.map(realPersonDto,RealPerson.class);
        realPersonService.updateReal(realPerson);
        logger.info("endUpdateRealFacade");
        return new ResponseDto(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
    }


    public ResponseDto<RealPerson> findReal(@RequestParam String nationalCode) {
        logger.info("startFindingRealFacade");


        logger.info("endRealFindingFacade");
        return new ResponseDto(ResponseStatus.Ok, null, null, null);
    }



    public ResponseDto<List<RealPersonDto>> findRealAll(@RequestBody RealPersonDto realPersonDto) {
        logger.info("startFindingRealFacade");

        logger.info("endRealFindingFacade");
        return new ResponseDto(ResponseStatus.Ok, null, null, null);
    }



    public ResponseDto<Object> decreaseTransaction(@RequestBody Transaction transaction) {
        logger.info("startDepositTransactionFacade");



        logger.info("endDepositTransactionFacade");
        return new ResponseDto<>(ResponseStatus.Ok, null, "واریز وجه با موفقیت انجام شد.", null);
    }

    //bardasht

    public ResponseDto<Object> increaseTransaction(@RequestBody Transaction transaction) {
        logger.info("startIncreaseTransactionFacade");



            return new ResponseDto<>(ResponseStatus.Ok, null, "برداشت با موفقیت انجام شد.", null);

    }




    //enteghal vajh

    public ResponseDto<Object> transferTransaction(@RequestBody Transaction transaction) {
        logger.info("startTransferTransactionFacade");
        decreaseTransaction(transaction);
        increaseTransaction(transaction);
        logger.info("endTransferTransactionFacade");
        return new ResponseDto<>(ResponseStatus.Ok, null, "انتقال وجه با موفقیت انجام شد.", null);


    }



}
