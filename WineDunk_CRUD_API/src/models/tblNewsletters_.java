package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:41.797+0100")
@StaticMetamodel(tblNewsletters.class)
public class tblNewsletters_ {
	public static volatile SingularAttribute<tblNewsletters, Integer> id;
	public static volatile SingularAttribute<tblNewsletters, String> name;
	public static volatile ListAttribute<tblNewsletters, tblUserSubscriptions> userSubscriptions;
}