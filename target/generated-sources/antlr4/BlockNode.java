
public class BlockNode {
	String nodeName;
	BlockNode exit1;
	BlockNode exit2;
	String content;
	
	public BlockNode(String name, String content, BlockNode outA){
		this.nodeName = name;
		if (content == null){
			content = "";
		}
		this.content = content;
		exit1 = outA;
	}
	
	public void setExit1 (BlockNode outA){
		exit1 = outA;
	}
	
	public void setExit2 (BlockNode outB){
		exit2 = outB;
	}
	
	public void setExits (BlockNode outA, BlockNode outB){
		exit1 = outA;
		exit2 = outB;
	}
	
	public void addContent (String content){
		this.content = this.content + "  " + content;
	}
	
	public BlockNode getTail(){
		if (exit1 != null){
			return exit1.getTail();
		} else if (exit2 != null){
			return exit2.getTail();
		}else{
			return this;
		}
	}
	
	public void print(){
		System.out.println(this.nodeName + " " + this.content);
		if (this.exit1 != null){
			exit1.print();
		} else if (this.exit2 != null){
			exit2.print();
		}
	}
	
	public BlockNode compressCFG(){
		
		if (this == null){
			return null;
		}
		while(exit1.isRegularLeaf()){
			if (this.exit1 != null){
				compressNextNodeA();
			}
			else if (this.exit2 != null){
				compressNextNodeB();
			}else{ 
				break;
			}
		}
		System.out.println("compressed node");
		
		if (this.exit1 != null){
			System.out.println("compressing exit1");
			if(Integer.valueOf(nodeName)!=-1){
			exit1.compressCFG();
			}else {
				return this;
			}
		}
		if (this.exit2 != null){
			System.out.println("compressing exit2");
			exit2.compressCFG();
		}
		
		
		return this;
	}
	
	public void compressNextNodeA(){
		if (exit1.isRegularLeaf()){
			this.addContent(exit1.content);
			//System.out.println("Node compressed, new node: " + this.content);
			this.exit1 = exit1.exit1;
		}
	}
	
	public void compressNextNodeB(){
		if (exit2.isRegularLeaf()){
			this.addContent(exit2.content);
			//System.out.println("2Node compressed, new node: " + this.content);
			this.exit2 = exit2.exit1;
		}
	}
	
	
	public boolean isChangeLeaf(){
		int value = Integer.valueOf(nodeName);
		if ((value == 22)||(value ==15)||(value ==41)||(value ==6)||(value ==21)||(value ==50)||(value ==13)||(value ==11)||(value ==36)||(value ==4)||(value ==47)||(value ==7)||(value ==23)||(value ==44)||(value ==45)){
			return true;
		}
		return false;
	}
	
	public boolean isChangeLeaf(String  val,String prev){
		int value = Integer.valueOf(val);
		int previous = Integer.valueOf(prev);
		if ((value == 22)||(value ==15)||(value ==41)||(value ==6)||(value ==21)||(value ==50)||(value ==13)||(value ==11)||(value ==36)||(value ==4)||(value ==47)||(value ==7)||(value ==23)||(value ==44)||(value ==45)||(value==59)||((value==59)&&(previous==22))){
			return true;
		}
		return false;
	}
	
	public boolean isChangeLeaf(String  val){
		int value = Integer.valueOf(val);
		if ((value == 22)||(value ==15)||(value ==41)||(value ==6)||(value ==21)||(value ==50)||(value ==13)||(value ==11)||(value ==36)||(value ==4)||(value ==47)||(value ==7)||(value ==23)||(value ==44)||(value ==45)||(value==59)){
			return true;
		}
		return false;
	}
	
	public boolean isRegularLeaf(){
		if (!isChangeLeaf()){
			return true;
		}
		return false;
	}
	
	public boolean isRegularLeaf(String val, String prev){
		if (!isChangeLeaf(val, prev)){
			return true;
		}
		return false;
	}
	
	
	public boolean isMultipleConditionLeaf(){
		int value = Integer.valueOf(nodeName);
		if ((value == 77)||(value ==78)){
			return true;
		}
		return false;
	}
	
	public boolean isMultipleConditionLeaf(String val){
		int value = Integer.valueOf(val);
		if ((value == 77)||(value ==78)){
			return true;
		}
		return false;
	}

