package com.epam.microservice.sandbox.microservicesandbox.remote;

import com.epam.microservice.sandbox.microservicesandbox.model.TestDataCollection;
import com.epam.microservice.sandbox.microservicesandbox.remote.TestDataClient.TestDataClientFallback;
import com.epam.microservice.sandbox.microservicesandbox.web.TestDataController;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import java.util.Collections;

/**
 * @author Dmytro_Maksutov
 */
//TODO Fix issue with feign service discovery. 
//TODO Currently Feign can't find proper ULR by serviceId
@FeignClient(
    name="dataprovider-service",
    url = "http://localhost:8093",
    fallback = TestDataClientFallback.class
)
public interface TestDataClient extends TestDataController
{
    @Component
    class TestDataClientFallback implements TestDataClient {

        @Override
        public TestDataCollection getAllTestData()
        {
            return new TestDataCollection(Collections.emptyList());
        }
    }
}
