/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3and4_1406398_2si4;
import java.util.Random;

/**
 *
 * @author Darren
 */
public class HugeInteger {
    private int size;
    private int numArray[];
    
    public HugeInteger(String val){
        size = val.length();
        int num[] = new int[size];
        for (int i = 0; i < size; i++) 
        {
            num[i] = (int)val.charAt(i) - 48;
        }
        numArray = num;
    }
    public HugeInteger(int n) throws IndexOutOfBoundsException{
        size = n;
        if(n<1){
            throw new IndexOutOfBoundsException("Argument is invalid!");
        }
        else{
            int rNum;
            int num[] = new int[n];
            Random r = new Random();
            for(int i = 0;i<n;i++){
                rNum = r.nextInt(9)+1;
                num[i] = rNum;
            }
            numArray = num;
        }
    }
    public HugeInteger add(HugeInteger h){
        boolean flag = false, flag2 = false, neg = false;
        if (this.numArray[0] == -3 && h.numArray[0] != -3) {
            flag = true;
            this.strip();
            if(this.compareTo(h) == 0){
                HugeInteger s = new HugeInteger("0");
                return s;
            }
            if (this.compareTo(h) > 0){
                neg = true;
                h.complement(this);
            }
            else{
            this.complement(h);
            }
        }
        if (this.numArray[0] != -3 && h.numArray[0] == -3) {
            flag2 = true;
            h.strip();
            if(this.compareTo(h) == 0){
                HugeInteger s = new HugeInteger("0");
                return s;
            }
            if (h.compareTo(this) > 0) {
                neg = true;
                this.complement(h);
            }
            else{
            h.complement(this);
            }
        }
        if (this.numArray[0] == -3 && h.numArray[0] == -3){
            this.strip();
            h.strip();
            neg = true;
        }
        int i = this.size - 1, j = h.size - 1, k = 0, c = 0, num, carry = 0;
        if (i == j) {
            int tempsize = i + 1;
            int sum[] = new int[tempsize];
            while (i >= 0) {
                num = this.numArray[i] + h.numArray[j];
                num = num + carry;
                if (num > 9) {
                    carry = 1;
                } else {
                    carry = 0;
                }
                if (i == 0) {
                    sum[k] = num;
                } else {
                    num = num % 10;
                    sum[k] = num;
                }
                i--; j--; k++; c++;
            }
            this.size = c;
            this.numArray = sum;
            this.numArray = this.reverse(numArray);
         }
        else if(j > i){
            int tempsize = j + 1, zeros = (j + 1) - (i + 1), m = 0;
            int sum[] = new int[tempsize], tempArray[] = new int[tempsize];
            for (int l = 0;l < j+1; l++){
                if(l < zeros){
                tempArray[l] = 0;
                }
                else{
                tempArray[l] = this.numArray[m];   
                m++;
                }  
            }    
            while (j >= 0) {
                num = tempArray[j] + h.numArray[j];
                num = num + carry;
                if (num > 9) {
                    carry = 1;
                } else {
                    carry = 0;
                }
                if (j == 0) {
                    sum[k] = num;
                } else {
                    num = num % 10;
                    sum[k] = num;
                }
                j--; k++; c++;
            }
            this.size = c;
            this.numArray = sum;
            this.numArray = this.reverse(numArray);            
        }
        else{
            int tempsize = i + 1, zeros = (i + 1) - (j + 1), m = 0;
            int sum[] = new int[tempsize], tempArray[] = new int[tempsize];
            for (int l = 0;l < i+1; l++){
                if(l < zeros){
                tempArray[l] = 0;
                }
                else{
                tempArray[l] = h.numArray[m];   
                m++;
                }            
            }    
            while (i >= 0) {
                num = tempArray[i] + this.numArray[i];
                num = num + carry;
                if (num > 9) {
                    carry = 1;
                } else {
                    carry = 0;
                }
                if (i == 0) {
                    sum[k] = num;
                } else {
                    num = num % 10;
                    sum[k] = num;
                }
                i--; k++; c++;
            }
            this.size = c;
            this.numArray = sum;
            this.numArray = this.reverse(numArray); 
        }
        if (flag2 == true || flag == true){
            this.numArray[0] = this.numArray[0] % 10;
            HugeInteger s = new HugeInteger("1");
            this.add(s);
        }
        if (neg == true){
            this.negative();
        }
        return this;
    }
    
