//Hannah Huang
//main program, demo

package campusquest;

//should make everything work
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	//main program for the demos
	public static void main(String[] args)
	{
		//creating Students HashSet
		Set<Student> students = new HashSet<>();
		Student Adeline = new Student("Adeline");
		Student Susie = new Student("Susie");
		Student John1 = new Student("John");
		Student John2 = new Student("John2");
		students.add(Adeline);
		students.add(Susie);
		students.add(John1);
		students.add(John2);
		
		System.out.println("Size of students set: " + students.size());
		
		//creating quests
		StreakQuest streak1 = new StreakQuest(1, "Attendance 3", 1, 3);
		StreakQuest streak2 = new StreakQuest(2, "Attendance 5", 1, 5);
		EventCheckInQuest event1 = new EventCheckInQuest(3, "Event (5pts)", 5, "eventFive");
		EventCheckInQuest event2 = new EventCheckInQuest(4, "Event (10 pts)", 10, "eventTen");
		VolunteerQuest volunteer1 = new VolunteerQuest(5, "Plant Trees", 3, 3);
		VolunteerQuest volunteer2 = new VolunteerQuest(6, "Grounds Cleanup", 3, 5);
		StreakQuest streakDupe = new StreakQuest(3, "Dupe Streak", 2, 2);
		
		//adding quests to the board
		QuestBoard questBoard = new QuestBoard();
		questBoard.addQuest(streak1);
		questBoard.addQuest(streak2);
		questBoard.addQuest(volunteer1);
		questBoard.addQuest(volunteer2);
		questBoard.addQuest(event1);
		questBoard.addQuest(event2);
		try {
			questBoard.addQuest(streakDupe); //should throw exception
		} catch (IllegalArgumentException e) {
			System.out.println("Caught expected duplicate ID exception: " + e.getMessage());
		}
		
		//assigning quests
		questBoard.assignQuest(Susie, 1);
		questBoard.assignQuest(Susie, 3);
		questBoard.assignQuest(Adeline, 6);
		questBoard.assignQuest(John1, 4);
		
		//completing quests
		questBoard.completeQuest(Susie, 1);
		questBoard.completeQuest(Adeline, 6);
		questBoard.completeQuest(Susie, 3);
		
		//putting this in a list because that's how the reward util thing works
		List<Student> studentList = new ArrayList<>();
		studentList.add(Susie);
		studentList.add(Adeline);
		studentList.add(John1);
		studentList.add(John2);
		
		//printing
		System.out.println("Quests:\n");
		questBoard.printAllQuests();
		System.out.println("\n");
		System.out.println("Students, point totals, assignments\n");
		questBoard.printAssignmentsFor(Susie);
		System.out.println("\n");
		questBoard.printAssignmentsFor(Adeline);
		System.out.println("\n");
		questBoard.printAssignmentsFor(John1);
		System.out.println("\n");
		questBoard.printAssignmentsFor(John2);
		System.out.println("Total points: " + RewardUtil.sumPoints(studentList) + "\n");
		
	} //end function
}
