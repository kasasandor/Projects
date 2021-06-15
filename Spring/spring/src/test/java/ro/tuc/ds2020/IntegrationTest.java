package ro.tuc.ds2020;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class IntegrationTest extends Ds2020TestConfig{

    @Autowired
    private MockMvc mockMvc;


}