    public HugeInteger subtract(HugeInteger h){
        if(this.numArray[0] != -3 && h.numArray[0] != -3){
            h.negative();
            return this.add(h);
        }
        if(this.numArray[0] != -3 && h.numArray[0] == -3){
            h.strip();
            return this.add(h);
        }
        if(this.numArray[0] == -3 && h.numArray[0] != -3){
            h.negative();
            return this.add(h);
        }
        if(this.numArray[0] == -3 && h.numArray[0] == -3){
            h.strip();
            return this.add(h);
        }
        return this;
    }
    
    public HugeInteger multiply(HugeInteger h) {
        String mul;
        boolean neg = false;
        int carry = 0, pow = 0, sum, num1[], num2[];
        if (this.numArray[0] == -3 && h.numArray[0] != -3) {
            this.strip();
            neg = true;
        }
        if (this.numArray[0] != -3 && h.numArray[0] == -3) {
            h.strip();
            neg = true;
        }
        if (this.numArray[0] == -3 && h.numArray[0] == -3) {
            h.strip();
            this.strip();
            neg = false;
        }
        if (this.size > h.size) {
            num1 = this.numArray;
            num2 = h.numArray;
        } else {
            num2 = this.numArray;
            num1 = h.numArray;
        }
        HugeInteger a, b = new HugeInteger("0");
        for (int i = num2.length - 1; i >= 0; i--) {
            mul = "";
            for (int j = num1.length - 1; j >= 0; j--) {
                int temp = num1[j] * num2[i] + carry;
                carry = (int)(temp / 10);
                sum = temp % 10;
                mul = sum + mul;
            }
            if (carry != 0) {
                mul = carry + mul;
                carry = 0;
            }
            for (int k = pow; k > 0; k--) {
                mul = mul + "0";
            }
            a = new HugeInteger(mul);
            b = b.add(a);
            pow++;
        }
        if (neg == true) {
            return b.negative();
        } else {
            return b;
        }
    }
    
    public int compareTo(HugeInteger h){
        int i = this.size, j = h.size, c = 0;
        if(i == j){
            for(int l = 0; l < i ; l++ ){
                if(this.numArray[l] == h.numArray[l]){
                c++;
                }
            }
            if(c == i){
                return 0;
            }
        }
        if(this.numArray[0] == -3 && h.numArray[0] != -3){
            return -1;
        }
        else if(this.numArray[0] != -3 && h.numArray[0] == -3){
            return 1;
        }
        else if(this.numArray[0] == -3 && h.numArray[0] == -3){
            if(i<j){
            return 1;
            }
            else if(j<i){
            return -1;
            }
            else{
                for(int k = 0;k < i + 1; k++){
                if(this.numArray[k] > h.numArray[k]){
                        return -1;
                }
                if(this.numArray[k] < h.numArray[k]){
                        return 1;
                    }
                }
            }
        }
        else{
        if(i>j){
            return 1;
        }
        else if(j>i){
            return -1;
        }
        else{
            for(int k = 0;k < i + 1; k++){
                if(this.numArray[k] > h.numArray[k]){
                        return 1;
                }
                if(this.numArray[k] < h.numArray[k]){
                        return -1;
                    }
                }
            return 0;
        }
        }
        return 0;
    }
    
    public HugeInteger strip(){
        int num[] = new int[this.size - 1]; 
        for (int i = 0; i < this.size - 1; i++){
            num[i] = this.numArray[i+1];     
        }
        this.size = this.size - 1;
        this.numArray = num;
        return this;
    }
    
