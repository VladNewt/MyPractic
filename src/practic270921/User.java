package practic270921;

import java.util.Objects;

public class User implements Cloneable{

    public static class Contacts implements Cloneable{
        private String phone;
        private String mail;

        public Contacts() {

        }
        public Contacts(String phone, String mail) {
            this.phone = phone;
            this.mail = mail;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Contacts contacts = (Contacts) o;
            return Objects.equals(phone, contacts.phone) && Objects.equals(mail, contacts.mail);
        }

        @Override
        public int hashCode() {
            return Objects.hash(phone, mail);
        }

        @Override
        public String toString() {
            return "Contacts{" +
                    "phone='" + phone + '\'' +
                    ", mail='" + mail + '\'' +
                    '}';
        }

        @Override
        protected Contacts clone() throws CloneNotSupportedException {
            return (Contacts) super.clone();
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }
    }




    private String name;
    private int age;
    private boolean activated;
    private Contacts contacts;

    public User() {
    }

    public User(String name, int age, boolean activated) {
        this.name = name;
        this.age = age;
        this.activated = activated;
    }

    public User(String name, int age, boolean activated, Contacts contacts) {
        this.name = name;
        this.age = age;
        this.activated = activated;
        this.contacts = contacts;
    }

    //    public User(User user) {
//        this(user.name, user.age,user.isActivated());
//    }

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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", activated=" + activated +
                ", contacts=" + contacts +
                '}';
    }

    @Override
    protected User clone() throws CloneNotSupportedException {
        User u = (User) super.clone();
        u.setContacts(contacts.clone());
        return (User) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && activated == user.activated && Objects.equals(name, user.name) && Objects.equals(contacts, user.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, activated, contacts);
    }
}
