package test;

import org.testng.ITestListener;
import org.testng.ITestResult;

import pojo.BaseTest;
import utility.ScreenShot;

public class Listeners extends BaseTest implements ITestListener {
	
	public void onTestStart(ITestResult result) {
		System.out.println("on test start :- " +result.getName());
	}
	
	public void onTestSuccess (ITestResult result) {
		System.out.println("on test success :-" +result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		try {
			ScreenShot.takeScreenShot(driver, result.getName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		System.out.println("on test skipped :-" +result.getName());
	}
	
	public void onTestFinish(ITestResult result) {
		System.out.println("on test Finish :-" +result.getName());
	}

}
