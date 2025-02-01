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

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ManageAccountServletTest {
    @Mock
    HttpSession session;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @InjectMocks
    ManageAccountServlet servlet;

    @Test
    public void doPostModifyAdmin() throws Exception {
        //setup
        User u = mock(User.class);
        try (MockedConstruction<UsersDaoDataSource> mockedDAO = mockConstruction(UsersDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveByEmail(anyString())).thenReturn(u);
        })) {
            when(request.getSession()).thenReturn(session);
            when(request.getParameter("activity")).thenReturn("modify");
            when(request.getParameter("email")).thenReturn("test@test.com");
            when(u.isAdmin()).thenReturn(true);
            //esecuzione
            servlet.doPost(request, response);

            //controllo
            verify(u).setAdmin(false);
            verify(mockedDAO.constructed().get(0)).doUpdate(any(User.class));
            verify(response).sendRedirect("GestioneACC");


        }
    }

    @Test
    public void doPostModifyNoAdmin() throws Exception {
        //setup
        User u = mock(User.class);
        try (MockedConstruction<UsersDaoDataSource> mockedDAO = mockConstruction(UsersDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveByEmail(anyString())).thenReturn(u);
        })) {
            when(request.getSession()).thenReturn(session);
            when(request.getParameter("activity")).thenReturn("modify");
            when(request.getParameter("email")).thenReturn("test@test.com");
            when(u.isAdmin()).thenReturn(false);
            //esecuzione
            servlet.doPost(request, response);

            //controllo
            verify(u).setAdmin(true);
            verify(mockedDAO.constructed().get(0)).doUpdate(any(User.class));
            verify(response).sendRedirect("GestioneACC");
        }
    }

    @Test
    public void doPostRemove() throws Exception {
        //setup
        User u = mock(User.class);
        try (MockedConstruction<UsersDaoDataSource> mockedDAO = mockConstruction(UsersDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveByEmail(anyString())).thenReturn(u);
        })) {
            when(request.getSession()).thenReturn(session);
            when(request.getParameter("activity")).thenReturn("remove");
            when(request.getParameter("email")).thenReturn("test@test.com");

            //esecuzione
            servlet.doPost(request, response);

            //controllo
            verify(mockedDAO.constructed().get(0)).doDelete("test@test.com");
            verify(response).sendRedirect("GestioneACC");


        }
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        ManageAccountServlet serv = spy(new ManageAccountServlet());
        doNothing().when(serv).doPost(request, response);

        serv.doGet(request, response);

        verify(serv).doPost(request, response);
    }

}
