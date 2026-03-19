package by.lisakeyy.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class MailTests extends TestBase {

    @Test
    void canDrainInbox() {
        app.mail().drain("user1@localhost", "password");
    }

    @Test
    void canRecieveEmail() {
        var messages = app.mail().recieve("user1@localhost", "password", Duration.ofSeconds(100));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);
    }
}
