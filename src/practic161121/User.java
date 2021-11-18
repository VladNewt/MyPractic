package practic161121;

public class User {

    private String name;
    private int age;
    private String resume;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, int age, String resume) {
        this.name = name;
        this.age = age;
        this.resume = resume;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Override
    public String toString() {
        return "pack.User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", resume='" + resume + '\'' +
                '}';
    }
}
