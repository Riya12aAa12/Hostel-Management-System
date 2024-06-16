package hostel.management.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import java.util.StringJoiner;

public class Room {
    private int roomNumber;
    private String roomType;
    private List<Integer> bedNumber;
    private List<String> students;
    private List<Integer> studentMarks;
    private List<String> studentBranches;
    private List<String> studentYears;
     private String bedType; 
     private Set<String> studentIds;
     

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.roomType = "";
        this.bedNumber = new ArrayList<>();
        this.students = new ArrayList<>();
        this.studentMarks = new ArrayList<>();
        this.studentBranches = new ArrayList<>();
        this.studentYears = new ArrayList<>();
        this.studentIds = new HashSet<>();    }

    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.bedNumber = new ArrayList<>();
        this.students = new ArrayList<>();
        this.studentMarks = new ArrayList<>();
        this.studentBranches = new ArrayList<>();
        this.studentYears = new ArrayList<>();
         this.studentIds = new HashSet<>();  
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    
    
     
    
public void setRoomType(String roomType) {
    this.roomType = roomType;
}


    public String getRoomType() {
        return roomType;
    }

    public List<Integer> getBedNumber() {
        return bedNumber;
    }

    public String getBedNumbersAsString() {
        StringJoiner joiner = new StringJoiner(", ");
        for (int bedNumbers : bedNumber) {
            joiner.add(String.valueOf(bedNumbers));
        }
        return joiner.toString();
    }

    public List<String> getStudents() {
        return students;
    }
    
    public int getNextBedNumber() {
        for (int i = 1; i <= getMaxCapacity(); i++) {
            if (!bedNumber.contains(i)) {
                return i;
            }
        }
        return -1; // No available bed number found
    }


    public String getStudentsAsString() {
        return String.join(", ", students);
    }

    public void allocateBed(int bedNumbers) {
        bedNumber.add(bedNumbers);
    }
   
    public List<String> getStudentBranches() {
        return studentBranches;
    }
    
   public List<String> getStudentYears() {
        return studentYears;
    }
   
   
    public Set<String> getStudentIds() {
        return studentIds;
    }
   
   
    public List<Integer> getAllocatedBedNumber() {
        return bedNumber;
    }
    
        public boolean hasAvailableBed() {
        return bedNumber.size() < getMaxCapacity();
    }

    public void addStudent(String student, int marks) {
        students.add(student);
        studentMarks.add(marks);
    }

    public void addStudentMarks(String student, int marks) {
        studentMarks.add(marks);
    }

    public void addStudentBranch(String student, String branch) {
        studentBranches.add(branch);
    }

    public void addStudentYear(String student, String year) {
        studentYears.add(year);
    }
    
    
    public void addStudentId(String studentId) {
        studentIds.add(studentId);
    }


    public String getStudentIdsAsString() {
        // Convert the studentIds set to a formatted string (e.g., "ID1, ID2, ID3")
        return String.join(", ", studentIds);
    }

   
    

    
    
    public boolean isVacant() {
        return students.isEmpty();
    }
    public int getVacantBedCount() {
        return getMaxCapacity() - bedNumber.size();
    }
    
      private int getMaxCapacity() {
        if (roomType.equals("Single")) {
            return 1;
        } else if (roomType.equals("Double")) {
            return 2;
        } else if (roomType.equals("Triple")) {
            return 3;
        } else if (roomType.equals("Quadruple")) {
            return 4;
        } else {
            return 0;
        }
    }
     

    public void deallocateBed(int bedNumber) {
        this.bedNumber.remove(Integer.valueOf(bedNumber));
    }
}
