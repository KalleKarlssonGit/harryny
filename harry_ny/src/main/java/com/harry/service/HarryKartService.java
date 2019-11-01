package com.harry.service;

import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.harry.domain.HarryResponse;
import com.harry.exception.HarryEmptyException;
import com.harry.exception.HarryServiceException;
import com.harry.generated.HarryKartType;
import com.harry.generated.LoopType;
import com.harry.generated.ParticipantType;

public interface HarryKartService {

	public HarryResponse getHarryResponse(String xmlString)  throws HarryServiceException, HarryEmptyException;

	public HarryResponse getHarryResponseFromXmlAsJava(JAXBElement<HarryKartType> hkt) throws HarryEmptyException;

	public Double getHorseTotalTime(BigInteger numberOfLoops, ParticipantType participantType, List<LoopType> loopTypeList);

}

