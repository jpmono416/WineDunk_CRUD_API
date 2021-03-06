package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblWines;

/**
 * 
 * 
 *
 */
@Stateless
@LocalBean
public class WinesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblWines> getWines()
    {
        Query query	= em.createQuery("SELECT b FROM tblWines b");
        try { return (List<tblWines>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

	/**
	 * 
	 * @param id
	 * @return
	 */
    public tblWines getWineById(Integer id)
    {
    	try { return em.find(tblWines.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    /**
     * 
     * @param gtin
     * @return
     */
    public tblWines getWineByGtin(String gtin)
    {
    	try {
    		return em.createNamedQuery("tblWines.findByGtin", tblWines.class).setParameter(0, gtin).getSingleResult();
    	} catch(NoResultException noResExc) {
    		return null;
    	}
    }

    /**
     * 
     * @param name
     * @param bottleSize
     * @param vintage
     * @return
     */
    public tblWines getWineByNameBottleAndVintage(String name, Float bottleSize, Integer vintage)
    {
    	try {
    		return em.createNamedQuery("tblWines.FindByNameBottleAndVintage", tblWines.class)
    			 .setParameter("name", name)
    			 .setParameter("bottleSize", bottleSize)
    			 .setParameter("vintage", vintage)
    			 .getSingleResult();
    	} catch(NoResultException noResExc) {
    		return null;
    	}
    }

    /**
     * 
     * @param wine
     * @return
     */
    public Integer addWine(tblWines wine) {
        try
        {
        	if(wine.getId() != null) { wine.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = wine.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (wine.getName() != null && wine.getName().length() > maxColumnLength) {
        		wine.setName( wine.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = wine.getClass().getDeclaredField("shortDescription").getAnnotation(Column.class).length();
        	if (wine.getShortDescription() != null && wine.getShortDescription().length() > maxColumnLength) {
        		wine.setShortDescription( wine.getShortDescription().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = wine.getClass().getDeclaredField("imageURL").getAnnotation(Column.class).length();
        	if (wine.getImageURL() != null && wine.getImageURL().length() > maxColumnLength) {
        		wine.setImageURL( wine.getImageURL().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = wine.getClass().getDeclaredField("gtin").getAnnotation(Column.class).length();
        	if (wine.getGtin() != null && wine.getGtin().length() > maxColumnLength) {
        		wine.setGtin( wine.getGtin().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(wine);
        	em.flush();
        	return wine.getId();
        } catch (Exception e) { return null; }
    }

    /**
     * 
     * @param wine
     * @return
     */
    public Boolean updateWine(tblWines wine)
    {	
    	if(wine == null || wine.getId() == null) { return false; }
        em.merge(wine);
        return true;
    }

    /**
     * 
     * @param id
     * @return
     */
    public Boolean deleteWine(Integer id)
    {
        tblWines wine = getWineById(id);
        if(wine != null)
        {
            wine.setDeleted(true);
            em.merge(wine);
            return true;
        }
        return false;
    }

	public boolean setMinimumPrices()
	{
		try {
			this.em.createNamedStoredProcedureQuery("setMinimumPrices").execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}