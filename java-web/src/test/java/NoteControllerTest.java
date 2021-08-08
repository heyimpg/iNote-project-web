import com.amela.Controllers.NoteController;
import com.amela.Models.Note;
import com.amela.Service.INoteService;
import com.amela.Service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit.jupiter.SpringJUnitJupiterConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebAppConfiguration
@SpringJUnitJupiterConfig(NoteControllerTestConfig.class)
public class NoteControllerTest {
    private MockMvc mockMvc;

    private INoteService noteService = Mockito.mock(NoteService.class);
    @InjectMocks
    private NoteController noteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(noteController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void noteListPageIsExists() throws Exception {
        mockMvc
                .perform(get("/home"))
                .andExpect(status().is(200))
                .andExpect(view().name("note/home"));
    }

  /*  @Test
    void showInformationSuccessControlling() throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String time = "2021-08-03";
        LocalDate localDate = LocalDate.parse(time, dateTimeFormatter);
        Note note = new Note(1, "Đi chợ", localDate, "Thử thêm nội dung từ trang WEB");
        given(noteService.findById(1)).willReturn(note);
        mockMvc
                .perform(get("/customers/1"))
                .andExpect(status().is(200))
                .andExpect(view().name("customers/info"));
    }*/

}
