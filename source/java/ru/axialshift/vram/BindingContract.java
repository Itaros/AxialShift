package ru.axialshift.vram;

import org.lwjgl.opengl.GL20;

/*
 * Class ensures that particular program is designed 
 * to be working with this particular bindings
 */
public class BindingContract {

	private class ContractPair{

		private int index;
		private String pointname;
		
		public ContractPair(int index, String pointname) {
			this.index=index;
			this.pointname=pointname;
		}

		public int getIndex() {
			return index;
		}

		public String getPointname() {
			return pointname;
		}
		
		
		
	}
	
	private ContractPair[] cps;
	
	/*
	 * Supply binding pairs. Order matters for interleaved VBOIncapsulator!
	 * Example:
	 * {0->in_Position}
	 */
	public BindingContract(String[] pairs){
		cps = new ContractPair[pairs.length];
		
		int i = -1;
		for(String s:pairs){
			i++;
			String[] ps = s.split("->");
			
			int index = Integer.parseInt(ps[0]);
			String pointname = ps[1];

			cps[i]=new ContractPair(index,pointname);
		}
	}
	
	public ContractPair[] getContracts(){
		return cps.clone();
	}
	
	public void configureGLShader(int pointer) {
		for(ContractPair p:cps){
			GL20.glBindAttribLocation(pointer, p.getIndex(), p.getPointname());
		}
	}

	public void enableVBOAttribArrays() {
		for(ContractPair p:cps){
			GL20.glEnableVertexAttribArray(p.getIndex());
		}
	}

}
