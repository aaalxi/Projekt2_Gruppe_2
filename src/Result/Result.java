package Result;
abstract class Result {    //klasse til at skabe resultat objekter
    private boolean isCompetitive;
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
}
