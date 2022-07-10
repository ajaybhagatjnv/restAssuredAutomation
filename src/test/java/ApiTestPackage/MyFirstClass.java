package ApiTestPackage;

import CommonUtil.BaseClass;
import CommonUtil.FilePath;
import CommonUtil.JSONUtility;
import CommonUtil.URLCollection;
import Models.GeoLoc;
import RestAssuredUtil.RestAssuredUtil;
import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class MyFirstClass {

    public BaseClass baseClass;
    public JSONUtility jsonUtility;
    public RestAssuredUtil restUtil;
    public ValidatorServiceHelper serviceHelper;


    @BeforeClass
    public void beforeClass(){

        baseClass = new BaseClass();
        jsonUtility = new JSONUtility();
        restUtil = new RestAssuredUtil();
        serviceHelper = new ValidatorServiceHelper(this.getClass().getSimpleName());
    }

    @Test(priority = 1)
    public void firstTestMethod() throws IOException {

        System.out.println("MyFirstClass");
        URLCollection.baseUri = BaseClass.properties.getProperty("baseUri");
        String requestBody = jsonUtility.returnJsonAsString(FilePath.testPath1);
        JSONObject response = restUtil.post(requestBody, null, null,  null, URLCollection.POST_REQUEST_1, 200);
        GeoLoc responseObject = baseClass.jsonToModelObject(GeoLoc.class, response);
        serviceHelper.validateMyFirstClassResponse(response);
        serviceHelper.validateResponseUsingMapper(responseObject);
    }
}
