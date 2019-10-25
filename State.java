public class State implements Comparable<State> {

	int[][] arr;
	int level;
	State parent;
	static State goal;


	public State(){
		arr=new int[3][3];
	}
	public State(State state) {
		arr=new int[3][3];
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				arr[i][j]=state.arr[i][j];
			}
		}
	}
	@Override
	public boolean equals(Object obj){
		if(this==obj)
			return true;
		State o=(State)obj;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++)
			  if(this.arr[i][j]!=o.arr[i][j])
				 return false;
		}
		return true;
	}

	@Override
	public int compareTo(State o) {

		int u=this.mismatch()+this.level;
		int v=o.mismatch()+o.level;
		if(u<v)
			return -1;
		if(u>v)
			return 1;

		int x=this.manhattan()+level;
		int y=o.manhattan()+o.level;
		if(x<y)
			return -1;
		if(x>y)
			return 1;

		return 0;
	}

	public int mismatch() {
		int cnt=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(this.arr[i][j]!=goal.arr[i][j]){
					cnt++;
				}
			}
		}
		return cnt;
	}

	private int manhattan() {
		int cnt=0;
		int i1,j1,i2,j2;
		for(i1=0;i1<3;i1++)
		{
			for(j1=0;j1<3;j1++)
			{
				cnt += computeMan(arr[i1][j1],i1,j1);
			}
		}
		return cnt;
	}
	private int computeMan(int x, int i1, int j1) {
		// TODO Auto-generated method stub
		int i,j=0,cnt=0;
		boolean match =false;
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				if(goal.arr[i][j]==x){
					match = true;
					break;
				}
			}
			if(match)
			  break;
		}
		cnt = Math.abs(i-i1)+Math.abs(j-j1);
		return cnt;
	}
	public State getUp(){
		State newState=new State(this);
		for(int i=0;i<3;i++){
		  for(int j=0;j<3;j++){
			try
			{
				if(newState.arr[i][j]==9)
				{
					newState.arr[i][j]=arr[i-1][j];
					newState.arr[i-1][j]=9;
					newState.level=this.level+1;
					newState.parent=this;
					return newState;
				}
			}
			catch(ArrayIndexOutOfBoundsException aioobe){
				continue;
			}
		  }
		}
		return null;
	}
	public State getDown() {
		State newState=new State(this);
		for(int i=0;i<3;i++)
		{
		  for(int j=0;j<3;j++)
		  {
			try
			{
				if(newState.arr[i][j]==9)
				{
					newState.arr[i][j]=arr[i+1][j];
					newState.arr[i+1][j]=9;
					newState.level=this.level+1;
					newState.parent=this;
					return newState;
				}
			}
			catch(ArrayIndexOutOfBoundsException aioobe)
			{
				continue;
			}
		  }
		}
		return null;
	}
	public State getLeft(){
		State newState=new State(this);
		for(int i=0;i<3;i++)
		{
		  for(int j=0;j<3;j++)
		  {
			try
			{
				if(newState.arr[i][j]==9)
				{
					newState.arr[i][j] = newState.arr[i][j-1];
					newState.arr[i][j-1] = 9;
					newState.level=this.level+1;
					newState.parent=this;
					return newState;
				}
			}
			catch(ArrayIndexOutOfBoundsException aioobe)
			{
				continue;
			}
		  }
		}
		return null;
	}
	public State getRight() {

		State newState=new State(this);
		for(int i=0;i<3;i++)
		{
		  for(int j=0;j<3;j++)
		  {
			try
			{
				if(newState.arr[i][j]==9)
				{
					newState.arr[i][j] = newState.arr[i][j+1];
					newState.arr[i][j+1] = 9;
					newState.level=this.level+1;
					newState.parent=this;
					return newState;
				}
			}
			catch(ArrayIndexOutOfBoundsException aioobe)
			{
				continue;
			}
		  }
		}
		return null;

	}

	@Override
	public String toString() {
		int i,j;
		String s="";
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				if(arr[i][j]!=9)
					s=s+arr[i][j]+"\t";
				else
					s=s+" \t";
			}
			s=s+"\n";
		}
		return s;
	}

}
