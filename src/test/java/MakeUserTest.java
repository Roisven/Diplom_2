import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MakeUserTest extends PriorTests {

    @Test
    @Feature("Создание юзера")
    @DisplayName("Создание юзера")
    @Description("Test for /auth/register endpoint")
    public void testCreateUniqueUser() {
        assertEquals(200, response.getStatusCode());
    }

    @Test
    @Feature("Создание существующего пользователя")
    @DisplayName("Создание существующего пользователя")
    @Description("Test for /auth/register endpoint")
    public void testCreateExistUser() {
        user = new MakeUser(userPassword, userName, userEmail);
        response = user.getResponse();
        assertEquals(403, response.getStatusCode());
        assertEquals("User already exists",
                response.getBody().jsonPath().getString("message"));
    }

    @Test
    @Feature("Создание пользователя без email")
    @DisplayName("Создание пользователя без email")
    @Description("Test for /auth/register endpoint")
    public void testCreateUserNoEmail() {
        user = new MakeUser(userPassword, userName, "");
        response = user.getResponse();
        assertEquals(403, response.getStatusCode());
        assertEquals("Email, password and name are required fields",
                response.getBody().jsonPath().getString("message"));
    }

}