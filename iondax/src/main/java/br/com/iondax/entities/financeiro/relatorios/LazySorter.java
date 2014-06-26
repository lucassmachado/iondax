package br.com.iondax.entities.financeiro.relatorios;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

public class LazySorter implements Comparator<Lancamentos>{
	 private String sortField;
     
	    private SortOrder sortOrder;
	     
	    public LazySorter(String sortField, SortOrder sortOrder) {
	        this.sortField = sortField;
	        this.sortOrder = sortOrder;
	    }
	 
	    public int compare(Lancamentos lancamentos1, Lancamentos lancamentos2) {
	        try {
	            Object value1 = Lancamentos.class.getField(this.sortField).get(lancamentos1);
	            Object value2 = Lancamentos.class.getField(this.sortField).get(lancamentos2);
	 
	            int value = ((Comparable)value1).compareTo(value2);
	             
	            return SortOrder.DESCENDING.equals(sortOrder) ? value : -1 * value;
	        }
	        catch(Exception e) {
	            throw new RuntimeException();
	        }
	    }
}
