import org.sql2o.*;
import java.util.*;
import java.time.*;

public class Animal {
  private String animalName;
  private String animalGender;
  private LocalDateTime admitDate;
  private String animalType;
  private String animalBreed;
  private int id;

  public Animal(String animalName, String gender, String animalType, String animalBreed) {
    this.animalName = animalName;
    this.animalGender = gender;
    this.animalType = animalType;
    this.animalBreed = animalBreed;
    admitDate = LocalDateTime.now();
  }

  public String getAnimalName() {
    return animalName;
  }

  public String getAnimalGender() {
    return animalGender;
  }

  public String getAnimalType() {
    return animalType;
  }

  public String getAnimalBreed() {
    return animalBreed;
  }

  public LocalDateTime getAdmitDate() {
    return admitDate;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherAnimal){
    if (!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getAnimalName().equals(newAnimal.getAnimalName()) &&
             this.getId() == newAnimal.getId() &&
             this.getAnimalGender().equals(newAnimal.getAnimalGender()) &&
             this.getAnimalType().equals(newAnimal.getAnimalType()) &&
             this.getAnimalBreed().equals(newAnimal.getAnimalBreed());
    }
  }

  public static List<Animal> all() {
    String sql = "SELECT id, animalName, animalGender, animalType, animalBreed FROM animals";
    try(Connection con = DB.sql2o.open()) {
     return con.createQuery(sql).executeAndFetch(Animal.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals(animalName, animalGender, animalType, animalBreed) VALUES (:animalName, :animalGender, :animalType, :animalBreed)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animalName", this.animalName)
        .addParameter("animalGender", this.animalGender)
        .addParameter("animalType", this.animalType)
        .addParameter("animalBreed", this.animalBreed)
        .executeUpdate()
        .getKey();
    }
  }

  public static Animal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where id=:id";
      Animal animal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Animal.class);
      return animal;
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM animals WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }



}
