package com.apiTest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;


public class bookTestApi {
	
	
	String baseURI = "https://simple-books-api.glitch.me";
	
	
	@Test
	public void bookTestWelcome() {
		
		given().get(baseURI).then().log().all();
		
	}
	
	@Test
	public void bookStatus() {
		given().get(baseURI+"/status").then().log().all();
		
	}
	
	@Test
	public void bookStatusVerify() {
		given().accept(ContentType.JSON)
		.when().get(baseURI+"/status").then().statusCode(200).log().all();
	}
	
	
	@Test
	public void listOfBooks() {
		given().get(baseURI+"/books").then().log().all();
	}
	
	@Test
	public void listOfBooksLimit() {
		given().get(baseURI+"/books?limit=3").then().log().all();
	}
	
	@Test
	public void listOfBooksType() {
		given().get(baseURI+"/books?type=fiction").then().log().all();
	}
	
	@Test
	public void getSingleBook() {
		given().get(baseURI+"/books/2").then().log().all();
		
		int bookid = 5;
		given().get(baseURI+"/books/"+bookid).then().log().all();
	}
	

	@Test
	public void apiClient() {
		JSONObject jObj = new JSONObject();
		
		jObj.put("clientName", "Valentin");
		jObj.put("clientEmail", "valentiname5@example.com");
		
		given().headers(
	              "Authorization",
	              "Bearer " + "a3f0461d1ceb17d86f2ed8c94d8244ae42847c74154bd9d48930adff782a20e1",
	              "Content-Type",
	              ContentType.JSON,
	              "Accept",
	              ContentType.JSON)
			.body(jObj.toJSONString())
			.when().post(baseURI+"/api-clients").then().contentType(ContentType.JSON)
			.statusCode(201).log().all();
	}
	
	@Test
	public void orderBook() {		
		JSONObject jObj = new JSONObject();
			
		jObj.put("bookId", 4);
		jObj.put("customerName", "John Snow");
		
		given().headers(
	              "Authorization",
	              "Bearer " + "a3f0461d1ceb17d86f2ed8c94d8244ae42847c74154bd9d48930adff782a20e1",
	              "Content-Type",
	              ContentType.JSON,
	              "Accept",
	              ContentType.JSON)
			.body(jObj.toJSONString())
			.when().post(baseURI+"/orders").then().contentType(ContentType.JSON)
			.statusCode(201).log().all();
	}
	
	
	@Test
	public void getAllOrders() {
		  given()
          .headers(
              "Authorization",
              "Bearer " + "a3f0461d1ceb17d86f2ed8c94d8244ae42847c74154bd9d48930adff782a20e1",
              "Content-Type",
              ContentType.JSON,
              "Accept",
              ContentType.JSON)
          .when()
          .get(baseURI+"/orders")
          .then()
          .contentType(ContentType.JSON)
          .log().all();
	}
	
	@Test
	public void singleOrderStatus() {
		String id = "HxOwMvjQvufNNXuMKtMG9";
		given()
        .headers(
            "Authorization",
            "Bearer " + "a3f0461d1ceb17d86f2ed8c94d8244ae42847c74154bd9d48930adff782a20e1",
            "Content-Type",
            ContentType.JSON,
            "Accept",
            ContentType.JSON)
        .when()
        .get(baseURI+"/orders/"+id)
        .then()
        .contentType(ContentType.JSON)
        .log().all();
		
	}
	
	
	

}
