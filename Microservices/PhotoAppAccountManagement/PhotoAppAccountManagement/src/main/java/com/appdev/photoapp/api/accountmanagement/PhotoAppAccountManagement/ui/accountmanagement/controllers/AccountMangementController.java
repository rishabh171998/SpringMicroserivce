package com.appdev.photoapp.api.accountmanagement.PhotoAppAccountManagement.ui.accountmanagement.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accmanage")
public class AccountMangementController {

    @GetMapping("/status/check")
    public String status()
    {
        return "Working";
    }
}
