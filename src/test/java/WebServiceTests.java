import classes.Exception.Classes.NotAValidIDException;
import classes.Exception.Classes.UserNotLoggedInException;
import classes.Exception.Classes.UserNotRegisteredException;
import classes.Exception.Classes.WrongArticleException;
import classes.User;
import classes.WebServiceTestClass;

import interfaces.WebService;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WebServiceTests {

    @Mock
    WebService webService;

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

        when(webService.isUserRegistered(anyString())).thenReturn(true);
        when(webService.isUserLoggedIn(anyString())).thenReturn(true);
        when(webService.isValidId(anyInt())).thenReturn(true);
        when(webService.getArticle(anyInt())).thenReturn(WebService.testArticleName);

        assertEquals("REFUND GRANTED", user.refund(webService, 1, "testUser"));

        verify(webService).getArticle(1);
    }

    @Test
    //public void testUserNotRegisteredException() throws UserNotRegisteredException {  <- geht auch, scheint aber seit JUnit (5?) nicht mehr notwendig zu sein durch assertThrows
    public void testUserNotRegisteredException() {
        assertThrows(UserNotRegisteredException.class, () -> {
            when(webService.isUserRegistered(anyString())).thenReturn(false);

            user.refund(webService, 001, "testUser");
        });
    }

    @Test
    public void testUserNotLoggedInException() {
        assertThrows(UserNotLoggedInException.class, () -> {
           when(webService.isUserRegistered(anyString())).thenReturn(true);
           when(webService.isUserLoggedIn(anyString())).thenReturn(false);
            user.refund(webService, 001, "testUser");
        });
    }

    @Test
    public void testNotAValidIDException() {
        assertThrows(NotAValidIDException.class, () -> {
            when(webService.isUserRegistered(anyString())).thenReturn(true);
            when(webService.isUserLoggedIn(anyString())).thenReturn(true);
            when(webService.isValidId(anyInt())).thenReturn(false);

            user.refund(webService, 001, "testUser");
        });
    }

    @Test
    public void testWrongArticleException() {
        assertThrows(WrongArticleException.class, () -> {
            when(webService.isUserRegistered(anyString())).thenReturn(true);
            when(webService.isUserLoggedIn(anyString())).thenReturn(true);
            when(webService.isValidId(anyInt())).thenReturn(true);
            when(webService.getArticle(anyInt())).thenReturn("A WRONG ARTICLE");

            user.refund(webService, 001, "testUser");
        });
    }

    /*
    Im Interface initialisierte Werte werden beibehalten
     */
    @Test
    public void testMockAndSpyWithChangingIDOnInterface() {
        WebService spyWebService = spy(WebService.class);

        assertEquals(3, webService.testArticleID + 2);
        assertEquals(3, spyWebService.testArticleID + 2);
    }

    /*
    In Klasse werden initialisierte Werte werden auf 0/default zurückgesetzt
     */
    @Test
    public void testMockAndSpyWithChangingIDOnClass() {
        WebServiceTestClass mockWebService = mock(WebServiceTestClass.class);
        WebServiceTestClass spyWebService = spy(new WebServiceTestClass());

        List mockedList = mock(ArrayList.class);
        List spyList = spy(new ArrayList());

        //mockWebService.liste.add("One");
        //assertEquals(0, mockWebService.liste.size());
        spyWebService.liste.add("One");
        assertEquals(1, spyWebService.liste.size());

        assertEquals(2, mockWebService.id + 2);
        assertEquals(3, spyWebService.id + 2);
        //assertEquals(1, mockWebService.addOne());
        assertEquals(2, spyWebService.addOne());
    }

    @Test
    public void testArgThat(){
        when(webService.getArticle(anyString())).thenReturn("test");
        webService.getArticle("eingabe");

        /*verify(webService).getArticle(argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(String s) {
                if (s.equals("eingabe")) {
                    return true;
                }
                return false;
            }
        }));*/

        verify(webService).getArticle(argThat(String -> String.equals("eingabe") == true));



    }
}
