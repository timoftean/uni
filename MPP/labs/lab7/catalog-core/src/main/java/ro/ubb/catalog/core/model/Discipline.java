package ro.ubb.catalog.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by radu.
 */
@Entity
@Table(name = "discipline")
public class Discipline extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "teacher", nullable = false)
    private String teacher;
    @Column(name = "credits", nullable = false)
    private int credits;

    public Discipline() {
    }

    public Discipline(String name, String teacher, int credits) {
        this.name = name;
        this.teacher = teacher;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discipline that = (Discipline) o;

        if (credits != that.credits) return false;
        if (!name.equals(that.name)) return false;
        return teacher.equals(that.teacher);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + teacher.hashCode();
        result = 31 * result + credits;
        return result;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", credits=" + credits +
                "} " + super.toString();
    }
}
