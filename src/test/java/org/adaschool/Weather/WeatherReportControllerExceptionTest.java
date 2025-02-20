package org.adaschool.Weather;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WeatherReportController.class)
public class WeatherReportControllerExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherReportService weatherReportService;

    @Test
    public void testServiceThrowsException() throws Exception {
        double latitude = 37.8267;
        double longitude = -122.4233;

        // Configuramos el servicio para que arroje una excepción
        Mockito.when(weatherReportService.getWeatherReport(latitude, longitude))
                .thenThrow(new RuntimeException("Error en el servicio"));

        // Se espera un error 500 (Internal Server Error)
        mockMvc.perform(get("/v1/api/weather-report")
                        .param("latitude", String.valueOf(latitude))
                        .param("longitude", String.valueOf(longitude)))
                .andExpect(status().isInternalServerError());
    }
}
