package pickle.greeting;

import java.util.Optional;

import org.springframework.web.client.RestTemplate;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hello.Greeting;
import pickle.BaseStepDefinition;

public class GreetingStepDefinition extends BaseStepDefinition {

	public static final String GREETINGS_URL = "http://localhost:8081/greeting";
	public static final String INPUT = "input";
	public static final String RESULT = "result";

	@Given("^an input of ([^\"]*)$")
	public void an_input_of(String name) {
		addValue(INPUT, name);
	}

	@When("the Greetings API is called")
	public void the_greetings_api_is_called() {
		RestTemplate template = new RestTemplate();

		Optional<Object> oInput = getValue(INPUT);

		if (oInput.isPresent()) {
			String url;

			if ("-null-".equals(oInput.get())) {
				url = GREETINGS_URL + "?name=";
			} else {
				url = GREETINGS_URL + "?name=" + oInput.get();
			}

			addValue(RESULT, template.getForObject(url, Greeting.class));
		}

	}

	@Then("^the return value should be ([^\"]*)$")
	public void the_return_value_should_be(String expected) throws Exception {
		Optional<Object> result = getValue(RESULT);
		if (result.isPresent()) {
			Greeting resultGreeting = (Greeting) result.get();
			if (!expected.equals(resultGreeting.getContent())) {
				throw new Exception(
						"Expected: \"" + expected + "\", but received \"" + resultGreeting.getContent() + "\".");
			}

		}
	}

}
