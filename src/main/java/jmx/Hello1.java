package jmx;

public class Hello1 implements Hello1MBean {

    public void sayHello(String name){

        System.out.println("Hello1 " + name);
    }
}
