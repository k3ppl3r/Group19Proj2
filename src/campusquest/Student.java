//Brady Keppler
//represents a student in the quest system, tracks their name and point total

package campusquest;

public class Student {

    private final String name;
    private int points;

    public Student(String name) {
        //cant have a nameless student, that would break everything downstream
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Student needs a name");
        this.name = name;
        this.points = 0;  // everyone starts at zero
    }

    public String getName() { return name; }

    public int getPoints() { return points; }

    //quests call this when they complete - passing 0 or negative here is a bug on the caller's end
    public void addPoints(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Can only add a positive point amount");
        points += amount;
    }

    //two students with the same name are treated as the same person
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();  //stays consistent with equals above
    }

    @Override
    public String toString() {
        return name + " | " + points + " pts";
    }
}
