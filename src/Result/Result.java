package Result;

import java.time.LocalDate;

abstract class Result {    //klasse til at skabe resultat objekter
    private boolean isCompetitive;
    private LocalDate date;
    private String name;
    private String category;
    private String discipline;           //enum i stedet
    private int distance;
    private double time;


    public Result(boolean isCompetitive, String name, String category, String discipline, int distance, double time) {
        this.isCompetitive=isCompetitive;
        this.name = name;
        this.category = category;    //U18, O18
        this.discipline = discipline;
        this.distance = distance;
        this.time = time;
    }


    public boolean getIsCompetitive () {
        return isCompetitive;
    }

    public void setIsCompetitive () {
        this.isCompetitive=isCompetitive;
    }

    public String getName () {
        return name;
    }

    public void setName () {
        this.name = name;
    }

    public String getCategory () {
        return category;
    }

    public void setCategory (String category) {
        this.category = category;
    }

    public String getdiscipline () {
        return discipline;
    }

    public void setDiscipline (String discipline) {
        this.discipline=discipline;
    }

    public int getDistance () {
        return distance;
    }

    public void setDistance (int distance) {
        this.distance=distance;
    }

    public double getTime () {
        return time;
    }

    public void setTime () {
        this.time=time;
    }


}
