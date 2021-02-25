package DayFour20210225;

public class Student {

    private Integer age;

    private String username;

    public  void sleep(){
        System.out.println("sleep....");
    }

    public Student(Integer age, String username) {
        this.age = age;
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Student(){}
}
