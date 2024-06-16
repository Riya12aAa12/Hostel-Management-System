
package hostel.management.system;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Node {
    int fill_room;
    int room_capacity;
    String[] name;
    Node next;

    Node(int fill_room, int room_capacity) {
        this.fill_room = fill_room;
        this.room_capacity = room_capacity;
        this.name = new String[3];
        this.next = null;
    }
}

class Hostel {
    private Node[] head;
    private Node created_node;
    private String[][] name_save;

    public void create() {
        head = new Node[3];
        name_save = new String[3][3];
        for (int i = 0; i < 3; i++) {
            head[i] = null;
        }
    }

    public void book(int people) {
        // Implement the book method with GUI elements if needed
         Scanner sc = new Scanner(System.in);
        int floor;
        int room;
        int i = 0;
        int flag = 0;
        System.out.print("Enter the floor number: ");
        floor = sc.nextInt();
        if (floor < 1 || floor > 3) {
            System.out.println("Invalid Floor number: " + floor);
        } else {
            System.out.print("Enter the room number: ");
            room = sc.nextInt();
            if (room < 1 || room > 10) {
                System.out.println("Invalid Room number: " + room);
            } else {
                created_node = head[floor - 1];
                while (i < room - 1) {
                    created_node = created_node.next;
                    i++;
                }
                i = 0;
                while (i < 3) {
                    if (created_node.name[i] == null) {
                        flag = 1;
                        break;
                    }
                    i++;
                }
                if (flag == 1 && created_node.fill_room < created_node.room_capacity) {
                    System.out.print("Enter the name: ");
                    created_node.name[created_node.fill_room] = sc.next();
                    name_save[room - 1][floor - 1] = created_node.name[created_node.fill_room];
                    created_node.fill_room++;
                    created_node.room_capacity--;
                    System.out.println("Room Booked successfully");
                } else {
                    System.out.println("Room Not Available");
                }
            }
        }
    }
    }

    public void cancel(int floor) {
        // Implement the cancel method with GUI elements if needed
    }

    public void display() {
        // Implement the display method with GUI elements if needed
    }

    public void search(String key) {
        // Implement the search method with GUI elements if needed
    }
}
 class Main {
    private JFrame frame;
    private JPanel panel;
    private JButton bookButton;
    private JButton cancelButton;
    private JButton searchButton;
    private JButton displayButton;
    private JButton exitButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.createAndShowGUI();
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("Hostel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        bookButton = new JButton("Book Room");
        cancelButton = new JButton("Cancel Room");
        searchButton = new JButton("Search Record");
        displayButton = new JButton("Display Records");
        exitButton = new JButton("Exit");

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle book button click event
                // Call the book method from the Hostel class
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle cancel button click event
                // Call the cancel method from the Hostel class
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle search button click event
                // Call the search method from the Hostel class
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle display button click event
                // Call the display method from the Hostel class
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(bookButton);
        panel.add(cancelButton);
        panel.add(searchButton);
        panel.add(displayButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
