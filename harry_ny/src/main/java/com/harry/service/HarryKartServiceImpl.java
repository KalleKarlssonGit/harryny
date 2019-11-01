package com.harry.service;

import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.harry.domain.HarryResponse;
import com.harry.domain.Ranking;
import com.harry.exception.HarryEmptyException;
import com.harry.exception.HarryServiceException;
import com.harry.generated.HarryKartType;
import com.harry.generated.LaneType;
import com.harry.generated.LoopType;
import com.harry.generated.ParticipantType;

@Service
public class HarryKartServiceImpl implements HarryKartService {

	private static final int MEDAL_FINISHERS = 3;

	private static final Logger LOGGER = Logger.getLogger(HarryKartServiceImpl.class);

	@Override
	public HarryResponse getHarryResponse(String xmlString) throws HarryServiceException, HarryEmptyException {
		try {
			JAXBElement<HarryKartType> hkt = getXmlAsJavaObjectAndValidate(xmlString);
			return getHarryResponseFromXmlAsJava(hkt);
		} catch (HarryEmptyException e) {
			e.printStackTrace();
			throw e;
		} catch (HarryServiceException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unknown exception", e);
		}
	}

	@SuppressWarnings("unchecked")
	private JAXBElement<HarryKartType> getXmlAsJavaObjectAndValidate(String xmlString) throws HarryServiceException {
		JAXBElement<HarryKartType> hkt = null;

		try {
			JAXBContext jc = JAXBContext.newInstance("com.harry.generated");
			Unmarshaller um = jc.createUnmarshaller();
			hkt = (JAXBElement<HarryKartType>) um.unmarshal(new StringReader(xmlString));
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new HarryServiceException("Can not convert xml.");
		}

		validate(hkt);

		return hkt;
	}

	@Override
	public HarryResponse getHarryResponseFromXmlAsJava(JAXBElement<HarryKartType> hkt) throws HarryEmptyException {
		// Get result for all participants. All participants will be placed in this
		// list, and is null if it doesnt finish.

		List<Ranking> allRanking = getAllRanking(hkt.getValue().getNumberOfLoops(), hkt.getValue().getStartList().getParticipant(), hkt.getValue().getPowerUps().getLoop());

		List<Ranking> topXRankingList = getFirstFinsihers(allRanking);

		return new HarryResponse(topXRankingList);
	}

	// Java8
	private List<Ranking> getAllRanking(BigInteger numberOfLoops, List<ParticipantType> participantTypeList,
			List<LoopType> loopTypeList) {
		return participantTypeList.stream().map(x -> createRanking(numberOfLoops, x, loopTypeList)).sorted((o1,
				o2) -> o1 != null && o2 != null ? o1.getTotalTime().compareTo(o2.getTotalTime()) : o1 != null ? 1 : -1)
				.collect(Collectors.toList());
	}

	public Ranking createRanking(BigInteger numberOfLoops, ParticipantType pt, List<LoopType> loopTypeList) {
		Ranking rank = new Ranking(pt.getName());
		Double horseTotalTime = getHorseTotalTime(numberOfLoops, pt, loopTypeList);
		rank.setTotalTime(horseTotalTime);
		return horseTotalTime != null ? rank : null;
	}

	private List<Ranking> getFirstFinsihers(List<Ranking> allRanking) throws HarryEmptyException {
		List<Ranking> rankingList = new ArrayList<>();
		for (int i = 0; i < allRanking.size(); i++) {
			Ranking tmpRank = allRanking.get(i);
			if (tmpRank == null) {
				continue;
			}
			tmpRank.setPosition(rankingList.size() + 1);
			rankingList.add(tmpRank);
			if (rankingList.size() == MEDAL_FINISHERS) {
				return rankingList;
			}
		}

		if (rankingList.size() == 0) {
			LOGGER.error("HarryKartServiceImpl getFirstFinsihers, no finishers. ts:" + System.currentTimeMillis());
			throw new HarryEmptyException("No finishers.");
		}

		return rankingList;
	}

	@Override
	public Double getHorseTotalTime(BigInteger numberOfLoops, ParticipantType participantType, List<LoopType> loopTypeList) {
		int speed = participantType.getBaseSpeed().intValue();

		if (speed <= 0) {
			return null;
		}

		Double timeToCompleteRace = (1000 / (double) speed);

		for (int k = 1; k <= numberOfLoops.intValue(); k++) {
			for (LoopType loopType : loopTypeList) {
				if ((loopType.getNumber() != null) && (loopType.getNumber().intValue() == k)) {
					List<LaneType> laneTypeList = loopType.getLane();
					for (LaneType laneType : laneTypeList) {
						if (laneType.getNumber() != null && laneType.getNumber().equals(participantType.getLane())) {
							speed += laneType.getValue().intValue();
							if (speed <= 0) {
								return null;
							}
							break;
						}
					}
				}
			}
			timeToCompleteRace += (1000 / (double) speed);
		}

		return timeToCompleteRace;
	}

	private void validate(JAXBElement<HarryKartType> hkt) throws HarryServiceException {
		if (((hkt != null) && (hkt.getValue() != null) && (hkt.getValue().getNumberOfLoops() != null)
				&& (hkt.getValue().getNumberOfLoops().intValue() > 0) && (hkt.getValue().getStartList() != null)
				&& (hkt.getValue().getStartList().getParticipant() != null)
				&& (hkt.getValue().getStartList().getParticipant().size() > 0) && (hkt.getValue().getPowerUps() != null)
				&& (hkt.getValue().getPowerUps().getLoop() != null)
				&& (hkt.getValue().getPowerUps().getLoop().size() > 0)

		) == false) {
			LOGGER.error("HarryKartServiceImpl getHarryResponse, Wrong input data. ts:" + System.currentTimeMillis());
			throw new HarryServiceException("Invalid input data. Found inconsistent data");
		}
	}

}
