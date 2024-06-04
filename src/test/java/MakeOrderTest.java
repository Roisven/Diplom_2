import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MakeOrderTest extends PriorTests {

    private List<String> ingredients;
    private MakeOrder order;
    private String[] id;


    @Before
    public void setUp_() {
        // Генерируем ингридиенты для заказа
        order = new MakeOrder();
        id = order.getIngredients().getBody().jsonPath().getString("data._id").split(",");
    }

    @Test
    @Feature("Создание заказа с правильными ингридиентами")
    @DisplayName("Создание заказа с правильными ингридиентами")
    @Description("Test for /orders/ endpoint")
    public void testCreateOrderValidIngredients() {
        ingredients = List.of(id[4].trim(), id[3].trim(), id[2].trim());
        response = order.postOrder(ingredients, accessToken);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    @Feature("Создание заказа с неправильными ингридиентами")
    @DisplayName("Создание заказа с неправильными ингридиентами")
    @Description("Test for /orders/ endpoint")
    public void testCreateOrderNotValidIngredients() {
        ingredients = List.of(id[4], id[3], id[2]);
        response = order.postOrder(ingredients, accessToken);
        assertEquals(500, response.getStatusCode());
    }

    @Test
    @Feature("Создание заказа без ингридиентов")
    @DisplayName("Создание заказа без ингридиентов")
    @Description("Test for /orders/ endpoint")
    public void testCreateOrderEmptyIngredients() {
        response = order.postOrder(ingredients, accessToken);
        assertEquals(400, response.getStatusCode());
        assertEquals("Ingredient ids must be provided",
                response.getBody().jsonPath().getString("message"));
    }

    @Test
    @Feature("Создание заказа с правильными без авторизации")
    @DisplayName("Создание заказа с правильными без авторизации")
    @Description("Test for /orders/ endpoint")
    public void testCreateOrderValidIngredientsNoAuth() {
        ingredients = List.of(id[4].trim(), id[3].trim(), id[2].trim());
        response = order.postOrder(ingredients, "");
        assertEquals(200, response.getStatusCode());
    }
}