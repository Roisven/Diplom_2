import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;


public class PriorTests {
    String userName;
    String userPassword;
    String userEmail;
    String accessToken;
    MakeUser user;
    Response response;
    String newUserName;
    String newUserMail;
    String newUserPassword;

    @Before
    public void setUp() {


        userName = "Rois"; //
        userPassword = "12345678"; //
        userEmail = "Aloxart@yandex.ru";

        // Создаем пользователя
        user = new MakeUser(userPassword, userName, userEmail);
        response = user.getResponse();
        accessToken = user.getAccessToken();

        //новый пользователь
        newUserName = "new_user_test";
        newUserMail = "new_user_test@ya.ru";
        newUserPassword = "new_user_test12345";
    }

    @After
    public void rollBck(){
        Allure.attachment("Answer status code: ", String.valueOf(response.getStatusCode()));
        Allure.attachment("Answer body: ", String.valueOf(response.getBody().prettyPrint()));
        user.delete();
    }
}