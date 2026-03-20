//Fady Francis
//A quest that is going to get completed by checking in at a campus event

package campusquest;

public class EventCheckInQuest extends AbstractQuest{

private String eventName;
    public EventCheckInQuest(int questId, String questTitle, int basePoints, String event) {
        super(questId, questTitle, basePoints);
        //constructor for safety 
        if (event == null || event.isBlank()) {
            throw new IllegalArgumentException("Event name cannot be null or blank");
        }
        this.eventName = event;
    }

    public String getEventName(){
        return eventName;
    }
    //this quests point system is simple they jsut get the points they got    
    @Override
    public int completeFor(Student student) {
        markCompleted();
        int pointsAwarded = getBasePoints();
        student.addPoints(pointsAwarded);
        return pointsAwarded;
    }

    @Override
    public String toString() {
        return super.toString() + " EventCheckIn[" + eventName + "]";
    }
}