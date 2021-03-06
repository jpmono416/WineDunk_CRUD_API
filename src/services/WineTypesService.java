package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblWineTypes;

@Stateless
@LocalBean
public class WineTypesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblWineTypes> getWineTypes() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblWineTypes b");
        try { return (List<tblWineTypes>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblWineTypes getWineTypeById(Integer id)
    {
    	try { return em.find(tblWineTypes.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Integer addWineType(tblWineTypes wineType) {
        try
        {
        	if(wineType.getId() != null) { wineType.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = wineType.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (wineType.getName() != null && wineType.getName().length() > maxColumnLength) {
        		wineType.setName( wineType.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(wineType);
        	em.flush();
        	return wineType.getId();
        } catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean updateWineType(tblWineTypes wineType)
    {
    	if(wineType == null || wineType.getId() == null) { return false; }
        em.merge(wineType);
        return true;
    }

    public Boolean deleteWineType(Integer id)
    {
        tblWineTypes wineType = getWineTypeById(id);
        if(wineType != null)
        {
            wineType.setDeleted(true);
            em.merge(wineType);
            return true;
        }
        return false;
    }

	public tblWineTypes getByDefaultName() {
		
		tblWineTypes wineType = new tblWineTypes();
		try {
			
			wineType = em.createNamedQuery("tblWineTypes.findByName", tblWineTypes.class)
			     	.setParameter("name", "Other")
			     	.getSingleResult();
			
			return wineType;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return new tblWineTypes();
		}
	}

	public tblWineTypes getByName(String wineTypeName) {
		
		tblWineTypes wineType = new tblWineTypes();
		try {
			
			wineType = em.createNamedQuery("tblWineTypes.findByName", tblWineTypes.class)
				     	.setParameter("name", wineTypeName)
				     	.getSingleResult();
			if ( (wineType != null) && (wineType.getId() != null) ) { return wineType; } else {
				
				return getByDefaultName();
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return getByDefaultName();
			
		}
	}
}