package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:42.294+0100")
@StaticMetamodel(tblUsersFacebookLogin.class)
public class tblUsersFacebookLogin_ {
	public static volatile SingularAttribute<tblUsersFacebookLogin, Integer> id;
	public static volatile SingularAttribute<tblUsersFacebookLogin, tblUsers> userId;
	public static volatile SingularAttribute<tblUsersFacebookLogin, String> fbAccessToken;
	public static volatile SingularAttribute<tblUsersFacebookLogin, String> fbUserID;
}
