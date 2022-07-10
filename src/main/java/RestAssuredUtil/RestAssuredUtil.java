package RestAssuredUtil;

import CommonUtil.URLCollection;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.util.Strings;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;

public class RestAssuredUtil{

    public JSONObject post(String body, Map<String, Object> queryParam, Map<String, Object> pathParam ,  String token, String endPoint, int expectedStatusCode ) {
        baseURI = URLCollection.baseUri;

        Response response = buildRequest(body, queryParam, pathParam, token)
                .body(body)
                .when()
                .post(endPoint);

        return (convertResponseAsJson(response, expectedStatusCode));
    }

    public JSONObject get(Map<String, Object> queryParam, Map<String, Object> pathParam, String token, String endPoint, int expectedStatusCode ) {
        baseURI = URLCollection.baseUri;
        Response response = given()
                .headers(getHeaders(token))
                .queryParams(queryParam)
                .pathParams(pathParam)
                .when()
                .get(endPoint);
        return (convertResponseAsJson(response, expectedStatusCode));
    }

    public void delete(Map<String, Object> pathParam ,  String token, String endPoint, int expectedStatusCode ) {
        baseURI = URLCollection.baseUri;
        given()
                .headers(getHeaders(token))
                .pathParams(pathParam)
                .when()
                .delete(endPoint).then().log().body().assertThat().statusCode(expectedStatusCode);
    }

    public JSONObject put(String body, Map<String, Object> queryParam, Map<String, Object> pathParam ,  String token, String endPoint, int expectedStatusCode ) {
        baseURI = URLCollection.baseUri;
        Response response = given()
                .headers(getHeaders(token))
                .queryParams(queryParam)
                .body(body)
                .pathParams(pathParam)
                .when()
                .put(endPoint);
        return (convertResponseAsJson(response, expectedStatusCode));
    }


    private JSONObject convertResponseAsJson(Response response, int expectedStatusCode){

        response.then().log().all().assertThat().statusCode(expectedStatusCode);
        JsonPath jsonPath = new JsonPath(response.asString());
        return (new JSONObject(jsonPath.prettify()));
    }

    private Map<String, String> getHeaders(String token){
        Map<String, String> headerMap = new HashMap<>(){{put("content-type", "Application/JSON"); }};
        if(Strings.isNotNullAndNotEmpty(token)){
            headerMap.put("Authorization", "Bearer "+ token);
        }
        return headerMap;
    }

    private RequestSpecification buildRequest(String body, Map<String, Object> queryParam, Map<String, Object> pathParam ,  String token ){

        RequestSpecification request = given();

        request.headers(getHeaders(token));
        if(Objects.nonNull(queryParam))
            request.queryParams(queryParam);
        if(Objects.nonNull(pathParam))
            request.pathParams(pathParam);
        if(Objects.nonNull(body))
            request.body(body);

        return request;
    }
}
