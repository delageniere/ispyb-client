package webservice.batchUpdate;

import generated.ws.common.update.Exception_Exception;
import generated.ws.common.update.IspybWS;
import generated.ws.common.update.UpdateFromSMISWebService;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import webservice.UtilsDoNotCommit;

/**
 * 
 * @author DELAGENI
 * 
 *         TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateProposalFromSMIS {
	
	private static UpdateFromSMISWebService ws;
	
	public static void main(String[] args) throws Exception {

		// Get the service
		System.out.println("Launching batch job to update ISPyB from SMIS");
		
		System.out.println("-----------------------------------------------------------");

		// To be sure it has enough time to achieve (service launched once a day at 4:12 am)
		//ws.setTimeout(120 * 60 * 1000);

		System.out.println("WS for update is created: now calling smis WS");

		init();
		updateProposal();

		System.out.println("update successfull");

		System.out.println();

		System.out.println("------");				

	}
	private static void init() {
	
		IspybWS service = new IspybWS();	

		ws=service.getUpdateFromSMISWebServicePort();
		BindingProvider bindingProvider = (BindingProvider)ws;
		Map requestContext = bindingProvider.getRequestContext();
	
		requestContext.put(BindingProvider.USERNAME_PROPERTY, UtilsDoNotCommit.ISPYBU);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, UtilsDoNotCommit.ISPYBP);
		System.out.println("WS for update is created: now calling smis WS");
		
	}
	
	private static void updateProposal() throws Exception_Exception {
		String code = "MX";
		String number = "2229";
		ws.updateProposalFromSMIS(code, number);
		
		//ws.updateFromSMIS();
		
	}
}