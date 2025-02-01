package utenti;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {

    @Mock
    HttpSession session;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @InjectMocks
    Login servlet;

    @Test
    public void testDoPostCorrect() throws Exception {
        //setup
        User u = mock(User.class);
        try (MockedConstruction<UsersDaoDataSource> mockedDAO = mockConstruction(UsersDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveByEmail(anyString())).thenReturn(u);
        })) {
        when(request.getParameter("email")).thenReturn("test");
        when(request.getParameter("pwd")).thenReturn("test");
        when(u.getPwd()).thenReturn(Encrypter.hashPassword("test"));
        when(request.getSession()).thenReturn(session);
        //esecuzione
        servlet.doPost(request,response);

        //controllo
        verify(mockedDAO.constructed().get(0)).doRetrieveByEmail(anyString());
        verify(session).setAttribute("user", u);
        verify(response).sendRedirect("index.jsp");
        }
    }

    @Test
    public void testDoPostEmptyEmail() throws Exception {
        //setup

            when(request.getParameter("email")).thenReturn("");
            when(request.getParameter("pwd")).thenReturn("test");
            //esecuzione
            servlet.doPost(request,response);

            //controllo

            verify(request).setAttribute(eq("errors"), any(ArrayList.class));
            verify(response).sendRedirect("login.jsp");

    }

    @Test
    public void testDoPostEmptyPwd() throws Exception {
        //setup

        when(request.getParameter("email")).thenReturn("test");
        when(request.getParameter("pwd")).thenReturn("");
        //esecuzione
        servlet.doPost(request,response);

        //controllo

        verify(request).setAttribute(eq("errors"), any(ArrayList.class));
        verify(response).sendRedirect("login.jsp");

    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        Login serv = spy(new Login());
        doNothing().when(serv).doPost(request, response);

        serv.doGet(request, response);

        verify(serv).doPost(request, response);
    }

}
