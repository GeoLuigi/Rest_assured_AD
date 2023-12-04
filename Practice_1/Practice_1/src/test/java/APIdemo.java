import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIdemo {

    int lastId;
    @Test(priority = 1)
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

    @Test(priority = 2)
    void createUser(){
        JSONObject user = new JSONObject();
        user.put("name", "George");
        user.put("job", "Quality Assurance");

        lastId = given()
            .contentType("application/json")
            .body(user)
        .when()
            .post("https://reqres.in/api/users")
                .jsonPath().getInt("id")
//        .then()
//            .statusCode(201)
//            .log().all()
        ;
    }

    @Test(priority = 3)
    void updateUser() {
        JSONObject user = new JSONObject();
        user.put("name", "George");
        user.put("job", "Tester");

        given()
            .contentType("application/json")
            .body(user)
        .when()
            .put("https://reqres.in/api/users/"+lastId)
        .then()
            .statusCode(200)
            .log().all()
        ;
    }

    @Test(priority = 5)
    void deleteUser() {
        given()
        .when()
            .delete("https://reqres.in/api/users/"+lastId)
        .then()
            .statusCode(204)
            .log().all()
        ;
    }

    @Test(priority = 4)
    void patchUser() {
        JSONObject updatedInfo = new JSONObject();
        updatedInfo.put("name", "morpheus");
        updatedInfo.put("job", "zion resident");

        given()
            .contentType("application/json")
            .body(updatedInfo.toString())
        .when()
            .patch("https://reqres.in/api/users/" + lastId)
        .then()
            .statusCode(200)
            .body("name", equalTo("morpheus"))
            .body("job", equalTo("zion resident"))
        .log().all()
        ;
    }
}
