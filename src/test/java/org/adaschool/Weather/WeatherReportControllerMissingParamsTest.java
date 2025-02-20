package org.adaschool.Weather;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WeatherReportController.class)
public class WeatherReportControllerMissingParamsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherReportService weatherReportService;

    @Test
    public void testMissingLatitudeParameter() throws Exception {
        // Se omite el parámetro 'latitude'
        mockMvc.perform(get("/v1/api/weather-report")
                        .param("longitude", "-122.4233"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testMissingLongitudeParameter() throws Exception {
        // Se omite el parámetro 'longitude'
        mockMvc.perform(get("/v1/api/weather-report")
                        .param("latitude", "37.8267"))
                .andExpect(status().isBadRequest());
    }
}
