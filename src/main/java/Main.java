import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        //find rowCount in Course table
        Number quantityRowsInDb = (Number)(session.createQuery("select count(id) from Course").uniqueResult());
        int rowCount = quantityRowsInDb.intValue();

        //output courses table columns
        System.out.printf("%-10s%-40s%-10s%-15s%-10s%-20s%-20s%n", "id", "Course name", "Duration", "Type", "Price", "Price per hour", "Students Count");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        for(int i = 1; i <= rowCount; i++){
            Course course = session.get(Course.class, i);
            System.out.printf("%-10d%-40s%-10d%-15s%-10d%-20.2f%-20d%n",
                    course.getId(),
                    course.getName(),
                    course.getDuration(),
                    course.getType().toString(),
                    course.getPrice(),
                    course.getPricePerHour(),
                    course.getStudentsCount());
        }


        //output purchase with certain compoundKey
        System.out.println();
        Course course = session.get(Course.class, 1);
        Student student = session.get(Student.class, 2);
        CompoundKey compoundKey = new CompoundKey(student, course);
        Subscription subscription = session.get(Subscription.class, compoundKey);
        System.out.println("Дата подписки студента " + subscription.getStudent().getName() +
                " на курс " + subscription.getCourse().getName() + " " + subscription.getSubscriptionDate());


        //add to LinkedPurchaseList table
        Transaction transaction = session.beginTransaction();
        List<Purchase> purchaseList = getPurchase(session);

        for(Purchase purchase : purchaseList) {
            LinkedPurchaseList addedPurchase = new LinkedPurchaseList();
            addedPurchase.setStudentId(getObj(session, purchase.getStudentName(), Student.class).getId());
            addedPurchase.setCourseId(getObj(session, purchase.getCourseName(), Course.class).getId());
            addedPurchase.setStudentName(getObj(session, purchase.getStudentName(), Student.class).getName());
            addedPurchase.setCourseName(getObj(session, purchase.getCourseName(), Course.class).getName());
            session.save(addedPurchase);
        }

        transaction.commit();
        System.out.println("successful linked_purchase_list table creation");
        session.close();
        sessionFactory.close();

    }

    //search student or course by id
    public static <T>T getObj(Session session, String name, Class<T> T){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(T);
        Root<T> root = query.from(T);
        query.select(root).where(builder.equal(root.get("name"), name));
        T obj = session.createQuery(query).getSingleResult();
        return obj;
    }

    //get all purchase from purchaselist table
    public static List<Purchase> getPurchase(Session session){

        String sql = "select student_name, course_name from purchaselist";
        Query query = session.createSQLQuery(sql);
        List<Object[]> rows = query.list();

        for(Object[] row : rows) {
            Purchase purchase = new Purchase(row[0].toString(), row[1].toString());
        }

        return Purchase.getPurchaseList();
    }
}
