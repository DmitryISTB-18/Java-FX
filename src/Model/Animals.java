package Model;

//Главный общий класс
public abstract class Animals implements Request {

    // Fields.
    private String animalsName;


    // Getters and Setters.

    public String getAnimalsName() {

        return animalsName;
    }
    public void setAnimalsName(String animalsName) {

        this.animalsName = animalsName;
    }

    public String toString() {
        return "Название: " + getAnimalsName() ;
    }
    public int quanAni() {
        String str = getAnimalsName();
        int count = 0;
        for(int i = 0; i<str.length(); i++) {
            count++;
        }
        return count;

    }
}
