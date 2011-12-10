import java.util.ArrayList;


public class Activite
{Data data;
 int cursor=0;
 ArrayList<Long> visitedStep =new ArrayList<Long>();
	public Activite(Data d)
	{data=d;
		
	}

	private void addStep(long stepId)
	{
		data.resize();	 
		data.stepIds[data.length()-1]=stepId;			
	}
	


	
	public void insertStep(long stepId, long[] predStepsId,long[] succStepsId)
	{	
		addStep(stepId);
		int indexOfCurrentStep= data.getIndexOfStep(stepId);
		if(predStepsId!=null)
		   for(int i=0;i<predStepsId.length;i++)
			setPredLink(indexOfCurrentStep, predStepsId[i]);
		else
		{
			data.activityMatrix[0][indexOfCurrentStep]=1;
		}
		if(succStepsId!=null)
			 for(int i=0;i<succStepsId.length;i++)
			setSuccLink(indexOfCurrentStep,succStepsId[i]);
		
		
	}
	private void setPredLink(int indexOfCurrentStep, long predStepId)
	{
		byte preLevel;
		int idx= data.getIndexOfStep(predStepId);
		preLevel=data.getMaxLevelOf(idx);
		data.activityMatrix[idx][indexOfCurrentStep]=(byte) (preLevel+1); 
			
		
	}

	private void setSuccLink(int indexOfCurrentStep, long succStepId)
	{
		int idx= data.getIndexOfStep(succStepId);
		byte preLevel=data.getMaxLevelOf(indexOfCurrentStep);
		data.activityMatrix[indexOfCurrentStep][idx]=(byte) (preLevel+1);
		updateSuccessors((byte) (preLevel+1),indexOfCurrentStep,idx);
		
	}

	private void updateSuccessors(byte currentLevel, int indexOfCurrentStep, int idx) 
	{for(int i=0;i<data.length();i++)
		for(int j=0;j<data.length();j++)
			if(i==indexOfCurrentStep && j==idx)
				continue;
			else
			if(data.activityMatrix[i][j]>=currentLevel)
				data.activityMatrix[i][j]++;
				
		
	}

	
/*	
public void chekLevelsIntegrityFrom(int origine)
        {
    		int[] outs= getStepsOutOf(origine);
    		int[] ins= getStepsInOf(origine);
		
	    for(int o=0;o<outs.length;o++)// on cherche les entrants pour chaque nodeud J
    		{byte outLevel=data.activityMatrix[origine][outs[0]];
    			
    			for(int i=1;i<ins.length;i++)
    				if(data.activityMatrix[ins[i]][origine]>outLevel)
    		
    		}
	
	
        }
*/
	public void removeStep(long stepId)
	{ 
	  int willRemove = data.getIndexOfStep(stepId);
	  int[] result= new int[4];
	  for(int i=0;i<result.length; i++)
        result[i]=-1;
	  int k=0;
	// recuperer les indicies des successeurs de willremove, 4 au max
	  for(int j=0;j<data.length();j++) 
			if(data.activityMatrix[willRemove][j]>=1)
				{result[k++]= j;
				data.activityMatrix[willRemove][j]=0;
				
				}
			
	 //deplacer les antecedents vers les successeurs 
	  for(int i=0;i<data.length();i++)
	   
	    { if(data.activityMatrix[i][willRemove]>0)
		    {
		      for(k=0;k<result.length;k++)
		       
		    	  if(result[k]>=0 && i!=result[k])// evite d'ecrire sur la diagonale.
		    	  {//System.out.println("--> "+i+" , "+result[k]);
		    	  data.activityMatrix[i][result[k]]=data.activityMatrix[i][willRemove];
		    	  
		    	  }
		      data.activityMatrix[i][willRemove]=0; 
		       
		       
		    }
	    }
		  
				
	}

	
	public long[] getFirstSteps()
	{ long[] result= new  long[4];
	  int k=0;
	  int minLevel =data.length()*data.length();
	  for(int i=0;i<data.length();i++)
		for(int j=0;j<data.length();j++)
			if(data.activityMatrix[i][j]<=minLevel && data.activityMatrix[i][j]>0)
				 minLevel=data.activityMatrix[i][j];
		           
		         
	  for(int i=0;i<data.length();i++)
			for(int j=0;j<data.length();j++)
				if(data.activityMatrix[i][j]==minLevel)			
	                   result[k++]=data.stepIds[j];  
		
	return result;
	
	}
	
	public long[] nextStepsOf(long stepInId)
	{ long[] result= new  long[4]; 
	  int k=0;
		
		int indexOfStep = data.getIndexOfStep(stepInId);
		//byte level=data.getMaxLevelOf(indexOfStep);
		for(int j=0;j<data.length();j++)
			if(data.activityMatrix[indexOfStep][j]>=1)
				result[k++]= data.stepIds[j];
		
		visitedStep.add(stepInId);
		if(result.length==1)
			cursor++;
		
		
		return result;
	}
	public long next()
	{
		return cursor<this.visitedStep.size()? visitedStep.get(cursor++):visitedStep.get(cursor-1);
	
	}
	public long previous()
	{
		return cursor>=0? visitedStep.get(cursor--):visitedStep.get(0);
	}
	
	public long[] prevStepsOf(long stepInId)
	{ long[] result= new  long[4]; 
	  int k=0;
		
		int indexOfStep = data.getIndexOfStep(stepInId);
		//byte level=data.getMaxLevelOf(indexOfStep);
		for(int i=0;i<data.length();i++)
			if(data.activityMatrix[i][indexOfStep]>=1)
				result[k++]= data.stepIds[i];
		if(result.length==1)
			cursor--;
		return result;
	}
	
}
