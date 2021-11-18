//package practic270921;
//
//import java.lang.reflect.Method;
//
//public class Container<T> implements Cloneable{
//    private T val;
//
//    public Container() {
//    }
//
//    public Container(T val) {
//        this.val = val;
//    }
//
//    public T getVal() {
//        return val;
//    }
//
//    public void setVal(T val) {
//        this.val = val;
//    }
//
//    @Override
//    public String toString() {
//        return "Container{" +
//                "val=" + val +
//                '}';
//    }
//
//    @Override
//    protected Container<T> clone() throws CloneNotSupportedException {
//        Container<T> c = (Container<T>) super.clone();
//        try {
//            //Method cloneMethod = val.getClass().getDeclaredMethod("class");
//            Class cl = val.getClass();
////            while ((cl.getSuperclass()) != null) {
////                cl = cl.getSuperclass();
//
//                //Method cloneMethod;
//            }
//            c.setVal((T)cloneMethod.invoke(val));
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new CloneNotSupportedException(e.getMessage());
//        }
//
//        return c;
//    }
//}
