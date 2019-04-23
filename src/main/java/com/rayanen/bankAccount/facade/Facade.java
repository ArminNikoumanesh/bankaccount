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

import javax.transaction.Transactional;
import java.util.List;


@Component
public class Facade {
    private static Logger logger = LoggerFactory.getLogger(LegalPersonRestController.class);


    ModelMapper modelMapper = new ModelMapper();

    RealPersonServiceImpl realPersonService;
    LegalPersonServiceImpl legalPersonService;
    public Facade(RealPersonServiceImpl realPersonService, LegalPersonServiceImpl legalPersonService) {
        this.realPersonService = realPersonService;
        this.legalPersonService = legalPersonService;
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> saveLegal(@RequestBody LegalPersonDto legalPersonDto) {
        logger.info("startSaveLegalFacade");
        LegalPerson legalPerson = modelMapper.map(legalPersonDto, LegalPerson.class);
        legalPersonService.saveLegal(legalPerson);
        ResponseDto responseDto=legalPersonService.saveLegal(legalPerson);
        logger.info("endSaveLegalFacade");
        return new ResponseDto<>(ResponseStatus.Ok, null, responseDto.getNotificationMessage(), null);
    }


    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> updateLegal(@RequestBody LegalPersonDto legalPersonDto) {
        logger.info("startUpdateLegalFacade");

        LegalPerson legalPerson = modelMapper.map(legalPersonDto, LegalPerson.class);
        legalPersonService.updateLegal(legalPerson);

        logger.info("endUpdateLegalFacade");
        return new ResponseDto<>(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
    }


    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<LegalPerson> findLegal(@RequestBody LegalPersonDto legalPersonDto) {
        logger.info("startFindingLegalFacade");
        LegalPerson legalPerson = modelMapper.map(legalPersonDto, LegalPerson.class);
        legalPersonService.findLegal(legalPerson);
        ResponseDto responseDto = legalPersonService.findLegal(legalPerson);
        LegalPersonDto legalPersonDtoResponse = modelMapper.map(responseDto, LegalPersonDto.class);
        logger.info("endFindingLegalFacade");
        return new ResponseDto(ResponseStatus.Ok, legalPersonDtoResponse, null, null);
    }


    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<List<LegalPersonDto>> findLegalAll(@RequestBody LegalPersonDto legalPersonDto) {
        logger.info("startFindingAllLegalFacade");
        LegalPerson legalPerson = modelMapper.map(legalPersonDto, LegalPerson.class);
        legalPersonService.findLegalAll(legalPerson);
        ResponseDto responseDto = legalPersonService.findLegalAll(legalPerson);
        LegalPersonDto legalPersonDtoResponse = modelMapper.map(responseDto.getResponseObject(), LegalPersonDto.class);
        logger.info("endFindingAllLegalFacade");
        return new ResponseDto(ResponseStatus.Ok, legalPersonDtoResponse, null, null);
    }


    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> deleteLegalAccount(@RequestBody LegalPersonDto legalPersonDto) {
        logger.info("startLegalUpdateRestController");
        LegalPerson legalPerson = modelMapper.map(legalPersonDto, LegalPerson.class);
        legalPersonService.deleteLegalAccount(legalPerson);
        logger.info("endLegalUpdateRestController");
        return new ResponseDto<>(ResponseStatus.Ok, null, "حساب مسدود شد", null);
    }


    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> saveReal(@RequestBody RealPersonDto realPersonDto) throws Exception {
        logger.info("startRealSaveFacade");

        RealPerson realPerson = modelMapper.map(realPersonDto, RealPerson.class);
        realPersonService.saveReal(realPerson);

        logger.info("endRealSaveFacade");
        return new ResponseDto(ResponseStatus.Ok, null, "oo.", null);
    }


    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> updateReal(@RequestBody RealPersonDto realPersonDto) throws Exception {
        logger.info("startUpdateRealFacade");
        RealPerson realPerson = modelMapper.map(realPersonDto, RealPerson.class);
        realPersonService.updateReal(realPerson);
        logger.info("endUpdateRealFacade");
        return new ResponseDto(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<RealPerson> findReal(@RequestBody RealPersonDto realPersonDto) {
        logger.info("startFindingRealFacade");
        RealPerson realPerson = modelMapper.map(realPersonDto, RealPerson.class);
        realPersonService.findReal(realPerson);
        ResponseDto responseDto = realPersonService.findReal(realPerson);
        RealPersonDto realPersonDtoResponse = modelMapper.map(responseDto.getResponseObject(), RealPersonDto.class);
        logger.info("endRealFindingFacade");
        return new ResponseDto(ResponseStatus.Ok, realPersonDtoResponse, null, null);
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<List<RealPersonDto>> findRealAll(@RequestBody RealPersonDto realPersonDto) {
        logger.info("startFindingRealFacade");
        RealPerson realPerson = modelMapper.map(realPersonDto, RealPerson.class);
        realPersonService.findRealAll(realPerson);
        ResponseDto responseDto = realPersonService.findRealAll(realPerson);
        RealPersonDto realPersonDtoResponse = modelMapper.map(responseDto.getResponseObject(), RealPersonDto.class);
        logger.info("endRealFindingFacade");
        return new ResponseDto(ResponseStatus.Ok, realPersonDtoResponse, null, null);
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> deleteRealAccount(@RequestBody RealPersonDto realPersonDto) {
        logger.info("startLegalUpdateRestController");
        RealPerson realPerson = modelMapper.map(realPersonDto, RealPerson.class);
        realPersonService.deleteRealAccount(realPerson);
        logger.info("endLegalUpdateRestController");
        return new ResponseDto<>(ResponseStatus.Ok, null, "حساب مسدود شد", null);
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
