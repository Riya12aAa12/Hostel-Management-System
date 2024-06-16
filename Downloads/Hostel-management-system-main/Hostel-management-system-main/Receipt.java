package hostel.management.system;


import java.util.HashMap;
import java.util.Map;

public class Receipt {
    private Map<String, Object> receiptData;

    public Receipt(String studentName, String studentId, int roomNumber, String roomType,
                   String paymentMethod, String paymentDate, double roomPrice,
                 double discountPercentage, double receiptNumber) {
        receiptData = new HashMap<>();
    
        receiptData.put("studentName", studentName);
        receiptData.put("studentId", studentId);
        receiptData.put("roomNumber", roomNumber);
        receiptData.put("roomType", roomType);
        receiptData.put("paymentMethod", paymentMethod);
        receiptData.put("paymentDate", paymentDate);
        receiptData.put("roomPrice", roomPrice);
  
        receiptData.put("discountPercentage", discountPercentage);
        receiptData.put("receiptNumber", receiptNumber);
    }

    public String getStudentName() {
        return (String) receiptData.get("studentName");
    }

    public String getStudentId() {
        return (String) receiptData.get("studentId");
    }

    public int getRoomNumber() {
        return (int) receiptData.get("roomNumber");
    }

    public String getRoomType() {
        return (String) receiptData.get("roomType");
    }

    public String getPaymentMethod() {
        return (String) receiptData.get("paymentMethod");
    }

    public String getPaymentDate() {
        return (String) receiptData.get("paymentDate");
    }

//    public double getTotalAmount() {
//        return (double) receiptData.get("totalAmount");
//    }
    
    public double getTotalAmount() {
    double totalAmount = (double) receiptData.get("roomPrice");
    double discountPercentage = (double) receiptData.get("discountPercentage");
//    double discountApplied = totalAmount * (discountPercentage / 100.0);
    return totalAmount ;
}


    public double getDiscountApplied() {
        return (double) receiptData.get("discountApplied");
    }

    public int getReceiptNumber() {
        return (int) Math.round((double) receiptData.get("receiptNumber"));
    }
}

