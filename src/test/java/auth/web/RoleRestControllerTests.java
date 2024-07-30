package auth.web;

import auth.AuthApplicationTests;
import auth.entity.Role;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RoleRestControllerTests extends AuthApplicationTests {

    @Test
    public void testCreateRole() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        role.setDescription("system administrator");

        String token = getToken();
        given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(role)
                .post("/auth/roles")
                .then().log().all()
                .statusCode(200);

        given()
                .header("Authorization", "Bearer " + token)
                .get("/auth/roles")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void testgetRoleWorks() {
        String token = getToken();
        given()
                .header("Authorization", "Bearer " + token).log().all()
                .get("/auth/roles")
                .then().log().all()
                .statusCode(200);

    }
}
