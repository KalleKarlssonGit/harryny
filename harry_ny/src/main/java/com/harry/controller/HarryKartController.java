package com.harry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.harry.domain.HarryResponse;
import com.harry.exception.HarryEmptyException;
import com.harry.exception.HarryServiceException;
import com.harry.service.HarryKartService;

@RestController
@RequestMapping("/api")
public class HarryKartController {

	@Autowired
	private HarryKartService harryKartService;

	@RequestMapping(method = RequestMethod.POST, path = "/play", consumes = "application/xml", produces = "application/json")
	public @ResponseBody HarryResponse playHarryKart(@RequestBody String xmlString) throws HarryEmptyException, HarryServiceException {
		return harryKartService.getHarryResponse(xmlString);
	}

}
