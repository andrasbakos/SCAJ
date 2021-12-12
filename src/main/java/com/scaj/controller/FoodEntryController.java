package com.scaj.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.PropertySource;

import com.scaj.controller.base.FoodEntryBaseController;

@RestController
@PropertySource("classpath:${configfile.path}/SCAJ.properties")
@RequestMapping(value="${endpoint.api}", headers = "Accept=application/json")
public class FoodEntryController extends FoodEntryBaseController {

	//OVERRIDE HERE YOUR CUSTOM CONTROLLER

}
