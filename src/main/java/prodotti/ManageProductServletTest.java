package prodotti;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.MockitoJUnitRunner;
import utenti.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ManageProductServletTest {

    @Mock
    HttpSession session;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @InjectMocks
    private ManageProductServlet servlet;

    @Before
    public void setUp() throws Exception {
        servlet = new ManageProductServlet();
    }

    @Test
    public void testDoPostNoAdmin() throws Exception {
        //setup
        User u = mock(User.class);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(u);
        when(u.isAdmin()).thenReturn(false);

        //esecuzione
        servlet.doPost(request, response);

        verify(response).sendRedirect("index.jsp");
    }

    @Test
    public void testDoPostNullUser() throws Exception {
        //setup
        User u = mock(User.class);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("user")).thenReturn(null);

        //esecuzione
        servlet.doPost(request, response);

        verify(response).sendRedirect("index.jsp");
    }

    @Test
    public void testDoPostActivityNull() throws Exception {
        //setup
        ProductBean pb = mock(ProductBean.class);
        try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveByKey(anyInt())).thenReturn(pb);

        })) {
            User u = mock(User.class);
            when(request.getSession()).thenReturn(session);
            when(request.getSession().getAttribute("user")).thenReturn(u);
            when(u.isAdmin()).thenReturn(true);
            when(request.getParameter("activity")).thenReturn(null);
            when(request.getParameter("id")).thenReturn("1");


            //esecuzione
            servlet.doPost(request, response);

            //controllo
            verify(session).setAttribute("mod", pb);
            verify(response).sendRedirect("admin/ProdottoForm.jsp");

        }
    }

    @Test
    public void testDoPostActivityModifyEmpty() throws Exception {
        //setup
        ProductBean pb = mock(ProductBean.class);
        try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveByKey(anyInt())).thenReturn(pb);
            doNothing().when(mock).doUpdate(any(ProductBean.class));
        })) {
            User u = mock(User.class);
            when(request.getSession()).thenReturn(session);
            when(request.getSession().getAttribute("user")).thenReturn(u);
            when(u.isAdmin()).thenReturn(true);
            when(request.getParameter("activity")).thenReturn("modify");
            when(request.getParameter("id")).thenReturn("1");
            when(request.getParameter("valore")).thenReturn("");
            when(request.getParameter("prezzo")).thenReturn("");
            when(request.getParameter("tipo")).thenReturn("");
            when(request.getParameter("nome")).thenReturn("");
            ServletContext cx = mock(ServletContext.class);
            //config non funzionante
            when(servlet.getServletContext()).thenReturn(cx);
            when(cx.getRealPath("/")).thenReturn("./");


            //esecuzione
            servlet.doPost(request, response);

            //controllo
            verify(mockedDAO.constructed().get(0)).doUpdate(any(ProductBean.class));

        }
    }

    @Test
    public void testDoPostActivityModify() throws Exception {
        //setup
        ProductBean pb = mock(ProductBean.class);
        try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class, (mock, context) ->
        {
            when(mock.doRetrieveByKey(anyInt())).thenReturn(pb);
            doNothing().when(mock).doUpdate(any(ProductBean.class));
        })) {
            User u = mock(User.class);
            when(request.getSession()).thenReturn(session);
            when(request.getSession().getAttribute("user")).thenReturn(u);
            when(u.isAdmin()).thenReturn(true);
            when(request.getParameter("activity")).thenReturn("modify");
            when(request.getParameter("id")).thenReturn("1");
            when(request.getParameter("valore")).thenReturn("1");
            when(request.getParameter("prezzo")).thenReturn("1");
            when(request.getParameter("tipo")).thenReturn("moneta");
            when(request.getParameter("nome")).thenReturn("nome");
            Part p = mock(Part.class);
            when(request.getPart("foto")).thenReturn(p);
            when(p.getSubmittedFileName()).thenReturn("mock");

            //esecuzione
            servlet.doPost(request, response);

            //controllo
            verify(pb).setName("nome");
            verify(pb).setType("moneta");
            verify(pb).setValue(Double.parseDouble("1"));
            verify(pb).setPrice(Float.parseFloat("1"));
            verify(pb).setFoto(anyString());
            verify(mockedDAO.constructed().get(0)).doUpdate(any(ProductBean.class));
        }
    }

}
