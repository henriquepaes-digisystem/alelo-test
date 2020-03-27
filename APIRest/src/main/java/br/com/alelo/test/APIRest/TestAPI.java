package br.com.alelo.test.APIRest;

import static io.restassured. RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class TestAPI {
	private String url = "https://jsonplaceholder.typicode.com/todos/1";
	
    @Test
    public void AvaliacaoItem2() {
        given()
        .when()
            .get(url).
        then()
            .assertThat().body("userId", equalTo(1))
            .and().statusCode(200)
            .and().body("id", equalTo(1))
            .and().body("title", equalTo("delectus aut autem"))
            .and().body("completed", equalTo(false))
            ;
    }
}