package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T16:01:39.371+0100")
@StaticMetamodel(tblLanguages.class)
public class tblLanguages_ {
	public static volatile SingularAttribute<tblLanguages, Integer> id;
	public static volatile SingularAttribute<tblLanguages, String> name;
	public static volatile SingularAttribute<tblLanguages, String> shortName;
	public static volatile SingularAttribute<tblLanguages, Boolean> deleted;
	public static volatile ListAttribute<tblLanguages, tblCountries> countries;
	public static volatile ListAttribute<tblLanguages, tblUsers> users;
}
