//Hannah Huang
//QuestBoard for coordinating quest storage and assignment
//using Eclipse IDE this time so hopefully there's no funky porting stuff that leads to AI flagging

package campusquest;

//various imports to make things work
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class QuestBoard {
	private Map<Integer, Quest> questsById = new HashMap<>();
	private Map<Student, List<Quest>> assignments = new HashMap<>();
	
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
		//note: I'm making this with the assumption that all students used will exist and be on the HashSet, because as of writing I don't know hwo to search that here without making the HashSet public
		//hold/utility variables
		boolean studentFound = false; //for if student is on the board
		Quest questToAssign = findQuest(questId); //getting the quest to assign
		
		//if the quest to assign doesn't exist, throw error
		if(questToAssign == null) {
			throw new IllegalArgumentException("Could not find quest.");
		}
		
		//searching for the student on the assignment board via the created equals function
		for(Student key : assignments.keySet()) {
			if(key.equals(s)) {
				studentFound = true;
			}
		} //end search loop
		
		//if the student isn't on the assignments map, put them in
		if(studentFound == false) {
			assignments.put(s, new ArrayList<>());
			
			//assigning quest if nothing went wrong
			assignments.get(s).add(questToAssign);
			System.out.println("Quest assigned!");
		}
		//if the student is on the quest board, make sure they haven't already been assigned the quest
		else if(studentFound == true && questToAssign != null) {
			List<Quest> hold = assignments.get(s); //holding the student's assignments
			
			//search loop by ID
			for(int i = 0; i < hold.size(); i++) {
				//if the quest has already been assigned, throw error
				if(hold.get(i).getId() == questToAssign.getId()) {
					throw new IllegalArgumentException("Quest already assigned to this student");
				}
			} //end searching for loop
			
			//add quest if nothing went wrong
			assignments.get(s).add(questToAssign);
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
				throw new IllegalArgumentException("That quest isn't assigned to this student.");
			} //end finding quest if/else
		}
		//if student isn't found, let the user know
		else {
			throw new IllegalArgumentException("Could not find student.");
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
		
		System.out.println(s.toString() + ":\n"); //printing student and newline
		
		//if the student was found, print their quest list
		if(studentFound == true) {
			RewardUtil.printAll(hold);
		} 
		//if student wasn't found, let the user know
		else {
			System.out.println("No quests assigned.");
		} //end student found check if/else
		
	} //end function
}
