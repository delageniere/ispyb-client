package webservice.ispyb.em;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import generated.ws.em.IspybWS;
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

			testStoreMovie();
//		 testFindSession();

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

}
