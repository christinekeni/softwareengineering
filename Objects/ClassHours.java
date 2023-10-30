package Objects;

public class ClassHours {
    private int classHours_ID;
    private int time_Start;
    private int time_End;

    public ClassHours(int classHours_ID, int time_Start, int time_End) {
        this.classHours_ID = classHours_ID;
        this.time_Start = time_Start;
        this.time_End = time_End;
    }

    public int getClassHoursID() {
        return classHours_ID;
    }

    public void setClassHoursID(int classHours_ID) {
        this.classHours_ID = classHours_ID;
    }

    public int getTimeStart() {
        return time_Start;
    }

    public void setTimeStart(int time_Start) {
        this.time_Start = time_Start;
    }

    public int getTimeEnd() {
        return time_End;
    }

    public void setTimeEnd(int time_End) {
        this.time_End = time_End;
    }

    public void getClassHours() {
        // Code to get class hours information
        System.out.println("Class Hours: ID - " + classHours_ID + ", Start Time - " + time_Start + ", End Time - " + time_End);
    }

    public void updateClassHours(int newTimeStart, int newTimeEnd) {
        // Code to update class hours
        this.time_Start = newTimeStart;
        this.time_End = newTimeEnd;
        System.out.println("Class Hours updated successfully!");
    }

    public void editClassHours(int classHours_ID, int newTimeStart, int newTimeEnd) {
        // Code to edit class hours
        this.classHours_ID = classHours_ID;
        this.time_Start = newTimeStart;
        this.time_End = newTimeEnd;
        System.out.println("Class Hours edited successfully!");
    }
}
