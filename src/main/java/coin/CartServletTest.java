package coin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.MockitoJUnitRunner;

import prodotti.ProductBean;
import prodotti.ProductDaoDataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServletTest {
    @Mock
    HttpSession session;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @InjectMocks
    CartServlet servlet;

    @Before
    public void setup() {
        servlet = new CartServlet();
    }

    @Test
    public void testDoPostADD() throws Exception {
        ProductBean PB = mock(ProductBean.class);
        try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveByKey(1)).thenReturn(PB);
        })) {

                when(request.getSession()).thenReturn(session);
                when(request.getParameter("id")).thenReturn("1");
                when(request.getParameter("action")).thenReturn("add");
                Carrello c = mock(Carrello.class);
                when(session.getAttribute("cart")).thenReturn(c);

                when(request.getParameter("quantity")).thenReturn("1");

                PrintWriter pw = mock(PrintWriter.class);
                when(response.getWriter()).thenReturn(pw);

                servlet.doPost(request, response);

                verify(c).addProduct(PB,1);
                verify(session).setAttribute("cart", c);
                verify(pw).print(anyInt());


        }
    }

    @Test
    public void testDoPostREMOVE() throws Exception {
        ProductBean PB = mock(ProductBean.class);

        try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveByKey(1)).thenReturn(PB);
        })) {
            {
                when(request.getSession()).thenReturn(session);
                when(request.getParameter("id")).thenReturn("1");
                when(request.getParameter("action")).thenReturn("remove");
                Carrello c = mock(Carrello.class);
                when(session.getAttribute("cart")).thenReturn(c);

                when(request.getParameter("quantity")).thenReturn("1");
                PrintWriter pw = mock(PrintWriter.class);
                when(response.getWriter()).thenReturn(pw);

                servlet.doPost(request, response);

                verify(c).deleteProduct(PB, 1);
                verify(session).setAttribute("cart", c);
                verify(pw).print(anyInt());
            }
        }
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        CartServlet serv = spy(new CartServlet());
        doNothing().when(serv).doPost(request, response);

        serv.doGet(request, response);

        verify(serv).doPost(request, response);
    }

}