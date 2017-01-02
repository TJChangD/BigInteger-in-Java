/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3and4_1406398_2si4;

/**
 *
 * @author Darren
 */
public class Lab3and4_1406398_2SI4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HugeInteger A = new HugeInteger("1");
        HugeInteger B = new HugeInteger("1000");
        HugeInteger C = new HugeInteger("12345");
        HugeInteger D = new HugeInteger("123");
        HugeInteger E = new HugeInteger("12567");
        HugeInteger F = new HugeInteger("1233");
        HugeInteger G = new HugeInteger("127");
        HugeInteger H = new HugeInteger("123");
        try{
        HugeInteger R = new HugeInteger(50);
        System.out.println("Test 1 - Random Number: ");
        System.out.println(R);
        System.out.println("Test 2 - Addition: ");
        System.out.println(A.add(B).toString());
        System.out.println("Test 3 - Subtraction: ");
        System.out.println(C.subtract(D).toString());
        System.out.println("Test 4 - Multiplication: ");
        System.out.println(E.multiply(F).toString());
        System.out.println("Test 5 - Compare: ");
        System.out.println(G.compareTo(H));
        System.out.println("Test 6 - Runtime: ");
        System.out.println("n = 10");
        R.RunTime(10);
//        System.out.println("n = 50");
//        R.RunTime(50);
//        System.out.println("n = 75");
//        R.RunTime(75);
//        System.out.println("n = 100");
//        R.RunTime(100);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println(e);
        }
        
    }
    
}
