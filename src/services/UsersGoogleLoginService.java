package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUsersGoogleLogin;

@Stateless
@LocalBean
public class UsersGoogleLoginService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblUsersGoogleLogin> getUserGoogleLogins() 
	
    {
        Query query	= em.createQuery("SELECT p FROM tblUserGoogleLogins p");
        try { return (List<tblUsersGoogleLogin>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUsersGoogleLogin getUserGoogleLoginById(Integer id)
    {
    	try { return em.find(tblUsersGoogleLogin.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addUserGoogleLogin(tblUsersGoogleLogin device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = device.getClass().getDeclaredField("googleIdToken").getAnnotation(Column.class).length();
        	if (device.getGoogleIdToken() != null && device.getGoogleIdToken().length() > maxColumnLength) {
        		device.setGoogleIdToken( device.getGoogleIdToken().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(device);
        	return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Boolean updateUserGoogleLogin(tblUsersGoogleLogin device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }
}