import java.util.ArrayList;
import java.util.List;

public class Purchase {

    private static List<Purchase> purchaseList = new ArrayList<>();

    private String studentName;

    private String courseName;


    public Purchase(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
        purchaseList.add(this);
    }

    public static List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

}
