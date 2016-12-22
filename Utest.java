import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;


@RunWith(MockitoJUnitRunner.class)
public class Utest {
	DAOImpl newDAOobj=new DAOImpl();
	Product  product;
	   @Mock 
	   Connection conn;
	   @Mock
	   PreparedStatement psmt;
	   @InjectMocks
	   DAOImpl newDAO = new DAOImpl();
	  
	   @ Test
	   public void testproductCon()
	   {
	   	Product product = new Product(20);
	   	Assert.assertEquals(20,product.getId());
	   	} 
	   
	   @Test
	   public void  tsettype(){
	   
	   Product product= new Product(20);
	   product.setType("MRI");
	   Assert.assertEquals("MRI", product.getType());
	   
	   }
	   
	   @Test
	   public void  tsetManufacturer(){
	    Product product= new Product(20);
	    product.setManufacturer("philips");
	    Assert.assertEquals("philips", product.getManufacturer()); 
	    }
	   
	   @Test
	   public void  tsetProductionDate(){
	   Product product= new Product(20);
	   product.setProductionDate("2016");
	   Assert.assertEquals("2016", product.getProductionDate());
	   }
	
	   @Test
	   public void  tsetExpirayDate(){
	   Product product= new Product(20);
	   product.setExpiryDate("2024");
	   Assert.assertEquals("2024", product.getExpiryDate());
	   
	   }
	   
	   
	   @Test (expected = DAOException.class)
       public void ExceptionCase() throws SQLException, DAOException{
	    when(conn.prepareStatement(anyString())).thenReturn(psmt);
	    when(psmt.executeUpdate()).thenThrow(new SQLException());
	    Product product= new Product(20);
	    newDAO.insertProduct(product);
}
		
	  @Test
	   public void HappyTest2() throws SQLException, DAOException{
	   	when(conn.prepareStatement(anyString())).thenReturn(psmt);
	   	ArgumentCaptor<String> stringcaptor = ArgumentCaptor.forClass(String.class);
	 	ArgumentCaptor<Integer> intcaptor = ArgumentCaptor.forClass(int.class);
	  	Product product= new Product(20);
	   	newDAO.insertProduct(product);
	    verify(psmt, times(4)).setString(anyInt(), stringcaptor.capture());
	   	verify(psmt, times(1)).setInt(anyInt(), intcaptor.capture());
               
	   	
	   	Assert.assertTrue(stringcaptor.getAllValues().get(0).equals(20));
	   }

        
	   
	 
	   
	   @Test

	   public void integrationTestdelete() throws SQLException, DAOException{
	     product= new Product(22);
	     product.setType("Xray");
	     product.setManufacturer("GE");
	     product.setProductionDate("2016");
	     product.setExpiryDate("2026");
	     newDAOobj.insertProduct( product);
	     Assert.assertNotNull( newDAOobj.getProduct(22));
	     newDAOobj.deleteProduct(22);
	     Assert.assertNull( newDAOobj.getProduct(22));
	   }
	     
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
}