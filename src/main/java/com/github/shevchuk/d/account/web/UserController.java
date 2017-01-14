package com.github.shevchuk.d.account.web;

import com.github.shevchuk.d.account.model.User;
import com.github.shevchuk.d.account.service.SecurityService;
import com.github.shevchuk.d.account.service.UserService;
import com.github.shevchuk.d.account.validator.UserValidator;
import com.github.shevchuk.d.currency_new.service.CurrencyRESTGetter;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by dmsh0216 on 10/01/2017.
 */
@Controller
public class UserController {
    private final UserService userService;

    private final SecurityService securityService;

    private final UserValidator userValidator;

    private final CurrencyRESTGetter currencyRESTGetter;

    @Value("${currency.rest.service.url}")
    private String currencyRestServiceUrl;

    @Value("${list.of.currencies}")
    private String listOfCurrencies;

    @Value("${start.date.for.dump}")
    private String startDateForDump;

    @Autowired
    public UserController(SecurityService securityService, CurrencyRESTGetter currencyRESTGetter, UserService userService, UserValidator userValidator) {
        this.securityService = securityService;
        this.currencyRESTGetter = currencyRESTGetter;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) throws IOException {
        Arrays.stream(listOfCurrencies.split(",")).forEach(c -> {
            try {
                currencyRESTGetter.readJsonForParameters(currencyRestServiceUrl, new DateTime(startDateForDump), c);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}
