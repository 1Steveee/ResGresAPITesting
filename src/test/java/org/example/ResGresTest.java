package org.example;

import data.Employee;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ResGresTest extends BaseSetup{
    private final String POST_URI = "/api/users";


    @Test
    public void testCreatingNewEmployee() {
        Employee newEmployee = new Employee("Shane Michaels", "Data Engineer");
        int statusCode = 201;

        given()
                .body(newEmployee)
                .when()
                .post(POST_URI)
                .then()
                .statusCode(statusCode)
                .and()
                .assertThat()
                .body("name", equalTo(newEmployee.getName()))
                .and()
                .assertThat()
                .body("job",equalTo(newEmployee.getJob()))
                .and()
                .assertThat()
                .body("id", notNullValue())
                .and()
                .assertThat()
                .body("createdAt", notNullValue());
    }

    @Test
    public void testUpdateExistingEmployee() {
        Employee updateEmployee = new Employee("Curtis Jackson", "CEO");
        given()
                .body(updateEmployee)
                .when()
                .put("api/users/2")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("name", equalTo(updateEmployee.getName()))
                .and()
                .assertThat()
                .body("job", equalTo(updateEmployee.getJob()));
    }

    @Test
    public void testDeleteEmployee() {
        given()
                .when()
                .delete("api/users/2")
                .then()
                .assertThat()
                .statusCode(204);
    }

}
