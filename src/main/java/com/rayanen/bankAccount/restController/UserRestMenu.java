package com.rayanen.bankAccount.restController;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.dto.ResponseStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class UserRestMenu {




    @RequestMapping(value = "/ws/login", method = RequestMethod.GET)
    public ResponseDto<MenuItmDto> getUserMenu() {
        AfterLoginInfoDto afterLoginInfoDto = new AfterLoginInfoDto();
        MenuItmDto menuItmDto = new MenuItmDto(null, null, null, new ArrayList<MenuItmDto>(Arrays.asList(
                new MenuItmDto(MenuItemType.MENU, "خدمات مشتری", null, new ArrayList<MenuItmDto>(Arrays.asList(
                        new MenuItmDto(MenuItemType.PAGE, "افزودن شخص حقیقی", new UIPageDto(null, "addRealPerson"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, "افزودن شخص حقوقی", new UIPageDto(null, "addLegalPerson"), new ArrayList<>())))),

                new MenuItmDto(MenuItemType.MENU, "خدمات بانکی", null, new ArrayList<>(Arrays.asList(
                        new MenuItmDto(MenuItemType.PAGE, "برداشت", new UIPageDto(null, "increaseTransaction"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, " واریز", new UIPageDto(null, "decreaseTransaction"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, " انتقال وجه", new UIPageDto(null, "transferTransaction"), new ArrayList<>())
                ))),
                new MenuItmDto(MenuItemType.MENU, "خدمات ویرایش", null, new ArrayList<>(Arrays.asList(
                        new MenuItmDto(MenuItemType.PAGE, "شخص حقیقی", new UIPageDto(null, "updateRealPerson"), new ArrayList<>()),
                        new MenuItmDto(MenuItemType.PAGE, " شخص حقوقی", new UIPageDto(null, "updateLegalPerson"), new ArrayList<>())
                ))))));
        afterLoginInfoDto.setMenu(menuItmDto);
        return new ResponseDto(ResponseStatus.Ok, afterLoginInfoDto,null,null);    }



    @RequestMapping(value = "/pws/uipage/getPage", method = RequestMethod.POST)
    public ResponseDto<String> getPage(@RequestParam String name) throws IOException {
        return new ResponseDto<>(ResponseStatus.Ok, readFile(name, StandardCharsets.UTF_8), null, null);
    }

    @RequestMapping(value = "/pws/error", method = RequestMethod.GET)
    public ResponseDto error(@RequestParam String code){
        switch (code){
            case "loginFailure":
                return new ResponseDto(ResponseStatus.Error,null,null,new ResponseException("نام کاربری یا کلمه عبور درست وارد نشده"));
            default:
                return new ResponseDto(ResponseStatus.Error, null,null,new ResponseException("خطای سیستمی رخ داده است"));
        }
    }


    String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(new ClassPathResource(path + ".xml").getFile().getPath()));
        return new String(encoded, encoding);
    }
}