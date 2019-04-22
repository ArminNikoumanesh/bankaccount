package com.rayanen.bankAccount.restController;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.dto.ResponseStatus;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserRestMenu {


    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

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

    @RequestMapping(value = "/ws/activiti/startProcess", method = RequestMethod.POST)
    public ResponseDto startProcess() {
        runtimeService.startProcessInstanceByKey("facility");
        return new ResponseDto(ResponseStatus.Ok, null, "فرایند آغاز شد.", null);

    }

    @RequestMapping(value = "/ws/activiti/getTasks", method = RequestMethod.POST)
    public ResponseDto<List<TaskDto>> getTasks(@RequestParam String taskId) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(getUsername()).list();
        List<TaskDto> taskDtos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TaskDto taskDto = new TaskDto();
            taskDto.setTaskId(list.get(i).getId());
            taskDto.setName(list.get(i).getName());
            taskDtos.add(taskDto);
        }
        return new ResponseDto(ResponseStatus.Ok, taskDtos, null, null);
    }

    @RequestMapping(value = "/ws/activiti/approveTask", method = RequestMethod.POST)
    public ResponseDto approveTask(@RequestBody TaskDto taskDto) {
        // Map<String,Object> map = new HashMap<>();
        //   map.put("sanad",obj);
        // taskService.complete(taskId, map);
        taskService.complete(taskDto.getTaskId());
        //taskService.setVariable(taskId,"sanad", obj);
        return new ResponseDto(ResponseStatus.Ok, null, "تأیید شد.", null);
    }

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }



    String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(new ClassPathResource(path + ".xml").getFile().getPath()));
        return new String(encoded, encoding);
    }
}