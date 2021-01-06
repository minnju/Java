package cse3040_hw1_20171702;
import java.util.Scanner;

public class Problem5 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter exam scores of each student.");
		int[] score=new int[5];
		int[] rank=new int[2];
		int[] rank_idx=new int[2];
		rank[0]=0;
		rank[1]=0;
		rank_idx[0]=0;
		rank_idx[1]=0;
		int temp=0;
		int temp_idx=0;
		for(int i=0;i<5;i++) {
			System.out.println("Score of student "+i+":");
			score[i]=scan.nextInt();
			if(score[i]>rank[0]) {
				temp=rank[0];
				temp_idx=rank_idx[0];
				rank[0]=score[i];
				rank_idx[0]=i;
				if (i>0) {
					rank[1]=temp;
					rank_idx[1]=temp_idx;
				}
			}
		}
		for(int i=0;i<2;i++) {
			System.out.println("The "+(int)(i+1)+"st place is student "+(int)(rank_idx[i]+1)+" with "+rank[i]+" points.");
		}
		
		
	}
}
