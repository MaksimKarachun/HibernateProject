import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        //find rowCount in Course table
        Number quantityRowsInDb = (Number)(session.createQuery("select count(id) from Course").uniqueResult());
        int rowCount = quantityRowsInDb.intValue();

        System.out.printf("%-10s%-40s%-10s%-15s%-10s%-10s%n", "id", "Course name", "Duration", "Type", "Price", "Price per hour");
        System.out.println("---------------------------------------------------------------------------------------------------");
        for(int i = 1; i <= rowCount; i++){
            Course course = session.get(Course.class, i);
            System.out.printf("%-10d%-40s%-10d%-15s%-10d%-10.2f%n",
                    course.getId(),
                    course.getName(),
                    course.getDuration(),
                    course.getType().toString(),
                    course.getPrice(),
                    course.getPricePerHour());
        }

        System.out.println();
        Course course = session.get(Course.class, 1);
        Student student = session.get(Student.class, 2);
        CompoundKey compoundKey = new CompoundKey(student, course);
        Subscription subscription = session.get(Subscription.class, compoundKey);
        System.out.println("Дата подписки студента " + subscription.getStudent().getName() +
                " на курс " + subscription.getCourse().getName() + " " + subscription.getSubscriptionDate());

    }
}
