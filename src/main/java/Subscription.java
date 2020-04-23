import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
@IdClass(CompoundKey.class)
public class Subscription implements Serializable {

    public Subscription(){}

    public Subscription(CompoundKey compoundKey) {
        student = compoundKey.getStudent();
        course = compoundKey.getCourse();
    }

    @Id
    @OneToOne
    private Student student;

    @Id
    @OneToOne
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscription_date(Date subscription_date) {
        this.subscriptionDate = subscription_date;
    }
}

