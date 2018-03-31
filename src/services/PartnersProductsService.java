package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblPartnersProducts;

@Stateless
@LocalBean
public class PartnersProductsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblPartnersProducts> getPartnersProducts() 
	
    {
        Query query	= em.createQuery("SELECT p FROM tblPartnersProducts p");
        try { return (List<tblPartnersProducts>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblPartnersProducts getPartnersProductById(Integer id)
    {
    	try { return em.find(tblPartnersProducts.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Integer addPartnersProduct(tblPartnersProducts product) {
        try
        {
        	if(product.getId() != null) { product.setId(null); }
        	em.persist(product);
        	em.flush();
        	return product.getId();
        } catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean updatePartnersProduct(tblPartnersProducts device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deletePartnersProduct(Integer id)
    {
        tblPartnersProducts device = getPartnersProductById(id);
        if(device != null)
        {
            device.setDeleted(true);
            em.merge(device);
            return true;
        }
        return false;
    }
    
    public tblPartnersProducts getByPartnerProductIdAndMerchantProductId(String partnerProductId, String merchantProductId)
    {
    	try {
    		return em.createNamedQuery("tblPartnersProducts.findByPartProdIdAndMercProdId", tblPartnersProducts.class)
    				 .setParameter("ppId", partnerProductId)
    				 .setParameter("mpId", merchantProductId)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return new tblPartnersProducts();
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    

	// aripe 2018-03-31
    public tblPartnersProducts getByPartnerIdAndPartnerProductId(String partnerId, String partnerProductId) {
		try {
			int partnerIdInt = Integer.parseInt(partnerId);
    		return em.createNamedQuery("tblPartnersProducts.findByPartnerIdAndPartnerProductId", tblPartnersProducts.class)
    				 .setParameter("partnerId", partnerIdInt)
    				 .setParameter("partnerProductId", partnerProductId)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return new tblPartnersProducts();
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}	
    }
}