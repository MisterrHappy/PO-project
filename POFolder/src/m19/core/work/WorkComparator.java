package m19.core.work;

import java.util.Comparator;
import m19.core.work.Work;

public class WorkComparator implements Comparator<Work> {
    @Override
    public int compare(Work a, Work b) {
        return a.hashCode() - b.hashCode();
    }
}