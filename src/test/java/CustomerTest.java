import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class CustomerTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/animal_shelter_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM customers *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void customer_instanceOfCustomer_true() {
    Customer newCustomer = new Customer("Test", "34334 Address", "503-420-6969", "Dog", "Mastiff");
    assertEquals(true, newCustomer instanceof Customer);
  }

}
