//Brady Keppler
//Student model and equals/hashcode

package campusquest;

public class Student {

    private final String name;
    private int points;

    public Student(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Student needs a name");
        this.name = name;
        this.points = 0;
    }

    public String getName() { return name; }

    public int getPoints() { return points; }

    public void addPoints(int amount) {
        //no negatives no zeros
        if (amount <= 0)
            throw new IllegalArgumentException("Can only add a positive point amount");
        points += amount;
    }

    //same name = same student
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return this.name.equals(other.name);
    }

    //keeps hashCode in sync with equals
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name + " | " + points + " pts";
    }
}
