package by.lisakeyy.mantis.tests;

import by.lisakeyy.mantis.common.CommonFunctions;
import by.lisakeyy.mantis.model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase {

    public static Stream<String> RandomUsernameProvider() {
        Supplier<String> randomUsername = () -> CommonFunctions.randomString(5);
        return Stream.generate(randomUsername).limit(1);
    }

    public static Stream<UserData> RandomUserdataProvider() {
        String username = CommonFunctions.randomString(5);
        Supplier<UserData> randomUser = () -> new UserData().withUsername(username)
                .withEmail(String.format("%s@localhost", username))
                .withPassword("password");
        return Stream.generate(randomUser).limit(1);
    }

    @ParameterizedTest
    @MethodSource("RandomUsernameProvider")
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        var password = "password";
        // create user on James (JamesHelper)
        app.jamesCli().addUser(email, password);
        // fill and send form (WebSiteHelper)
        app.website().startCreatingAccount(username, email);
        // wait for mail (MailHelper)
        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        // extract link from message
        var url = app.mail().extractLink(messages);
        // follow the link and finish creating account (WebSiteHelper)
        app.driver().get(url);
        app.website().finishCreatingAccount(username, password);
        // make sure user can login (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @ParameterizedTest
    @MethodSource("RandomUserdataProvider")
    void canRegisterUserAlternative(UserData userData) {
        // create user on James
        app.jamesApi().addUser(userData.email(), userData.password());
        // start creating user with REST API
        app.rest().startCreatingAccount(userData);
        // wait for mail (MailHelper)
        var messages = app.mail().receive(userData.email(), userData.password(), Duration.ofSeconds(10));
        // extract link from message
        var url = app.mail().extractLink(messages);
        // follow the link and finish creating account (WebSiteHelper)
        app.driver().get(url);
        app.website().finishCreatingAccount(userData.username(), userData.password());
        // make sure user can login (HttpSessionHelper)
        app.http().login(userData.username(), userData.password());
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
