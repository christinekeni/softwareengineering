/* package Objects;

public class Fingerprint {
    private int student_ID;
    private int student_FingerPrintID;
    private String FingerPrint_Record;

    public Fingerprint(int student_ID, int student_FingerPrintID, String FingerPrint_Record) {
        this.student_ID = student_ID;
        this.student_FingerPrintID = student_FingerPrintID;
        this.FingerPrint_Record = FingerPrint_Record;
    }

    // Getter and setter methods for student_ID, student_FingerPrintID, and FingerPrint_Record

    public void captureStudentAttendance() {
        // Code to capture student attendance using the fingerprint
        Login student = findStudentByID(student_ID);
        if (student != null) {
            student.markAttendance();
            System.out.println("Attendance captured for student: " + student_ID);
        } else {
            System.out.println("Student with ID " + student_ID + " not found.");
        }
    }
}

 */
