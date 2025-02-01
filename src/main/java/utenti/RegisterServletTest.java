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
public class RegisterServletTest {

    @Mock
    HttpSession session;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @InjectMocks
    RegisterServlet servlet;


    @Test
    public void doPostCorrect() throws Exception {
        //setup
        User u = mock(User.class);
        try (MockedConstruction<UsersDaoDataSource> mockedDAO = mockConstruction(UsersDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveByEmail(anyString())).thenReturn(u);
        })) {
            when(request.getParameter("name")).thenReturn("name");
            when(request.getParameter("surname")).thenReturn("surname");
            when(request.getParameter("email")).thenReturn("email");
            when(request.getParameter("pwd")).thenReturn("pwd");

            //esecuzione
            servlet.doPost(request, response);

            //controllo
            verify(u).setNome("name");
            verify(u).setCognome("surname");
            verify(u).setEmail("email");
            verify(u).setPwd(Encrypter.hashPassword("pwd"));
            verify(u).setAdmin(false);
            verify(mockedDAO.constructed().get(0)).doSave(any(User.class));
            verify(response).sendRedirect("login.jsp");
        }
    }


    @Test
    public void testDoGet() throws ServletException, IOException {
        RegisterServlet serv = spy(new RegisterServlet());
        doNothing().when(serv).doPost(request, response);

        serv.doGet(request, response);

        verify(serv).doPost(request, response);
    }

}
