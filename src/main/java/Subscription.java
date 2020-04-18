import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "subscriptions")
@IdClass(CompoundKey.class)
public class Subscription implements Serializable {

    public Subscription(){}

    public Subscription(CompoundKey compoundKey) {
        studentID = compoundKey.getStudentID();
        courseID = compoundKey.getCourseID();
    }

    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "studentID",
                    column = @Column(name="student_id")),
            @AttributeOverride(name = "courseID",
                    column = @Column(name="couse_id"))
    })

    @Column(name = "student_id")
    private int studentID;

    @Column(name = "course_id")
    private int courseID;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "purchaselist",
            joinColumns = {@JoinColumn(name = "student_name"), @JoinColumn(name = "course_name")},
            inverseJoinColumns = {@JoinColumn(name = "subscription_date")})
    Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscription_date(Date subscription_date) {
        this.subscriptionDate = subscription_date;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
