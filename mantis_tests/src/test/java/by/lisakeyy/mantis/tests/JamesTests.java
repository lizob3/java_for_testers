package by.lisakeyy.mantis.tests;

import by.lisakeyy.mantis.common.CommonFunctions;
import org.junit.jupiter.api.Test;

public class JamesTests extends TestBase{

    @Test
    void canCreateUser() {
        app.jamesCli().addUser(String.format("%s@localhost", CommonFunctions.randomString(8)),
                "password");
    }
}
