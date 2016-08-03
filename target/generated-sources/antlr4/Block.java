

public class Block 
{
    String name = null;
    String content = null;
    String exit1 = null;
    String exit2 = null;
    String value = null;
    
    public Block(String name, String content, String exit1,String exit2, String value)
    {
     this.name = name;  
     this.content = content;
     this.exit1 = exit1;
     this.exit2 = exit2;
     this.value = value;
    }
    
    public Block(String[] array){
    	if (array.length == 5){
    		this.name = array[0];  
    	    this.content = array[1];
    	    this.exit1 = array[2];
    	    this.exit2 = array[3];
    	    this.value = array[4];
    	}
    }
    
  
     public String getName()
    {
        return name;
    }
      public String getContent()
    {
        return content;
    }
       public String getExit1()
    {
        return exit1;
    }
       
    public String getExit2()
     {
           return exit2;
     }
    
    public String getValue()
    {
          return value;
    }
}

