package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.metamodel.Metamodel;

import models.tblClosures;

@Stateless
@LocalBean
public class ClosuresService extends DefaultServiceClass {
	
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblClosures> getClosures() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblClosures b");
        try { return (List<tblClosures>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblClosures getClosureById(Integer id)
    {
    	try { return em.find(tblClosures.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblClosures getByName(String name)
    {
    	try {
    		return em.createNamedQuery("tblClosures.findByName", tblClosures.class)
    				 .setParameter("name", name)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return new tblClosures();
    	}
    }

    public Integer addClosure(tblClosures closure) {
        try
        {
        	if(closure.getId() != null) { closure.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = closure.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (closure.getName() != null && closure.getName().length() > maxColumnLength) {
        		closure.setName( closure.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(closure);
        	em.flush();
        	return closure.getId();
        } catch (Exception e) { return null; }
    }

    public Boolean updateClosure(tblClosures closure)
    {
    	if(closure == null || closure.getId() == null) { return false; }
        em.merge(closure);
        return true;
    }

    public Boolean deleteClosure(Integer id)
    {
        tblClosures closure = getClosureById(id);
        if(closure != null)
        {
            closure.setDeleted(true);
            em.merge(closure);
            return true;
        }
        return false;
    }
}