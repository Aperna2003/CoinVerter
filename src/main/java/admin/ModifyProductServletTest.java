package admin;

import com.mysql.cj.Session;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ModifyProductServletTest {

    @Mock
    HttpSession session;

    @Mock
    ProductDaoDataSource source;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @InjectMocks
    ModifyProductServlet servlet;

    @Before
    public void setup() {
        servlet = new ModifyProductServlet();
    }

    @Test
    public void testDoPost() throws Exception {
        ProductBean PB = mock(ProductBean.class);

        try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class,(mock,context)->
                {when(mock.doRetrieveByKey(1)).thenReturn(PB);}
                )){

            when(request.getSession()).thenReturn(session);
            String id = "1";
            when(request.getParameter("id")).thenReturn(id);


            //when(mockedDAO.constructed().get(0).doRetrieveByKey(Integer.parseInt(id))).thenReturn(PB);
            //when(source.doRetrieveByKey(Integer.parseInt(id))).thenReturn(PB);

            servlet.doPost(request,response);

            verify(session).setAttribute("mprod",PB);
            verify(response).sendRedirect("admin/ModificaForm.jsp");
        }
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        ModifyProductServlet serv = spy(new ModifyProductServlet());
        doNothing().when(serv).doPost(request, response);

        serv.doGet(request, response);

        verify(serv).doPost(request, response);
    }
}