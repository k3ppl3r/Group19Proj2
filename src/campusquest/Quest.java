//Brady Keppler
//common interface for all quest types so we can store them together and call completeFor
//without caring whether its a check-in, survey, or whatever else gets added later

package campusquest;

public interface Quest {

    int getId();
    String getTitle();
    int getBasePoints();

    boolean isCompleted();

    //does the quest for a given student, gives them their points, returns the amount
    int completeFor(Student s);
}
