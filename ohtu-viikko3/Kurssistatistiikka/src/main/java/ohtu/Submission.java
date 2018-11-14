package ohtu;

public class Submission {
    private int week;
    private int hours;
    private String course;
    private int[] exercises;

    public void setWeek(int week) {
        this.week = week;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public int getHours() {
        return hours;
    }

    public String getCourse() {
        return course;
    }

    public int[] getExercises() {
        return exercises;
    }

    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        String s = course+", viikko "+week+" tehtyjä tehtäviä yhteensä "
                + exercises.length + " aikaa kului " + hours + " tehdyt tehtävät:";
        for (int e : exercises){
            s += " " + e;
        }
        return s;
    }
    
}