package arrays;

public class AbstractTest
{
    public class A
    {
        public void doWork1() { System.out.println("Ran doWork1 in A"); }
    }
    
    public class B extends A
    {
        public void doWork1() { System.out.println("Ran doWork1 in B"); }
    }
    
    public static void main(String[] args)
    {
        AbstractTest myTest = new AbstractTest();
        myTest.runtest();
    }
    
    public void runtest()
    {
        A myA = new B();
        myA.doWork1();
        
        doWork2((B)myA);
    }
    
    public void doWork2(A arg) { System.out.println("Ran method A."); }
    public void doWork2(B arg) { System.out.println("Ran method B."); }

}