import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import static io.restassured.http.ContentType.JSON;

class BasicLInks {

    static String getOrdersLink() {
        return "/orders";
    }

    static String getIngredientsLink() {
        return "/ingredients";
    }

    static String getAuthUserLink() {
        return "/auth/user";
    }

    static String getAuthRegisterLink() {
        return "/auth/register";
    }

    static String getLoginUserLink() {
        return "/auth/login";
    }

    static RequestSpecification getBaseSpec() {
        String baseLink = "https://stellarburgers.nomoreparties.site/api/";
        return new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(baseLink)
                .build();
    }

}