package com.qa.listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.PrintWriter;
import java.io.StringWriter;

public class TestListener implements ITestListener {
    public void onTestFailure(ITestResult iTestResult){
        if(iTestResult.getThrowable() !=null){
            StringWriter stringWriter=new StringWriter();
            PrintWriter printWriter=new PrintWriter(stringWriter);
            iTestResult.getThrowable().printStackTrace(printWriter);
            System.out.println(stringWriter.toString());
        }
    }

}
