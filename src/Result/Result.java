package Result;

import java.time.LocalDate;

abstract class Result {    //klasse til at skabe resultat objekter
    private boolean isCompetitive;
    private String name;
    private String category;
    private LocalDate date;
    private String discipline;           //enum i stedet
    private int distance;
    private double time;


    public Result(boolean isCompetitive, String name, String category, LocalDate date, String discipline,
                  int distance, double time) {
        this.isCompetitive=isCompetitive;
        this.name = name;
        this.category = category.toUpperCase();    //U18, O18
        this.date = date;
        this.discipline = discipline.toUpperCase();
        this.distance = distance;
        this.time = time;
    }


    public boolean getIsCompetitive () {
        return isCompetitive;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getDiscipline () {
        return discipline.toUpperCase();
    }

    public void setDiscipline (String discipline) {
        this.discipline=discipline.toUpperCase();
    }

    public Integer getDistance () {
        return distance;
    }

    public Double getTime () {
        return time;
    }

    public void setDate (LocalDate date) {
        this.date=date;
    }

    public LocalDate getDate () {
        return date;
    }

    public String getCategory () {
        return category.toUpperCase();
    }

    public String toString () {
        return "Er konkurrence: " + isCompetitive + " | Dato: " + date + " | " + name + " | " + category + " | "
                + discipline + " | " + distance + " m | " + time + " mm.ss";
    }
}
