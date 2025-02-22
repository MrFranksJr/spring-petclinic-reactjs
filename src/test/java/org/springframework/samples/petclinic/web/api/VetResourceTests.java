package org.springframework.samples.petclinic.web.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VetResource.class)
public class VetResourceTests {

	@Autowired
	private MockMvc	mvc;

	@MockBean
	ClinicService		clinicService;

	@Test
	public void shouldGetAListOfVetsInJSonFormat() throws Exception {

		Vet vet = new Vet();
		vet.setId(1);

		given(clinicService.findVets()).willReturn(Arrays.asList(vet));

		mvc.perform(get("/api/vets") //
				.accept(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$[0].id").value(1));
	}

}
