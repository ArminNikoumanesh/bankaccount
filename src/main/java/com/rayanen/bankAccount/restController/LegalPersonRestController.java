package com.rayanen.bankAccount.restController;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.dto.ResponseStatus;
import com.rayanen.bankAccount.facade.Facade;
import com.rayanen.bankAccount.model.entity.LegalPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
public class LegalPersonRestController  {

    private static Logger logger= LoggerFactory.getLogger(LegalPersonRestController.class);



Facade facade ;

    public LegalPersonRestController(Facade facade) {
        this.facade = facade;
    }




    @RequestMapping(value = "ws/saveLegal", method = RequestMethod.POST)
    public ResponseDto<String> saveLegal(@Valid @RequestBody LegalPersonDto legalPersonDto) {
        logger.info("startLegalSaveRestController");
        facade.saveLegal(legalPersonDto);
        logger.info("endLegalSaveRestController");
        return new ResponseDto<>(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
    }



    @RequestMapping(value = "ws/updateLegal", method = RequestMethod.POST)
    public ResponseDto<String> updateLegal(@RequestBody LegalPersonDto legalPersonDto) {
        logger.info("startLegalUpdateRestController");
        facade.updateLegal(legalPersonDto);
        logger.info("endLegalUpdateRestController");
        return new ResponseDto<>(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);    }




    @RequestMapping(value = "ws/findLegal", method = RequestMethod.POST)
    public ResponseDto<LegalPerson> findLegal(@RequestParam String code) {
        logger.info("startFindingLegalRestController");
        facade.findLegal(code);
        logger.info("endFindingLegalRestController");
        return new ResponseDto(ResponseStatus.Ok, null, null, null);
    }

    @RequestMapping(value = "ws/findLegalAll", method = RequestMethod.POST)
    public ResponseDto<List<LegalPersonDto>> findLegalAll(@RequestBody LegalPersonDto legalPersonDto) {
        logger.info("startFindingAllLegalRestController");
        facade.findLegalAll(legalPersonDto);
        logger.info("endFindingLegalRestController");
        return new ResponseDto(ResponseStatus.Ok, null, null, null);

    }


}
