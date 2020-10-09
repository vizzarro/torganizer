package it.vizzarro.torganizer.service;

public abstract class CrudServiceImpl<S,F extends BaseFilter,M,ID>  implements CrudService<S,F,M,ID> {
	
	public abstract void populate(M target, S source);

}
