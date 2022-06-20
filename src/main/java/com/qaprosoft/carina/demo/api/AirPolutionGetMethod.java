package com.qaprosoft.carina.demo.api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "${base_url}/air_pollution/history?lat=50&lon=50&start=1606223802&end=1606482999&appid=307be471edad0bcb829b53082c0b00a7",
        methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/pollution/_get/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class AirPolutionGetMethod extends AbstractApiMethodV2 {
    public AirPolutionGetMethod(){replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));}
}
