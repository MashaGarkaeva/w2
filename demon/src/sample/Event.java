package sample;

public class Event {

    private String NameEventColum;
    private String DataEventColum;

    public Event(String nameEventColum, String dataEventColum) {
        NameEventColum = nameEventColum;
        DataEventColum = dataEventColum;
    }

    public String getNameEventColum() {
        return NameEventColum;
    }

    public void setNameEventColom(String nameEventColum) {
        NameEventColum = nameEventColum;
    }

    public String getDataEventColum() {
        return DataEventColum;
    }

    public void setDataEventColum(String dataEventColum) {
        DataEventColum = dataEventColum;
    }
}
