package com.TestsBack;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestBack {
    String baserUrl = "https://parabank.parasoft.com/parabank/services/bank";
    String customerId = null;
    String accountId = null;

    @BeforeEach
    public void login() {
        // recupera el usuario creado desde el front
        String username = null;
        try(BufferedReader in = new BufferedReader(new FileReader("./target/username.txt"))){
            username = in.readLine();
        } catch (IOException ignored) {}

        Response responseCustomer = RestAssured.get(baserUrl+"/login/"+username+"/12345678");
        customerId = responseCustomer.xmlPath().getString("customer.id");
        System.out.println(customerId);

        Response responseAccount = RestAssured.get(baserUrl+"/customers/"+customerId+"/accounts");
        accountId = responseAccount.xmlPath().getString("accounts.account[0].id");
    }

    @Test
    void test_GET_customer() {
        given()
                .when()
                .get(baserUrl+"/customers/"+customerId)
                .then()
                .statusCode(200).log().all();
    }

    @Test
    void test_POST_new_account() {
        given()
                .queryParams(Map.of(
                        "customerId",customerId,
                        "newAccountType", 1,
                        "fromAccountId", accountId
                ))
                .when()
                .post(baserUrl+"/createAccount")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    void test_GET_account_overview() {
        given()
                .when()
                .get(baserUrl+"/accounts/"+accountId)
                .then()
                .statusCode(200).log().all();
    }

    @Test
    void test_POST_transfer() {
        Response response = RestAssured.get(baserUrl+"/customers/"+customerId+"/accounts");
        String fromAccountId = response.xmlPath().getString("accounts.account[0].id");
        String toAccountId = response.xmlPath().getString("accounts.account[1].id");

        given()
                .queryParams(Map.of(
                        "fromAccountId", fromAccountId,
                        "toAccountId", toAccountId,
                        "amount", 50
                ))
                .when()
                .post(baserUrl+"/transfer")
                .then()
                .statusCode(200).log().all();
    }

    @Test
    void test_GET_account_activity() {
        given()
                .when()
                .get(baserUrl+"/accounts/"+accountId+"/transactions/month/All/type/All")
                .then()
                .statusCode(200).log().all();
    }
}
