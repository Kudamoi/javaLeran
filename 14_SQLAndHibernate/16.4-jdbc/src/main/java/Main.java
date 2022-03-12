import com.mysql.cj.QueryResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;


import java.util.HashMap;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Query q =  session.createSQLQuery("SELECT * from purchaselist");
        List<Object[]> purchases = q.getResultList();

        q =  session.createSQLQuery("SELECT id, name from courses");
        List<Object[]> courses = q.getResultList();
        HashMap<String, Integer> hashCourses = new HashMap<>();

        q =  session.createSQLQuery("SELECT id, name from students");
        List<Object[]> students = q.getResultList();
        HashMap<String, Integer> hashStudents = new HashMap<>();


        for(Object[] course: courses){
            hashCourses.put((String) course[1], (Integer) course[0]);
        }

        for(Object[] student: students){
            hashStudents.put((String) student[1], (Integer) student[0]);
        }

        for(Object[] purchase: purchases){
            int studentId, courseId;

            if (hashCourses.containsKey((String) purchase[1])) {
                courseId = hashCourses.get(purchase[1]);
            } else {
                courseId = 0;
            }

            if (hashStudents.containsKey((String) purchase[0])) {
                studentId = hashStudents.get(purchase[0]);
            } else {
                studentId = 0;
            }

            if (courseId != 0 && studentId != 0) {
                LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
                Key key = new Key(studentId,courseId);
                linkedPurchaseList.setId(key);

                session.save(linkedPurchaseList);
            }

            System.out.printf("%-30s | %s\n",purchase[0], purchase[1]);
        }


        transaction.commit();

        session.close();
    }
}
