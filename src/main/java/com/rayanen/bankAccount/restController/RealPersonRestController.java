package com.rayanen.bankAccount.restController;


import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.dto.ResponseStatus;
import com.rayanen.bankAccount.facade.Facade;
import com.rayanen.bankAccount.model.entity.RealPerson;
import com.rayanen.bankAccount.validation.rest.ValidationRealPersonRest;
import org.omg.CORBA.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RealPersonRestController {

    private static Logger logger = LoggerFactory.getLogger(RealPersonRestController.class);

    ValidationRealPersonRest validationRealPersonRest;
    Facade facade;

    public RealPersonRestController(ValidationRealPersonRest validationRealPersonRest, Facade facade) {
        this.validationRealPersonRest = validationRealPersonRest;
        this.facade = facade;
    }




    @RequestMapping(value = "ws/saveReal", method = RequestMethod.POST)
    public ResponseDto<String> saveReal(@RequestBody RealPersonDto realPersonDto) throws Exception {
        logger.info("startRealSaveRestController");
        validationRealPersonRest.RealValidation(realPersonDto);
        facade.saveReal(realPersonDto);
        logger.info("endRealSaveRestController");
        return new ResponseDto(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
    }


    @RequestMapping(value = "ws/updateReal", method = RequestMethod.POST)
    public ResponseDto<String> updateReal(@RequestBody RealPersonDto realPersonDto) throws Exception {
        logger.info("startUpdateSaveRestController");
        validationRealPersonRest.RealValidation(realPersonDto);
        facade.updateReal(realPersonDto);
        logger.info("endUpdateSaveRestController");
        return new ResponseDto(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
    }


    @RequestMapping(value = "ws/findReal", method = RequestMethod.POST)
    public ResponseDto<RealPerson> findReal(@RequestParam String nationalCode) throws Exception {
        logger.info("startFindRealRestController");
        RealPersonDto realPersonDtoResponse = facade.findByNationalCode(nationalCode);
        logger.info("endFindRealRestController");
        return new ResponseDto(ResponseStatus.Ok, realPersonDtoResponse, null, null);
    }


    @RequestMapping(value = "ws/findRealAll", method = RequestMethod.POST)
    public ResponseDto<List<RealPersonDto>> findLegalAll(@RequestBody RealPersonDto realPersonDto) throws Exception {
        logger.info("startFindRealAllRestController");
        //facade.findRealAll(realPersonDto);
        List<RealPersonDto> realPersonListDto = facade.findRealAll(realPersonDto);
        logger.info("endFindRealAllRestController");
        return new ResponseDto(ResponseStatus.Ok, realPersonListDto, null, null);
    }




}


