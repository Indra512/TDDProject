package keywords;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ValidationKeyword extends GenericKeyword {

	public void validateTitle(String expectedTitleKey) {
		Assert.assertEquals(driver.getTitle(), properties.getProperty(expectedTitleKey));
	}

	public void validateValueInPortfolioDropdpwn(String locatortKey, String value) {
		Select select = new Select(getElement(locatortKey));
		String selectedValue = select.getFirstSelectedOption().getText();
		if (!selectedValue.equals(value)) {
			reportFailure("Selected value " + value + " is not in portfolio drodown", true);
		}
	}

	public void validateValueNotInPortfolioDropdpwn(String locatortKey, String value) {
		Select select = new Select(getElement(locatortKey));
		String selectedValue = select.getFirstSelectedOption().getText();
		if (selectedValue.equals(value)) {
			reportFailure("Selected value " + value + "is available in portfolio drodown", true);
		}
	}

	public void validateText() {

	}

	public void validateElementPresent() {

	}

	public void validateElementClickable() {

	}

	public void validateElementDisplayed() {

	}
}
