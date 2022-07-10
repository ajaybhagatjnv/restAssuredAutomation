package ApiTestPackage;

import CommonUtil.BaseClass;
import CommonUtil.FilePath;
import CommonUtil.JSONUtility;
import Models.GeoLoc;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.IOException;

public class ValidatorServiceHelper {

    private JSONObject expectedDataAsJson = null;
    public ValidatorServiceHelper(String className) {
        JSONObject expectedDataFromFile = new JSONObject(new JSONUtility().returnJsonAsString(FilePath.expectedDataPath));
        expectedDataAsJson = expectedDataFromFile.getJSONObject(className);
    }

    public void validateMyFirstClassResponse(JSONObject response){

        Assert.assertEquals(response.getString("status"), expectedDataAsJson.getString("status"), "Status Mismatch Found");
        Assert.assertEquals(response.getString("scope"), expectedDataAsJson.getString("scope"), "scope Mismatch Found");
        Assert.assertNotNull(response.getString("place_id"), "place_id null found");
        Assert.assertNotNull(response.getString("reference"), "reference null found");
        Assert.assertNotNull(response.getString("id"), "id null found");
    }

    public void validateResponseUsingMapper(GeoLoc responseObject) throws IOException {

        GeoLoc expectedData = new BaseClass().jsonToModelObject(GeoLoc.class, expectedDataAsJson);
        Assert.assertEquals(responseObject.getStatus(), expectedData.getStatus(), "Status Mismatch Found");
        Assert.assertEquals(responseObject.getScope(), expectedData.getScope(), "scope Mismatch Found");
        Assert.assertNotNull(responseObject.getPlace_id(), "place_id null found");
        Assert.assertNotNull(responseObject.getReference(), "reference null found");
        Assert.assertNotNull(responseObject.getId(), "id null found");
    }
}
