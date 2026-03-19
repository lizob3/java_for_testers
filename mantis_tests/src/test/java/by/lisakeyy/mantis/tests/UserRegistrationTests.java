package by.lisakeyy.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        // создать пользователя (адрес) на почтовом сервера (JamesHelper)
        // заполнить и отправить форму создания (браузер - создать класс-помощник)
        // подождать почту (MailHelper)
        // извлечь ссылку из письма
        // пройти по ссылке и завершить регистрацию (браузер - создать класс-помощник)
        // проверить, что пользователь может залогиниться (HttpSessionHelper)
    }
}
