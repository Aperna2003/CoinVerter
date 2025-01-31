package prodotti;

import coin.OrderDaoDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductAdminServletTest {

    @Mock
    HttpSession session;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @InjectMocks
    ProductAdminServlet servlet;

    @Before
    public void setup() {
        servlet = new ProductAdminServlet();
    }

    @Test
    public void testDoPost() throws Exception {
        ArrayList prd = mock(ArrayList.class);
        //setup
        try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveAll("")).thenReturn(prd);
        })) {



        when(request.getSession()).thenReturn(session);

        //esecuzione
        servlet.doPost(request, response);

        //controllo
        verify(session).setAttribute("prd", prd);
        response.sendRedirect("admin/gestioneProdotti.jsp");

        }
    }
}
