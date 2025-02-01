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
public class AccountAdminServletTest {

    @Mock
    HttpSession session;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @InjectMocks
    AccountAdminServlet servlet;

    @Test
    public void testDoPost() throws Exception {
        //setup
        when(request.getSession()).thenReturn(session);
        ArrayList<User> u = mock(ArrayList.class);
        try (MockedConstruction<UsersDaoDataSource> mockedDAO = mockConstruction(UsersDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveAll("")).thenReturn(u);
        })) {


            //esecuzione
            servlet.doPost(request, response);

            //controllo
            verify(mockedDAO.constructed().get(0)).doRetrieveAll("");
            verify(response).sendRedirect("admin/gestioneAccount.jsp");
        }
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        AccountAdminServlet serv = spy(new AccountAdminServlet());
        doNothing().when(serv).doPost(request, response);

        serv.doGet(request, response);

        verify(serv).doPost(request, response);
    }

}
