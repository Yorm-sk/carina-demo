package com.qaprosoft.carina.demo;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.demo.api.AirPolutionGetMethod;
import com.qaprosoft.carina.demo.api.GetCatFactMethod;
import com.qaprosoft.carina.demo.api.GetWeatherMethod;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class APIWeatherTest implements IAbstractTest {

    @Test()
    public void testGetWeather(){
        GetWeatherMethod api = new GetWeatherMethod();
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/weather/_get/rs.schema");
    }

    @Test()
    public void testGetPollution(){
        AirPolutionGetMethod api =  new AirPolutionGetMethod();
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/pollution/_get/rs.schema");
    }

    @Test()
    public void testGetCatFact(){
        GetCatFactMethod factMethod = new GetCatFactMethod();
        factMethod.callAPIExpectSuccess();
        factMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        factMethod.validateResponseAgainstSchema("api/cat/_get/rs.schema");
    }
}
