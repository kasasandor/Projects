package ro.tuc.ds2020.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ro.tuc.ds2020.Ds2020TestConfig;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.DoctorDetailsDTO;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/test-sql/createDoctor.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:/test-sql/deleteDoctor.sql")
public class DoctorServiceIntegrationTests extends Ds2020TestConfig {
    @Autowired
    DoctorService doctorService;

    @Test
    public void testInsertCorrectWithGetById() {
        DoctorDetailsDTO p = new DoctorDetailsDTO("John", "Somewhere Else street", 40);
        UUID insertedID = doctorService.insert(p);

        DoctorDTO insertedDoctor = new DoctorDTO(insertedID, p.getName(), p.getAddress(), p.getAge());
        DoctorDTO fetchedDoctor = doctorService.findDoctorById(insertedID);

        assertEquals("Test Inserted doctor", insertedDoctor, fetchedDoctor);
    }

    @Test
    public void testInsertCorrectWithGetAll() {
        DoctorDetailsDTO p = new DoctorDetailsDTO("John", "Somewhere Else street", 40);
        doctorService.insert(p);

        List<DoctorDTO> doctorDTOList = doctorService.findDoctors();
        assertEquals("Test Inserted doctors", 2, doctorDTOList.size());
    }
}
