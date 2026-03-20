//Fady Francis
// A quest that is compeleted by volunteering a number of hours

package campusquest;

public class VolunteerQuest extends AbstractQuest{

    private int hoursVolunteered;
    public VolunteerQuest(int questId, String questTitle, int basePoints, int hoursVolunteered){
    
    super(questId, questTitle, basePoints);
//this is just an important constructor incase they attempt to put in negative hours or zero for volunteer hours
    if(hoursVolunteered <= 0)
        throw new IllegalArgumentException("hours must contain some valid number");
    this.hoursVolunteered = hoursVolunteered;
    }

    public int getHours(){
        return hoursVolunteered;
    }
//for this quest points are going to be decided by multiplying base points given to the student by the number of hours they have volunteered 
    @Override
    public int completeFor(Student student){
        markCompleted();
        int pointsAwarded = getBasePoints() * hoursVolunteered;
        student.addPoints(pointsAwarded);
        return pointsAwarded;
    }
    @Override
    public String toString(){
        return super.toString() + " Volunteer[" + hoursVolunteered + "]";
    }
}