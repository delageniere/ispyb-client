package webservice.smis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;

//import com.google.gson.Gson;

import generated.ws.smis.ExpSessionInfoLightVO;
import generated.ws.smis.ProposalParticipantInfoLightVO;
import generated.ws.smis.SMISWebService;
import generated.ws.smis.SMISWebService_Service;
import generated.ws.smis.SampleSheetInfoLightVO;
import webservice.UtilsDoNotCommit;

public class TestCallSMISWebService {

	protected static SMISWebService ws;
	protected static SMISWebService_Service service;
	
	public TestCallSMISWebService() throws Exception {
		super();
		initWebService();
	}
	
	private static void initWebService() throws Exception {
		// Get the services for ISPyB
		service = new SMISWebService_Service();	
		ws=service.getSMISWebServiceBeanPort();
		BindingProvider bindingProvider = (BindingProvider)ws;
		Map requestContext = bindingProvider.getRequestContext();
		
		requestContext.put(BindingProvider.USERNAME_PROPERTY, UtilsDoNotCommit.SMISU);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, UtilsDoNotCommit.SMISP);
		// To be sure it has enough time to achieve (service launched once a day at 4:12 am)
		// ws.setTimeout(120*60*1000);

	}

	public static void main(String args[]) {
		try {

			System.out.println("*************** testCallSMISWebServices ***************");
			initWebService();
			testCallSMIS();
			//testFindSamplesheetInfoLightForProposalPk();
			//testFindMainProposersForProposal();
			//testFindSessionsByBeamlineAndDates();
			//testFindModifiedProposals();
			//testFindScientistsForProposal();
			//testPersonsForProposal();
			testFindSessionsByProposalPk();
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

	private static void testCallSMIS() throws Exception {
		System.out.println("*************** testfindRecentSessionsInfoLightForProposalPk ***************");
		//Long proposalPk =new Long(31529); //MX415
		//Long proposalPk =new Long(57256); //MX1752
		Long proposalPk =new Long(93223); //MX2229
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<ExpSessionInfoLightVO>  vos = ws.findRecentSessionsInfoLightForProposalPk(proposalPk);

		if (vos != null) {
			System.out.println("Sessions length = " + vos.size() + "\n");
			int i=0;
			for (Iterator iterator = vos.iterator(); iterator.hasNext();) {
				ExpSessionInfoLightVO sesVO = (ExpSessionInfoLightVO) iterator.next();
				System.out.println("Session[" + i + "] = " + sesVO.getBeamlineName() 
				+ " - " + sesVO.getPk() 
				+  " - " +formatter.format(sesVO.getStartDate().getTime())
						+  " - " +formatter.format(sesVO.getEndDate().getTime()) + "\n");
				i=i+1;
			}
			System.out.println("This is what I got as a response :\n" + vos.size() + " entries "+ vos);
		} else
			System.out.println("This is what I got as a response : NULL \n");
		
		
		System.out.println();
		System.out.println("------");
	}
	
	private static void testFindSamplesheetInfoLightForProposalPk() throws Exception {
		System.out.println("*************** testFindSamplesheetInfoLightForProposalPk() ***************");
		Long proposalPk =new Long(31529); //MX415
		//Long proposalPk =new Long(57256); //MX1752
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<SampleSheetInfoLightVO>  vos = ws.findSamplesheetInfoLightForProposalPk(proposalPk);

		if (vos != null) {
			System.out.println("Samplesheets length = " + vos.size() + "\n");
			int i=0;
			for (Iterator iterator = vos.iterator(); iterator.hasNext();) {
				SampleSheetInfoLightVO sampleVO = (SampleSheetInfoLightVO) iterator.next();
				System.out.println("Sample[" + i + "] = " + sampleVO.getAcronym() + "\n");
				i=i+1;
			}
			System.out.println("This is what I got as a response :\n" + vos.size() + " entries ");
		} else
			System.out.println("This is what I got as a response : NULL \n");
		
		
		System.out.println();
		System.out.println("------");
	}
	
	private static void testFindMainProposersForProposal() throws Exception {
		System.out.println("*************** testfindMainProposersForProposalForProposalPk ***************");
		Long pk =new Long(31529);//mx415
		//Long pk =new Long(57256); //MX1752
		List<ProposalParticipantInfoLightVO> vos = ws.findMainProposersForProposal(pk);

		if (vos != null) {
			System.out.println("Proposal participants length = " + vos.size() + "\n");
			int i=0;
			for (Iterator<ProposalParticipantInfoLightVO> iterator = vos.iterator(); iterator.hasNext();) {
				ProposalParticipantInfoLightVO sesVO = (ProposalParticipantInfoLightVO) iterator.next();
				System.out.println("ProposalParticipantInfoLightVO[" + i + "] = " + sesVO.getScientistName() + "   " + sesVO.getScientistFirstName()+  "\n");
				i=i+1;
			}
			System.out.println("This is what I got as a response :\n" + vos.toString() + vos);
		} else
			System.out.println("This is what I got as a response : NULL \n");
		
		
		System.out.println();
		System.out.println("------");
	}
	
	private static void testFindModifiedProposals() throws Exception {
		System.out.println("*************** testFindModifiedProposals ***************");
		String date1="01/10/2018";
		String date2="06/10/2018";
		
		List<Long>  pks = ws.findNewMXProposalPKs(date1, date2);
		if (pks != null) {
			System.out.println("nb of proposals = " + pks.size() + "\n");
			int i=0;
			for (Iterator<Long> iterator = pks.iterator(); iterator.hasNext();) {
				Long pk = (Long) iterator.next();
				System.out.println("proposalPk[" + i + "] = " + pk.toString() + "\n");
				i=i+1;
			}
			System.out.println("This is what I got as a response :\n" + pks.toString() );
		} else
			System.out.println("This is what I got as a response : NULL \n");
		System.out.println();
		System.out.println("------");
	}
	
	private static void testFindSessionsByBeamlineAndDates() throws Exception {
		System.out.println("*************** testfindSessionsByBeamlineAndDates ***************");
		//Long proposalPk =new Long(1170); //MX415
		Long proposalPk =new Long(57256); //MX1752
		String bl = "BM30A" ;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());
		Calendar cal2 = cal1;
		cal1.roll(Calendar.YEAR, -1);
		
		List<ExpSessionInfoLightVO>  vos = ws.findSessionsByBeamlineAndDates(bl , cal1, cal2);
		if (vos != null) {
			System.out.println("Sessions length = " + vos.size() + "\n");
			int i=0;
			for (Iterator<ExpSessionInfoLightVO> iterator = vos.iterator(); iterator.hasNext();) {
				ExpSessionInfoLightVO sesVO = (ExpSessionInfoLightVO) iterator.next();
				System.out.println("Session[" + i + "] = " + sesVO.toString() + "\n");
				i=i+1;
			}
			System.out.println("This is what I got as a response :\n" + vos.toString() + vos);
		} else
			System.out.println("This is what I got as a response : NULL \n");
		System.out.println();
		System.out.println("------");
	}
	
	private static void testFindSessionsByProposalPk() throws Exception {
		System.out.println("*************** testfindSessionsByBeamlineAndDates ***************");
		//Long proposalPk =new Long(1170); //MX415
		Long proposalPk =new Long(96193); //IM
		
		List<ExpSessionInfoLightVO>  vos = ws.findSessionsInfoLightForProposalPk(proposalPk);
		if (vos != null) {
			System.out.println("Sessions length = " + vos.size() + "\n");
			int i=0;
			for (Iterator<ExpSessionInfoLightVO> iterator = vos.iterator(); iterator.hasNext();) {
				ExpSessionInfoLightVO sesVO = (ExpSessionInfoLightVO) iterator.next();
				System.out.println("Session[" + i + "] = " + sesVO.toString() + "\n");
				i=i+1;
			}
			System.out.println("This is what I got as a response :\n" + vos.toString() + vos);
		} else
			System.out.println("This is what I got as a response : NULL \n");
		System.out.println();
		System.out.println("------");
	}
	
	private static void testFindScientistsForProposal() throws Exception {
		System.out.println("*************** testfindScientistsForProposal ***************");
		Long proposalPk =new Long(1170); //MX415
		String name="DELAGENIERE";
		String firstName = "Solange";
		int maxResults = 2;
		
		//Long pk =new Long(57256); //MX1752
	
		List<ProposalParticipantInfoLightVO> vos = ws.findScientistsByNameAndFirstName(name, firstName, maxResults);
		int i=0;
		for (Iterator<ProposalParticipantInfoLightVO> iterator = vos.iterator(); iterator.hasNext();) {
			ProposalParticipantInfoLightVO vo = (ProposalParticipantInfoLightVO) iterator.next();
			System.out.println("Scientist[" + i + "] = " + vo.getScientistName() + vo.getScientistFirstName() + vo.getLabName() + "\n");
			i=i+1;
		}
		
		 System.out.println("Json scientists: ");
		 //System.out.println(new Gson().toJson(vos));

		System.out.println();
		System.out.println("------");
		
		 vos = ws.findScientistsForProposalByNameAndFirstName(proposalPk, name, firstName);
		 
		i=0;
		for (Iterator<ProposalParticipantInfoLightVO> iterator = vos.iterator(); iterator.hasNext();) {
				ProposalParticipantInfoLightVO vo = (ProposalParticipantInfoLightVO) iterator.next();
				System.out.println("Scientist[" + i + "] = " + vo.getScientistName() + vo.getScientistFirstName() + vo.getLabName() + "\n");
				i=i+1;
		}
		 
		 System.out.println("Json scientists: ");
		// System.out.println(new Gson().toJson(vos));

		System.out.println();
		System.out.println("------");
	}

	private static void testPersonsForProposal() throws Exception {
		System.out.println("*************** testPersonsForProposal ***************");
		//Long pk =new Long(1170); //MX415
		//Long pk =new Long(31529);//mx415
		//Long pk =new Long(57256); //MX1752
		Long pk =new Long(82054);//IH-HC-3376
		
		
		List<ExpSessionInfoLightVO> vos  = ws.findSessionsInfoLightForProposalPk(pk);
		System.out.println("Proposal sessions length = " + vos.size() + "\n");

		for (Iterator iterator = vos.iterator(); iterator.hasNext();) {
			ExpSessionInfoLightVO expSessionInfoLightVO = (ExpSessionInfoLightVO) iterator.next();
			List<ProposalParticipantInfoLightVO> pvos = ws.findUsersByExpSession(expSessionInfoLightVO.getPk());
			System.out.println("session pk = " + expSessionInfoLightVO.getPk() + "\n");
			
			if (pvos != null) {
				System.out.println("Proposal persons length = " + pvos.size() + "\n");
				int i=0;
				for (Iterator<ProposalParticipantInfoLightVO> iterator2 = pvos.iterator(); iterator2.hasNext();) {
					ProposalParticipantInfoLightVO pVO = (ProposalParticipantInfoLightVO) iterator2.next();
					System.out.println("ProposalParticipantInfoLightVO[" + i + "] = " + pVO.getScientistName() + "   " + pVO.getScientistFirstName()
							+ "   mp:" + pVO.isMainProposer() + "   proposer:" + pVO.isProposer()+ "   user:" + pVO.isUser()+"\n");
					i=i+1;
				}
			}				
			else {
					System.out.println("No persons found\n" );
				
			}
						
			System.out.println();
			System.out.println("------");
		}
	}


}