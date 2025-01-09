package reports;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports report;
	public static String screenshotPath;

	public static ExtentReports getExtentReport() {
		if (report == null) {
			report = new ExtentReports();

			Date date = new Date();
			String reportFolderName = date.toString().replace(":", "_").replace(" ", "-");

			String reportPath = System.getProperty("user.dir") + "/reports/" + reportFolderName;
			screenshotPath = reportPath + "/screenshots";

			File file = new File(screenshotPath);
			file.mkdirs();

			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			reporter.config().setTheme(Theme.STANDARD);
			reporter.config().setDocumentTitle("Data Driven Framework");
			reporter.config().setReportName("Reddif Web Automation");
			report.attachReporter(reporter);
		}
		return report;
	}

}
