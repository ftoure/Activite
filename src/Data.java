
public class Data {

	byte[][] activityMatrix;
	long [] stepIds;
public Data	()
	{
	activityMatrix = new byte[1][1];
	//activityMatrix[0][1]=1;
	stepIds= new long[activityMatrix.length]	;
	}
public int length()
{return stepIds.length;}

public void resize() 
{
	long[] temp1 = stepIds;
	stepIds= new long [stepIds.length+1];
	for(int i =0; i<temp1.length;i++)
	  stepIds[i]=temp1[i];
	
	
	
	byte[][] temp2 = activityMatrix;;
	activityMatrix= new byte [activityMatrix.length+1][activityMatrix.length+1];
	for(int i =0; i<temp2.length;i++)
		for(int j =0; j<temp2.length;j++)
			activityMatrix[i][j]=temp2[i][j];
	
}

public int getIndexOfStep(long stepId) 
{
	for(int i=0;i<stepIds.length;i++)
		if(stepIds[i]==stepId)
			return i;
	return -1;
}

public byte getMaxLevelOf(int idx) 
{
	byte max=0;
	for(int i=0; i<stepIds.length ;i++)
		max=(activityMatrix[i][idx]>max)?activityMatrix[i][idx]:max;
	
	return max;
}

public String toString()
{
	String result="";
	for(int i =0; i<activityMatrix.length;i++)
		{for(int j =0; j<activityMatrix.length;j++)
			result+="\t"+activityMatrix[i][j];
		result+="\n";
		}
 return result;
}

}