package com.mergenie.apiaggregate.config;

import org.junit.Before;
import org.junit.Test;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.Assert.assertEquals;

public class SwaggerConfigTest {

    private SwaggerConfig swaggerConfig;

    @Before
    public void setUp() throws Exception {
        swaggerConfig = new SwaggerConfig();
    }

    @Test
    public void testShouldReturnTitleForApiInfo() {
        Docket docket = swaggerConfig.api();
        assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
    }
}
