import java.time.LocalDate;

public class ResultsCompetitive extends Result{

    private int placement;
    private String tournament;


    public ResultsCompetitive(String tournament, String name, String category, LocalDate date, String discipline,
                              int distance, double time, int placement) {

        super(true, name, category, date, discipline, distance, time);
        this.placement=placement;
        this.tournament=tournament.toUpperCase();
    }

    public void setPlacement (int placement) {
        this.placement = placement;
    }

    public int getPlacement () {
        return placement;
    }

    public String getTournament () {
        return tournament;
    }

    public void setTournament (String tournament) {
        this.tournament = tournament;
    }

    public String toString () {
        return super.toString() + " | " + tournament + " | Placering: " + placement;
    }
}