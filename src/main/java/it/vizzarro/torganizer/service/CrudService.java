package it.vizzarro.torganizer.service;

import java.util.List;

public interface CrudService<S,F extends BaseFilter,M,ID> {

    List<S> find(F filter) throws  ServiceException;
    List<S> findAll() throws  ServiceException;
    S findById(ID id) throws  ServiceException;
    S save(S entityService ) throws  ServiceException;

}
