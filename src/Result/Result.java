package Result;
class Result {    //klasse til at skabe resultat objekter
    String name;
    String discipline;
    int distance;
    double time;
    int placement;
    String kategory;

    public Result(String name, String discipline, int distance, double time,
                  int placement, String kategory) {
        this.name = name;
        this.discipline = discipline;
        this.distance = distance;
        this.time = time;
        this.placement = placement;
        this.kategory = kategory;
    }
}