	public String[][] toArray2(String[][] array, int counter){
		if (this != null){
			//System.out.println("toArray called! " + counter);
			array[counter][0]=this.nodeName;
			array[counter][1]=this.content;
			array[counter][2]=this.exit1.nodeName;
			counter++;
			//System.out.println(this.nodeName + " " + this.content);
		}
		
		if (this.exit1 != null){
			 //this.exit1.toArray(array, counter);
		}
		return array;
	}
	
	public String[][] toArray(String[][] array, int count, int count2){
		System.out.println("toArray called! " + count + " " + this.nodeName);
		if (this != null){
			array[count][0]=this.nodeName;
			System.out.println("\t\t\ttesting: nodename: "+ array[count][0]);
			array[count][1]=this.content;
			array[count][2]=this.exit1.nodeName;
			count++;
		}
		if (count == count2-1){
			return array;
		}
		
		if (this.exit1 != null){
			return exit1.toArray(array, count, count2);
		}
		return array;
	}
	
	public String[][] getArray(){
		System.out.println("getArray called!");
		int nodecount = this.countNodes(0);
		//System.out.println("node count = " + nodecount);
		String[][]array = new String [nodecount][5];
		System.out.println("array created!");
		array = this.toArray(array, 0, nodecount);
		String[][] trimmedarray = trimArray(array);
		return trimmedarray;
	}
	
	public String[][] trimArray(String[][] array){
		String[][] trimmedarray = new String[array.length-3][5];
		//System.out.println("trimArray called! trimmedarrayarray.length = "+ trimmedarray.length);
		for (int i = 0; i < trimmedarray.length; i++){
			trimmedarray[i][0] = array[i+1][0];
			trimmedarray[i][1] = array[i+1][1];
			trimmedarray[i][2] = array[i+1][2];
			trimmedarray[i][3] = array[i+1][3];
			trimmedarray[i][4] = array[i+1][4];
		}
		return trimmedarray;
		
	}
	
	public int countNodes(int count){
		System.out.println("countNodes called!");
		if (this != null){
			count++;
		}
		if (this.exit1 != null){
			return exit1.countNodes(count);
		}
		return count;
	}
	
	public void printArray(String[][] array){
		for (int i = 0; i < array.length; i++){
			//System.out.println("Node #: "+ array[i][0]+ " | Node Content: " + array[i][1]+ " | Node Exit1: " + array[i][2]+ " | Node Exit2: " + array[i][3]+ " | Node Value: " + array[i][4]);
			System.out.println(array[i][0]+ "," + array[i][1]+ "," + array[i][2]+ "," + array[i][3]+ "," + array[i][4]);
		}
	}
	
	public String[][] constructSimpleCFG(String[][] array){
		System.out.println("constructSimpleCFG called!");
		int length = getNumberofChangeofControl(array);
		String[][] simpleCFG = new String [length][5];
		System.out.println("simpleCFG created! Length " + length);
		int index = 0;
		
		for (int i = 0; i < length; i++){
			System.out.println("Inside for loop!");
			int changeofcontrolindex = getNextChangeofControl(array, index);
			if(index!=-1){
			String blockcontent = getBlockContent (array, index, changeofcontrolindex);
			//TODO get the exit1 and exit2 of the nodes
			String nextnode="";
			
			simpleCFG[i][0] = "" + i;
			simpleCFG[i][1] = blockcontent;
			simpleCFG[i][2] = "";
			simpleCFG[i][3] = "";
			simpleCFG[i][4] = ""+ array[i][0];
			index = changeofcontrolindex;
			if(i>=1){
			if(simpleCFG[i-1][2].equals("")){
				if(59==Integer.valueOf(simpleCFG[i-1][4])){
					simpleCFG[i-1][2] = "" + i;
				}
				nextnode=getParent(array, simpleCFG, i);
				if(nextnode.equals("-1")){
					simpleCFG[i-1][2]=getParent(array, simpleCFG, i+1);
					simpleCFG[i+1][2]=getParent(array, simpleCFG, i-1);
				}
				else{
				simpleCFG[i-1][2]=nextnode;
				}
			}else{
				if(59==Integer.valueOf(simpleCFG[i-1][4])){
					simpleCFG[i-1][3] = "" + i;
				}
				nextnode=getParent(array, simpleCFG, i-1);
				if(nextnode.equals("-1")){
					simpleCFG[i-1][3]=getParent(array, simpleCFG, i+1);
					simpleCFG[i+1][3]=getParent(array, simpleCFG, i-1);
				}else{
				simpleCFG[i-1][3]=nextnode;
				}
			}
			}
			//*******************************
			
			//pass once and do simple edge from method call to open curly and open curly to open curly
			
			//fillExits()
			
		
		//return simpleCFG;
		
		/*
		int changeofcontrolindex = getNextChangeofControl(array, 0);
		int count = changeofcontrolindex;
		if (changeofcontrolindex == -1 && array.length > changeofcontrolindex){
			changeofcontrolindex = array.length-1;
		}
		String blockcontent = getBlockContent (array, 0, changeofcontrolindex);
		BlockNode SimpleCFG = new BlockNode("0", blockcontent, null);
		while (count < array.length){
			if (isChangeLeaf(array[index][0]))
		}
		*/
		/*
		for(int i=0; i<array.length;i++){
			if (isRegularLeaf(array[i][0])){
				this.addContent(array[i][1]);
			} else if (isChangeLeaf(array[i][0])){
				this.exit1 = new BlockNode (array[i][0], array[i][1], null);
			}*/
		}
		}
		//return fillSimpleExits(simpleCFG);
		return simpleCFG;
	}
	
