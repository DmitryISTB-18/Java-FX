package Model;

public class Birds extends Animals {

    // Fields.
    private boolean relationshipFlight;

    public Birds(String birdsName, boolean relationshipFlight) {
        super.setAnimalsName(birdsName);
        this.relationshipFlight = relationshipFlight;
    }

    public Birds() {

    }

    public boolean isRelationshipFlight() {

        return relationshipFlight;
    }
    public void setRelationshipFlight(boolean relationshipFlight) {

        this.relationshipFlight = relationshipFlight;
    }

    @Override
    public String toString() {
        return "Птица:\n - Название: " + getAnimalsName() + "\n - Перелётные(true) или оставшиеся на зимовку(false) : " + relationshipFlight + "\n" ;
    }




}

