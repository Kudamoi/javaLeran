import com.mysql.cj.QueryResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        List<Course> courses = session.createQuery("From Course").getResultList();
        HashMap<String, Integer> hashCourses = new HashMap<>();
        for (Course course : courses) {
            hashCourses.put(course.getName(), course.getId());
        }

        List<Student> students = session.createQuery("From Student").getResultList();
        HashMap<String, Integer> hashStudents = new HashMap<>();
        for (Student student : students) {
            hashStudents.put(student.getName(), student.getId());
        }

        List<Purchase> purchases = session.createQuery("From Purchase").getResultList();

        for (Purchase purchase : purchases) {
            int studentId, courseId;

            courseId = hashCourses.getOrDefault(purchase.getCourseName(), 0);
            studentId = hashStudents.getOrDefault(purchase.getStudentName(), 0);

            if (courseId != 0 && studentId != 0) {
                LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
                Key key = new Key(studentId, courseId);
                linkedPurchaseList.setId(key);

                session.save(linkedPurchaseList);
            }

            System.out.printf("%-30s | %s\n", purchase.getStudentName(), purchase.getCourseName());

        }
        transaction.commit();

        session.close();
    }
}
