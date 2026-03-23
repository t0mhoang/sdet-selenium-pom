package com.herokuapp.tests.api;

import com.herokuapp.config.ApiConfig;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Posts API")
@Feature("GET and POST endpoints")
public class PostApiTest {

    @Test
    @Story("Fetch all posts")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("GET /posts returns 200 and 100 posts")
    void getAllPosts_returns200AndCorrectCount() {
        Response response = given()
                .spec(ApiConfig.getRequestSpec())
            .when()
                .get("/posts")
            .then()
                .statusCode(200)
                .extract().response();

        int postCount = response.jsonPath().getList("$").size();
        assertEquals(100, postCount, "Should return exactly 100 posts");
    }

    @Test
    @Story("Fetch single post")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("GET /posts/1 returns correct post data")
    void getPostById_returnsCorrectData() {
        Response response = given()
                .spec(ApiConfig.getRequestSpec())
            .when()
                .get("/posts/1")
            .then()
                .statusCode(200)
                .extract().response();

        assertEquals(1, (int) response.jsonPath().get("id"));
        assertEquals(1, (int) response.jsonPath().get("userId"));
        assertNotNull(response.jsonPath().getString("title"));
    }

    @Test
    @Story("Fetch non-existent post")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("GET /posts/999 returns 404")
    void getPostById_nonExistent_returns404() {
        given()
            .spec(ApiConfig.getRequestSpec())
        .when()
            .get("/posts/999")
        .then()
            .statusCode(404);
    }

    @Test
    @Story("Create post")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("POST /posts with valid body returns 201")
    void createPost_validBody_returns201() {
        String requestBody = """
                {
                    "title": "BasicPOM API Test",
                    "body": "Testing API layer inside a UI framework",
                    "userId": 1
                }
                """;

        Response response = given()
                .spec(ApiConfig.getRequestSpec())
                .body(requestBody)
            .when()
                .post("/posts")
            .then()
                .statusCode(201)
                .extract().response();

        assertEquals("BasicPOM API Test", response.jsonPath().getString("title"));
        assertNotNull(response.jsonPath().get("id"));
    }
}