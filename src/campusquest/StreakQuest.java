//Hannah Huang
//a quest that is completed for behavior over a certain number of days

package campusquest;

public class StreakQuest extends AbstractQuest 
{
	//various storage/utility variables
	private int days;
	
	//for this I mainly based it off of the code for VolunteerQuest with alterations to fit StreakQuest
	public StreakQuest(int questId, String questTitle, int basePoints, int days)
	{
		super(questId, questTitle, basePoints);
		
		//constructor to make sure class specific invariants are enforced, i.e. days must be > 0
		if(days <= 0) {
			throw new IllegalArgumentException("Days must be greater than 0");
		}
		this.days = days;
	}
	
	//function to get the number of days
	public int getDays()
	{
		return days;
	} //end function
	
	//completeFor function, uses basePoints + (days * 2) for total quest points
	@Override
	public int completeFor(Student s)
	{
		markCompleted(); //marking it complete
		int pointsAwarded = getBasePoints() + (getDays() * 2); //calculating points to award
		s.addPoints(pointsAwarded); //adding points
		return pointsAwarded; //returning the amount of points added
	} // end function
	
	//toString function
	@Override
	public String toString()
	{
		return super.toString() + " Streak[" + getDays() + "]"; //basically adding on class specific info to existing thing
	} //end function
	
}
