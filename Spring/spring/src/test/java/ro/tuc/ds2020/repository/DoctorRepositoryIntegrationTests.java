package ro.tuc.ds2020.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import ro.tuc.ds2020.Ds2020TestConfig;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.repositories.DoctorRepository;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/test-sql/createDoctor.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:/test-sql/deleteDoctor.sql")
public class DoctorRepositoryIntegrationTests extends Ds2020TestConfig {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void findByName_thenReturnDoctor(){
        Doctor doctor = new Doctor("My Name", "My Address", 40);

        Doctor found = doctorRepository.findByName(doctor.getName()).get();

        assertEquals("Test findByName Doctor", doctor.getName(), found.getName());
    }

    @Test
    public void findAllDoctors(){
        Doctor doctor = new Doctor("John", "My Address", 40);

        doctorRepository.save(doctor);

        List<Doctor> doctorList = doctorRepository.findAll();

        assertEquals("Test findAll Doctor", 2, doctorList.size());
    }
}
