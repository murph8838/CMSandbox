package pickle;

import java.util.HashMap;
import java.util.Optional;

public class BaseStepDefinition {
	private HashMap<String, Object> testContext = new HashMap<>();

	public void addValue(String key, Object value) {
		testContext.put(key, value);
	}
	
	public Optional<Object> getValue(String key) {
		return Optional.of(testContext.get(key));
	}
}
