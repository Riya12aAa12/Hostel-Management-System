package hostel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import javax.swing.table.TableColumnModel;


public class RoomAllocationGUI extends JFrame {
private JTextField nameTextField;
private JTextField marksTextField;
private final JLabel backgroundPanel;
private  JLabel backgroundPanel1;
private JTextField studentIdTextField;
private JTextField nameTextField1;
JLabel studentIdLabel;
private JButton generateReceiptButton;
private JFrame receiptFrame;

    private JComboBox<String> branchComboBox;
    private JComboBox<String> yearComboBox;
    private final JButton allocateButton;
    private final JButton showDetailsButton;
    private int nextRoomNumber;
    private int nextBedNumber;
    private final Connection connection;
    private final JTextField jTextFieldAllocatedBeds;
    private final JButton cancelAllocationButton;

    private double discountPercentage;

    private Set<String> studentIdSet;
    
    private Map<Integer, Room> allocatedRooms;




    public RoomAllocationGUI() {
        // Set up the JFrame
        setTitle("Hostel Management System");
        setSize(860, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/7.jpg"));
        Image i2 = i1.getImage().getScaledInstance(850,650,Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        backgroundPanel = new JLabel(i3);
        backgroundPanel.setBounds(0,0,850,650);
        add(backgroundPanel);

        setLayout(null);
setContentPane(backgroundPanel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        nameLabel.setBounds(240,220,100,30);
        nameTextField = new JTextField(15);
         nameTextField.setBounds(315,220,210,30);
         backgroundPanel.add(nameLabel);
        backgroundPanel.add(nameTextField);
        
       
        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        studentIdLabel.setBounds(180, 160, 130, 30);
        backgroundPanel.add(studentIdLabel);

        studentIdTextField = new JTextField(15);
        studentIdTextField.setBounds(315, 160, 210, 30);
        backgroundPanel.add(studentIdTextField);

        JLabel text = new JLabel("ROOM BOOKING");
        text.setBounds(245, 0, 1000, 90);
        text.setForeground(Color.black);
        text.setFont(new Font("Serif", Font.PLAIN, 50));
        backgroundPanel.add(text);
        
        JLabel marksLabel = new JLabel("Marks:");
        marksTextField = new JTextField(15);
                 marksTextField.setBounds(315,280,210,30);

        marksLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        marksLabel.setBounds(235,280,100,30);
        backgroundPanel.add(marksLabel);
        backgroundPanel.add(marksTextField);

        JLabel branchLabel = new JLabel("Branch:");
        branchLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        branchLabel.setBounds(225,340,100,30);
        String[] branches = {"CSE", "ECE", "Mechanical"};
        branchComboBox = new JComboBox<>(branches);
        branchComboBox.setForeground(Color.black);
        branchComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        branchComboBox.setBounds(315,340,210,32);

        backgroundPanel.add(branchLabel);
        backgroundPanel.add(branchComboBox);

        JLabel yearLabel = new JLabel("Year:");
        String[] years = {"1", "2", "3", "4"};
        yearLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        yearLabel.setBounds(250,400,100,30);
        yearComboBox = new JComboBox<>(years);
        yearComboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
        yearComboBox.setBounds(315,400,210,32);
        backgroundPanel.add(yearLabel);
        backgroundPanel.add(yearComboBox);

        allocateButton = new JButton("Allocate Room");
        allocateButton.setBounds(343,540,214,30);
        allocateButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        allocateButton.setBackground(Color.BLACK);
        allocateButton.setForeground(Color.WHITE);
        backgroundPanel.add(allocateButton);
        
        generateReceiptButton = new JButton("Generate Receipt");
        generateReceiptButton.setBounds(690, 440, 140, 32);
        generateReceiptButton.setBackground(Color.green);
        generateReceiptButton.setForeground(Color.black);
        backgroundPanel.add(generateReceiptButton);
        
        generateReceiptButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showReceiptGenerationPage();
        }
    });
        showDetailsButton = new JButton(" Hostel Details");
        backgroundPanel.add(showDetailsButton);
        showDetailsButton.setBounds(690, 264, 140, 32);
        showDetailsButton.setBackground(Color.green);
        showDetailsButton.setForeground(Color.black);

        allocatedRooms = new HashMap<>();
        nextRoomNumber = 1;
        nextBedNumber = 1;

        jTextFieldAllocatedBeds = new JTextField();
        backgroundPanel.add(jTextFieldAllocatedBeds);
        
        JButton btnExit = new JButton("Back");
        btnExit.setBounds(170, 544, 120, 30);
                btnExit.setBackground(Color.RED);
                btnExit.setForeground(Color.WHITE);
        backgroundPanel.add(btnExit );
		btnExit.addActionListener(new ActionListener() {
                    @Override

			public void actionPerformed(ActionEvent e) {
                            new Dashboard().setVisible(true);
                            setVisible(false);
			}
		}); 

        allocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String marksStr = marksTextField.getText();
                String branch = (String) branchComboBox.getSelectedItem();
                String year = (String) yearComboBox.getSelectedItem();
                String studentId = studentIdTextField.getText(); // Get the student ID


                if (name.isEmpty() || marksStr.isEmpty()) {
                    JOptionPane.showMessageDialog(RoomAllocationGUI.this, "Please enter Name and Marks.");
                } else {
                    try {
                        int marks = Integer.parseInt(marksStr);
                        allocateRoom(name, marks, branch, year, studentId);                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(RoomAllocationGUI.this,
                                "Invalid Marks. Please enter a numeric value.");
                    }
                }
            }
        });

        cancelAllocationButton = new JButton("Cancel Allocation");
        backgroundPanel.add(cancelAllocationButton);
        cancelAllocationButton.setBounds(690, 354, 140, 30);
                cancelAllocationButton.setBackground(Color.green);
                cancelAllocationButton.setForeground(Color.black);

        cancelAllocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCancellationPage();
            }
        });

        showDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHostelDetails();
            }
        });
       
        connection = getConnection();
        if (connection == null) {
            JOptionPane.showMessageDialog(RoomAllocationGUI.this, "Failed to connect to the database.");
            System.exit(0);
        }

        loadAllocatedRooms();
          studentIdSet = new HashSet<>();
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/hostelmanagementsystem", "root", "Kaavya@24");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getRoomType(int marks, String branch) {
        if (marks >= 90) {
            return "Single";
        } else if (marks >= 80) {
            return "Double";
        } else if (branch.equals("CSE")) {
            return "Triple";
        } else {
            return "Quadruple";
        }
    } 
 private Room allocateNextRoom(String roomType) {
    for (Room room : allocatedRooms.values()) {
        if (room.getRoomType().equals(roomType) && room.hasAvailableBed()) {
            int nextBedNumber = room.getNextBedNumber();
            room.allocateBed(nextBedNumber);
            return room;
        }
    }
    for (Room room : allocatedRooms.values()) {
        if (room.hasAvailableBed()) {
            int nextBedNumber = room.getNextBedNumber();
            room.allocateBed(nextBedNumber);
            return room;
        }
    }
    if (nextRoomNumber > 15) {
        JOptionPane.showMessageDialog(RoomAllocationGUI.this, "Maximum number of rooms reached.");
        throw new IllegalStateException("Maximum number of rooms reached.");
    }
    for (int i = 1; i < nextRoomNumber; i++) {
        if (!allocatedRooms.containsKey(i)) {
            Room vacantRoom = new Room(i, roomType);
            vacantRoom.allocateBed(1);
            allocatedRooms.put(i, vacantRoom);
            return vacantRoom;
        }
    }
    Room newRoom = new Room(nextRoomNumber, roomType);
    newRoom.allocateBed(1);
    allocatedRooms.put(nextRoomNumber, newRoom);
    nextRoomNumber++;

    return newRoom;
}
private void allocateRoom(String name, int marks, String branch, String year,String studentId) {
     if (marks < 45) {
        JOptionPane.showMessageDialog(RoomAllocationGUI.this, "Not eligible for room allocation for this Term");
        return;
    }
    String roomType = getRoomType(marks, branch);
    
    try {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT COUNT(*) FROM room_allocation WHERE student_id = ?"
        );
        statement.setString(1, studentId);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        if (count > 0) {
            JOptionPane.showMessageDialog(RoomAllocationGUI.this, "Student ID already exists.");
            nameTextField.setText("");
            marksTextField.setText("");
            branchComboBox.setSelectedIndex(0);
            yearComboBox.setSelectedIndex(0);
            studentIdTextField.setText("");
            return;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    Room room = allocateNextRoom(roomType);
   if (room == null) {
        JOptionPane.showMessageDialog(RoomAllocationGUI.this, "Maximum number of rooms reached.");
        return;
    }
    room.addStudent(name,marks);
    room.addStudentMarks(name, marks);
    room.addStudentBranch(name, branch);
    room.addStudentYear(name, year);
    room.addStudentId(studentId);
    room.setRoomType(roomType); 
        allocatedRooms.put(room.getRoomNumber(), room); // Add the allocated room to the HashMap

    // Update the room allocation in the database
    try {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO room_allocation (room_number, bed_number, name, marks, branch, year, room_type,student_id) VALUES (?,?, ?, ?, ?, ?, ?, ?)"
        );
        statement.setInt(1, room.getRoomNumber());
        List<Integer> allocatedBedNumber = room.getAllocatedBedNumber();
        if (!allocatedBedNumber.isEmpty()) {
            statement.setInt(2, allocatedBedNumber.get(allocatedBedNumber.size() - 1)); // Use the last allocated bed number
        } else {
            // Handle the case when there are no allocated bed numbers
            throw new IllegalStateException("No allocated bed numbers for the room.");
        }
        statement.setString(3, name);
        statement.setInt(4, marks);
        statement.setString(5, branch);
        statement.setString(6, year);
        statement.setString(7, roomType);
        statement.setString(8, studentId);
        statement.executeUpdate();
            
        JOptionPane.showMessageDialog(RoomAllocationGUI.this, "Student allocated to the room successfully!");
        
            studentIdSet.add(studentId);

            // Clear the input fields
            nameTextField.setText("");
            marksTextField.setText("");
            branchComboBox.setSelectedIndex(0);
            yearComboBox.setSelectedIndex(0);
            studentIdTextField.setText("");
    } catch (SQLException e) {
        e.printStackTrace();
    }

    updateRoomAllocation();
}



    private void updateRoomAllocation() {
        // Update the text field with room details
        StringBuilder allocatedBeds = new StringBuilder();
        for (Room room : allocatedRooms.values()) {
            allocatedBeds.append(room.getBedNumbersAsString()).append(", ");
        }
        jTextFieldAllocatedBeds.setText(allocatedBeds.toString());
    }

    private void loadAllocatedRooms() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM room_allocation");
        studentIdSet = new HashSet<>(); // Initialize the studentIdSet
            while (resultSet.next()) {
                int roomNumber = resultSet.getInt("room_number");
                int bedNumber = resultSet.getInt("bed_number");
                String name = resultSet.getString("name");
                int marks = resultSet.getInt("marks");
                String branch = resultSet.getString("branch");
                String year = resultSet.getString("year");
                String studentId = resultSet.getString("student_id");
                String roomType = resultSet.getString("room_type"); // Retrieve the bed_type value
                
                 

                Room room = findOrCreateRoom(roomNumber);
                room.allocateBed(bedNumber);
                room.addStudent(name, marks);
                room.addStudentMarks(name, marks);
                room.addStudentBranch(name, branch);
                room.addStudentYear(name, year);
               room.addStudentId(studentId);
               studentIdSet.add(studentId);
                room.setRoomType(roomType); // Set the room_type value for the room
                
                               
                if (bedNumber >= nextBedNumber && roomNumber >= nextRoomNumber) {
                nextBedNumber = bedNumber + 1;
                nextRoomNumber = roomNumber + 1;
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Room findOrCreateRoom(int roomNumber) {
        for (Room room : allocatedRooms.values()) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }

        Room room = new Room(roomNumber);
        allocatedRooms.put(roomNumber, room);
        if (roomNumber >= nextRoomNumber) {
            nextRoomNumber = roomNumber + 1;
        }
        return room;
    }

    private void showCancellationPage() {
        // Create a new JFrame to display the cancellation page
        ImageIcon p1 = new ImageIcon(ClassLoader.getSystemResource("icons/7.jpg"));
        Image p2 = p1.getImage().getScaledInstance(850,650,Image.SCALE_DEFAULT);
        ImageIcon p3 =  new ImageIcon(p2);
        backgroundPanel1 = new JLabel(p3);
        backgroundPanel1.setBounds(0,0,850,650);
        add(backgroundPanel1);
        


setTitle("Cancellation Page");
        setSize(860, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(null);
        setContentPane(backgroundPanel1);

        // Create and add components to the cancellation page
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        nameLabel.setBounds(215,200,100,30);
        nameTextField = new JTextField(15);
         nameTextField.setBounds(290,200,210,30);
        backgroundPanel1.add(nameLabel);
        backgroundPanel1.add(nameTextField);
        
        studentIdLabel = new JLabel("Student ID:");
studentIdLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
studentIdLabel.setBounds(160, 270, 400, 30);
backgroundPanel1.add(studentIdLabel);


 nameTextField1 = new JTextField(15);
         nameTextField1.setBounds(290,270,210,30);
         backgroundPanel1.add(nameTextField1);
   
        
        JLabel text1 = new JLabel("ROOM CANCELLATION");
        text1.setBounds(155, 0, 1000, 90);
        text1.setForeground(Color.black);
        text1.setFont(new Font("Serif", Font.PLAIN, 50));
        backgroundPanel1.add(text1);

        JLabel branchLabel = new JLabel("Branch:");
        branchLabel.setBounds(203,330,100,30);
        branchLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        String[] branches = {"CSE", "ECE", "Mechanical"};
        JComboBox<String> branchComboBox = new JComboBox<>(branches);
        branchComboBox.setForeground(Color.black);
        branchComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        branchComboBox.setBounds(290,330,210,32);
        backgroundPanel1.add(branchLabel);
        backgroundPanel1.add(branchComboBox);
        
        

        JLabel yearLabel = new JLabel("Year:");
        String[] years = {"1", "2", "3", "4"};
        yearLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        yearLabel.setBounds(220,400,100,30);
        JComboBox<String> yearComboBox = new JComboBox<>(years);
        yearComboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
        yearComboBox.setBounds(290,400,210,32);
        backgroundPanel1.add(yearLabel);
        backgroundPanel1.add(yearComboBox);
       

        JButton cancelButton = new JButton("Cancel");
        backgroundPanel1.add(cancelButton);
                cancelButton.setFont(new Font("Tahoma", Font.BOLD, 16));
cancelButton.setBounds(344,540,213,30);
                cancelButton.setBackground(Color.red);
                cancelButton.setForeground(Color.black);

        JButton btnExit = new JButton("Back");
        btnExit.setBounds(170, 544, 120, 30);
                btnExit.setBackground(Color.blue);
                btnExit.setForeground(Color.WHITE);
        backgroundPanel1.add(btnExit );
		btnExit.addActionListener(new ActionListener() {
                    @Override

			public void actionPerformed(ActionEvent e) {
                            new RoomAllocationGUI().setVisible(true);
                            setVisible(false);
			}
		}); 

        // Add action listener to the cancelButton
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String branch = (String) branchComboBox.getSelectedItem();
                String year = (String) yearComboBox.getSelectedItem();

                cancelAllocation(name, branch, year);
                backgroundPanel1.setVisible(true);
            }
        });

        backgroundPanel1.setVisible(true);
    }


    
    
    
    
    private void cancelAllocation(String name, String branch, String year) {
    String studentId = nameTextField1.getText();
    if (studentId.isEmpty()) {
        JOptionPane.showMessageDialog(RoomAllocationGUI.this, "Please enter Student ID.");
        return;
    }

    // Search for the allocated room and remove the student's details
    boolean cancellationSuccessful = false;
        for (Room room : allocatedRooms.values()) {
        List<String> students = room.getStudents();
        List<String> studentBranches = room.getStudentBranches();
        List<String> studentYears = room.getStudentYears();
        Set<String> studentIds = room.getStudentIds();
        
        if (studentIds == null) {
            continue;
        }
         List<String> studentIdList = new ArrayList<>(studentIds);

        for (int i = 0; i < students.size(); i++) {
            String student = students.get(i);
            String studentBranch = studentBranches.get(i);
            String studentYear = studentYears.get(i);
            String studentIdFromList = studentIdList.get(i);

            
            
            if (student.equals(name) && studentBranch.equals(branch) && studentYear.equals(year) && studentId.equals(studentIdFromList)) {
                students.remove(i);
                studentBranches.remove(i);
                studentYears.remove(i);
                 studentIds.remove(studentIdFromList);
                // Update the room allocation in the database
                // Update the database to reflect the cancellation
                try {
                    PreparedStatement statement = connection.prepareStatement(
                            "DELETE FROM room_allocation WHERE room_number = ? AND name = ? AND branch = ? AND year = ? AND student_id = ?"
                    );
                    statement.setInt(1, room.getRoomNumber());
                    statement.setString(2, name);
                    statement.setString(3, branch);
                    statement.setString(4, year);
                    statement.setString(5, studentId);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                JOptionPane.showMessageDialog(RoomAllocationGUI.this, "Allocation canceled successfully!");

                // Update the room allocation display
                updateRoomAllocation();

                // Clear the input fields
                nameTextField.setText("");
                marksTextField.setText("");
                branchComboBox.setSelectedIndex(0);
                yearComboBox.setSelectedIndex(0);
                 nameTextField1.setText("");

                cancellationSuccessful = true;
                break;
            }
        }

        if (cancellationSuccessful) {
            break;
        }
    }

    // If the student's allocation was not found
    if (!cancellationSuccessful) {
        JOptionPane.showMessageDialog(RoomAllocationGUI.this, "Allocation not found for the given details or invalid Student ID.");
    }
}


    private void showHostelDetails() {
    // Create a new JFrame to display the hostel details
    JFrame detailsFrame = new JFrame("Hostel Details");
    detailsFrame.setSize(960, 700);
    detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    detailsFrame.setLocationRelativeTo(null);
    detailsFrame.setLayout(new BorderLayout());

    // Create a table to display the details
    String[] columnNames = {"Room Number", "Room Type", "Bed Numbers", "Students", "Branch", "Year", "Student IDs"};
    Object[][] rowData = new Object[allocatedRooms.size()][7];
    int i = 0;
    for (Room room : allocatedRooms.values()) {
        String bedNumbers = room.getBedNumbersAsString();
        String students = room.getStudentsAsString();
        List<String> branches = room.getStudentBranches();
        List<String> years = room.getStudentYears();
        Set<String> studentIds = room.getStudentIds();

        rowData[i][0] = room.getRoomNumber();
        rowData[i][1] = room.getRoomType();
        rowData[i][2] = bedNumbers;
        rowData[i][3] = students;
        rowData[i][4] = branches;
        rowData[i][5] = years;
        rowData[i][6] = studentIds;
        i++;
    }

    JTable table = new JTable(rowData, columnNames);
    table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
    table.setFont(new Font("Tahoma", Font.PLAIN, 14));
    table.setRowHeight(45);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    // Set column widths to fit the content
    TableColumnModel columnModel = table.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(120);
    columnModel.getColumn(1).setPreferredWidth(100);
    columnModel.getColumn(2).setPreferredWidth(150);
    columnModel.getColumn(3).setPreferredWidth(150);
    columnModel.getColumn(4).setPreferredWidth(150);
    columnModel.getColumn(5).setPreferredWidth(80);
    columnModel.getColumn(6).setPreferredWidth(200);

    JScrollPane scrollPane = new JScrollPane(table);
            table.setBackground(new Color(173, 216, 230));
        table.setForeground(Color.BLUE);
    detailsFrame.add(scrollPane, BorderLayout.CENTER);

    detailsFrame.setVisible(true);
}

    
    
    
   private double calculateTotalAmount(String roomType, int studentMarks) {
    double roomPrice = 0.0;
    
    // Determine the room price based on the room type
    if (roomType.equals("Single")) {
        roomPrice = 100000.0; // Set the price for a single room
    } else if (roomType.equals("Double")) {
        roomPrice = 85000.0; // Set the price for a double room
    } else if (roomType.equals("Triple")) {
        roomPrice = 75000.0; // Set the price for a triple room
    } else if (roomType.equals("Quadruple")) {
        roomPrice = 55000.0; // Set the price for a quadruple room
    }
    
    // Apply discount based on student marks
     discountPercentage = 0.0;
    if (studentMarks >= 90) {
        discountPercentage = 20.0; // 20% discount for students with marks 90 and above
    } else if (studentMarks >= 80) {
        discountPercentage = 15.0; // 15% discount for students with marks between 80 and 89
    } else if (studentMarks >= 70) {
        discountPercentage = 10.0; // 10% discount for students with marks between 70 and 79
    }
    
    // Apply the discount to the room price
    double discountAmount = roomPrice * (discountPercentage / 100.0);
    double discountedPrice = roomPrice - discountAmount;
    
    // Additional charges or discounts can be applied here if necessary
    
    return discountedPrice;
}

    
private Receipt generateReceipt(String studentId) {
    // Retrieve the student's allocation details from the database
    try {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT * FROM room_allocation WHERE student_id = ?"
        );
        statement.setString(1, studentId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Retrieve the other details from the database
            String studentName = resultSet.getString("name");
            int roomNumber = resultSet.getInt("room_number");
            String roomType = resultSet.getString("room_type");
            int studentMarks = resultSet.getInt("marks");

            // Calculate the total amount based on the room type
            double totalAmount = calculateTotalAmount(roomType,studentMarks);
               

            // Capture the payment method using the current date and time
            String paymentMethod = JOptionPane.showInputDialog(RoomAllocationGUI.this, "Enter Payment Method (Cash/Card):");
            String paymentDate = LocalDateTime.now().toString();
            // Generate a random receipt number
            int receiptNumber = generateRandomReceiptNumber();
            
            
Receipt receipt = new Receipt(studentName, studentId, roomNumber, roomType, paymentMethod, paymentDate, totalAmount, receiptNumber,discountPercentage);


            return receipt;
        } else {
            JOptionPane.showMessageDialog(RoomAllocationGUI.this, "Allocation not found for the given Student ID.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}

private int generateRandomReceiptNumber() {
    Random random = new Random();
    int receiptNumber = random.nextInt(9000) + 1000; // Generate a random number between 1000 and 9999
    return receiptNumber;
}

    
    private void showReceipt(Receipt receipt) {
    String receiptText = generateReceiptText(receipt);
    JOptionPane.showMessageDialog(RoomAllocationGUI.this, receiptText, "Receipt", JOptionPane.INFORMATION_MESSAGE);
}

    
private String generateReceiptText(Receipt receipt) {
    String studentName = receipt.getStudentName();
    String studentId = receipt.getStudentId();
    int roomNumber = receipt.getRoomNumber();
    String roomType = receipt.getRoomType();
    String paymentMethod = receipt.getPaymentMethod();
    String paymentDate = receipt.getPaymentDate();
    double totalAmount = receipt.getTotalAmount();
    int receiptNumber =receipt.getReceiptNumber();
    
    StringBuilder receiptBuilder = new StringBuilder();
    receiptBuilder.append("-----------------------------------------\n");
    receiptBuilder.append("            HOSTEL RECEIPT\n");
    receiptBuilder.append("-----------------------------------------\n\n");
    receiptBuilder.append("Date: ").append(paymentDate).append("\n");
    receiptBuilder.append("Receipt Number: ").append(receiptNumber).append("\n\n");
    receiptBuilder.append("-----------------------------------------\n");
    receiptBuilder.append("Student Information:\n");
    receiptBuilder.append("-----------------------------------------\n");
    receiptBuilder.append("Name: ").append(studentName).append("\n");
    receiptBuilder.append("Student ID: ").append(studentId).append("\n");
    receiptBuilder.append("Room Number: ").append(roomNumber).append("\n");
    receiptBuilder.append("Room Type: ").append(roomType).append("\n\n");
    receiptBuilder.append("-----------------------------------------\n");
    receiptBuilder.append("Payment Details:\n");
    receiptBuilder.append("-----------------------------------------\n");
    receiptBuilder.append("Payment Method: ").append(paymentMethod).append("\n");
    receiptBuilder.append("Discount Applied: ").append(discountPercentage).append("%\n");
    receiptBuilder.append("Total Amount: ").append(totalAmount).append("\n");
    receiptBuilder.append("-----------------------------------------\n");
    receiptBuilder.append("Thank you for your payment!\n");
    receiptBuilder.append("For any queries, please contact the hostel management.\n");
    receiptBuilder.append("-----------------------------------------\n");

    return receiptBuilder.toString();
}



private void showReceiptGenerationPage() {
    // Create a new JFrame to display the receipt generation page
    JFrame receiptFrame = new JFrame("Receipt Generation");
    receiptFrame.setSize(860, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    receiptFrame.setLocationRelativeTo(null);
    receiptFrame.setLayout(null);
//    receiptFrame.setLayout(new FlowLayout());

    ImageIcon p1 = new ImageIcon(ClassLoader.getSystemResource("icons/6.6.jpg"));
    Image p2 = p1.getImage().getScaledInstance(850, 650, Image.SCALE_DEFAULT);
    ImageIcon p3 = new ImageIcon(p2);
    JLabel backgroundPanel1 = new JLabel(p3);
    backgroundPanel1.setBounds(0, 0, 850, 650);
    backgroundPanel1.setLayout(null);

    // Create the components for entering the student ID
    studentIdLabel = new JLabel("Student ID:");
    studentIdLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
    studentIdLabel.setBounds(173,200,230,30);
    studentIdTextField = new JTextField(15);
    studentIdTextField.setBounds(315,200,210,30);
    
    JButton generateButton = new JButton("Generate");

    // Add components to the backgroundPanel1
    backgroundPanel1.add(studentIdLabel);
    backgroundPanel1.add(studentIdTextField);
    backgroundPanel1.add(generateButton);
          generateButton.setBounds(270, 254, 140, 30);
                generateButton.setBackground(Color.green);
                generateButton.setForeground(Color.black);
    // Add the background panel to the receiptFrame
    receiptFrame.add(backgroundPanel1);

    // Set the visibility of the background panel
    backgroundPanel1.setVisible(true);

    // Add action listener to the generateButton
    generateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String studentId = studentIdTextField.getText();
            Receipt receipt = generateReceipt(studentId);
            if (receipt != null) {
                showReceipt(receipt);
                receiptFrame.setVisible(true);
            }
        }
    });

    receiptFrame.setVisible(true);
}



    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RoomAllocationGUI().setVisible(true);
            }
        });
    }
}
