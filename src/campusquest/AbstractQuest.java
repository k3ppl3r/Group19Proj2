//Brady Keppler
//Base class for all quests, handles the common stuff so subclasses dont have to repeat it

package campusquest;

public abstract class AbstractQuest implements Quest {

    private final int id;
    private final String title;
    private final int basePoints;
    private boolean completed;

    public AbstractQuest(int id, String title, int basePoints) {
        //id has to be a real positive number, 0 or negative doesnt make sense for a quest
        if (id <= 0)
            throw new IllegalArgumentException("Quest id must be positive, got: " + id);
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Quest title can't be blank");
        // points have to actually mean something
        if (basePoints <= 0)
            throw new IllegalArgumentException("basePoints must be > 0");

        this.id = id;
        this.title = title;
        this.basePoints = basePoints;
        this.completed = false;  // starts incomplete, obviously
    }

    //once a quest is done its done, no redoing it
    protected void markCompleted() {
        if (completed)
            throw new IllegalStateException("Quest " + id + " is already marked complete");
        completed = true;
    }

    @Override public int getId() { return id; }
    @Override public String getTitle() { return title; }
    @Override public int getBasePoints() { return basePoints; }
    @Override public boolean isCompleted() { return completed; }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " (" + basePoints + " pts)" + (completed ? " -- done" : "");
    }
}
