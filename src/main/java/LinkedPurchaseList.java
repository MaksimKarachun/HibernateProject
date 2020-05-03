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
    private Integer studentId;

    @Id
    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "student_name")
    String studentName;

    @Column(name = "course_name")
    String courseName;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    Student student;

    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    Course course;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
