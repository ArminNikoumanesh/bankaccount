package com.rayanen.bankAccount.restController;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.dto.ResponseStatus;
import com.rayanen.bankAccount.facade.Facade;
import com.rayanen.bankAccount.model.entity.Transaction;
import com.rayanen.bankAccount.serviceController.TransactionService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
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
    @Autowired
    private Environment environment;
    @Autowired
    private TransactionService transactionService;




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
            menuItmDto.getChildren().get(1).getChildren().add(new MenuItmDto(MenuItemType.PAGE, "ثبت تسهیلات ", new UIPageDto(null, "startTask"), new ArrayList<MenuItmDto>()));
        }
        if(principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            menuItmDto.getChildren().get(1).getChildren().add(new MenuItmDto(MenuItemType.PAGE, "مسدود کردن حساب شخص حقیقی", new UIPageDto(null, "deleteRealAccount"), new ArrayList<MenuItmDto>()));
            menuItmDto.getChildren().get(1).getChildren().add(new MenuItmDto(MenuItemType.PAGE, "مسدود کردن حساب شخص حقوقی", new UIPageDto(null, "deleteLegalAccount"), new ArrayList<MenuItmDto>()));
        }



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
        return new ResponseDto(ResponseStatus.Ok, null, environment.getProperty("app.message.activiti.startProcess"), null);

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
        taskInputDto.setAmount((BigDecimal)taskLocalVariables.get("amount"));

        return new ResponseDto(ResponseStatus.Ok, taskInputDto, null, null);
    }

    @RequestMapping(value = "/ws/activiti/approveTask", method = RequestMethod.POST)
    public ResponseDto approveTask(@RequestBody TaskDto taskDto) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> var = taskService.getVariables(taskDto.getTaskId());
        map.put("Accept", true);
        map.put("cNumber",var.get("cNumber"));
        map.put("amount", var.get("amount"));
        if(taskService.createTaskQuery().taskId(taskDto.getTaskId()).singleResult().getAssignee().equals("UserC")){
            Transaction transactionDto=new Transaction();
            transactionDto.setDecreaserAccountNumber(var.get("cNumber").toString());
            transactionDto.setAmount(new BigDecimal(var.get("amount").toString()) );
            transactionService.decreaseTransaction(transactionDto);
//            transactionRestController.decreaseTransaction(transactionDto);
//            taskService.complete(taskDto.getTaskId());
        }
        taskService.complete(taskDto.getTaskId(), map);
        return new ResponseDto(ResponseStatus.Ok, null, environment.getProperty("app.message.activiti.approveTask"), null);
    }









    @RequestMapping(value = "/ws/activiti/rejectTask", method = RequestMethod.POST)
    public ResponseDto rejectTask(@RequestBody TaskDto taskDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("Accept", false);
        taskService.complete(taskDto.getTaskId(), map);
        return new ResponseDto(ResponseStatus.Ok, null, environment.getProperty("app.message.activiti.rejectTask"), null);
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