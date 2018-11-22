package suit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class HTMLUnitOnly {
	public static void main(String[] args) throws Exception {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		//1
		HtmlPage page = webClient.getPage("http://svyatoslav.biz/testlab/wt/");
		HtmlForm form = page.getForms().get(0);
		assertTrue(form.isDisplayed());
		assertTrue(form.asText().contains("Имя"));
		assertTrue(form.asText().contains("Рост"));
		assertTrue(form.asText().contains("Вес"));
		assertTrue(form.asText().contains("Пол"));
		assertTrue(form.asText().contains("Пол"));
		assertTrue(form.asText().contains("М"));
		assertTrue(form.asText().contains("Ж"));

		HtmlInput nameField = form.getInputByName("name");
		HtmlInput weightField = form.getInputByName("weight");
		HtmlInput heightField  = form.getInputByName("height");
		List<HtmlRadioButtonInput> sexInput = form.getRadioButtonsByName("gender");
		HtmlRadioButtonInput MaleButton = sexInput.get(0);
		//HtmlRadioButtonInput FemaleButton = sexInput.get(1); was not used in test
		HtmlSubmitInput countButton = form.getInputByValue("Рассчитать");
		
		//2
		nameField.type("Имя");
		assertEquals(nameField.asText(), "Имя");
		
		//3
		weightField.type("50");
		assertEquals(weightField.asText(), "50");
		
		//4
		heightField.type("3");
		assertEquals(heightField.asText(), "3");
		
		//5
		MaleButton.click();
		assertTrue(MaleButton.isChecked());
		
		//6
		page = countButton.click();
		assertTrue(page.asText().contains("Рост должен быть в диапазоне 50-300 см."));
		
		webClient.close();
	}
}