	public String[][] constructMultipleCFG(String[][] array){
		System.out.println("constructSimpleCFG called!");
		int length = getNumberofChangeofControl2(array);
		String[][] simpleCFG = new String [length][5];
		System.out.println("simpleCFG created! Length " + length);
		int index = 0;
		
		for (int i = 0; i < length; i++){
			System.out.println("Inside for loop!");
			int changeofcontrolindex = getNextChangeofControl2(array, index);
			if(index!=-1){
				String blockcontent = getBlockContent (array, index, changeofcontrolindex);
				//TODO get the exit1 and exit2 of the nodes
				String nextnode="";
				
				simpleCFG[i][0] = "" + i;
				simpleCFG[i][1] = blockcontent;
				simpleCFG[i][2] = "";
				simpleCFG[i][3] = "";
				simpleCFG[i][4] = ""+ array[i][0];
				index = changeofcontrolindex;
					if(i>=1){
					if(simpleCFG[i-1][2].equals("")){
						if(59==Integer.valueOf(simpleCFG[i-1][4])){
							simpleCFG[i-1][2] = "" + i;
						}
						nextnode=getParent(array, simpleCFG, i);
						if(nextnode.equals("-1")){
							simpleCFG[i-1][2]=getParent(array, simpleCFG, i+1);
							simpleCFG[i+1][2]=getParent(array, simpleCFG, i-1);
						}
						else{
						simpleCFG[i-1][2]=nextnode;
						}
					}else{
						if(59==Integer.valueOf(simpleCFG[i-1][4])){
							simpleCFG[i-1][3] = "" + i;
						}
						nextnode=getParent(array, simpleCFG, i-1);
						if(nextnode.equals("-1")){
							simpleCFG[i-1][3]=getParent(array, simpleCFG, i+1);
							simpleCFG[i+1][3]=getParent(array, simpleCFG, i-1);
						}else{
						simpleCFG[i-1][3]=nextnode;
						}
					}
				}
			}
		}
		//return fillSimpleExits(simpleCFG);
		return simpleCFG;
	}
	
	public String [][] fillSimpleExits(String[][] array){
		String[][] cfg = array;
	
		System.out.println("fillSimpleExits called!");
		
		for (int i = 1; i < cfg.length; i++){
			if(cfg[i][4] != null){
				if (59==Integer.valueOf(cfg[i][4])){
					if ((59==Integer.valueOf(cfg[i-1][4])) || isChangeLeaf(cfg[i-1][4])){
						cfg[i-1][2] = "" + i;
					}
				}
			}
			if (59==Integer.valueOf(cfg[i-1][4])){
				cfg[i-1][2] = "" + i;
			}
			if(cfg[i][4]!= null){
				if (60==Integer.valueOf(cfg[i][4])){
					int opencurly = findOpeningCurly(cfg, i);
					if (opencurly != -1){
						cfg[i][3] = "" + opencurly;
						//cfg[opencurly][4] = "" + 200;
					}
				}
			}
		}
		System.out.println("fillSimpleExits finished!");
		return cfg;
	}
	
