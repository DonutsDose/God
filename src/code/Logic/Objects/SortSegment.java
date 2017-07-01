package code.Logic.Objects;

/**
 * Created by DonutsDose-PC on 30.06.2017.
 */
public class SortSegment implements Comparable<SortSegment>{

    public int x = 0;
    public String name = "";

    public SortSegment(int x, String name) {
        this.x = x;
        this.name = name;
    }

    @Override
    public int compareTo(SortSegment o) {
        if (o.x == x) return 0;
        if (o.x < x) return -1; else return 1;
    }
}
