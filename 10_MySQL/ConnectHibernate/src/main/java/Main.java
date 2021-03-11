import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
try {
    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
    SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

    Session session = sessionFactory.openSession();

        Course course = session.get(Course.class, 15);
        System.out.println();
        System.out.println("Количество студентов " + course.getStudentsCount()  + " на курсе " + course.getName());
        System.out.println();

        session.close();
        sessionFactory.close();
}catch (Exception e ) {
    e.printStackTrace();
}

    }
}