import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "linked_purchase_list")
@IdClass(CompositeKey.class)
public class LinkedPurchaseList {

    public LinkedPurchaseList(){}

    public LinkedPurchaseList(CompositeKey compositeKey) {
        studentId = compositeKey.getStudentId();
        courseId = compositeKey.getCourseId();
    }

    @Id
    @Column(name = "student_id")
    private int studentId;

    @Id
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "price")
    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
