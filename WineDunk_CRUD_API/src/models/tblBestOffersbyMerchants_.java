package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-15T20:31:41.623+0100")
@StaticMetamodel(tblBestOffersbyMerchants.class)
public class tblBestOffersbyMerchants_ {
	public static volatile SingularAttribute<tblBestOffersbyMerchants, Integer> id;
	public static volatile SingularAttribute<tblBestOffersbyMerchants, tblShops> merchantId;
	public static volatile SingularAttribute<tblBestOffersbyMerchants, tblWines> wineId;
	public static volatile SingularAttribute<tblBestOffersbyMerchants, Integer> positionIndex;
}