import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UpdateUserTest extends PriorTests {

    @Test
    @Feature("Изменение данных авторизированного пользователя")
    @DisplayName("Изменение данных авторизированного пользователя")
    @Description("Testing for /auth/user endpoint")
    public void testChangedDataAuthorizedUser() {
        UserData user = new UserData(
                accessToken, newUserMail, newUserPassword, newUserName);
        response = user.reformUserData();

        // Проверка  изменения данных пользователя
        assertEquals(200, response.getStatusCode());

        response = user.getUserData();
        // Проверка корректировки емайла
        assertEquals(newUserMail, response.getBody().jsonPath().getString("user.email"));
        // Проверка корректировки имени
        assertEquals(newUserName, response.getBody().jsonPath().getString("user.name"));
    }

}