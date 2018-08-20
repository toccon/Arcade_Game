
public class InexistentKeyException extends RuntimeException{

	public InexistentKeyException(String config) {
		super ("This configuration does not exist in the dictionary: " + config);

		
	}

}
