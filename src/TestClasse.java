
public class TestClasse {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Data d= new Data();
		Activite a = new Activite(d);
		//a.insertStep(9L,null, null);
		a.insertStep(10L,null, null);
		a.insertStep(20L, new long[]{10L}, null);
		a.insertStep(30L, new long[]{10L}, null);
		
		a.insertStep(40L, new long[]{20L}, null);
		a.insertStep(50L, new long[]{30L}, null);
		a.insertStep(60L, new long[]{50L}, null);
		a.insertStep(70L, new long[]{60L,30L,40L}, new long[]{30L});
		a.insertStep(80L, new long[]{70L}, null);
		
		for(int i=0; i<d.length();i++)
			System.out.print("\t"+d.stepIds[i]);
		
		System.out.println("\n\n");
		
		System.out.println(d);
		
		System.out.println("\n\n");
		
		a.removeStep(30L);
	    System.out.println(d);
		
		
		
		/*System.out.println("next Steps \n\n");
		long[] next=a.nextStepsOf(40L);
		for(int i=0;i<next.length;i++)
		  {if(next[i]>0)
			 System.out.print(next[i]+"\t"); 
		  }
		System.out.println("\n\nprevious step\n\n ");
		long[] prev=a.prevStepsOf(40L);
		for(int i=0;i<prev.length;i++)
		  {if(prev[i]>0)
			 System.out.print(prev[i]+"\t"); 
		  }
		System.out.println("\n\nEntrees step\n\n ");
		long[] entree=a.getFirstSteps();
		for(int i=0;i< entree.length;i++)
		  {if( entree[i]>0)
			 System.out.print( entree[i]+"\t"); 
		  }
		*/
	}

}
