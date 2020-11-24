package Model;


//Класс грузовиков
public class Fish extends Animals {

    // Fields.
    private String habitatType;

    public Fish(String fishName, String habitatType) {
        setAnimalsName(fishName);
        setHabitatType(habitatType);
    }

    public Fish() {

    }

    public String getHabitatType() {

        return habitatType;
    }
    public void setHabitatType(String habitatType) {

        this.habitatType = habitatType;
    }


    @Override
    public String toString() {
        return "Рыба:\n  - Название: " + getAnimalsName() + "\n  - Место проживания: " + habitatType + "\n";
    }



}
