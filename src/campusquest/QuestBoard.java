//Hannah Huang
//QuestBoard for coordinating quest storage and assignment
//using Eclipse IDE this time so hopefully there's no funky porting stuff that leads to AI flagging
//I feel like some of the simple printing things for when something goes wrong should throw errors, but I'm not sure what errors

package campusquest;

//various imports to make things work
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class QuestBoard {
	Map<Integer, Quest> questsById = new HashMap<>();
	Map<Student, List<Quest>> assignments = new HashMap<>();
	
	//addQuest
	public void addQuest(Quest q) 
	{
		//for brevity, I shorten "duplicate" to "dupe" a lot
		int holdId = q.getId(); //this is just to store the ID to search for dupes
		
		//using findQuest to check for duplicate IDs
		//if findQuest returns null, then we're good to add the quest
		if(findQuest(holdId) == null) {
			questsById.put(holdId, q);
			System.out.println("Quest added!");
		}
		//else, throw error
		else {
			throw new IllegalArgumentException("Quest has same ID as another.");
		}
		//end dupe check/adding if/else
		
	} //end addQuest function
	
	//findQuest function. I don't know if this will be used outside of this class, so it's public just in case
	public Quest findQuest(int id)
	{
		Quest found = null; //going to return this after the function runs through
		
		//searching through the list to find the quest
		for(Integer key : questsById.keySet()) {
			Quest holdQuest = questsById.get(key); //temporary hold variable to keep from making a long getting chain
			
			//basically, if a quest is found with an ID matching the query value, set the return variable to be that quest
			if(holdQuest.getId() == id) {
				found = holdQuest;
			}
		} //end search loop
		
		return found; //returning whatever found value
	} //end findQuest function
	
	//assignQuest function. currently don't know what to assume in terms of assigning the same quest multiple times to the same student
	public void assignQuest(Student s, int questId) 
	{
		//note: I'm making this with the assumption that needed students will be on the list
		//hold/utility variables
		boolean studentFound = false; //for if student exists
		Quest questToAssign = findQuest(questId); //getting the quest to assign
		
		//searching for the student in the assignments map
		for(Student key : assignments.keySet()) {
			//if the student is found, make sure it knows
			if(key.equals(s)) {
				studentFound = true;
			}
		} //end search loop
		
		//if the student has been found and the quest has been found, assign the quest to the student
		if(studentFound == true || questToAssign != null) {
			assignments.get(s).add(questToAssign);
		}
		//if student or quest couldn't be found, say it
		else if(studentFound == false) {
			throw new IllegalArgumentException("Could not find student.");
		}
		else if(questToAssign == null) {
			throw new IllegalArgumentException ("Could not find quest.");
		} //end assignment checks
	} //end function
	
	//completeQuest
	public int completeQuest(Student s, int questId)
	{
		//hold/utility variables
		boolean studentFound = false;
		int questIn = -1;
		int pointsAwarded = 0; //to be returned
		
		//searching for the student in the assignments map
		for(Student key : assignments.keySet()) {
			//if the student is found, make sure it knows
			if(key.equals(s)) {
				studentFound = true;
			}
		} //end search loop
		
		//if the student is found, find the quest
		if(studentFound == true) {
			List<Quest> hold = assignments.get(s); //holding the list of quests to search through
			
			//searching for the quest, getting the index of it
			for(int i = 0; i < hold.size(); i++) {
				if(hold.get(i).getId() == questId) {
					questIn = i;
					break;
				} //found quest
			} //end search loop
			
			//if the index has been found, figure out whether to mark it complete
			if(questIn != -1) {
				//checking if the quest has been completed, marking it complete if it hasn't
				if(hold.get(questIn).isCompleted() == false) {
					pointsAwarded = hold.get(questIn).completeFor(s); //marking it complete
				}
				//if it has been completed, throw error
				else {
					throw new IllegalStateException("Quest already complete");
				} //is complete check if/else
			}
			//else, let the user know that quest is not assigned to the student
			else {
				System.out.println("That quest isn't assigned to this student.");
			} //end finding quest if/else
		}
		//if student isn't found, let the user know
		else {
			System.out.println("Could not find student.");
		} //end student found if/else
		
		return pointsAwarded; //return value
	} //end function
	
	//printAllQuests
	public void printAllQuests() 
	{
		//hold variable for the quest
		String hold = "hold";
		
		//literally just a loop to get the quests, convert them to strings, and print them
		for(Integer key : questsById.keySet()) {
			hold = questsById.get(key).toString(); //setting hold to be the title of the quest
			System.out.println(hold + "\n"); //printing, I think \n is the newline character? I had to google it
		} //end printing loop
	} //end function
	
	//printAssignmentsFor
	public void printAssignmentsFor(Student s)
	{
		//hold variable for the student's task list
		List<Quest> hold = null; 
		//boolean for if the student was found
		boolean studentFound = false;
		
		//looping through to find the student
		for(Student key : assignments.keySet()) {
			//basically, if the key matches up with the student, get their quest list and show the student was found
			if(key.equals(s)) {
				hold = assignments.get(s);
				studentFound = true;
			}
		} //end search loop
		
		//if the student was found, print their quest list
		if(studentFound == true) {
			//hold variable for quest printing
			String printHold = "hold";
			
			System.out.println(s.getName() + ":\n"); //printing name and newline
			
			//quest printing loop
			for(int i = 0; i < hold.size(); i++) {
				printHold = hold.get(i).toString(); //converting the quest to a string
				System.out.println(printHold + "\n"); //should print a list of the quests through the loop
			} //end printing loop
		} 
		//if student wasn't found, let the user know
		else {
			System.out.println("Could not find student");
		} //end student found check if/else
		
	} //end function
}
