package keywords;

import java.io.FileInputStream;
import java.util.Properties;

public class ApplicationKeyword extends ValidationKeyword {

	public ApplicationKeyword() {
		try {
			properties = new Properties();
			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/Project.properties");
			properties.load(fs);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void login() {

	}

	public void selectDate() {

	}
}
