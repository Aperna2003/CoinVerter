package utenti;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogoutTest {

    @Mock
    HttpSession session;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @InjectMocks
    Logout servlet;

    @Test
    public void testDoGet() throws ServletException, IOException {
        //setup
        when(request.getSession()).thenReturn(session);

        //esecuzione
        servlet.doGet(request, response);

        //controllo
        verify(session).invalidate();
        verify(response).sendRedirect("/index.jsp");
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        Logout serv = spy(new Logout());
        doNothing().when(serv).doGet(request, response);

        serv.doPost(request, response);

        verify(serv).doGet(request, response);
    }
}