	public int findOpeningCurly(String[][] cfg, int index){
		for (int i = index; i >= 0; i--){
			if(59==Integer.valueOf(cfg[i][4])){
				return i;
			}
		} return -1;
	}
	
	
		
	
	public String getParent(String [][] arr, String [][] current, int index){
		int value=0;
		for (int i = index; i < current.length; i++){
			if(!(current[i][4]==null)){
			if(i>1){	
			value= Integer.valueOf(current[i-2][4]);
			}
			}else{
			}
			if (((value ==21)||(value ==50)||(value ==13)/*||(value ==35)*/)){
				return current[i-2][0];
			}
			else{
				return current[i][0];
			}	
		}
		return "fail";
	}
				
		
	public String[][] collapseCurlyBracketBlocks(String[][] array){
		
		for (int i = 1; i < array.length; i++){
			if((getFirstTwoChars(array[i][0]).equalsIgnoreCase(" {") && (getFirstThreeChars(array[i-1][0]).equalsIgnoreCase(" If")))){
				return collapseCurlyBracketBlocks(collapse(array, i));
			}
		}
		return array;
	}
	
	public String getFirstChar(String string){
		if (string == null){
			return "";
		}
		String retval = "";
		if (string.length() > 1){
			retval = "" + string.charAt(0);
		}
		return retval;
	}
	
	public String getFirstTwoChars(String string){
		if (string == null){
			return "";
		}
		String retval = "";
		if (string.length() > 2){
			retval = "" + string.charAt(0);
			retval = retval + string.charAt(1);
		}
		return retval;
	}
	
	public String getFirstThreeChars(String string){
		if (string == null){
			return "";
		}
		String retval = "";
		if (string.length() > 3){
			retval = "" + string.charAt(0);
			retval = retval + string.charAt(1);
			retval = retval + string.charAt(2);
		}
		return retval;
	}
	
	public String[][] collapse(String[][] array, int index){
		String[][] collapsedarray = new String [array.length-1][4];
		for (int i = 0; i < collapsedarray.length; i++){
			if (i < index){
				collapsedarray[i] = array[i];
			} else if (i == index){
				collapsedarray[i-1][1] = collapsedarray[i-1][1] + array[i][1];
			} else{
				collapsedarray[i-1] = array[i];
			}
		}
		return collapsedarray;
	}
		
	
	
	public String[][] constructMultiCFG(String[][] array){
		return array;
	}
	
	public int getNextChangeofControl(String [][] array, int i){
		System.out.println("getNextChangeofControl called!");
		String prev="0";
		for (int index = i+2; index < array.length; index++){
			if (isChangeLeaf(array[index][0], prev)){
				if(array[index][0].equals("22")){
					prev= array[index][0];
				}
				System.out.println("returning " + index);
				return index;
				}
			}
		System.out.println("returning -1");
		return -1;
	}
	public int getNextChangeofControl2(String [][] array, int i){
		System.out.println("getNextChangeofControl called!");
		String prev="0";
		for (int index = i+2; index < array.length; index++){
			if (isChangeLeaf(array[index][0], prev)||this.isMultipleConditionLeaf(array[index][0])){
				if(array[index][0].equals("22")){
					prev= array[index][0];
				}
				System.out.println("returning " + index);
				return index;
				}
			}
		System.out.println("returning -1");
		return -1;
	}
	
	public int getNumberofChangeofControl(String[][] array){
		System.out.println("getNumberofChangeofControl called!");
		int count = 1;
		int index = 0;
		//int counter = 10;
		boolean check = true;
		while(check){
			count++;
			//System.out.println("count: " + count);
			index = getNextChangeofControl(array, index);
			//System.out.println("index: " + index);
			if (index == -1){
				check = false;
			}
			//counter--;
		}
		return count;
	}
	
	public int getNumberofChangeofControl2(String[][] array){
		System.out.println("getNumberofChangeofControl called!");
		int count = 1;
		int index = 0;
		//int counter = 10;
		boolean check = true;
		while(check){
			count++;
			//System.out.println("count: " + count);
			index = getNextChangeofControl2(array, index);
			//System.out.println("index: " + index);
			if (index == -1){
				check = false;
			}
			//counter--;
		}
		return count;
	}
	
	public String getBlockContent(String[][] array, int start, int end){
		if (end == -1){
			end = array.length;
		}
		
		String content = "";
		for (int i = start; i < end; i++){
			//System.out.println("start: "+start);
			//System.out.println("end: "+end);
			content = content +" "+ array[i][1];
		}
		return content;
	}
	

}
