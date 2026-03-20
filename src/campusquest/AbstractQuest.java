//Brady Keppler
//Abstract base

package campusquest;

public abstract class AbstractQuest implements Quest {

    private final int id;
    private final String title;
    private final int basePoints;
    private boolean completed;

    public AbstractQuest(int id, String title, int basePoints) {
        //no junk data
        if (id <= 0)
            throw new IllegalArgumentException("Quest id must be positive, got: " + id);
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Quest title can't be blank");
        if (basePoints <= 0)
            throw new IllegalArgumentException("basePoints must be > 0");

        this.id = id;
        this.title = title;
        this.basePoints = basePoints;
        this.completed = false;
    }

    //call this once the quest is finished; can't go back.
    protected void markCompleted() {
        if (completed)
            throw new IllegalStateException("Quest " + id + " already completed");
        completed = true;
    }

    //getters
    @Override public int getId() { return id; }
    @Override public String getTitle() { return title; }
    @Override public int getBasePoints() { return basePoints; }
    @Override public boolean isCompleted() { return completed; }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " (" + basePoints + " pts)" + (completed ? " -- done" : "");
    }
}
