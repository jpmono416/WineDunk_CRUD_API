package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import models.TblPFProductsBlackList;

/**
 * Session Bean implementation class PFProductsBlacklistService
 * Created by aripe on 24-mar-2018
 */
@Stateless
@LocalBean
public class PFProductsBlackListService {

    /**
     * Default constructor. 
     */
    public PFProductsBlackListService() {}

	@PersistenceContext(unitName = "Winedunk")
	private EntityManager em;

	public boolean isProductBlacklisted (Integer partnerId, String partnerProductId) {
		
		if ((partnerId > 0) && (partnerProductId != null && !partnerProductId.equals(""))) {
			TypedQuery<TblPFProductsBlackList> typedQuery = em.createNamedQuery("TblPFProductsBlackList.findByPartnerIdPartnerProductId", TblPFProductsBlackList.class);
			typedQuery.setParameter("partnerId", partnerId);
			typedQuery.setParameter("partnerProductId", partnerProductId);
			try {
				TblPFProductsBlackList TblPFProductsBlackList = typedQuery.getSingleResult();
				return ((TblPFProductsBlackList != null) && (TblPFProductsBlackList.getId() > 0));
			} catch (NoResultException noResExc) { 
				return false; 
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	public TblPFProductsBlackList getPFProductsBlackListById (Integer id) {
		
		if (id > 0) {
			TypedQuery<TblPFProductsBlackList> typedQuery = em.createNamedQuery("TblPFProductsBlackList.findById", TblPFProductsBlackList.class);
			typedQuery.setParameter("id", id);
			try {
				TblPFProductsBlackList singleResult = typedQuery.getSingleResult();
				return singleResult;
			} catch (NoResultException noResExc) { 
				return new TblPFProductsBlackList(); 
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}
	
	public List<TblPFProductsBlackList> getPFProductsBlackListAll () {
		
		TypedQuery<TblPFProductsBlackList> typedQuery = em.createNamedQuery("TblPFProductsBlackList.findAll", TblPFProductsBlackList.class);
		try {
			List<TblPFProductsBlackList> pfProductsBlackListAll = typedQuery.getResultList();
			return pfProductsBlackListAll;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<TblPFProductsBlackList> getPFProductsBlackListByPartnerId (Integer partnerId) {
		
		if (partnerId > 0) {
			TypedQuery<TblPFProductsBlackList> typedQuery = em.createNamedQuery("TblPFProductsBlackList.findAllByPartnerId", TblPFProductsBlackList.class);
			typedQuery.setParameter("partnerId", partnerId);
			try {
				List<TblPFProductsBlackList> pfProductsBlackListByPartnerId = typedQuery.getResultList();
				return pfProductsBlackListByPartnerId;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}
	
	public TblPFProductsBlackList getPFProductsBlackListByPartnerIdPartnerProductId (Integer partnerId, String partnerProductId) {
		
		if ((partnerId > 0) && (partnerProductId != null && !partnerProductId.equals(""))) {
			TypedQuery<TblPFProductsBlackList> typedQuery = em.createNamedQuery("TblPFProductsBlackList.findByPartnerIdPartnerProductId", TblPFProductsBlackList.class);
			typedQuery.setParameter("partnerId", partnerId);
			typedQuery.setParameter("partnerProductId", partnerProductId);
			try {
				TblPFProductsBlackList singleResult = typedQuery.getSingleResult();
				return singleResult;
			} catch (NoResultException noResExc) { 
				return new TblPFProductsBlackList(); 
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	public TblPFProductsBlackList addPFProductsBlackLis(TblPFProductsBlackList pfProductsBlackList) {
		if (pfProductsBlackList != null) {
			try {
	        	
	        	// aripe 2018-04-12
	        	Integer maxColumnLength = pfProductsBlackList.getClass().getDeclaredField("partnerProductId").getAnnotation(Column.class).length();
	        	if (pfProductsBlackList.getPartnerProductId() != null && pfProductsBlackList.getPartnerProductId().length() > maxColumnLength) {
	        		pfProductsBlackList.setPartnerProductId( pfProductsBlackList.getPartnerProductId().substring(0, maxColumnLength - 3).concat("...") );
	        	}
				em.persist(pfProductsBlackList);
				em.flush();
				return pfProductsBlackList;				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

}

