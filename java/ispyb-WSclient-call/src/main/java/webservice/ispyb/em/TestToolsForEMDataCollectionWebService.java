package webservice.ispyb.em;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import generated.ws.em.IspybWS;
import generated.ws.em.MotionCorrection;
import generated.ws.em.Movie;
import webservice.UtilsDoNotCommit;


public class TestToolsForEMDataCollectionWebService {


	public TestToolsForEMDataCollectionWebService() throws Exception {
		super();
		initWebService();
	}

	
	protected static generated.ws.em.IspybWS iws;
	protected static generated.ws.em.ToolsForEMWebService ws;

	private static void initWebService() throws Exception {
		
		iws = new IspybWS();	

		System.out.println("-----------------------------------------------------------");

		ws=iws.getToolsForEMWebServicePort();
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

			//testStoreMovie();
			testStoreMotionCorrection();

		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

	private static void testStoreMovie() throws Exception {
		System.out.println("*************** testStoreMovie ***************");
		Integer ret = -1;
		
		String proposal = "mx415";
		String proteinAcronym = "SOL";
		String sampleAcronym = "smp";
		String movieDirectory = "/data/visitor/mx415/cm01/20200429";
		String moviePath = "/data/visitor/mx415/cm01/20200429/RAW_DATA/like-epu2/bgal-215k-like-epu_0518.tif";
		String movieNumber = "999";
		String micrographPath = "/data/visitor/mx415/cm01/20200429/RAW_DATA/like-epu2";

		String thumbnailMicrographPath = "/data/visitor/mx415/cm01/20200429/RAW_DATA/like-epu2";
		String xmlMetaDataPath = "/data/visitor/mx415/cm01/20200429/RAW_DATA/like-epu2";
		String voltage = "0.1";
		String sphericalAberration = "0.1";
		String amplitudeContrast = "0.1"; 
		String magnification = "1";

		String scannedPixelSize = "0.1";
		String noImages = "999";
		String dosePerImage = "0.1";
		String positionX = "0.1";
		String positionY = "0.1";
		String beamlineName = "CM01";
		String startTimeStr  = "09-06-2020";
		String gridSquareSnapshotFullPath=  "/data/visitor/mx415/cm01/20200429/RAW_DATA/like-epu2";

		Movie movie = ws.addMovie(proposal,  proteinAcronym,  sampleAcronym,  movieDirectory,  moviePath,  movieNumber,  micrographPath,
				thumbnailMicrographPath, xmlMetaDataPath, voltage,  sphericalAberration,  amplitudeContrast,  magnification,
				scannedPixelSize,  noImages,  dosePerImage,  positionX,  positionY,  beamlineName,  gridSquareSnapshotFullPath, startTimeStr);

		System.out.println("This is what I got as a response :movie = " + movie.getMoviePath() + "  \n");
	}
	
	private static void testStoreMotionCorrection() throws Exception {			
		System.out.println("*************** testStoreMotionCorrection ***************");
		Integer ret = -1;

			String proposal="mx415";
			String movieFullPath= "/data/visitor/mx415/cm01/20200610/RAW_DATA/mx2294_Grid3_ADC_EPU_3/Images-Disc1/GridSquare_27341627/Data/FoilHole_27346716_Data_27345534_27345536_20200527_150537-0089.mrc";
			String firstFrame = "1";
			String lastFrame= "99";
			String dosePerFrame= "1";
			String doseWeight= "1";
			String totalMotion= "200";
			String averageMotionPerFrame= "1";
			String driftPlotFullPath= "/data/visitor/mx415/cm01/20200610/PROCESSED_DATA/mx2294_Grid3_ADC_EPU_3/mx2294_Grid3_ADC_EPU_3_20200610-113853/Runs/000064_ProtMotionCorr/extra/GridSquare_27341627_Data_FoilHole_27346716_Data_27345534_27345536_20200527_150537-0089_global_shifts.png";
			String micrographFullPath= "";
			String micrographSnapshotFullPath = "/data/visitor/mx415/cm01/20200610/PROCESSED_DATA/mx2294_Grid3_ADC_EPU_3/mx2294_Grid3_ADC_EPU_3_20200610-113853/Runs/000064_ProtMotionCorr/extra/GridSquare_27341627_Data_FoilHole_27346716_Data_27345534_27345536_20200527_150537-0089_thumbnail.png";
			String correctedDoseMicrographFullPath = "";
			String logFileFullPath = "/data/visitor/mx415/cm01/20200610/PROCESSED_DATA/mx2294_Grid3_ADC_EPU_3/mx2294_Grid3_ADC_EPU_3_20200610-113853/Runs/000064_ProtMotionCorr/logs/run.log";
			
			MotionCorrection motion = ws.addMotionCorrection(proposal, movieFullPath, firstFrame, lastFrame, dosePerFrame, doseWeight, totalMotion, averageMotionPerFrame, driftPlotFullPath, micrographFullPath,
					micrographSnapshotFullPath, correctedDoseMicrographFullPath, logFileFullPath);
			
			System.out.println("This is what I got as a response :motion = " + motion.getLogFileFullPath() + "  \n");
	}
}
