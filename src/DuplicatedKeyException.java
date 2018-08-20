
public class DuplicatedKeyException extends RuntimeException{

	public DuplicatedKeyException(String config) {
	      super ("This configuration has already been stored in the dictionary: " + config);
	}

}
