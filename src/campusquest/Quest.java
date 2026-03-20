//Brady Keppler
//Interface

package campusquest;

//every quest type has to implement these
public interface Quest {

    int getId();
    String getTitle();
    int getBasePoints();

    boolean isCompleted();

    //mark done, give the student their points, return how many they got
    int completeFor(Student s);
}
