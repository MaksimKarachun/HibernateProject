import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.text.ParseException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws ParseException {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        Student student = session.get(Student.class, 1);
        List<Course> courseList = student.getCourseList();
        for(Course course : courseList)
            System.out.println(course.getName());

        CompoundKey compoundKey = new CompoundKey(1,2);
        Subscription subscription = session.get(Subscription.class, compoundKey);
        System.out.println(subscription.getStudent());

    }

}
