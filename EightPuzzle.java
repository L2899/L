import java.util.*;

public class EightPuzzle {
	public static void main(String args[]) throws CloneNotSupportedException
	{
		Queue<State> Q=new PriorityQueue<>();
		State temp = null,next,curr=new State();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter initial state: \n");
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++)
			  curr.arr[i][j]=sc.nextInt();
		}
		System.out.println("Enter final state: \n");
		curr.goal = new State();
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++)
				  curr.goal.arr[i][j]=sc.nextInt();
		}
		curr.level=0;
		Q.offer(curr);
		while(true){
			curr=Q.poll();
//			System.out.println("Current State : ");
//			System.out.println(curr);
			if(curr==null)
				break;
			if(curr.equals(State.goal)){
				break;
			}
			next=curr.getUp();
//			System.out.println("Next1 : ");
//			System.out.println(next);
			if(next!=null)
			{
				if(!Q.contains(next))
				{
					Q.offer(next);
				}else{
					for(State st:Q){
						if(next.equals(st)&&(next.compareTo(st)>0)){
							temp=st;
						}
					}
					if(temp!=null)
					{
						Q.remove(temp);
						Q.offer(next);
						temp=null;
					}
				}
			}
			next=curr.getDown();
//			System.out.println("Next2 : ");
//			System.out.println(next);
			if(next!=null)
			{
				if(!Q.contains(next))
				{
					Q.offer(next);
				}
				else
				{
					for(State st:Q)
					{
						if(next.equals(st)&&(next.compareTo(st)>0))
						{
							temp=st;
						}
					}
					if(temp!=null)
					{
						Q.remove(temp);
						Q.offer(next);
						temp=null;
					}
				}
			}
			next=curr.getLeft();
//			System.out.println("Next3 : ");
//			System.out.println(next);
			if(next!=null)
			{
				if(!Q.contains(next))
				{
					Q.offer(next);
				}
				else
				{
					for(State st:Q)
					{
						if(next.equals(st)&&(next.compareTo(st)>0))
						{
							temp=st;
						}
					}
					if(temp!=null)
					{
						Q.remove(temp);
						Q.offer(next);
						temp=null;
					}
				}
			}
			next=curr.getRight();
//			System.out.println("Next4 : ");
//			System.out.println(next);
			if(next!=null)
			{
				if(!Q.contains(next))
				{
					Q.offer(next);
				}
				else
				{
					for(State st:Q)
					{
						if(next.equals(st)&&(next.compareTo(st)>0))
						{
							temp=st;
						}
					}
					if(temp!=null)
					{
						Q.remove(temp);
						Q.offer(next);
						temp=null;
					}
				}
			}
		}
		for(;curr!=null;curr=curr.parent)
		{
			System.out.println(curr);
		}
	}
}
