package project;

import java.util.Scanner;

public class Main{
	int czero = 0;
	int cone = 0;
	
    public static void main(String[] args){
    	Main ms = new Main();
    	
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int[] getNum = new int[num1];
        int number = 0;
        for(int i = 0;i<num1;i++)
        {
                getNum[i] = sc.nextInt();
                
        }
        
        for(int i = 0;i<num1;i++)
        {
               
                ms.fibonacci(getNum[i]);
                System.out.print(ms.czero + " ");
                System.out.println(ms.cone);
                ms.czero = 0;
                ms.cone = 0;
        }
        
    }
    
    void fibonacci(int num){
        if(num==0){
            czero++;
        }else if(num==1){
            cone++;
        }else{
            fibonacci(num-1);
            fibonacci(num-2);
        }
    }
}


