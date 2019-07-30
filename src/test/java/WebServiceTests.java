import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WebServiceTests {

    @Mock WebService webService;

    User user;

    @BeforeEach
    public void setup() {
        user = new User();
    }

    @Test
    public void testIsWebServiceAvailable() {
        when(webService.isShopActive()).thenReturn(true);

        webService.isShopActive();

        verify(webService).isShopActive();
    }

    @Test
    public void testUserWantsRefund() {
        when(webService.isValidId(anyInt())).thenReturn(true);
        when(webService.getArticleByID(anyInt())).thenReturn(WebService.articleName);

        assertEquals("REFUND GRANTED", user.refund(webService, 1));

        verify(webService).getArticleByID(1);
    }
}
