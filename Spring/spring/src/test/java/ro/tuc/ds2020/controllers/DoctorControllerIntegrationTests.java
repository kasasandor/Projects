package ro.tuc.ds2020.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ro.tuc.ds2020.Ds2020TestConfig;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.DoctorDetailsDTO;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.services.DoctorService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/test-sql/createDoctor.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:/test-sql/deleteDoctor.sql")
public class DoctorControllerIntegrationTests extends Ds2020TestConfig {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Test
    public void insertDoctorTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        DoctorDetailsDTO doctorDTO = new DoctorDetailsDTO("John", "Somewhere Else street", 40);

        mockMvc.perform(post("/doctor")
                .content(objectMapper.writeValueAsString(doctorDTO))
                .contentType("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    public void findAllDoctorsTest() throws Exception {

        mockMvc.perform(get("/doctor"))
                .andExpect(status().isOk());
    }

}
