package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:41.693+0100")
@StaticMetamodel(tblColours.class)
public class tblColours_ {
	public static volatile SingularAttribute<tblColours, Integer> id;
	public static volatile SingularAttribute<tblColours, String> name;
	public static volatile SingularAttribute<tblColours, Boolean> deleted;
	public static volatile ListAttribute<tblColours, tblWines> wines;
}