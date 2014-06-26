package br.com.iondax.entities.financeiro.relatorios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import br.com.iondax.repositories.financeiro.ILancamentosRepositories;
 
/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class LazyLancamentosDataModel extends LazyDataModel<Lancamentos> {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 825600964660413548L;

	ILancamentosRepositories servico;
	
    private List<Lancamentos> datasource;
     
    public LazyLancamentosDataModel(List<Lancamentos> datasource,ILancamentosRepositories servico) {
        this.datasource = datasource;
        this.servico = servico;
    }
    

     
    @Override
    public Lancamentos getRowData(String rowKey) {
        for(Lancamentos lancamentos : datasource) {
            if(lancamentos.getId().equals(rowKey))
                return lancamentos;
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(Lancamentos Lancamentos) {
        return Lancamentos.getId();
    }
 
    @Override
    public List<Lancamentos> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Lancamentos> data = new ArrayList<Lancamentos>();
        datasource = servico.findAll();
        
        if(datasource.size()>0){
        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			for(int i=0;i<datasource.size();i++){
				if(i>0){
					datasource.get(i).setSaldo(datasource.get(i-1).getSaldo().add(datasource.get(i).getValor())); 
				}else{
					datasource.get(i).setSaldo(datasource.get(i).getValor());
				}
				datasource.get(i).setDataTransacaoString(dateFormat.format(datasource.get(i).getDataTransacao()));
				datasource.get(i).setNomeContaBancaria(datasource.get(i).getContaBancaria().getNomeContaBancaria());
			}
		}
        
        //filter
        for(Lancamentos lancamentos : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(lancamentos.getClass().getField(filterProperty).get(lancamentos));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(lancamentos);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
    
    public List<Lancamentos> getDataSource(){
    	return datasource;
    }
    
}