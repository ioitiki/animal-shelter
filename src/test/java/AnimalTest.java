import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class AnimalTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/animal_shelter_test", null, null);
  }

  @After
  public void tearDown() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM animals *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void animal_instanceOfAnimal_true() {
    Animal newAnimal = new Animal("Test", "Male", "Dog", "Mastiff");
    assertEquals(true, newAnimal instanceof Animal);
  }

  @Test
  public void getAnimalName_returnsNameOfAnimal_Test() {
    Animal newAnimal = new Animal("Test", "Male", "Dog", "Mastiff");
    assertEquals("Test", newAnimal.getAnimalName());
  }

  @Test
  public void getAnimalGender_returnGenderOfAnimal_Male() {
    Animal newAnimal = new Animal("Test", "Male", "Dog", "Mastiff");
    assertEquals("Male", newAnimal.getAnimalGender());
  }

  @Test
  public void getAnimalType_returnTypeOfAnimal_Dog() {
    Animal newAnimal = new Animal("Test", "Male", "Dog", "Mastiff");
    assertEquals("Dog", newAnimal.getAnimalType());
  }

  @Test
  public void getAnimalBreed_returnBreedOfAnimal_Mastiff() {
    Animal newAnimal = new Animal("Test", "Male", "Dog", "Mastiff");
    assertEquals("Mastiff", newAnimal.getAnimalBreed());
  }

  @Test
  public void getAdmitDate_instantiatesWithCurrentTime_today() {
    Animal newAnimal = new Animal("Test", "Male", "Dog", "Mastiff");
    assertEquals(LocalDateTime.now().getDayOfWeek(), newAnimal.getAdmitDate().getDayOfWeek());
  }

  @Test
  public void getId_instantiatesWithAnId_1() {
    Animal newAnimal = new Animal("Test", "Male", "Dog", "Mastiff");
    newAnimal.save();
    assertEquals(true, newAnimal.getId() > 0);
  }

  @Test
  public void all_returnsAllInstancesOfTask_true() {
    Animal newAnimal1 = new Animal("Test", "Male", "Dog", "Mastiff");
    newAnimal1.save();
    Animal newAnimal2 = new Animal("Test2", "Male2", "Dog2", "Mastiff2");
    newAnimal2.save();
    assertEquals(true, Animal.all().get(0).equals(newAnimal1));
    assertEquals(true, Animal.all().get(1).equals(newAnimal2));
  }

  @Test
  public void find_returnsAnimalWithSameId_newAnimal2() {
    Animal newAnimal1 = new Animal("Test", "Male", "Dog", "Mastiff");
    newAnimal1.save();
    Animal newAnimal2 = new Animal("Test2", "Male2", "Dog2", "Mastiff2");
    newAnimal2.save();
    assertEquals(Animal.find(newAnimal2.getId()), newAnimal2);
  }

  @Test
  public void delete_deletesAnimal_true() {
    Animal newAnimal = new Animal("Test", "Male", "Dog", "Mastiff");
    newAnimal.save();
    int newAnimalId = newAnimal.getId();
    newAnimal.delete();
    assertEquals(null, Animal.find(newAnimalId));
  }

}
