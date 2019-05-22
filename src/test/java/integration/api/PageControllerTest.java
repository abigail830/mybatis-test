package integration.api;

import com.github.abigail830.mybatictest.api.dto.SimpleUserResponseDTO;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import integration.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class PageControllerTest extends IntegrationTestBase {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DatabaseSetup(value = "/dbunit/UserTest_allUsers.xml", type = DatabaseOperation.CLEAN_INSERT)
    void wishlist() throws Exception {
        //given
        SimpleUserResponseDTO simpleUserResponseDTO1 =
                new SimpleUserResponseDTO(1, "user1", "F", "url1");
        SimpleUserResponseDTO simpleUserResponseDTO2 =
                new SimpleUserResponseDTO(2, "user2", "M", null);
        final List<SimpleUserResponseDTO> mockReturn = Arrays.asList(simpleUserResponseDTO1, simpleUserResponseDTO2);

        //when
        ResultActions resultActions = mockMvc.perform(get("/userlist"))
                .andExpect(status().isOk())
                .andExpect(view().name("userlist"))
                .andExpect(model().attribute("simpleUserList", is(mockReturn)))
                .andExpect(content().string(containsString("<li>SimpleUserResponseDTO(id=1, userName=user1, gender=F, avatarUrl=url1)</li>")))
                .andExpect(content().string(containsString("<li>SimpleUserResponseDTO(id=2, userName=user2, gender=M, avatarUrl=null)</li>")));
        MvcResult mvcResult = resultActions.andReturn();
        ModelAndView mv = mvcResult.getModelAndView();

    }
}