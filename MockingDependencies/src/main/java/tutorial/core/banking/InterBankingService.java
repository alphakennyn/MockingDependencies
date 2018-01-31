package tutorial.core.banking;

import java.net.SocketException;

import tutorial.core.banking.CoreService.InteracTransferStatus;

/*
 *  This is an external Dependency
 */

public class InterBankingService implements ExternalDependency {

	
	public InteracTransferStatus Interac(double amount, Account from, String phoneNumber) throws SocketException {
		
		throw new SocketException
		("we can not call this method in our dev enviroment because we are no connected to Interac network");
		
	}

}
