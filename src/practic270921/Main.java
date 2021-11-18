package practic270921;

import practic160821.enumtest.ColorsConstants;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        User u1 = new User("N1",10,true, new User.Contacts("P1","M1"));
        //User u2 = new User(u1.getName(), u1.getAge(),u1.isActivated());
        u1.setName("N111");
        u1.getContacts().setPhone("P2");
        User u2 = u1.clone();
        //
        System.out.println("u1 = " + u1);
        System.out.println("u2 = " + u2);
        System.out.println(u1.equals(u2));

//        Container<User> c1 = new Container<User>(u1);
//        Container<User> c2 = c1.clone();
//        c1.getVal().setName("222");
//        System.out.println("c1 = " + c1);
//        System.out.println("c2 = "+ c2);









    }
}
