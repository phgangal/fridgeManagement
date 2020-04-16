package com.fridgemanagement;

import com.fridgemanagement.model.Fridge;
import com.fridgemanagement.model.Soda;
import com.fridgemanagement.service.FridgeService;
import com.fridgemanagement.service.SodaService;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.AsyncRestOperations;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class TestProductController {

    @MockBean
    AsyncRestOperations restTemplate;
    @Autowired
    FridgeService fridgeService;
    @Autowired
    SodaService sodaService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @Ignore
    public void getFridge() throws Exception {
        Fridge fridgeTest = fridgeService.retrieveFridge(1);
        List<Soda> sodasTest = (List<Soda>) sodaService.getAllSodasForAFridge(fridgeTest.getSodaList());
        Soda sodaTest = sodasTest.get(1);
        BDDMockito.when(fridgeTest);
        BDDMockito.when(sodasTest);

       mockMvc.perform(MockMvcRequestBuilders.get("v1/fridge/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fridgeId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fridgeName",Matchers.is(fridgeTest.getFridgeName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sodaList",Matchers.is(fridgeTest.getSodaList())));
        Mockito.verify(fridgeService.retrieveFridge(1));
    }


    @Test
    @Ignore

    public void getSoda() throws Exception {

            Soda sodaTest = sodaService.retrieveSoda(1);
            BDDMockito.when(sodaTest);
            mockMvc.perform(MockMvcRequestBuilders.get("v1/soda/1"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.sodaId", Matchers.is(sodaTest.getSodaId())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.fridgeId",Matchers.is(sodaTest.getFridgeId())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.sodaBranName",Matchers.is(sodaTest.getSodaBrandName())));
            Mockito.verify(sodaService.retrieveSoda(1));

    }

}
