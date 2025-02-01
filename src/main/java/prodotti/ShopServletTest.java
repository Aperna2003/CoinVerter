package prodotti;

import coin.CartServlet;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShopServletTest {

    @Mock
    HttpSession session;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @InjectMocks
    ShopServlet servlet;

    @Test
    public void testDoPostRicercaEmpty() throws Exception {
        //setup
            ArrayList<ProductBean> p = mock(ArrayList.class);
            try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class, (mock, context) ->
            {
                when(mock.doRetrieveAvailable()).thenReturn(p);
            })) {



            when(request.getSession()).thenReturn(session);

            //esecuzione
            servlet.doPost(request, response);

            //controllo
            verify(mockedDAO.constructed().get(0)).doRetrieveAvailable();
            session.setAttribute(eq("products"),any(ArrayList.class));
            verify(response).sendRedirect("shop.jsp");
            }
    }

    @Test
    public void testDoPostRicercaFilter() throws Exception {
        //setup
        ArrayList<ProductBean> p = mock(ArrayList.class);
        try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveByName(anyString())).thenReturn(p);
        })) {


            when(request.getSession()).thenReturn(session);
            when(request.getParameter("filter")).thenReturn("filter");

            //esecuzione
            servlet.doPost(request, response);

            //controllo
            verify(mockedDAO.constructed().get(0)).doRetrieveByName(anyString());
            session.setAttribute(eq("products"), any(ArrayList.class));
            verify(response).sendRedirect("shop.jsp");
        }
    }

    @Test
    public void testDoPostCategoria() throws Exception {
        //setup
        ArrayList<ProductBean> p = mock(ArrayList.class);
        try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveByCategory(anyString())).thenReturn(p);
        })) {


            when(request.getSession()).thenReturn(session);
            when(request.getParameter("filter")).thenReturn("filter");
            when(request.getParameter("action")).thenReturn("categoria");
            //esecuzione
            servlet.doPost(request, response);

            //controllo
            verify(mockedDAO.constructed().get(0)).doRetrieveByCategory(anyString());
            session.setAttribute(eq("products"), any(ArrayList.class));
            verify(response).sendRedirect("shop.jsp");
        }
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        ShopServlet serv = spy(new ShopServlet());
        doNothing().when(serv).doPost(request, response);

        serv.doGet(request, response);

        verify(serv).doPost(request, response);
    }
}
