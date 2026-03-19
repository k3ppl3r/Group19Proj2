//Brady Keppler
//Interface

package campusquest;

//behavior contract for all quest types
public interface Quest {

    int getId();

    String getTitle();

    int getBasePoints();

    //returns true once the quest has been marked done
    boolean isCompleted();

    //update completion state
    //credit student
    //return
    int completeFor(Student s);
}
