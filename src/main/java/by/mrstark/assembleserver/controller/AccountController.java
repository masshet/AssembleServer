package by.mrstark.assembleserver.controller;

import by.mrstark.assembleserver.entity.Account;
import by.mrstark.assembleserver.entity.IsFree;
import by.mrstark.assembleserver.service.AccountService;
import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mrstark on 13.3.16.
 */

@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Account regAccount(@RequestBody Account account) {
        return accountService.add(account);
    }

    @RequestMapping(value = "/free", method = RequestMethod.GET, params = "username")
    @ResponseBody
    public IsFree isUsernameFree(@RequestParam String username) {
        return accountService.findByUsername(username);
    }
}
