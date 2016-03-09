package com.example.myeventsourcing.transaction.controller;

import com.example.myeventsourcing.event.test.JsonTool;
import com.example.myeventsourcing.event.test.MockEventConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Administrador on 08/03/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({MockEventConfiguration.class, TransferControllerTest.TestContextConfiguration.class})
@WebAppConfiguration
public class TransferControllerTest {

    @Autowired
    private TransferController controller;

    @Autowired
    private JsonTool jsonTool;

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void transferTest() throws Exception {
        TransferController.TransferRequest request = new TransferController.TransferRequest();
        request.setAmount(new BigDecimal(100));
        request.setSourceAccountId(1L);
        request.setTargetAccountId(2L);
        this.mockMvc.perform(post("/transfer")
                .contentType(contentType)
                .content(jsonTool.json(request)))
                .andExpect(status().isAccepted());
    }

    @EnableAutoConfiguration
    @ComponentScan(basePackages = {"com.example.myeventsourcing.transaction"}, basePackageClasses = {JsonTool.class})
    @Configuration
    public static class TestContextConfiguration {

    }
}
