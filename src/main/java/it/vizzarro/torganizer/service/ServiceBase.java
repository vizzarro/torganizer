/**
 * 
 */
package it.vizzarro.torganizer.service;

/**
 * @author Utente
 *
 */
public abstract class ServiceBase<T, S> {

	/**
	 * 
	 */
	public ServiceBase() {
	}

	public abstract void populate(T target, S source);
	
	
}
