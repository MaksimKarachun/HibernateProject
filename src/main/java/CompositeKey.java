import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CompositeKey implements Serializable {

    private int studentId;

    private int courseId;

    public CompositeKey(){}

    public CompositeKey(int studentId, int courseId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

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
}
