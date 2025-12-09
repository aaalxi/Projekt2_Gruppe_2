import java.util.Comparator;

public class ResultComparitor implements Comparator <Result> {

        @Override
        public int compare(Result r1, Result r2) {
            return r1.getTime().compareTo(r2.getTime());
        }
    }
