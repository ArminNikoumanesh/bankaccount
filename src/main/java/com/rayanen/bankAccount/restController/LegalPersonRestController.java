package com.rayanen.bankAccount.restController;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.dto.ResponseStatus;
import com.rayanen.bankAccount.facade.Facade;
import com.rayanen.bankAccount.model.entity.LegalPerson;
import com.rayanen.bankAccount.validation.rest.ValidationLegalPersonRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
public class LegalPersonRestController  {

    private static Logger logger= LoggerFactory.getLogger(LegalPersonRestController.class);



    private  ValidationLegalPersonRest validationLegalPersonRest;
    private  Facade facade ;
    private Environment environment;

    public LegalPersonRestController(ValidationLegalPersonRest validationLegalPersonRest, Facade facade, Environment environment) {
        this.validationLegalPersonRest = validationLegalPersonRest;
        this.facade = facade;
        this.environment = environment;
    }


    @RequestMapping(value = "ws/saveLegal", method = RequestMethod.POST)
    public ResponseDto<String> saveLegal(@Valid @RequestBody LegalPersonDto legalPersonDto) throws Exception {
        logger.info("startLegalSaveRestController");
        validationLegalPersonRest.LegalValidation(legalPersonDto);
        facade.saveLegal(legalPersonDto);
        logger.info("endLegalSaveRestController");
        return new ResponseDto<>(ResponseStatus.Ok, null, environment.getProperty("app.message.saveLegal"), null);
    }



    @RequestMapping(value = "ws/updateLegal", method = RequestMethod.POST)
    public ResponseDto<String> updateLegal(@RequestBody LegalPersonDto legalPersonDto) throws Exception {
        logger.info("startLegalUpdateRestController");
        validationLegalPersonRest.LegalValidation(legalPersonDto);
        facade.updateLegal(legalPersonDto);
        logger.info("endLegalUpdateRestController");
        return new ResponseDto<>(ResponseStatus.Ok, null, environment.getProperty("app.message.updateLegal"), null);    }




    @RequestMapping(value = "ws/findLegal", method = RequestMethod.POST)
    public ResponseDto<LegalPerson> findLegal(@RequestParam String companyCode) throws Exception {
        logger.info("startFindingLegalRestController");
        facade.findLegal(companyCode);
        LegalPersonDto legalPersonDto=facade.findLegal(companyCode);
        logger.info("endFindingLegalRestController");
        return new ResponseDto(ResponseStatus.Ok,legalPersonDto, null, null);
    }


    @RequestMapping(value = "ws/findLegalAll", method = RequestMethod.POST)
    public ResponseDto<List<LegalPersonDto>> findLegalAll(@RequestBody LegalPersonDto legalPersonDto) throws Exception {
        logger.info("startFindingAllLegalRestController");
        facade.findLegalAll(legalPersonDto);
        List<LegalPersonDto> legalPersonListDto= facade.findLegalAll(legalPersonDto);
        logger.info("endFindingLegalRestController");
        return new ResponseDto(ResponseStatus.Ok, legalPersonListDto, null, null);

    }



}
