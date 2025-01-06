package keywords;

import org.testng.Assert;

public class ValidationKeyword extends GenericKeyword{

    public void validateTitle(String expectedTitle) {
        Assert.assertEquals(driver.getTitle(), expectedTitle);
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
