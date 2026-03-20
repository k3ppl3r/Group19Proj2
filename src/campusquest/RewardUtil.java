//Fady Francis
//Utility Class for generics

package campusquest;

//private constructor for the utility class
public class RewardUtil{

    private RewardUtil(){}
//for loop that is going to run through the list and print everything
    public static <T> void printAll(java.util.List<T> itemList){
        for(int i = 0; i < itemList.size(); i++){
            System.out.println(itemList.get(i));
        }
        
    }
//same idea as the other for loop but no printing instead adding up all the points
    public static int sumPoints(java.util.List<? extends Student> studentList){
    int totalPoints = 0;
        for(int i = 0; i < studentList.size(); i++){
            totalPoints = totalPoints + studentList.get(i).getPoints();
        }
        return totalPoints;
    }
}