package com.rayanen.bankAccount.restController;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.dto.ResponseStatus;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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

                        ))),
        new MenuItmDto(MenuItemType.MENU, "کارتابل", null, new ArrayList<>(Arrays.asList(
                new MenuItmDto(MenuItemType.PAGE, "تأیید", new UIPageDto(null, "approveTask"), new ArrayList<>()),
                new MenuItmDto(MenuItemType.PAGE, "کارتابل", new UIPageDto(null, "showTasks"), new ArrayList<>()))))

       )));
        UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SECRETARY"))) {
//          menuItmDto.getChildren().get(4).getChildren().add(new MenuItmDto(MenuItemType.MENU, "تسهیلات",new  ArrayList<>(Arrays.asList())));
            menuItmDto.getChildren().get(1).getChildren().add(new MenuItmDto(MenuItemType.PAGE, "ثبت تسهیلات ", new UIPageDto(null, "startTask"), new ArrayList<MenuItmDto>()));
//          menuItmDto.getChildren().get(0).getChildren().add(new MenuItmDto(MenuItemType.PAGE, "تأیید", new UIPageDto(null, "approveTask"), new ArrayList<MenuItmDto>()));
        }
//        afterLoginInfoDto.setMenu(menuItmDto);


        afterLoginInfoDto.setMenu(menuItmDto);
        return new ResponseDto(ResponseStatus.Ok, afterLoginInfoDto,null,null);




    }



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
    public ResponseDto startProcess(@RequestBody TaskInputDto taskInputDto) {
        Map<String,Object> map = new HashMap<>();
        map.put("cNumber",taskInputDto.getcNumber());
        map.put("amount", taskInputDto.getAmount());
        ProcessInstance facility = runtimeService.startProcessInstanceByKey("facility", map);
        taskService.complete(taskService.createTaskQuery().processInstanceId(facility.getProcessInstanceId()).singleResult().getId());
        return new ResponseDto(ResponseStatus.Ok, null, "فرایند آغاز شد.", null);

    }

    @RequestMapping(value = "/ws/activiti/getTasks", method = RequestMethod.POST)
    public ResponseDto<List<TaskDto>> getTasks() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(getUsername()).list();
        List<TaskDto> taskDtos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TaskDto taskDto = new TaskDto();
            taskDto.setTaskId(list.get(i).getId());
            taskDto.setName(list.get(i).getName());
            taskDto.setFormKey(list.get(i).getFormKey());
            taskDtos.add(taskDto);
        }
        return new ResponseDto(ResponseStatus.Ok, taskDtos, null, null);
    }

    @RequestMapping(value = "/ws/activiti/getTaskByTaskId", method = RequestMethod.POST)
    public ResponseDto<TaskInputDto> getTaskByTaskId(@RequestParam String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        TaskInputDto taskInputDto = new TaskInputDto();
        taskInputDto.setTaskId(task.getId());
        Map<String, Object> taskLocalVariables = runtimeService.getVariables(task.getProcessInstanceId());
        taskInputDto.setcNumber(taskLocalVariables.get("cNumber").toString());
        return new ResponseDto(ResponseStatus.Ok, taskInputDto, null, null);
    }

    @RequestMapping(value = "/ws/activiti/approveTask", method = RequestMethod.POST)
    public ResponseDto approveTask(@RequestBody TaskDto taskDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("Accept", true);
        taskService.complete(taskDto.getTaskId(), map);
        return new ResponseDto(ResponseStatus.Ok, null, "تأیید شد.", null);
    }

    @RequestMapping(value = "/ws/activiti/rejectTask", method = RequestMethod.POST)
    public ResponseDto rejectTask(@RequestBody TaskDto taskDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("Accept", false);
        taskService.complete(taskDto.getTaskId(), map);
        return new ResponseDto(ResponseStatus.Ok, null, "تأیید شد.", null);
    }

    @RequestMapping(value = "/pws/activiti/getUrlByFormKey", method = RequestMethod.POST)
    public ResponseDto<String> getUrlByFormKey(@RequestParam String formKey) {

        String url = "";

        switch (formKey){
            case "UserAForm":
                url = "approveTask";
                break;
            case "UserBForm":
                url = "approveTask";
                break;
            case "UserCForm":
                url = "approveTask";
                break;
            case "UserDForm":
                url = "approveTask";
                break;
        }
        return new ResponseDto(ResponseStatus.Ok, url, null, null);
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