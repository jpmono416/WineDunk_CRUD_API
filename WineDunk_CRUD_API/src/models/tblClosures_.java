package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:41.678+0100")
@StaticMetamodel(tblClosures.class)
public class tblClosures_ {
	public static volatile SingularAttribute<tblClosures, Integer> id;
	public static volatile SingularAttribute<tblClosures, String> name;
	public static volatile SingularAttribute<tblClosures, Boolean> deleted;
	public static volatile ListAttribute<tblClosures, tblWines> wines;
}
