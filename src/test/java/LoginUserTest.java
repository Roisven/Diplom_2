import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginUserTest extends PriorTests {

    @Test
    @Feature("Авторизация юзера")
    @DisplayName("Авторизация юзера")
    @Description("Test for /auth/login endpoint")
    public void testLoginUser() {
        LoginUser user = new LoginUser(userEmail, userPassword);
        response = user.getResponseLogin();
        assertEquals(200, response.getStatusCode());
    }

    @Test
    @Feature("Авторизация юзера с неправильными кредами")
    @DisplayName("Авторизация пользователя с неправильными кредами")
    @Description("Test for /auth/login endpoint")
    public void testLoginIncorrectUser() {
        LoginUser user = new LoginUser("userMail","userPassword");
        response = user.getResponseLogin();
        assertEquals(401, response.getStatusCode());
        assertEquals("email or password are incorrect",
                response.getBody().jsonPath().getString("message"));
    }

}