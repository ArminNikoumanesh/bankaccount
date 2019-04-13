package com.rayanen.bankAccount.restController;


import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.dto.ResponseStatus;
import com.rayanen.bankAccount.model.dao.RealPersonDao;
import com.rayanen.bankAccount.model.entity.RealPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
public class RealPersonRestController {

    private static Logger logger= LoggerFactory.getLogger(RealPersonRestController.class);


    private RealPersonDao realPersonDao;

    public RealPersonRestController(RealPersonDao realPersonDao) {
        this.realPersonDao = realPersonDao;
    }


    @Transactional(rollbackOn = Exception.class)

    @RequestMapping(value = "ws/saveReal", method = RequestMethod.POST)
    public ResponseDto<String> saveReal(@RequestBody RealPersonDto realPersonDto) {
        logger.info("start real save");


            logger.info("end real save");
            return new ResponseDto(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
        }



    @Transactional(rollbackOn = Exception.class)


    @RequestMapping(value = "ws/updateReal", method = RequestMethod.POST)
    public ResponseDto<String> updateReal(@RequestBody RealPersonDto realPersonDto) {
        logger.info("start update save");

            logger.info("end update save");
            return new ResponseDto(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
        }


    @RequestMapping(value = "ws/findReal", method = RequestMethod.POST)
    public ResponseDto<RealPerson> findReal(@RequestParam String nationalCode) {
        RealPersonDto realPersonDto = realPersonDao.findByNationalCode(nationalCode);
        logger.info("start finding Real");
        if (Objects.isNull(realPersonDto)) {
            logger.error("real wasn't found");
            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("not find"));
        }
        logger.info("real was found");
        return new ResponseDto(ResponseStatus.Ok, realPersonDto, null, null);
    }



    @RequestMapping(value = "ws/findRealAll", method = RequestMethod.POST)
    public ResponseDto<List<RealPersonDto>> findLegalAll(@RequestBody RealPersonDto realPersonDto) {
        List<RealPersonDto> result = realPersonDao.findByNameAndFamilyName(realPersonDto.getName(), realPersonDto.getFamilyName());
        return new ResponseDto(ResponseStatus.Ok, result, null, null);
    }


}


