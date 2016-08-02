

public class XMLCreate {


    
    

	public static Block[] Testing(String[][] arr)
    {
        
                
        Block[] table = new Block[arr.length];
        
        for (int i = 0; i < arr.length; i++){
        	table[i] = new Block (arr[i]);
        }
        
        return table;
        
        /*
         *    String[][]newArr=new String[arr.length][arr[0].length];
         *   for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[0].length;j++)
            {
                newArr[i][j]=arr[i][arr[0].length-1-j];
            }
           // return newArr;  
        }
         Block block1 = new Block(newArr[0][0], newArr[0][1],newArr[0][2], newArr[0][3],newArr[0][4]);
         Block block2 = new Block(newArr[1][0], newArr[1][1],newArr[1][2], newArr[1][3],newArr[1][4]);
         Block block3 = new Block(newArr[2][0], newArr[2][1],newArr[2][2], newArr[2][3],newArr[2][4]);
         Block block4 = new Block(newArr[3][0], newArr[3][1],newArr[3][2], newArr[3][3],newArr[3][4]);
         Block block5 = new Block(newArr[4][0], newArr[4][1],newArr[4][2], newArr[4][3],newArr[4][4]);
        
         Block[] tab = new Block[] { block1, block2, block3, block4, block5 };
		return (tab);    
		*/  
    }
}
