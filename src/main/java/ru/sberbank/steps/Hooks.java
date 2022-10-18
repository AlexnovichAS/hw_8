package ru.sberbank.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.sberbank.managers.DriverManager;
import ru.sberbank.managers.InitManager;
import ru.sberbank.managers.TestPropManager;

import static ru.sberbank.utils.PropConst.BASE_URL;

public class Hooks {

    private final DriverManager driverManager = DriverManager.getDriverManager();

    @Before
    public void before() {
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
        InitManager.initFramework();
    }

    @After
    public void after() {
        driverManager.getDriver().manage().deleteAllCookies();
        InitManager.quitFramework();
    }
}
