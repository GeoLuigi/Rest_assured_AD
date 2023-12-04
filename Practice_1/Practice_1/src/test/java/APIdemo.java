import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIdemo {
    @Test
    void getUsers(){
         given()
         .when()
                 .get("https://reqres.in/api/users?page=2")
         .then()
                 .statusCode(200)
                 .body("page", equalTo(2))
                 .log().all()
         ;
    }

    @Test
    void createUser(){
        JSONObject user = new JSONObject();
        user.put("name", "George");
        user.put("job", "Quality Assurance");

        given()
            .contentType("application/json")
            .body(user)
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201)
            .log().all()
        ;
    }

    @Test
    void updateUser() {
        JSONObject user = new JSONObject();
        user.put("name", "George");
        user.put("job", "Tester");

        given()
            .contentType("application/json")
            .body(user)
        .when()
            .put("https://reqres.in/api/users/2")
        .then()
            .statusCode(200)
            .log().all()
        ;
    }

    @Test
    void deleteUser() {
        given()
        .when()
            .delete("https://reqres.in/api/users/2")
        .then()
            .statusCode(204)
            .log().all()
        ;
    }
}
