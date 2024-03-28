package isd.aims.main.subsystem.interbank;


import isd.aims.main.common.exception.UnrecognizedException;
import isd.aims.main.utils.API;

public class InterbankBoundary {

	String query(String url, String data) {
		String response = null;
		try {
			response = API.post(url, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UnrecognizedException();
		}
		return response;
	}

}