    public HugeInteger negative(){
        int num[] = new int[this.size + 1];
        num[0] = -3;
        for (int i = 0; i < this.size; i++){
            num[i + 1] = this.numArray[i];     
        }
        this.size = this.size + 1;
        this.numArray = num;
        return this;
    }
    
    public HugeInteger complement(HugeInteger h){
        int num[] = new int[h.size];
        
        for (int i = 0; i < h.size ; i++){
            num[i] = 9;     
        }
        int c = 0, arraysize = this.size;
        for (int i = h.size - 1 ; c < arraysize ; i--){
            num[i] = num[i] - this.numArray[this.size-1];
            this.size--;
            c++;
        }
        this.size = num.length;
        this.numArray = num;
        return this;
    } 
    
    public int[] reverse(int[] array){
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }
    
    public int getSize(){
        return size;
    }
    @Override
    public String toString() {
        String str = "";
        if (numArray[0] == -3) {
            str = "-";
            for (int i = 1; i < size; i++) {
                str = str + numArray[i];
            }
        } else {
            for (int i = 0; i < size; i++) {
                str = str + numArray[i];
            }
        }
        return str;
    }
    
    public void RunTime(int n) {
        AddTime(n);
        SubTime(n);
        MulTime(n);
        CompTime(n);
    }

    public void AddTime(int n) {
        int MAXNUMINTS = 1000, MAXRUN = 100;

        HugeInteger h1, h2, h3;
        long startTime, endTime;
        double runTime = 0.0;
        for (int i = 0; i < MAXNUMINTS; i++) {
            h1 = new HugeInteger(n);
            h2 = new HugeInteger(n);
            startTime = System.currentTimeMillis();
            for (int j = 0; j < MAXRUN; j++) {
                h3 = h1.add(h2);
            }
            endTime = System.currentTimeMillis();
            runTime += (double) (endTime - startTime) / ((double) MAXRUN);
        }
        runTime = runTime / ((double) (MAXNUMINTS));
        System.out.println(runTime);
    }

    public void SubTime(int n) {
        int MAXNUMINTS = 1000, MAXRUN = 100;

        HugeInteger h1, h2, h3;
        long startTime, endTime;
        double runTime = 0.0;
        for (int i = 0; i < MAXNUMINTS; i++) {
            h1 = new HugeInteger(n);
            h2 = new HugeInteger(n);
            startTime = System.currentTimeMillis();
            for (int j = 0; j < MAXRUN; j++) {
                h3 = h1.subtract(h2);
            }
            endTime = System.currentTimeMillis();
            runTime += (double) (endTime - startTime) / ((double) MAXRUN);
        }
        runTime = runTime / ((double) (MAXNUMINTS));
        System.out.println(runTime);
    }

    public void MulTime(int n) {
        int MAXNUMINTS = 1000, MAXRUN = 100;

        HugeInteger h1, h2, h3;
        long startTime, endTime;
        double runTime = 0.0;
        for (int i = 0; i < MAXNUMINTS; i++) {
            h1 = new HugeInteger(n);
            h2 = new HugeInteger(n);
            startTime = System.currentTimeMillis();
            for (int j = 0; j < MAXRUN; j++) {
                h3 = h1.multiply(h2);
            }
            endTime = System.currentTimeMillis();
            runTime += (double) (endTime - startTime) / ((double) MAXRUN);
        }
        runTime = runTime / ((double) (MAXNUMINTS));
        System.out.println(runTime);
    }

    public void CompTime(int n) {
        int MAXNUMINTS = 1000, MAXRUN = 100;

        HugeInteger h1, h2;
        long startTime, endTime;
        double runTime = 0.0;
        for (int i = 0; i < MAXNUMINTS; i++) {
            h1 = new HugeInteger(n);
            h2 = new HugeInteger(n);
            startTime = System.currentTimeMillis();
            for (int j = 0; j < MAXRUN; j++) {
                h1.compareTo(h2);
            }
            endTime = System.currentTimeMillis();
            runTime += (double) (endTime - startTime) / ((double) MAXRUN);
        }
        runTime = runTime / ((double) (MAXNUMINTS));
        System.out.println(runTime);
    }
}
