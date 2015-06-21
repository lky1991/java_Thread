package com.lky.lock.condition;

public class FileMock {
	private String content[];
	private int index;
	
	public FileMock(int size,int length){
		content=new String[size];
		for(int i=0;i<size;++i){
			StringBuilder bufferBuilder=new StringBuilder(length);
			for(int j=0;j<length;++j){
				int res=(int)Math.random()*255;
				bufferBuilder.append((char)res);
			}
			content[i]=bufferBuilder.toString();
		}
		index=0;
	}
	
	public boolean hasMoreLines(){
	 return index<content.length;
  }
	
  public String getLine(){
	 if(this.hasMoreLines()){
		 System.out.println("Mock :"+(content.length-index));
		 return content[index++];
	 }
	 return null;
 }
  
}
