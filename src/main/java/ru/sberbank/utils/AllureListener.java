package ru.sberbank.utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.sberbank.managers.DriverManager;

public class AllureListener extends AllureCucumber5Jvm implements TestWatcher {

    @Attachment(value = "Screnshot", type = "image/png" , fileExtension = "png")
    public byte[] getScreenshot(){
        return ((TakesScreenshot) DriverManager.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        getScreenshot();
    }
}
