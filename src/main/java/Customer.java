import org.sql2o.*;

public class Customer {
  private String customerName;
  private String customerAddress;
  private String customerPhone;
  private String typePref;
  private String breedPref;
  private int id;

  public Customer(String customerName, String customerAddress, String customerPhone, String typePref, String breedPref) {
    this.customerName = customerName;
    this.customerAddress = customerAddress;
    this.customerPhone = customerPhone;
    this.typePref = typePref;
    this.breedPref = breedPref;

  }



}
