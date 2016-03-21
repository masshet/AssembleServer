package by.mrstark.assembleserver.controller;

import by.mrstark.assembleserver.entity.Account;
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

    @RequestMapping(value = "/register", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Account regAccount() {
        return accountService.add(createMockAccount());
    }

    @RequestMapping(value = "/isfree", method = RequestMethod.GET)
    @ResponseBody
    public String isUsernameFree(@RequestParam(value = "username", defaultValue = "masshet") String username) {
        boolean isFree = accountService.findByUsername(username);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(isFree);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Account createMockAccount() {
        Account account = new Account("masshet", "masshet");
        account.setFirstName("Alex");
        account.setLastName("Masliakov");
        account.setEmail("lmasliakov@gmial.com");
        return account;
    }

}
