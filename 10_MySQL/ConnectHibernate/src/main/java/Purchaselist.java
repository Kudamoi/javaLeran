import javax.persistence.*;
import java.util.Date;

@Entity
public class Purchaselist {
    @Column(name = "course_name")
    private String courseName;

    private Student student;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
