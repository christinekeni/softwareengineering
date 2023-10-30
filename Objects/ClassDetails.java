package Objects;

public class ClassDetails {
        private int classDetails_ID;
        private int classRoom_ID;
        private int classHours_ID;
        private int lecturer_ID;
        private int course_Code;

        public ClassDetails(int classDetails_ID, int classRoom_ID, int classHours_ID, int lecturer_ID, int course_Code) {
            this.classDetails_ID = classDetails_ID;
            this.classRoom_ID = classRoom_ID;
            this.classHours_ID = classHours_ID;
            this.lecturer_ID = lecturer_ID;
            this.course_Code = course_Code;
        }

        public int getClassDetailsID() {
            return classDetails_ID;
        }

        public void setClassDetailsID(int classDetails_ID) {
            this.classDetails_ID = classDetails_ID;
        }

        public int getClassRoomID() {
            return classRoom_ID;
        }

        public void setClassRoomID(int classRoom_ID) {
            this.classRoom_ID = classRoom_ID;
        }

        public int getClassHoursID() {
            return classHours_ID;
        }

        public void setClassHoursID(int classHours_ID) {
            this.classHours_ID = classHours_ID;
        }

        public int getLecturerID() {
            return lecturer_ID;
        }

        public void setLecturerID(int lecturer_ID) {
            this.lecturer_ID = lecturer_ID;
        }

        public int getCourseCode() {
            return course_Code;
        }

        public void setCourseCode(int course_Code) {
            this.course_Code = course_Code;
        }

        public void getClassDetails() {
            // Code to get class details information
            System.out.println("Class Details: ID - " + classDetails_ID + ", Room - " + classRoom_ID +
                    ", Hours - " + classHours_ID + ", Lecturer - " + lecturer_ID + ", Course - " + course_Code);
        }

        public void editClassDetails(int newClassRoomID, int newClassHoursID, int newLecturerID, int newCourseCode) {
            // Code to edit class details
            this.classRoom_ID = newClassRoomID;
            this.classHours_ID = newClassHoursID;
            this.lecturer_ID = newLecturerID;
            this.course_Code = newCourseCode;
            System.out.println("Class Details edited successfully!");
        }
    }


