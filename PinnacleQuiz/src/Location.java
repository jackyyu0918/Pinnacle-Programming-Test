
public enum Location {
	CA, //California
	FL, //Florida
	IL, //Illinois
	KS, //Kansas
	MO, //Missouri
	NJ, //New Jersey
	NY, //New York
	WA;  //Washington
	

	
	public static Location getLocation(String input) {
		switch(input) {
		case "CA":
			return Location.CA;
		case "FL":
			return Location.FL;
		case "IL":
			return Location.IL;
		case "KS":
			return Location.KS;
		case "MO":
			return Location.MO;
		case "NJ":
			return Location.NJ;
		case "NY":
			return Location.NY;
		case "WA":
			return Location.WA;
		}
		
		System.out.println("Cannot find corresponding location.");
		return null;
	}
}
