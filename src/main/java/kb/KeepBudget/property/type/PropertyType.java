package kb.KeepBudget.property.type;

public enum PropertyType {
    APARTMENT("아파트"),
    MULTIPLEX_HOUSING("연립다세대"),
    OFFICETEL("오피스텔");

    private final String name;

    PropertyType(String name){
        this.name = name;
    }

}
