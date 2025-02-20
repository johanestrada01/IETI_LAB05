package org.adaschool.Weather;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WeatherReportController.class)
public class WeatherReportControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherReportService weatherReportService;

	@Test
	public void testGetWeatherReport() throws Exception {
		double latitude = 37.8267;
		double longitude = -122.4233;

		// Configuramos un reporte de ejemplo.
		WeatherReport report = new WeatherReport();
		report.setTemperature(20.5);
		report.setHumidity(80);

		// Simulamos la respuesta del servicio.
		Mockito.when(weatherReportService.getWeatherReport(latitude, longitude)).thenReturn(report);

		// Realizamos la petición GET y verificamos que el JSON contenga los valores esperados.
		mockMvc.perform(get("/v1/api/weather-report")
						.param("latitude", String.valueOf(latitude))
						.param("longitude", String.valueOf(longitude)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.temperature").value(20.5))
				.andExpect(jsonPath("$.humidity").value(80));
	}
}
