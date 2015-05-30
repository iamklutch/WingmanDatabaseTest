package yukidev.com.wingmandatabasetest;

/**
 * Created by James on 5/29/2015.
 */
public class Airman {

    private String mName;
    private int mAge;
    private String mRank;

    public Airman (String name, int age, String rank) {

        name = "";
        age = 0;
        rank = "";

    }

    public String getName() {return mName;}

    public void setName(String name) {mName = name;}

    public int getAge() {return mAge;}

    public void setAge(int age) {mAge = age;}

    public String getRank() {return mRank;}

    public void setRank(String rank) {mRank = rank;}


}
