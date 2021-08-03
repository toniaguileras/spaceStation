package toni.aguilera.spaceStation.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import toni.aguilera.spaceStation.service.SpaceStationService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(SpaceStationController.class)
public class SpaceStationControllerTest {

    @MockBean
    SpaceStationService spaceStationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void launch() throws Exception {
        when(spaceStationService.launch()).thenReturn("Launch in 3..2..1.. Rocket Launched!!!");
        mockMvc.perform(post("http://localhost:8080/api/spacestation/launch").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
