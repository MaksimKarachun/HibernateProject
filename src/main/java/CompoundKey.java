import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class CompoundKey implements Serializable {

    @OneToOne
    private Student student;

    @OneToOne
    private Course course;

    public CompoundKey(){}

    public CompoundKey(Student student, Course course) {
        this.course = course;
        this.student = student;
    }

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
}
