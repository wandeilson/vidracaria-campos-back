package com.vidracariaCampos.controller;

import com.vidracariaCampos.service.TransationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transation")
public class TransationController {

    @Autowired
    private TransationService transationService;
}
