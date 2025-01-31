package prodotti;

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
    public void testDoPostEmpty() throws Exception {
        //setup
//        try (MockedConstruction<ArrayList> mockedAL = mockConstruction(ArrayList.class, (mock, context) ->
//        {
//        })) {
            ArrayList<ProductBean> p = mock(ArrayList.class);
            try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class, (mock, context) ->
            {
                when(mock.doRetrieveAvailable()).thenReturn(p);
            })) {

            }

            when(request.getSession()).thenReturn(session);
            when(request.getParameter("filter")).thenReturn(null);
            when(session.getAttribute("action")).thenReturn(null);

            //esecuzione
            servlet.doPost(request, response);

            //controllo
            session.setAttribute("products", null);

    }
    }
//}