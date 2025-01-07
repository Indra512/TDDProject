package keywords;

import org.testng.Assert;

public class ValidationKeyword extends GenericKeyword{

    public void validateTitle(String expectedTitleKey) {
        Assert.assertEquals(driver.getTitle(), properties.getProperty(expectedTitleKey));
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
