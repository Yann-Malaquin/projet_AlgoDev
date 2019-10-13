package fr.projet;

public class Case {
	 private String val;
	 private boolean modifiable;

	 public Case() {
		  setVal("0");
		  setModifiable(true); }

	public Case(String val, boolean modifiable) {
		  this.setVal(val);
		  this.setModifiable(modifiable); }

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public boolean isModifiable() {
		return modifiable;
	}

	public void setModifiable(boolean modifiable) {
		this.modifiable = modifiable;
	}
		}