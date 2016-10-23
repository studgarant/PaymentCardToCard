import org.junit.Test;
import com.cahek.ws.ConnectionDB;
import java.sql.Connection;
import static org.junit.Assert.assertNotNull;
public class ConnectionH2 {

  @Test
  public void connect() throws Exception {
      Connection connection = (Connection) ConnectionDB.getConnection();
      assertNotNull(connection);
  }
  

}
