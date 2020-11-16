package webservice.ispyb.collection;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.google.gson.Gson;

import generated.ws.mx.collection.BeamLineSetup3VO;
import generated.ws.mx.collection.BlSampleWS3VO;
import generated.ws.mx.collection.DataCollectionGroupWS3VO;
import generated.ws.mx.collection.DataCollectionInfo;
import generated.ws.mx.collection.DataCollectionPosition;
import generated.ws.mx.collection.DataCollectionWS3VO;
import generated.ws.mx.collection.Detector3VO;
import generated.ws.mx.collection.EnergyScanWS3VO;
import generated.ws.mx.collection.GridInfoWS3VO;
import generated.ws.mx.collection.ImagePosition;
import generated.ws.mx.collection.ImageWS3VO;
import generated.ws.mx.collection.IspybWS;
import generated.ws.mx.collection.PositionWS3VO;
import generated.ws.mx.collection.RobotActionWS3VO;
import generated.ws.mx.collection.SessionWS3VO;
import generated.ws.mx.collection.ToolsForCollectionWebService;
import generated.ws.mx.collection.Workflow3VO;
import generated.ws.mx.collection.XdsInfo;
import generated.ws.mx.collection.XfeFluorescenceSpectrumWS3VO;
import webservice.UtilsDoNotCommit;


public class TestCollectionToolsWebService {


	public TestCollectionToolsWebService() throws Exception {
		super();
		initWebService();
	}

	
	protected static generated.ws.mx.collection.IspybWS iws;
	protected static ToolsForCollectionWebService ws;

	private static void initWebService() throws Exception {
		
		iws = new IspybWS();	

		System.out.println("-----------------------------------------------------------");

		ws=iws.getToolsForCollectionWebServicePort();
		BindingProvider bindingProvider = (BindingProvider)ws;
		Map requestContext = bindingProvider.getRequestContext();
		
		requestContext.put(BindingProvider.USERNAME_PROPERTY, UtilsDoNotCommit.ISPYBU);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, UtilsDoNotCommit.ISPYBP);

