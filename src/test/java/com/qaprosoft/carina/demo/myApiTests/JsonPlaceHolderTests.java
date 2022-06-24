package com.qaprosoft.carina.demo.myApiTests;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.demo.api.myapi.*;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class JsonPlaceHolderTests implements IAbstractTest {

    @Test()
    public void creatingResourceTest(){
        PostPostMethod api = new PostPostMethod();
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test()
    public void updatingResource(){
        PutPostMethod api = new PutPostMethod();
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test()
    public void getPost(){
        GetPostMethod api = new GetPostMethod();
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/posts/_get/rs.schema");
    }

    @Test()
    public void patchResource(){
        PatchPostMethod api = new PatchPostMethod();
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test()
    public void deleteResource(){
        DeletePostMethod api = new DeletePostMethod();
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test()
    public void getUserPostById(){
        GetListPostMethod api = new GetListPostMethod(1);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/posts/_getList/rs.schema");
        GetListPostMethod api2 = new GetListPostMethod(2);
        api2.callAPIExpectSuccess();
        api2.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api2.validateResponseAgainstSchema("api/posts/_getList/rs.schema");
    }
}
