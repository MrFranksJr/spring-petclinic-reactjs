package org.springframework.samples.petclinic.web;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.PetClinicApplication;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Test class for {@link CrashController}
 *
 * @author Colin But
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PetClinicApplication.class)
@WebAppConfiguration
// Waiting https://github.com/spring-projects/spring-boot/issues/5574
public class CrashControllerTests {

    @Autowired
    private CrashController crashController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(crashController)
            //.setHandlerExceptionResolvers(new SimpleMappingExceptionResolver())
            .build();
    }

    @Test
    public void testTriggerException()  {
//        mockMvc.perform(get("/oups")
//                .contentType(MediaType.APPLICATION_JSON))
////            .andExpect(view().name("exception"))
////            .andExpect(model().attributeExists("exception"))
////            .andExpect(forwardedUrl("exception"))
////            .andExpect(status().isOk())
//            .andExpect(result -> assertInstanceOf(RuntimeException.class, result.getResolvedException()))
//            .andExpect(result -> assertEquals("Expected: controller used to showcase what " +
//                                    "happens when an exception is thrown", result.getResolvedException().getMessage()));

        Exception ex = assertThrows(ServletException.class, () -> {
            mockMvc.perform(get("/oups"));
        });

        assertInstanceOf(ServletException.class, ex);
        assertEquals("Request processing failed: java.lang.RuntimeException: Expected: controller used to showcase what " +
                "happens when an exception is thrown", ex.getMessage());
    }
}