		//ws.setTimeout(3 * 1000);// 15

	}

	public static void main(String args[]) {
		try {

			System.out.println("*************** testCollectionWebServices ***************");
			initWebService();

//			testStoreSessionDCgrougDcImage();
//			testStoreOrUpdateBeamLineSetup();
//		 testFindSession();
		 testFindSessionsToBeProtected();
//			testFindSessionsByProposalAndBeamLine();
//			 testStoreOrUpdateSession();
//			 testStoreOrUpdateDataCollection();
//			 testStoreOrUpdateDataCollectionBatch();
//			 testFindDataCollectionFromFileLocationAndFileName();
//			 testFindDataCollectionGroup();
//			 testFindDataCollectionFromImageDirectoryAndImagePrefixAndNumber();
//			 testStoreOrUpdateDataCollectionGroup();
//			 testFindDataCollection();
//			 testStoreOrUpdateXFEFluorescenceSpectrum();
//			 testStoreOrUpdateEnergyScan();
//			 testStoreOrUpdateImage();
//			 testFindDetectorByParam();
//			 testGetXDSInfo();
//			 testUpdateDataCollectionStatus();
//			 testGetPdbFilePath();
//			 testStoreOrUpdatePosition();
//			 testStoreOrUpdateWorkflow();
//			 testUpdateDataCollectionGroupWorkflowId();
//			 testUpdateWorkflowStatus();
//			 testSetDataCollectionPosition();
//			 testStoreOrUpdateGridInfo();
//			 testSetDataCollectionsPositions();
//			 testGetDataCollectionInfo();
//			 testSetImagesPositions();
//			 testSetBestWilsonPlotPath();
//			 testStoreWorkFlowStep();
//			 testStoreRobotAction();

		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

	public static String beamLineSetupToString(BeamLineSetup3VO vo) {
		if (vo == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("beamLineSetupId=" + vo.getBeamLineSetupId() + " " + "synchrotronMode=" + vo.getSynchrotronMode() + " ");
		str.append('}');

		return (str.toString());
	}

	public static String detectorToString(Detector3VO vo) {
		if (vo == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("detectorId=" + vo.getDetectorId() + " " + "detectorType=" + vo.getDetectorType()
				+ " "
				+
				// "detectorMode=" + vo.getDetectorMode() + " " +
				"detectorManufacturer=" + vo.getDetectorManufacturer() + " " + "detectorModel=" + vo.getDetectorModel() + " "
				+ "detectorPixelSizsHorizontal=" + vo.getDetectorPixelSizeHorizontal() + " " + "detectorPixelSizsVertical="
				+ vo.getDetectorPixelSizeVertical() + " ");
		str.append('}');

		return (str.toString());
	}

	private static void testFindSessionsByProposalAndBeamLine() throws Exception {
		System.out.println("*************** testFindSessionsByProposalAndBeamLine() ***************");
		String code = "MX";
		String number = "415";
		String beamLineName = "ID30A-2";
		List<SessionWS3VO> vos = ws.findSessionsByProposalAndBeamLine(code, number, beamLineName);
		if (vos != null) {
			System.out.println("Sessions length = " + vos.size() + "\n");
			int i=0;
			for (Iterator iterator = vos.iterator(); iterator.hasNext();) {
				SessionWS3VO sessionWS3VO = (SessionWS3VO) iterator.next();
				System.out.println("Session[" + i + "] = " + sessionToString(sessionWS3VO) + "\n");
				i=i+1;
			}
			System.out.println("This is what I got as a response :\n" + vos.toString() + vos);
		} else
			System.out.println("This is what I got as a response : NULL \n");
	}

	private static void testStoreOrUpdateBeamLineSetup() throws Exception {
		System.out.println("*************** testStoreOrUpdateBeamLineSetup ***************");
		Integer ret = -1;

		Integer beamLineSetupId = 286304; // null;
		String synchrotronMode = "synchrotronMode";
		String undulatorType1 = "undulatorType1_updated";
		String undulatorType2 = "undulatorType2";
		String undulatorType3 = "undulatorType3";
		Double focalSpotSizeAtSample = 0.3;
		String focusingOptic = "focusingOptic";
		Double beamDivergenceHorizontal = 0.4;
		Double beamDivergenceVertical = 0.5;
		Double polarisation = 0.6;
		String monochromatorType = "monochromatorType";
		Calendar setupDate = Calendar.getInstance();
		String synchrotronName = "synchrotronName";
		Double maxExpTimePerDataCollection = 0.03;
		Double minExposureTimePerImage = 0.04;
		Double goniostatMaxOscillationSpeed = 0.05;
		Double goniostatMinOscillationWidth = 0.06;
		Double minTransmission = 0.07;

		BeamLineSetup3VO value = new BeamLineSetup3VO();
		value.setBeamLineSetupId(beamLineSetupId);
		value.setSynchrotronMode(synchrotronMode);
		value.setUndulatorType1(undulatorType1);
		value.setUndulatorType2(undulatorType2);
		value.setUndulatorType3(undulatorType3);
		value.setFocalSpotSizeAtSample(focalSpotSizeAtSample);
		value.setFocusingOptic(focusingOptic);
		value.setBeamDivergenceHorizontal(beamDivergenceHorizontal);
		value.setBeamDivergenceVertical(beamDivergenceVertical);
		value.setPolarisation(polarisation);
		value.setMonochromatorType(monochromatorType);
		value.setSetupDate(setupDate);
		value.setSynchrotronName(synchrotronName);
		value.setMaxExpTimePerDataCollection(maxExpTimePerDataCollection);
		value.setMinExposureTimePerImage(minExposureTimePerImage);
		value.setGoniostatMaxOscillationSpeed(goniostatMaxOscillationSpeed);
		value.setGoniostatMinOscillationWidth(goniostatMinOscillationWidth);
		value.setMinTransmission(minTransmission);

		ret = ws.storeOrUpdateBeamLineSetup(value);
		System.out.println("This is what I got as a response : beamLineSetupId = " + ret + "  \n");
	}

	private static void testFindSession() throws Exception {
		System.out.println("*************** testFindSession ***************");
		SessionWS3VO session = null;
		Integer sessionId = 39645;

		session = ws.findSession(sessionId);
		if (session != null)
		System.out.println("This is what I got as a response : SessionValue = " + session.getBeamlineName() + " "+ session.getStartDate() + " "+ session.getBeamlineOperator() + " \n");
		else 
			System.out.println("This is what I got as a response : session is null" );
	}
	
	private static void testFindSessionsToBeProtected() throws Exception {
		System.out.println("*************** testFindSessionsToBeProtected ***************");
		
		Iterable<SessionWS3VO> sessions = null;
		Integer delay = 8;
		Integer window = 24*15;

		sessions = ws.findSessionsToBeProtected(delay, window);
		if (sessions != null)
		System.out.println("This is what I got as a response : SessionValue = " + sessions.toString() + "  \n");
		else 
			System.out.println("This is what I got as a response : session is null" );
	}

	private static void testFindSessionsByCodeAndNumberAndBeamline() throws Exception {
		System.out.println("*************** testFindSessionsByCodeAndBumberAndBeamline ***************");
		Iterable<SessionWS3VO> sessions = null;
		String number = "415";
		String code = "MX";
		String beamline = "ID30A-1";

		sessions = ws.findSessionsByProposalAndBeamLine(code, number, beamline);
		System.out.println("This is what I got as a response : SessionValue = " + sessions.toString() + "  \n");
	}

	private static void testFindDataCollectionGroup() throws Exception {
		System.out.println("*************** testFindDataCollectionGroup ***************");
		DataCollectionGroupWS3VO dcg = null;
		Integer pk = 19570;
		//pk = 1;

		dcg = ws.findDataCollectionGroup(pk);
		if (dcg != null)
			System.out.println("This is what I got as a response : DataCollectionGroupValue = " + dcg.toString() + "  \n");
		else
			System.out.println("No dcgroup found");
	}

	public static String sessionToString(SessionWS3VO vo) {
		if (vo == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("sessionId=" + vo.getSessionId() + " " + "beamLineSetupId=" + vo.getBeamLineSetupId() + " " + "proposalId="
				+ vo.getProposalId() + " " + "projectCode=" + vo.getProjectCode() + " " + "startDate=" + vo.getStartDate() + " "
				+ "endDate=" + vo.getEndDate() + " ");
		str.append('}');

		return (str.toString());
	}

	public static String dataCollectionToString(DataCollectionWS3VO vo) {
		if (vo == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("dataCollectionId=" + vo.getDataCollectionId()
				+ " "
				+
				// "dataCollectionGroupId=" + vo.getDataCollectionGroupId() + " " +
				"strategySubWedgeOrigId=" + vo.getStrategySubWedgeOrigId() + " " + "detectorId=" + vo.getDetectorId() + " "
				+ "transmission=" + vo.getTransmission() + " " + "beamSizeAtSampleX=" + vo.getBeamSizeAtSampleX() + " "
				+ "beamSizeAtSampleY=" + vo.getBeamSizeAtSampleY());
		str.append('}');

		return (str.toString());
	}

	public static String blSampleToString(BlSampleWS3VO blSample) {
		if (blSample == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("blSampleId=" + blSample.getBlSampleId() + " " + "crystalId=" + blSample.getCrystalId() + " "
				+ "diffractionPlanId=" + blSample.getDiffractionPlanId() + " " + "containerId=" + blSample.getContainerId() + " "
				+ "name= " + blSample.getName() + " " + "location=" + blSample.getLocation() + " " + " smiles = "
				+ blSample.getSmiles() + " ");
		str.append('}');

		return (str.toString());
	}

	public static String dataCollectionGroupToString(DataCollectionGroupWS3VO vo) {
		if (vo == null)
			return "null";
		StringBuffer str = new StringBuffer("{");

		str.append("dataCollectionGroupId=" + vo.getDataCollectionGroupId() + " " + "blSampleId=" + vo.getBlSampleId() + " "
				+ "sessionId=" + vo.getSessionId() + " " + "experimentType=" + vo.getExperimentType() + " " + "startTime="
				+ vo.getStartTime() + " " + "endTime=" + vo.getEndTime() + " ");
		str.append('}');

		return (str.toString());
	}

	private static void testStoreOrUpdateSession() throws Exception {
		System.out.println("*************** testStoreOrUpdateSession ***************");
		Integer ret = -1;
		long startWS = System.currentTimeMillis();
		Integer sessionId = null; // null;
		Integer beamLineSetupId = 1;//239342;
		Integer proposalId = 1;//1170;
		String projectCode = "projectCode";
		Calendar startDate = Calendar.getInstance();
		startDate.set(2016, 1, 16, 14, 50, 23);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2016, 1, 16, 18, 52, 55);
		endDate = null;
		String beamLineName = "ID29";
		Byte scheduled = 1;
		Integer nbShifts = 1;
		String comments = "comments";
		String beamLineOperator = "Tests for Solange";
		Integer visit_number = 2;
		Calendar recordTimeStamp = Calendar.getInstance();
		recordTimeStamp.set(2016, 1, 16, 14, 52, 55);
		Byte usedFlag = 1;
		String sessionTitle = "sessionTitle";
		Float structureDeterminations = (float) 0;
		Float dewarTransport = (float) 1;
		Float databackupFrance = (float) 2;
		Float databackupEurope = (float) 3;

		SessionWS3VO value = new SessionWS3VO();
		value.setSessionId(sessionId);
		value.setBeamLineSetupId(beamLineSetupId);
		value.setProposalId(proposalId);
		value.setProjectCode(projectCode);
		value.setStartDate(startDate);
		value.setEndDate(null);
		//value.setEndDate(endDate);
		value.setBeamlineName(beamLineName);
		value.setScheduled(scheduled);
		value.setNbShifts(null);
		//value.setNbShifts(nbShifts);
		value.setComments(comments);
		value.setBeamlineOperator(beamLineOperator);
		//value.setVisit_number(visit_number);
		value.setTimeStamp(recordTimeStamp);
		value.setUsedFlag(usedFlag);
		value.setSessionTitle(sessionTitle);
		value.setStructureDeterminations(structureDeterminations);
		value.setDewarTransport(dewarTransport);
		value.setDatabackupFrance(databackupFrance);
		value.setDatabackupEurope(databackupEurope);

		ret = ws.storeOrUpdateSession(value);

		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");
		System.out.println("This is what I got as a response : sessionId = " + ret + "  \n");

		// foreign key null
		// value.setProposalId(null);
		// ret = ws.storeOrUpdateSession(value);
		// System.out.println("F This is what I got as a response : sessionId = " + ret + "  \n");

	}
	
	private static void testStoreSessionDCgrougDcImage() throws Exception {
		System.out.println("*************** testStoreOrUpdateSession ***************");
		Integer ret = -1;
		long startWS = System.currentTimeMillis();
		Integer sessionId = null; // null;
		Integer beamLineSetupId = 1;//239342;
		Integer proposalId = 1;//1170;
		String projectCode = "projectCode";
		Calendar startDate = Calendar.getInstance();
		startDate.set(2015, 6, 5, 14, 50, 23);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2015, 3, 9, 14, 52, 55);
		String beamLineName = "ID29";
		Byte scheduled = 1;
		Integer nbShifts = 1;
		String comments = "comments";
		String beamLineOperator = "Solange WS java client";
		Integer visit_number = 2;
		Calendar recordTimeStamp = Calendar.getInstance();
		recordTimeStamp.set(2012, 10, 9, 14, 52, 55);
		Byte usedFlag = 1;
		String sessionTitle = "sessionTitle";
		Float structureDeterminations = (float) 0;
		Float dewarTransport = (float) 1;
		Float databackupFrance = (float) 2;
		Float databackupEurope = (float) 3;

		SessionWS3VO value = new SessionWS3VO();
		value.setSessionId(sessionId);
		value.setBeamLineSetupId(beamLineSetupId);
		value.setProposalId(proposalId);
		value.setProjectCode(projectCode);
		value.setStartDate(startDate);
		value.setEndDate(endDate);
		value.setBeamlineName(beamLineName);
		value.setScheduled(scheduled);
		value.setNbShifts(nbShifts);
		value.setComments(comments);
		value.setBeamlineOperator(beamLineOperator);
		//value.setVisit_number(visit_number);
		value.setTimeStamp(recordTimeStamp);
		value.setUsedFlag(usedFlag);
		value.setSessionTitle(sessionTitle);
		value.setStructureDeterminations(structureDeterminations);
		value.setDewarTransport(dewarTransport);
		value.setDatabackupFrance(databackupFrance);
		value.setDatabackupEurope(databackupEurope);

		ret = ws.storeOrUpdateSession(value);

		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");
		System.out.println("This is what I got as a response : sessionId = " + ret + "  \n");
		
		DataCollectionGroupWS3VO dataCollectionGroup = new DataCollectionGroupWS3VO();
		dataCollectionGroup.setExperimentType("OSC");
		dataCollectionGroup.setSessionId(ret);	
		dataCollectionGroup.setDataCollectionGroupId(null);
		ret = ws.storeOrUpdateDataCollectionGroup(dataCollectionGroup);
		
		System.out.println("This is what I got as a response : dataCollectionGroupId = " + ret + "  \n");

		DataCollectionWS3VO dataCollection = new DataCollectionWS3VO();
		dataCollection.setDataCollectionId(null);
		dataCollection.setDataCollectionGroupId(ret);
		dataCollection.setDetectorId(34);

		ret = ws.storeOrUpdateDataCollection(dataCollection);
		System.out.println("This is what I got as a response : dataCollectionId = " + ret + "  \n");
		
		ImageWS3VO image = new ImageWS3VO();
		image.setDataCollectionId(ret);
		image.setImageId(null);

		ret = ws.storeOrUpdateImage(image);
		System.out.println("This is what I got as a response : imageId = " + ret + "  \n");


	}
	private static void testStoreOrUpdateDataCollectionBatch ()throws Exception {
		System.out.println("*************** testStoreOrUpdateDataCollectionBatch ***************");
		for (int i = 0; i < 100; i++) {
			testStoreOrUpdateDataCollection();
		}
	}
	
	private static void testStoreOrUpdateDataCollection() throws Exception {
		System.out.println("*************** testStoreOrUpdateDataCollection ***************");
		Integer ret = -1;

		long startWS = System.currentTimeMillis();

		Integer dataCollectionId = null;// 1037306;// null;
		Integer dataCollectionGroupId = 19570;
		Integer strategySubWedgeOrigId = -1;
		Integer detectorId = 34;
		Integer blSubSampleId = null;
		Integer dataCollectionNumber = 1;
		Calendar startDate = Calendar.getInstance();
		// month is 0-based
		startDate.set(2016, 18, 9, 14, 50, 23);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2016, 18, 9, 15, 03, 32);
		String runStatus = "0";
		String rotationAxis = "Omega";
		Double phiStart = 0.1;
		Double kappaStart = 0.2;
		Double omegaStart = .03;
		Double axisStart = 0.1;
		Double axisEnd = 0.2;
		Double axisRange = .03;
		Double overlap = 0.4;
		Integer numberOfImages = 100;
		Integer startImageNumber = 1;
		Integer numberOfPasses = 1;
		Double exposureTime = 0.05;
		String imageDirectory = "imgDirectory";
		String imagePrefix = "imgPrefix1234567890imgPrefix1234567890imgPrefix1234567890imgPrefix1234567890imgPrefix1234567890";
		String imageSuffix = "imgSuffix";
		String fileTemplate = "fileTemplate";
		Double wavelength = 1.0;
		Double resolution = 0.02;
		Double resolutionAtCorner = 0.03;
		Double detectorDistance = 0.04;
		Double detector2Theta = 0.05;
		Double undulatorGap1 = 10.1;
		Double undulatorGap2 = 10.2;
		Double undulatorGap3 = 10.3;
		Double xbeam = 1.1;
		Double ybeam = 1.2;
		Double xbeamPix = 1.0;
		Double ybeamPix = 1.0;
		Double slitGapVertical = 0.1;
		Double slitGapHorizontal = 0.2;
		Double beamSizeAtSampleX = 0.3;
		Double beamSizeAtSampleY = 0.4;
		Double transmission = 0.5;
		String synchrotronMode = "synchrotron mode";
		String centeringMethod = "centering mode";
		Double averageTemperature = 0.0;
		String comments = "commentsSDSD";
		Byte printableForReport = 1;
		String xtalSnapshotFullPath1 = "";
		String xtalSnapshotFullPath2 = "";
		String xtalSnapshotFullPath3 = "";
		String xtalSnapshotFullPath4 = "";
		String beamShape = "";
		String actualCenteringPosition = "actualCenteringPosition";
		Double flux = new Double("198000000000");
		Double flux_end = new Double("197000000000");
		String bestWilsonPlotPath = "bestWilsonPlotPath";

		DataCollectionWS3VO dataCollection = new DataCollectionWS3VO();
		dataCollection.setDataCollectionId(dataCollectionId);
		dataCollection.setDataCollectionGroupId(dataCollectionGroupId);
		dataCollection.setStrategySubWedgeOrigId(strategySubWedgeOrigId);
		dataCollection.setDetectorId(detectorId);
		dataCollection.setBlSubSampleId(blSubSampleId);
		dataCollection.setDataCollectionNumber(dataCollectionNumber);
		dataCollection.setStartTime(startDate);
		dataCollection.setEndTime(endDate);
		dataCollection.setRunStatus(runStatus);
		dataCollection.setAxisStart(axisStart);
		dataCollection.setAxisEnd(axisEnd);
		dataCollection.setAxisRange(axisRange);
		dataCollection.setOverlap(overlap);
		dataCollection.setNumberOfImages(numberOfImages);
		dataCollection.setStartImageNumber(startImageNumber);
		dataCollection.setNumberOfPasses(numberOfPasses);
		dataCollection.setExposureTime(exposureTime);
		dataCollection.setImageDirectory(imageDirectory);
		dataCollection.setImagePrefix(imagePrefix);
		dataCollection.setImageSuffix(imageSuffix);
		dataCollection.setFileTemplate(fileTemplate);
		dataCollection.setWavelength(wavelength);
		dataCollection.setResolution(resolution);
		dataCollection.setDetectorDistance(detectorDistance);
		dataCollection.setXbeam(xbeam);
		dataCollection.setYbeam(ybeam);
		dataCollection.setXbeamPix(xbeamPix);
		dataCollection.setYbeamPix(ybeamPix);
		dataCollection.setComments(comments);
		dataCollection.setPrintableForReport(printableForReport);
		dataCollection.setSlitGapVertical(slitGapVertical);
		dataCollection.setSlitGapHorizontal(slitGapHorizontal);
		dataCollection.setTransmission(transmission);
		dataCollection.setSynchrotronMode(synchrotronMode);
		dataCollection.setXtalSnapshotFullPath1(xtalSnapshotFullPath1);
		dataCollection.setXtalSnapshotFullPath2(xtalSnapshotFullPath2);
		dataCollection.setXtalSnapshotFullPath3(xtalSnapshotFullPath3);
		dataCollection.setXtalSnapshotFullPath4(xtalSnapshotFullPath4);
		dataCollection.setRotationAxis(rotationAxis);
		dataCollection.setPhiStart(phiStart);
		dataCollection.setKappaStart(kappaStart);
		dataCollection.setOmegaStart(omegaStart);
		dataCollection.setResolutionAtCorner(resolutionAtCorner);
		dataCollection.setDetector2Theta(detector2Theta);
		dataCollection.setUndulatorGap1(undulatorGap1);
		dataCollection.setUndulatorGap2(undulatorGap2);
		dataCollection.setUndulatorGap3(undulatorGap3);
		dataCollection.setBeamSizeAtSampleX(beamSizeAtSampleX);
		dataCollection.setBeamSizeAtSampleY(beamSizeAtSampleY);
		dataCollection.setCenteringMethod(centeringMethod);
		dataCollection.setAverageTemperature(averageTemperature);
		dataCollection.setActualCenteringPosition(actualCenteringPosition);
		dataCollection.setBeamShape(beamShape);
		dataCollection.setFlux(flux);
		//dataCollection.setFlux_end(flux_end);
		dataCollection.setBestWilsonPlotPath(bestWilsonPlotPath);

		ret = ws.storeOrUpdateDataCollection(dataCollection);
		long endWS = System.currentTimeMillis();

		System.out.println("This is what I got as a response : dataCollectionId = " + ret + "  \n");
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		// foreign key null
		dataCollection.setDataCollectionGroupId(null);
		ret = ws.storeOrUpdateDataCollection(dataCollection);
		System.out.println("F This is what I got as a response : dataCollectionId = " + ret + "  \n");

	}

	private static void testUpdateDataCollectionStatus() throws Exception {
		System.out.println("*************** testUpdateDataCollectionStatus ***************");
		Integer ret = -1;
		Integer dataCollectionId = 1;//1037299;
		String runStatus = "Data collection successful";
		//ret = ws.updateDataCollectionStatus(dataCollectionId, runStatus);
		System.out.println("F This is what I got as a response : dataCollectionId = " + ret + "  \n");

	}

	private static void testFindDataCollectionFromFileLocationAndFileName() throws Exception {
		System.out.println("*************** testFindDataCollectionFromFileLocationAndFileName ***************");

		DataCollectionWS3VO dc = null;
		String fileLocation = "/data/visitor/mx1202/id23eh2/20111204/RAW_DATA/ZS/T123P-d123/181B4";
		String fileName = "181B4_9_0719.mccd";
		// String fileLocation = "/data/id14eh2/external/cyril/dntr160703";
		// String fileName = "daz_1_001.img";

		dc = ws.findDataCollectionFromFileLocationAndFileName(fileLocation, fileName);
		System.out.println("This is what I got as a response : dataCollection = " + dataCollectionToString(dc) + "  \n");
	}

	private static void testFindDataCollectionFromImageDirectoryAndImagePrefixAndNumber() throws Exception {
		System.out.println("*************** testFindDataCollectionFromImageDirectoryAndImagePrefixAndNumber ***************");

		DataCollectionWS3VO dc = null;

		String imageDirectory = "/data/id23eh1/inhouse/Pasha/data/visitor/mx1199/id29/20111205/RAW_DATA/4ESR372D11";
		String imagePrefix = "ref-4ESR372D11";
		Integer dataCollectionNumber = 1;

		dc = ws.findDataCollectionFromImageDirectoryAndImagePrefixAndNumber(imageDirectory, imagePrefix, dataCollectionNumber);
		System.out.println("This is what I got as a response : dataCollection = " + dataCollectionToString(dc) + "  \n");
	}

	private static void testStoreOrUpdateDataCollectionGroup() throws Exception {
		System.out.println("*************** testStoreOrUpdateDataCollectionGroup ***************");
		Integer ret = -1;

		long startWS = System.currentTimeMillis();
		
		DataCollectionGroupWS3VO value = new DataCollectionGroupWS3VO();
		//value.setSessionId(1); //local
		value.setSessionId(1120);//valid or prod
		value.setExperimentType("OSC");

//		Integer dataCollectionGroupId = 1023048;// null;
//		Integer sessionId = 33001;
//		Integer blSampleId = null;
//		Integer workflowId = null;
//		Calendar startTime = Calendar.getInstance();
//		Calendar endTime = Calendar.getInstance();
//		String experimentType = "SAD";
//		String comments = "this is a new one !";
//		String actualSampleBarcode = "actualSampleBarcode";
//		Integer actualSampleSlotInContainer = -1;
//		String actualContainerBarcode = "actualContainerBarcode";
//		Integer actualContainerSlotInSC = 3;
//
//		dataCollectionGroupId =1355639;
//		comments = "this is a NOT new one ! just updated";
//		value.setDataCollectionGroupId(dataCollectionGroupId);
//		value.setSessionId(sessionId);
//		value.setBlSampleId(blSampleId);
//		value.setWorkflowId(workflowId);
//		value.setStartTime(startTime);
//		value.setEndTime(endTime);
//		value.setExperimentType(experimentType);
//		value.setComments(comments);
//		value.setActualSampleBarcode(actualSampleBarcode);
//		value.setActualSampleSlotInContainer(actualSampleSlotInContainer);
//		value.setActualContainerBarcode(actualContainerBarcode);
//		value.setActualContainerSlotInSC(actualContainerSlotInSC);

		ret = ws.storeOrUpdateDataCollectionGroup(value);

		long endWS = System.currentTimeMillis();

		System.out.println("This is what I got as a response : dataCollectionGroupId = " + ret + "  \n");
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		 // update
//		 value.setDataCollectionGroupId(1037444);
//		 startTime.set(2011, 10, 9, 15, 03, 32);
//		 endTime.set(2011, 10, 9, 15, 05, 56);
//		 value.setStartTime(startTime);
//		 value.setEndTime(endTime);
//		
//		 ret = ws.storeOrUpdateDataCollectionGroup(value);
//		 System.out.println("This is what I got as a response : dataCollectionGroupId = " + ret + "  \n");
		
		 // foreign key null
		 value.setSessionId(null);
		 ret = ws.storeOrUpdateDataCollectionGroup(value);
		 System.out.println("F This is what I got as a response : dataCollectionGroupId = " + ret + "  \n");

	}

	private static void testFindDataCollection() throws Exception {
		System.out.println("*************** testFindDataCollection ***************");

		DataCollectionWS3VO dc = null;
		Integer dataCollectionId = 1;//976523;

		dc = ws.findDataCollection(dataCollectionId);
		System.out.println("This is what I got as a response : dataCollection = " + dataCollectionToString(dc) + "  \n");
	}

	private static void testStoreOrUpdateXFEFluorescenceSpectrum() throws Exception {
		System.out.println("*************** testStoreOrUpdateXFEFluorescenceSpectrum ***************");
		Integer ret = -1;
		long startWS = System.currentTimeMillis();

		Integer xfeFluorescenceSpectrumId = null;
		Integer sessionId = 31533;//1
		Integer blSampleId = null;
		String fittedDataFileFullPath = null;
		String scanFileFullPath = null;
		String jpegScanFileFullPath = "/full/path/to/jpeg";
		Calendar startTime = Calendar.getInstance();
		startTime.set(2012, 10, 9, 14, 50, 23);
		Calendar endTime = Calendar.getInstance();
		endTime.set(2012, 10, 9, 14, 52, 55);
		String filename = "filenameSD";
		Float energy = (float) 12;
		Float exposureTime = (float) 1;
		Float beamTransmission = (float) 3;
		String annotatedPymcaXfeSpectrum = "html-name";
		Float beamSizeVertical = (float) 0.0;
		Float beamSizeHorizontal = (float) 0.0;
		String crystalClass = null;
		String comments = "comments";
		Double flux = new Double("198000000000");
		Double flux_end = new Double("197000000000");

		XfeFluorescenceSpectrumWS3VO value = new XfeFluorescenceSpectrumWS3VO();
		value.setXfeFluorescenceSpectrumId(xfeFluorescenceSpectrumId);
		value.setSessionId(sessionId);
		value.setBlSampleId(blSampleId);
		value.setFittedDataFileFullPath(fittedDataFileFullPath);
		value.setScanFileFullPath(scanFileFullPath);
		value.setJpegScanFileFullPath(jpegScanFileFullPath);
		value.setStartTime(startTime);
		value.setEndTime(endTime);
		value.setFilename(filename);
		value.setEnergy(energy);
		value.setExposureTime(exposureTime);
		value.setBeamTransmission(beamTransmission);
		value.setAnnotatedPymcaXfeSpectrum(annotatedPymcaXfeSpectrum);
		value.setBeamSizeVertical(beamSizeVertical);
		value.setBeamSizeHorizontal(beamSizeHorizontal);
		value.setCrystalClass(crystalClass);
		value.setComments(comments);
		value.setFlux(flux_end);
		//value.setFlux_end(flux_end);

		ret = ws.storeOrUpdateXFEFluorescenceSpectrum(value);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");
		System.out.println("This is what I got as a response : xfeFluorescenceSpectrum = " + ret + "  \n");

		// foreign key null
		value.setSessionId(null);
		ret = ws.storeOrUpdateXFEFluorescenceSpectrum(value);
		System.out.println("This is what I got as a response : xfeFluorescenceSpectrum = " + ret + "  \n");
	}

	private static void testStoreOrUpdateEnergyScan() throws Exception {
		System.out.println("*************** testStoreOrUpdateEnergyScan ***************");
		Integer ret = -1;
		long startWS = System.currentTimeMillis();

		Integer energyScanId = null;//12878 test one
		Integer sessionId = 98;//1
		Integer sampleId = null; // 3223
		String fluorescenceDetector = "Fluo Detector";
		String scanFileFullPath = "scanFileFullPath";
		String choochFileFullPath = "choochFileFullPath";
		String jpegChoochFileFullPath = "jpegChoochFileFullPath";
		String element = "elementSD";
		Double startEnergy = 12.633;
		Double endEnergy = 12.733;
		Double transmissionFactor = 0.340823;
		Double exposureTime = 100.0;
		Double synchrotronCurrent = 0.5;
		Double temperature = 0.6;
		Double peakEnergy = 12.6603;
		Double peakFPrime = -7.47318;
		Double peakFDoublePrime = 5.2424;
		Double inflectionEnergy = 12.6584;
		Double inflectionFPrime = -9.81854;
		Double inflectionFDoublePrime = 3.71495;
		Double xrayDose = 0.04;
		Calendar startTime = Calendar.getInstance();
		startTime.set(2012, 10, 24, 9, 25, 34);
		Calendar endTime = Calendar.getInstance();
		endTime.set(2012, 10, 24, 9, 28, 8);
		String edgeEnergy = "K";
		String filename = "filename";
		Float beamSizeVertical = (float) 30;
		Float beamSizeHorizontal = (float) 80;
		String crystalClass = "";
		String comments = "comments";
		Double flux = new Double("198000000000");
		Double flux_end = new Double("197000000000");

		EnergyScanWS3VO value = new EnergyScanWS3VO();
		value.setEnergyScanId(energyScanId);
		value.setSessionId(sessionId);
		value.setBlSampleId(sampleId);
		value.setFluorescenceDetector(fluorescenceDetector);
		value.setScanFileFullPath(scanFileFullPath);
		value.setChoochFileFullPath(choochFileFullPath);
		value.setJpegChoochFileFullPath(jpegChoochFileFullPath);
		value.setElement(element);
		value.setStartEnergy(startEnergy);
		value.setEndEnergy(endEnergy);
		value.setTransmissionFactor(transmissionFactor);
		value.setExposureTime(exposureTime);
		value.setSynchrotronCurrent(synchrotronCurrent);
		value.setTemperature(temperature);
		value.setPeakEnergy(peakEnergy);
		value.setPeakFPrime(peakFPrime);
		value.setPeakFDoublePrime(peakFDoublePrime);
		value.setInflectionEnergy(inflectionEnergy);
		value.setInflectionFPrime(inflectionFPrime);
		value.setInflectionFDoublePrime(inflectionFDoublePrime);
		value.setXrayDose(xrayDose);
		value.setStartTime(startTime);
		value.setEndTime(endTime);
		value.setEdgeEnergy(edgeEnergy);
		value.setFilename(filename);
		value.setBeamSizeVertical(beamSizeVertical);
		value.setBeamSizeHorizontal(beamSizeHorizontal);
		value.setCrystalClass(crystalClass);
		value.setComments(comments);
		value.setFlux(flux_end);
		//value.setFlux_end(flux_end);

		ret = ws.storeOrUpdateEnergyScan(value);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		System.out.println("This is what I got as a response : energyScanId = " + ret + "  \n");

		// foreign key null
		value.setSessionId(null);
		ret = ws.storeOrUpdateEnergyScan(value);
		System.out.println("F This is what I got as a response : energyScanId = " + ret + "  \n");

	}

	private static void testStoreOrUpdateImage() throws Exception {
		System.out.println("*************** testStoreOrUpdateImage ***************");
		Integer ret = -1;
		long startWS = System.currentTimeMillis();

		Integer imageId = null;
		Integer dataCollectionId = 19570;//1
		Integer imageNumber = 2;
		String fileName = "fileNamefileNamefileNamefileNamefileNamefileNamefileNamefileNamefileName";
		String fileLocation = "/data/visitor/mx1378/id23eh2/20120906/RAW_DATA/AY/S50bcg_12";
		Float measuredIntensity = (float) 0.1;
		String jpegFileFullPath = "jpegFileFullPath";
		String jpegThumbnailFileFullPath = "jpegThumbnailFileFullPath";
		Float temperature = (float) 0.2;
		Float cumulativeIntensity = (float) 0.3;
		Float synchrotronCurrent = (float) 0.5;
		String comments = "";
		String machineMessage = "machineMessage";

		ImageWS3VO value = new ImageWS3VO();
		value.setImageId(imageId);
		value.setDataCollectionId(dataCollectionId);
		value.setImageNumber(imageNumber);
		value.setFileName(fileName);
		value.setFileLocation(fileLocation);
		value.setMeasuredIntensity(measuredIntensity);
		value.setJpegFileFullPath(jpegFileFullPath);
		value.setJpegThumbnailFileFullPath(jpegThumbnailFileFullPath);
		value.setTemperature(temperature);
		value.setCumulativeIntensity(cumulativeIntensity);
		value.setSynchrotronCurrent(synchrotronCurrent);
		value.setComments(comments);
		value.setMachineMessage(machineMessage);

		ret = ws.storeOrUpdateImage(value);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		System.out.println("This is what I got as a response : imageId  = " + ret + "  \n");

		// foreign key null
		value.setDataCollectionId(null);
		ret = ws.storeOrUpdateImage(value);
		System.out.println("This is what I got as a response : imageId  = " + ret + "  \n");

	}

	public Integer storeOrUpdateBeamLineSetup() throws Exception {
		System.out.println("*************** storeOrUpdateBeamLineSetup ***************");
		Integer ret = -1;
		long startWS = System.currentTimeMillis();

		Integer beamLineSetupId = null;
		String synchrotronMode = "synchrotronMode";
		String undulatorType1 = "undulatorType1SD";
		String undulatorType2 = "undulatorType2";
		String undulatorType3 = "undulatorType3";
		Double focalSpotSizeAtSample = 0.3;
		String focusingOptic = "focusingOptic";
		Double beamDivergenceHorizontal = 0.4;
		Double beamDivergenceVertical = 0.5;
		Double polarisation = 0.6;
		String monochromatorType = "monochromatorType";
		Calendar setupDate = Calendar.getInstance();
		String synchrotronName = "synchrotronName";
		Double maxExpTimePerDataCollection = 0.03;
		Double minExposureTimePerImage = 0.04;
		Double goniostatMaxOscillationSpeed = 0.05;
		Double goniostatMinOscillationWidth = 0.06;
		Double minTransmission = 0.07;

		BeamLineSetup3VO value = new BeamLineSetup3VO();
		value.setBeamLineSetupId(beamLineSetupId);
		value.setSynchrotronMode(synchrotronMode);
		value.setUndulatorType1(undulatorType1);
		value.setUndulatorType2(undulatorType2);
		value.setUndulatorType3(undulatorType3);
		value.setFocalSpotSizeAtSample(focalSpotSizeAtSample);
		value.setFocusingOptic(focusingOptic);
		value.setBeamDivergenceHorizontal(beamDivergenceHorizontal);
		value.setBeamDivergenceVertical(beamDivergenceVertical);
		value.setPolarisation(polarisation);
		value.setMonochromatorType(monochromatorType);
		value.setSetupDate(setupDate);
		value.setSynchrotronName(synchrotronName);
		value.setMaxExpTimePerDataCollection(maxExpTimePerDataCollection);
		value.setMinExposureTimePerImage(minExposureTimePerImage);
		value.setGoniostatMaxOscillationSpeed(goniostatMaxOscillationSpeed);
		value.setGoniostatMinOscillationWidth(goniostatMinOscillationWidth);
		value.setMinTransmission(minTransmission);

		ret = ws.storeOrUpdateBeamLineSetup(value);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		return ret;
	}

	public Integer storeOrUpdateSession(Integer proposalId, Integer beamLineSetupId) throws Exception {
		System.out.println("*************** storeOrUpdateSession ***************");
		Integer ret = -1;
		long startWS = System.currentTimeMillis();

		Integer sessionId = 32509; // null;
		String projectCode = "projectCode";
		Calendar startDate = Calendar.getInstance();
		startDate.set(2012, 10, 9, 14, 50, 23);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2012, 10, 9, 14, 52, 55);
		String beamLineName = "ID29";
		Byte scheduled = 1;
		Integer nbShifts = 1;
		String comments = "comments SD";
		String beamLineOperator = "BODIN M";
		Integer visit_number = 2;
		Calendar recordTimeStamp = Calendar.getInstance();
		recordTimeStamp.set(2012, 10, 9, 14, 52, 55);
		Byte usedFlag = 1;
		String sessionTitle = "sessionTitle";
		Float structureDeterminations = (float) 0;
		Float dewarTransport = (float) 1;
		Float databackupFrance = (float) 2;
		Float databackupEurope = (float) 3;

		SessionWS3VO value = new SessionWS3VO();
		value.setSessionId(sessionId);
		value.setBeamLineSetupId(beamLineSetupId);
		value.setProposalId(proposalId);
		value.setProjectCode(projectCode);
		value.setStartDate(startDate);
		value.setEndDate(endDate);
		value.setBeamlineName(beamLineName);
		value.setScheduled(scheduled);
		value.setNbShifts(nbShifts);
		value.setComments(comments);
		value.setBeamlineOperator(beamLineOperator);
		//value.setVisit_number(visit_number);
		value.setTimeStamp(recordTimeStamp);
		value.setUsedFlag(usedFlag);
		value.setSessionTitle(sessionTitle);
		value.setStructureDeterminations(structureDeterminations);
		value.setDewarTransport(dewarTransport);
		value.setDatabackupFrance(databackupFrance);
		value.setDatabackupEurope(databackupEurope);

		ret = ws.storeOrUpdateSession(value);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		return ret;
	}

	public Integer storeOrUpdateDataCollectionGroup(Integer sessionId, Integer blSampleId) throws Exception {
		System.out.println("*************** storeOrUpdateDataCollectionGroup ***************");
		Integer ret = -1;
		long startWS = System.currentTimeMillis();

		Integer dataCollectionGroupId = 1023048; // null;//1
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		String experimentType = "SAD";
		String comments = "comments SD";
		String actualSampleBarcode = "actualSampleBarcode";
		Integer actualSampleSlotInContainer = 0;
		String actualContainerBarcode = "actualContainerBarcode";
		Integer actualContainerSlotInSC = 1;

		DataCollectionGroupWS3VO value = new DataCollectionGroupWS3VO();
		value.setDataCollectionGroupId(dataCollectionGroupId);
		value.setSessionId(sessionId);
		value.setBlSampleId(blSampleId);
		value.setStartTime(startTime);
		value.setEndTime(endTime);
		value.setExperimentType(experimentType);
		value.setComments(comments);
		value.setActualSampleBarcode(actualSampleBarcode);
		value.setActualSampleSlotInContainer(actualSampleSlotInContainer);
		value.setActualContainerBarcode(actualContainerBarcode);
		value.setActualContainerSlotInSC(actualContainerSlotInSC);

		ret = ws.storeOrUpdateDataCollectionGroup(value);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		return ret;
	}

	public Integer storeOrUpdateDataCollection(Integer dataCollectionGroupId) throws Exception {
		System.out.println("*************** storeOrUpdateDataCollection ***************");
		Integer ret = -1;
		long startWS = System.currentTimeMillis();

		Integer dataCollectionId = null;
		Integer strategySubWedgeOrigId = null;
		Integer detectorId = null;
		Integer dataCollectionNumber = 1;
		Calendar startDate = Calendar.getInstance();
		// month is 0-based
		startDate.set(2011, 10, 9, 14, 50, 23);
		Calendar endDate = Calendar.getInstance();
		endDate.set(2011, 10, 9, 15, 03, 32);
		String runStatus = "0";
		String rotationAxis = "Omega";
		Double phiStart = 0.1;
		Double kappaStart = 0.2;
		Double omegaStart = .03;
		Double axisStart = 0.1;
		Double axisEnd = 0.2;
		Double axisRange = .03;
		Double overlap = 0.4;
		Integer numberOfImages = 100;
		Integer startImageNumber = 1;
		Integer numberOfPasses = 1;
		Double exposureTime = 0.05;
		String imageDirectory = "imgDirectory";
		String imagePrefix = "imgPrefix";
		String imageSuffix = "imgSuffix";
		String fileTemplate = "fileTemplate";
		Double wavelength = 1.0;
		Double resolution = 0.02;
		Double resolutionAtCorner = 0.03;
		Double detectorDistance = 0.04;
		Double detector2Theta = 0.05;
		Double undulatorGap1 = 10.1;
		Double undulatorGap2 = 10.2;
		Double undulatorGap3 = 10.3;
		Double xbeam = 1.1;
		Double ybeam = 1.2;
		Double slitGapVertical = 0.1;
		Double slitGapHorizontal = 0.2;
		Double beamSizeAtSampleX = 0.3;
		Double beamSizeAtSampleY = 0.4;
		Double transmission = 0.5;
		String synchrotronMode = "synchrotron mode";
		String centeringMethod = "centering mode";
		Double averageTemperature = 0.0;
		String comments = "comments SD";
		Byte printableForReport = 1;
		String xtalSnapshotFullPath1 = "";
		String xtalSnapshotFullPath2 = "";
		String xtalSnapshotFullPath3 = "";
		String xtalSnapshotFullPath4 = "";
		String beamShape = "";
		String actualCenteringPosition = "actualCenteringPosition";
		Double flux = 1.1;
		Double flux_end = 1.2;

		DataCollectionWS3VO dataCollection = new DataCollectionWS3VO();
		dataCollection.setDataCollectionId(dataCollectionId);
		dataCollection.setDataCollectionGroupId(dataCollectionGroupId);
		dataCollection.setStrategySubWedgeOrigId(strategySubWedgeOrigId);
		dataCollection.setDetectorId(detectorId);
		dataCollection.setDataCollectionNumber(dataCollectionNumber);
		dataCollection.setStartTime(startDate);
		dataCollection.setEndTime(endDate);
		dataCollection.setRunStatus(runStatus);
		dataCollection.setAxisStart(axisStart);
		dataCollection.setAxisEnd(axisEnd);
		dataCollection.setAxisRange(axisRange);
		dataCollection.setOverlap(overlap);
		dataCollection.setNumberOfImages(numberOfImages);
		dataCollection.setStartImageNumber(startImageNumber);
		dataCollection.setNumberOfPasses(numberOfPasses);
		dataCollection.setExposureTime(exposureTime);
		dataCollection.setImageDirectory(imageDirectory);
		dataCollection.setImagePrefix(imagePrefix);
		dataCollection.setImageSuffix(imageSuffix);
		dataCollection.setFileTemplate(fileTemplate);
		dataCollection.setWavelength(wavelength);
		dataCollection.setResolution(resolution);
		dataCollection.setDetectorDistance(detectorDistance);
		dataCollection.setXbeam(xbeam);
		dataCollection.setYbeam(ybeam);
		dataCollection.setComments(comments);
		dataCollection.setPrintableForReport(printableForReport);
		dataCollection.setSlitGapVertical(slitGapVertical);
		dataCollection.setSlitGapHorizontal(slitGapHorizontal);
		dataCollection.setTransmission(transmission);
		dataCollection.setSynchrotronMode(synchrotronMode);
		dataCollection.setXtalSnapshotFullPath1(xtalSnapshotFullPath1);
		dataCollection.setXtalSnapshotFullPath2(xtalSnapshotFullPath2);
		dataCollection.setXtalSnapshotFullPath3(xtalSnapshotFullPath3);
		dataCollection.setXtalSnapshotFullPath4(xtalSnapshotFullPath4);
		dataCollection.setRotationAxis(rotationAxis);
		dataCollection.setPhiStart(phiStart);
		dataCollection.setKappaStart(kappaStart);
		dataCollection.setOmegaStart(omegaStart);
		dataCollection.setResolutionAtCorner(resolutionAtCorner);
		dataCollection.setDetector2Theta(detector2Theta);
		dataCollection.setUndulatorGap1(undulatorGap1);
		dataCollection.setUndulatorGap2(undulatorGap2);
		dataCollection.setUndulatorGap3(undulatorGap3);
		dataCollection.setBeamSizeAtSampleX(beamSizeAtSampleX);
		dataCollection.setBeamSizeAtSampleY(beamSizeAtSampleY);
		dataCollection.setCenteringMethod(centeringMethod);
		dataCollection.setAverageTemperature(averageTemperature);
		dataCollection.setActualCenteringPosition(actualCenteringPosition);
		dataCollection.setBeamShape(beamShape);
		dataCollection.setFlux(flux);
		//dataCollection.setFlux_end(flux_end);

		ret = ws.storeOrUpdateDataCollection(dataCollection);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		return ret;
	}

	public Integer storeOrUpdateImage(Integer dataCollectionId, Integer imageNumber) throws Exception {
		Integer ret = -1;
		long startWS = System.currentTimeMillis();
		Integer imageId = null;
		String fileName = "fileName";
		String fileLocation = "fileLocation";
		Float measuredIntensity = (float) 0.1;
		String jpegFileFullPath = "jpegFileFullPath";
		String jpegThumbnailFileFullPath = "jpegThumbnailFileFullPath";
		Float temperature = (float) 0.2;
		Float cumulativeIntensity = (float) 0.3;
		Float synchrotronCurrent = (float) 0.5;
		String comments = "";
		String machineMessage = "machineMessage";

		ImageWS3VO value = new ImageWS3VO();
		value.setImageId(imageId);
		value.setDataCollectionId(dataCollectionId);
		value.setImageNumber(imageNumber);
		value.setFileName(fileName);
		value.setFileLocation(fileLocation);
		value.setMeasuredIntensity(measuredIntensity);
		value.setJpegFileFullPath(jpegFileFullPath);
		value.setJpegThumbnailFileFullPath(jpegThumbnailFileFullPath);
		value.setTemperature(temperature);
		value.setCumulativeIntensity(cumulativeIntensity);
		value.setSynchrotronCurrent(synchrotronCurrent);
		value.setComments(comments);
		value.setMachineMessage(machineMessage);

		ret = ws.storeOrUpdateImage(value);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		return ret;
	}

	private static void testFindDetectorByParam() throws Exception {
		System.out.println("*************** testFindDetectorByParam ***************");
		Detector3VO detector = null;
		String detectorType = "";
		String detectorManufacturer = "adsc";
		String detectorModel = "q210";
		String detectorMode = "unbinned";

		detector = ws.findDetectorByParam(detectorType, detectorManufacturer, detectorModel, detectorMode);
		System.out.println("This is what I got as a response : detector = " + detectorToString(detector) + "  \n");

		detectorManufacturer = "adsc";
		detectorModel = "q210";
		detectorMode = "hardware binned";

		detector = ws.findDetectorByParam(detectorType, detectorManufacturer, detectorModel, detectorMode);
		System.out.println("This is what I got as a response : detector = " + detectorToString(detector) + "  \n");

		detectorManufacturer = "adsc";
		detectorModel = "q315r";
		detectorMode = "unbinned";

		detector = ws.findDetectorByParam(detectorType, detectorManufacturer, detectorModel, detectorMode);
		System.out.println("This is what I got as a response : detector = " + detectorToString(detector) + "  \n");

		detectorManufacturer = "adsc";
		detectorModel = "q315r";
		detectorMode = "hardware binned";

		detector = ws.findDetectorByParam(detectorType, detectorManufacturer, detectorModel, detectorMode);
		System.out.println("This is what I got as a response : detector = " + detectorToString(detector) + "  \n");

		detectorManufacturer = "adsc";
		detectorModel = "q4";
		detectorMode = "unbinned";

		detector = ws.findDetectorByParam(detectorType, detectorManufacturer, detectorModel, detectorMode);
		System.out.println("This is what I got as a response : detector = " + detectorToString(detector) + "  \n");

		detectorManufacturer = "adsc";
		detectorModel = "q4";
		detectorMode = "hardware binned";

		detector = ws.findDetectorByParam(detectorType, detectorManufacturer, detectorModel, detectorMode);
		System.out.println("This is what I got as a response : detector = " + detectorToString(detector) + "  \n");

		detectorManufacturer = "mar";
		detectorModel = "mar225";
		detectorMode = "unbinned";

		detector = ws.findDetectorByParam(detectorType, detectorManufacturer, detectorModel, detectorMode);
		System.out.println("This is what I got as a response : detector = " + detectorToString(detector) + "  \n");

		detectorManufacturer = "mar";
		detectorModel = "mar225";
		detectorMode = "hardware binned";

		detector = ws.findDetectorByParam(detectorType, detectorManufacturer, detectorModel, detectorMode);
		System.out.println("This is what I got as a response : detector = " + detectorToString(detector) + "  \n");

		detectorManufacturer = "dectris";
		detectorModel = "6M_F";
		detectorMode = "";

		detector = ws.findDetectorByParam(detectorType, detectorManufacturer, detectorModel, detectorMode);
		System.out.println("This is what I got as a response : detector = " + detectorToString(detector) + "  \n");

		detectorManufacturer = "dectris";
		detectorModel = "1M";
		detectorMode = "";

		detector = ws.findDetectorByParam(detectorType, detectorManufacturer, detectorModel, detectorMode);
		System.out.println("This is what I got as a response : detector = " + detectorToString(detector) + "  \n");
	}

	public static void testGetXDSInfo() throws Exception {
		System.out.println("*************** testGetXDSInfo ***************");

		Integer dataCollectionId = 1246584;//1
		XdsInfo xds = null;
		xds = ws.getXDSInfo(dataCollectionId);
		System.out.println("This is what I got as a response : dataCollection: axisRange=" + xds.getAxisRange() + ", axisStart = "
				+ xds.getAxisStart() + ", axisEnd = " + xds.getAxisEnd() + ", detectorDistance=" + xds.getDetectorDistance()
				+ ", fileTemplate= " + xds.getFileTemplate() + ", iamgeDirectory=" + xds.getImageDirectory() + ", imageSuffix="
				+ xds.getImageSuffix() + ", numberOfImages=" + xds.getNumberOfImages() + ", phiStart =" + xds.getPhiStart()
				+ ", kappaStart=" + xds.getKappaStart() + ", startImageNumber=" + xds.getStartImageNumber() + ", wavelength="
				+ xds.getWavelength() + ", xbeam = " + xds.getXbeam() + ", ybeam = " + xds.getYbeam()
				+ "\nBeamLineSetup polarisation= " + xds.getPolarisation() + "\nDetector: detectorPixelSizeHorizontal="
				+ xds.getDetectorPixelSizeHorizontal() + ", detectorPixelSizeVertical= " + xds.getDetectorPixelSizeVertical()
				+ ", detectorModel= " + xds.getDetectorModel() + ", detectorManufacturer=" + xds.getDetectorManufacturer()
				+ " \n\n");
	}

	public static void testGetPdbFilePath() throws Exception {
		System.out.println("*************** testGetPdbFilePath ***************");

		Integer dataCollectionId = 1;//971155;
		String pdbFilePath = ws.getPdbFilePath(dataCollectionId);
		System.out.println("This is what I got as a response : pdb filePath : " + pdbFilePath + " \n");

		dataCollectionId = 2;//1056134;
		pdbFilePath = ws.getPdbFilePath(dataCollectionId);
		System.out.println("This is what I got as a response : pdb filePath : " + pdbFilePath + " \n");
	}

	private static void testStoreOrUpdatePosition() throws Exception {
		System.out.println("*************** testStoreOrUpdatePosition ***************");
		Integer ret = -1;
		long startWS = System.currentTimeMillis();

		Integer positionId = null;
		Integer relativePositionId = null;
		Double posX = 0.1;
		Double posY = 0.2;
		Double posZ = 0.3;
		Double scale = 0.4;

		PositionWS3VO value = new PositionWS3VO();
		value.setPositionId(positionId);
		value.setRelativePositionId(relativePositionId);
		value.setPosX(posX);
		value.setPosY(posY);
		value.setPosZ(posZ);
		value.setScale(scale);

		ret = ws.storeOrUpdatePosition(value);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		System.out.println("This is what I got as a response : positionId  = " + ret + "  \n");

	}

	private static void testStoreOrUpdateWorkflow() throws Exception {
		System.out.println("*************** testStoreOrUpdateWorkflow ***************");
		Integer ret = -1;
		long startWS = System.currentTimeMillis();

		Integer workflowId = null;
		String workflowTitle = "workflowTitle";
		String workflowType = "MXPressO";
		String comments = "comments";
		String status = "status";
		String resultFilePath = "resultFilePath";
		String logFilePath = "logFilePath";

		Workflow3VO value = new Workflow3VO();
		value.setWorkflowId(workflowId);
		value.setWorkflowTitle(workflowTitle);
		value.setWorkflowType(workflowType);
		value.setComments(comments);
		value.setStatus(status);
		value.setResultFilePath(resultFilePath);
		value.setLogFilePath(logFilePath);

		ret = ws.storeOrUpdateWorkflow(value);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		System.out.println("This is what I got as a response : workflowId  = " + ret + "  \n");

	}

	private static void testUpdateDataCollectionGroupWorkflowId() throws Exception {
		System.out.println("*************** testUpdateDataCollectionGroupWorkflowId ***************");
		Integer ret = -1;
		long startWS = System.currentTimeMillis();

		// imgId = 43389167 => dc = 1095173 => dcgId = 1095427
		String fileLocation = "/data/id14eh4/inhouse/opid144/20130319/RAW_DATA/1-2/discrete-1/";
		String fileName = "opid144_2_0005.img";
		Integer workflowId = 1;

		ret = ws.updateDataCollectionGroupWorkflowId(fileLocation, fileName, workflowId);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		System.out.println("This is what I got as a response : dataCollectionGroupId  = " + ret + "  \n");

	}

//	private static void testGroupDataCollections() throws Exception {
//		System.out.println("*************** testGroupDataCollections ***************");
//		int[] ret;
//		long startWS = System.currentTimeMillis();
//
//		// imgId = 43389167 => dc = 1095173 => dcgId = 1095427
//		// imgId = 43389166 => dc = 1095173 => dcgId = 1095427
//		// imgId = 43389140 => dc = 1095168 => dcgId = 1095422
//		String[] arrayOfFileLocation = new String[3];
//		int i = 0;
//		arrayOfFileLocation[i++] = "/data/id14eh4/inhouse/opid144/20130319/RAW_DATA/1-2/discrete-1";
//		arrayOfFileLocation[i++] = "/data/id14eh4/inhouse/opid144/20130319/RAW_DATA/1-2/discrete-1";
//		arrayOfFileLocation[i++] = "/data/id14eh4/inhouse/opid144/20130318/RAW_DATA/1-1/discrete-1";
//		String[] arrayOfFileName = new String[3];
//		int j = 0;
//		arrayOfFileName[j++] = "opid144_2_0005.img";
//		arrayOfFileName[j++] = "opid144_2_0004.img";
//		arrayOfFileName[j++] = "opid144_1_0003.img";
//
//		Integer dataCollectionGroupId = 1095413;
//
//		ret = ws.groupDataCollections(dataCollectionGroupId, arrayOfFileLocation, arrayOfFileName);
//		long endWS = System.currentTimeMillis();
//		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");
//
//		String t = "[";
//		if (ret != null) {
//			for (int k = 0; k < ret.length; k++) {
//				t += ret[k] + ", ";
//			}
//		}
//		t += "]";
//		System.out.println("This is what I got as a response : dataCollectionIds  = " + t + "  \n");
//		// restore
//		arrayOfFileLocation = new String[2];
//		i = 0;
//		arrayOfFileLocation[i++] = "/data/id14eh4/inhouse/opid144/20130319/RAW_DATA/1-2/discrete-1";
//		arrayOfFileLocation[i++] = "/data/id14eh4/inhouse/opid144/20130319/RAW_DATA/1-2/discrete-1";
//
//		arrayOfFileName = new String[2];
//		j = 0;
//		arrayOfFileName[j++] = "opid144_2_0005.img";
//		arrayOfFileName[j++] = "opid144_2_0004.img";
//
//		dataCollectionGroupId = 1095427;
//		ret = ws.groupDataCollections(dataCollectionGroupId, arrayOfFileLocation, arrayOfFileName);
//
//		arrayOfFileLocation = new String[1];
//		i = 0;
//		arrayOfFileLocation[i++] = "/data/id14eh4/inhouse/opid144/20130318/RAW_DATA/1-1/discrete-1";
//		arrayOfFileName = new String[1];
//		j = 0;
//		arrayOfFileName[j++] = "opid144_1_0003.img";
//
//		dataCollectionGroupId = 1095422;
//		ret = ws.groupDataCollections(dataCollectionGroupId, arrayOfFileLocation, arrayOfFileName);
//
//	}

	private static void testUpdateWorkflowStatus() throws Exception {
		System.out.println("*************** testUpdateWorkflowStatus ***************");
		Integer ret = -1;
		Integer workflowId = 2;
		String status = "Failed!";
		ret = ws.updateWorkflowStatus(workflowId, status);
		System.out.println("F This is what I got as a response : workflowId = " + ret + "  \n");

	}

	private static void testSetDataCollectionPosition() throws Exception {
		System.out.println("*************** testSetDataCollectionPosition ***************");
		long startWS = System.currentTimeMillis();

		Integer motorPositionId = null;
		Double phiX = 0.1;
		Double phiY = 0.2;
		Double phiZ = 0.3;
		Double sampX = 0.4;
		Double sampY = 0.5;
		Double omega = 0.6;
		Double kappa = 0.7;
		Double phi = 0.8;
		Integer gridIndexY = 1;
		Integer gridIndexZ = 2;

		String fileLocation = "/data/id14eh4/inhouse/opid144/20130319/RAW_DATA/1-2/discrete-1";
		String fileName = "opid144_2_0005.img";

		Integer ret = ws.setDataCollectionPosition(fileLocation, fileName);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		System.out.println("This is what I got as a response : dataCollection  = " + ret + "  \n");

	}

	private static void testStoreOrUpdateGridInfo() throws Exception {
		System.out.println("*************** testStoreOrUpdateGridInfo ***************");
		Integer ret = -1;
		long startWS = System.currentTimeMillis();

		Integer gridInfoId = null;
		Integer workflowMeshId = 1;
		Double xOffset = 0.1;
		Double yOffset = 0.2;
		Double dx_mm = 0.3;
		Double dy_mm = 0.4;
		Double steps_x = 0.5;
		Double steps_y = 0.6;
		Double meshAngle = 0.7;

		GridInfoWS3VO value = new GridInfoWS3VO();
		value.setGridInfoId(gridInfoId);
		value.setWorkflowMeshId(workflowMeshId);
		value.setXOffset(xOffset);
		value.setYOffset(yOffset);
		//value.setDx_mm(dx_mm);
		//value.setDy_mm(dy_mm);
		//value.setSteps_x(steps_x);
		//value.setSteps_y(steps_y);
		value.setMeshAngle(meshAngle);

		ret = ws.storeOrUpdateGridInfo(value);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		System.out.println("This is what I got as a response : gridInfoId  = " + ret + "  \n");

		// foreign key null
		value.setWorkflowMeshId(null);
		ret = ws.storeOrUpdateGridInfo(value);
		System.out.println("F This is what I got as a response : gridInfoId = " + ret + "  \n");
	}

	private static void testSetDataCollectionsPositions() throws Exception {
		System.out.println("*************** testSetDataCollectionsPositions ***************");
		long startWS = System.currentTimeMillis();

		Integer motorPositionId = null;
		Double phiX = 0.1;
		Double phiY = 0.2;
		Double phiZ = 0.3;
		Double sampX = 0.4;
		Double sampY = 0.5;
		Double omega = 0.6;
		Double kappa = 0.7;
		Double phi = 0.8;
		Integer gridIndexY = 1;
		Integer gridIndexZ = 2;

		String fileLocation = "/data/id14eh4/inhouse/opid144/20130319/RAW_DATA/1-2/discrete-1";
		String fileName = "opid144_2_0005.img";

		DataCollectionPosition dcp = new DataCollectionPosition();
		dcp.setFileName(fileName);
		dcp.setFileLocation(fileLocation);

		// 2 dc
		Integer motorPositionId2 = null;
		Double phiX2 = 1.1;
		Double phiY2 = 1.2;
		Double phiZ2 = 1.3;
		Double sampX2 = 1.4;
		Double sampY2 = 1.5;
		Double omega2 = 1.6;
		Double kappa2 = 1.7;
		Double phi2 = 1.8;
		Integer gridIndexY2 = 3;
		Integer gridIndexZ2 = 4;

		String fileLocation2 = "/data/id29/inhouse/opid291/20130521/RAW_DATA/BRAZIL/D5tri2/";
		String fileName2 = "ref-x_1_0004.cbf";

		DataCollectionPosition dcp2 = new DataCollectionPosition();
		dcp2.setFileName(fileName2);
		dcp2.setFileLocation(fileLocation2);
		// list
		DataCollectionPosition[] list = new DataCollectionPosition[2];
		list[0] = dcp;
		list[1] = dcp2;
		//int[] ret = ws.setDataCollectionsPositions(list);
//		long endWS = System.currentTimeMillis();
//		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");
//
//		String s = "";
//		if (ret != null) {
//			for (int i = 0; i < ret.length; i++) {
//				s += ret[i] + ", ";
//			}
//		}
//		System.out.println("This is what I got as a response : dataCollection  = " + s + "  \n");

	}

	private static void testGetDataCollectionInfo() throws Exception {
		System.out.println("*************** testGetDataCollectionInfo ***************");
		Integer dataCollectionId = 1246568;
		DataCollectionInfo info = ws.getDataCollectionInfo(dataCollectionId);
		if (info != null) {
			System.out.println("DataCollectioninformation: \n");
			System.out.println("axisStart= " + info.getAxisStart() + " \n");
			System.out.println("axisEnd= " + info.getAxisEnd() + " \n");
			System.out.println("axisRange= " + info.getAxisRange() + " \n");
			System.out.println("numberOfImages= " + info.getNumberOfImages() + " \n");
			System.out.println("exposureTime= " + info.getExposureTime() + " \n");
			System.out.println("wavelength= " + info.getWavelength() + " \n");
			System.out.println("resolution= " + info.getResolution() + " \n");
			System.out.println("transmission= " + info.getTransmission() + " \n");
			System.out.println("phiStart= " + info.getPhiStart() + " \n");
			System.out.println("actualCenteringPosition= " + info.getActualCenteringPosition() + " \n");
			System.out.println("localContact= " + info.getLocalContact() + " \n");
			System.out.println("localContactEmail= " + info.getLocalContactEmail() + " \n");
			BlSampleWS3VO sample = info.getBlSampleVO();
			String s = "NULL";
			if (sample != null) {
				s = blSampleToString(sample);
			}
			System.out.println("sample= " + s + " \n");

			System.out.println("spaceGroup= " + info.getSpaceGroup() + " \n");
			System.out.println("cellA= " + info.getCellA() + " \n");
			System.out.println("cellB= " + info.getCellB() + " \n");
			System.out.println("cellC= " + info.getCellC() + " \n");
			System.out.println("cellAlpha= " + info.getCellAlpha() + " \n");
			System.out.println("cellBeta= " + info.getCellBeta() + " \n");
			System.out.println("cellGamma= " + info.getCellGamma() + " \n");

			System.out.println("pdbFilePath= " + info.getPdbFilePath() + " \n");

		} else
			System.out.println("This is what I got as a response : NULL \n");
	}

	private static void testSetImagesPositions() throws Exception {
		System.out.println("*************** testSetImagesPositions ***************");
		long startWS = System.currentTimeMillis();

		Integer motorPositionId = null;
		Double phiX = 0.1;
		Double phiY = 0.2;
		Double phiZ = 0.3;
		Double sampX = 0.4;
		Double sampY = 0.5;
		Double omega = 0.6;
		Double kappa = 0.7;
		Double phi = 0.8;
		Integer gridIndexY = 1;
		Integer gridIndexZ = 2;

		String fileLocation = "/data/id14eh4/inhouse/opid144/20130319/RAW_DATA/1-2/discrete-1";
		String fileName = "opid144_2_0005.img";

		ImagePosition ip = new ImagePosition();
		ip.setFileName(fileName);
		ip.setFileLocation(fileLocation);

		// 2 image
		Integer motorPositionId2 = null;
		Double phiX2 = 1.1;
		Double phiY2 = 1.2;
		Double phiZ2 = 1.3;
		Double sampX2 = 1.4;
		Double sampY2 = 1.5;
		Double omega2 = 1.6;
		Double kappa2 = 1.7;
		Double phi2 = 1.8;
		Integer gridIndexY2 = 3;
		Integer gridIndexZ2 = 4;

		String fileLocation2 = "/data/id29/inhouse/opid291/20130521/RAW_DATA/BRAZIL/D5tri2/";
		String fileName2 = "ref-x_1_0004.cbf";

		ImagePosition ip2 = new ImagePosition();
		ip2.setFileName(fileName2);
		ip2.setFileLocation(fileLocation2);

		// 3 image
		Integer motorPositionId3 = null;
		Double phiX3 = 2.1;
		Double phiY3 = 2.2;
		Double phiZ3 = 2.3;
		Double sampX3 = 2.4;
		Double sampY3 = 2.5;
		Double omega3 = 2.6;
		Double kappa3 = 2.7;
		Double phi3 = 2.8;
		Integer gridIndexY3 = 4;
		Integer gridIndexZ3 = 5;

		String fileLocation3 = "/data/id29/inhouse/opid291/20130521/RAW_DATA/BRAZIL/D5tri2/test2";
		String fileName3 = "ref-x_1_2222.cbf";

		String jpegFileFullPath = "jpegFileFullPath";
		String jpegThumbnailFileFullPath = "jpegThumbnailFileFullPath";

		ImagePosition ip3 = new ImagePosition();
		ip3.setFileName(fileName3);
		ip3.setFileLocation(fileLocation3);
		ip3.setJpegFileFullPath(jpegFileFullPath);
		ip3.setJpegThumbnailFileFullPath(jpegThumbnailFileFullPath);
		// list
		ImagePosition[] list = new ImagePosition[3];
		list[0] = ip;
		list[1] = ip2;
		list[2] = ip3;
//		ImageCreation[] ret = ws.setImagesPositions(list);
//		long endWS = System.currentTimeMillis();
//		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");
//
//		String s = "";
//		if (ret != null) {
//			for (int i = 0; i < ret.length; i++) {
//				s += ret[i].getImageId() + ": " + ret[i].getIsCreated() + " (" + ret[i].getFileLocation() + ", "
//						+ ret[i].getFileName() + "), ";
//			}
//		}
//		System.out.println("This is what I got as a response : image  = " + s + "  \n");

	}

	private static void testSetBestWilsonPlotPath() throws Exception {
		System.out.println("*************** testSetBestWilsonPlotPath ***************");
		long startWS = System.currentTimeMillis();

		Integer dataCollectionId = 19745;
		String bestWilsonPlotPath = "bestWilsonPlotPath";
		Integer ret = ws.setBestWilsonPlotPath(dataCollectionId, bestWilsonPlotPath);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		System.out.println("This is what I got as a response : dataCollectionId = " + ret + "  \n");

	}
	
//	private static void testStoreWorkFlowStep() throws Exception {
//		System.out.println("*************** testStoreWorkFlowStep ***************");
//		long startWS = System.currentTimeMillis();
//
//		WorkflowStep3VO vo = new WorkflowStep3VO();
//		vo.setWorkflowId(1);
//		vo.setWorkflowStepType("Test");
//		vo.setComments("tests");
//		Gson gson = new Gson();		
//		String json = gson.toJson(vo);
//		
//		Integer ret = ws.storeWorkflowStep(json);
//		long endWS = System.currentTimeMillis();
//		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");
//
//		System.out.println("This is what I got as a response : dataCollectionId = " + ret + "  \n");
//
//	}
//	
	private static void testStoreRobotAction() throws Exception {
		System.out.println("*************** testStoreRobotAction ***************");
		long startWS = System.currentTimeMillis();

		RobotActionWS3VO vo = new RobotActionWS3VO();
		vo.setActionType("LOAD");
		vo.setBlSampleId(3119);
		vo.setSessionId(39645);//98
		vo.setBlSampleId(1);
		//vo.setSessionId(1);
		vo.setMessage("tests");
		vo.setStatus("SUCCESS");
		
		Integer ret = ws.storeRobotAction(vo);
		long endWS = System.currentTimeMillis();
		System.out.println("Time in ms :  " + (endWS - startWS) + "  \n");

		System.out.println("This is what I got as a response : robotActionId = " + ret + "  \n");

	}
}
