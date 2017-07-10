package com.jmp.service;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.jmp.entity.account.Account;
import com.jmp.exception.AccountNotFoundException;

@Service
public class WebAccountService {

    @Autowired
    private RestTemplate restTemplate;

    private String serviceUrl;

    private Logger logger = Logger.getLogger(WebAccountService.class.getName());

    public WebAccountService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
                : "http://" + serviceUrl;
    }

    @PostConstruct
    public void demoOnly() {
        // Can't do this in the constructor because the RestTemplate injection
        // happens afterwards.
        logger.warning("The RestTemplate request factory is "
                + restTemplate.getRequestFactory().getClass());
    }

    public Account findByNumber(String accountNumber) {

        logger.info("findByNumber() invoked: for " + accountNumber);
        return restTemplate.getForObject(serviceUrl + "/accounts/{number}",
                Account.class, accountNumber);
    }

    public List<Account> byOwnerContains(String name) {
        logger.info("byOwnerContains() invoked:  for " + name);
        Account[] accounts = null;

        try {
            accounts = restTemplate.getForObject(serviceUrl
                    + "/accounts/owner/{name}", Account[].class, name);
        } catch (HttpClientErrorException e) { // 404
            // Nothing found
        }

        if (accounts == null || accounts.length == 0)
            return null;
        else
            return Arrays.asList(accounts);
    }

    public Account getByNumber(String accountNumber) {
        Account account = restTemplate.getForObject(serviceUrl
                + "/accounts/{number}", Account.class, accountNumber);

        if (account == null)
            throw new AccountNotFoundException(accountNumber);
        else
            return account;
    }
}
