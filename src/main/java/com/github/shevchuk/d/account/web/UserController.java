package com.github.shevchuk.d.account.web;

import com.github.shevchuk.d.account.model.User;
import com.github.shevchuk.d.account.service.SecurityService;
import com.github.shevchuk.d.account.service.UserService;
import com.github.shevchuk.d.account.validator.UserValidator;
import com.github.shevchuk.d.chart.model.Chart;

import com.github.shevchuk.d.currency_new.model.CurrencyObject;
import com.github.shevchuk.d.currency_new.service.CurrencyRESTGetter;
import com.github.shevchuk.d.view.model.CurrencyCurrentView;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by dmsh0216 on 10/01/2017.
 */
@Controller
public class UserController {
    public static Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final SecurityService securityService;

    private final UserValidator userValidator;

    private final CurrencyRESTGetter currencyRESTGetter;

    @Value("${currency.rest.service.url}")
    private String currencyRestServiceUrl;

    @Value("${target.currency}")
    private String targetCurrency;

    @Value("${base.currency}")
    private String baseCurrency;

    @Value("${start.date.for.dump}")
    private String startDateForDump;

    @Value("${end.date.for.dump}")
    private String endDateForDump;

    @Value("${all.available.currencies}")
    private String allAvailableCurrencies;

    @Value("${periods}")
    private String periods;

    @Value("${the.beginning}")
    private String theBeginning;

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
    public String login(
            @ModelAttribute("currencySelector") CurrencyCurrentView c,
            BindingResult bindingResult,
            Model model,
            String error,
            String logout) throws IOException {
        CurrencyCurrentView currencySelector = (CurrencyCurrentView) bindingResult.getModel().get("currencySelector");
        if ( !(null == currencySelector.getBase()) && !"".equals(currencySelector.getBase()) ) baseCurrency = currencySelector.getBase();
        if ( !(null == currencySelector.getTarget()) && !"".equals(currencySelector.getTarget()) ) targetCurrency = currencySelector.getTarget();
        if ( !(null == currencySelector.getPeriod()) && !"".equals(currencySelector.getPeriod()) ) {
            int minus =  periodsToDays(currencySelector.getPeriod());
            endDateForDump = new DateTime().toString();
            startDateForDump = (minus == - 1)
                                ? theBeginning
                                : new DateTime().minusDays(minus).toString();
        }
        currencyRESTGetter.readJsonForParameters(currencyRestServiceUrl ,
                new DateTime(startDateForDump) ,
                endDateForDump.toLowerCase().equals("today") ? new DateTime() : new DateTime(endDateForDump) ,
                baseCurrency ,
                targetCurrency
                );
        Chart chart = new Chart();
        chart.setCoordinates(currencyRESTGetter.chart.getCoordinates());
        model.addAttribute("chart", chart.toString());
        model.addAttribute("chartChart", currencyRESTGetter.chart.getCoordinates());

        CurrencyCurrentView currencyCurrentView = new CurrencyCurrentView();
        currencyCurrentView.setTarget(targetCurrency);
        currencyCurrentView.setBase(baseCurrency);
        currencyCurrentView.setFromTime(startDateForDump);


        model.addAttribute("currencyCurrentView", currencyCurrentView);

        String[] currencies = allAvailableCurrencies.split(",");
        model.addAttribute("currencies", currencies);

        String[] periodSelect = periods.split(",");
        model.addAttribute("periodSelect", periodSelect);



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



    private int periodsToDays(String period){
        if ("1 week".equals(period)) return 7;
        if ("2 weeks".equals(period)) return 14;
        if ("1 month".equals(period)) return 30;
        if ("3 months".equals(period)) return 90;
        if ("6 months".equals(period)) return 180;
        if ("1 year".equals(period)) return 365;
        if ("3 years".equals(period)) return 1000;
        if ("5 years".equals(period)) return 1826;
        if ("10 years".equals(period)) return 3652;
        if ("15 years".equals(period)) return 5475;
        if ("from the beginning".equals(period)) return -1;
        return 7;
    }


}
