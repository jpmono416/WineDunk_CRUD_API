package views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "viewBestOffersbyMerchants")
public class viewBestOffersbyMerchants extends Object {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	@Column(name = "merchantId")
	private Integer merchantId;
	public Integer getMerchantId() { return merchantId; }
	public void setMerchantId(Integer merchantId) { this.merchantId = merchantId; }
	
	@Column(name = "wineId")
	private Integer wineId;
	public Integer getWineId() { return wineId; }
	public void setWineId(Integer wineId) { this.wineId = wineId; }

	@Column(name = "positionIndex")
	private Integer positionIndex;
	public Integer getPositionIndex() { return positionIndex; }
	public void setPositionIndex(Integer positionIndex) { this.positionIndex = positionIndex; }
	
	@Column(name = "merchantName")
	private String merchantName;
	public String getMerchantName() { return merchantName; }
	public void setMerchantName(String merchantName) { this.merchantName = merchantName; }
	
	@Column(name = "merchantImage")
	private String merchantImage;
	public String getMerchantImage() { return merchantImage; }
	public void setMerchantImage(String merchantImage) { this.merchantImage = merchantImage; }
	
	@Column(name = "merchantHomePage")
	private String merchantHomePage;
	public String getMerchantHomePage() { return merchantHomePage; }
	public void setMerchantHomePage(String merchantHomePage) { this.merchantHomePage = merchantHomePage; }

	@Column(name = "wineCountryId")
	private Integer wineCountryId;
	public Integer getWineCountryId() { return wineCountryId; }
	public void setWineCountryId(Integer wineCountryId) { this.wineCountryId = wineCountryId; }
	
    @Column(name = "wineCountryName")
    private String wineCountryName;
    public String getWineCountryName() { return wineCountryName; }
	public void setWineCountryName(String wineCountryName) { this.wineCountryName = wineCountryName; }
	
    @Column(name = "wineRegionId")
    private Integer wineRegionId;
    public Integer getWineRegionId() { return wineRegionId; }
	public void setWineRegionId(Integer wineRegionId) { this.wineRegionId = wineRegionId; }
	
    @Column(name = "wineRegionName")
    private String wineRegionName;
    public String getWineRegionName() { return wineRegionName; }
	public void setWineRegionName(String wineRegionName) { this.wineRegionName = wineRegionName; }
	
    @Column(name = "wineWineryId")
    private Integer wineWineryId;
    public Integer getWineWineryId() { return wineWineryId; }
	public void setWineWineryId(Integer wineWineryId) { this.wineWineryId = wineWineryId; }
	
    @Column(name = "wineWineryName")
    private String wineWineryName;
    public String getWineWineryName() { return wineWineryName; }
	public void setWineWineryName(String wineWineryName) { this.wineWineryName = wineWineryName; }
	
    @Column(name = "wineAppellationId")
    private Integer wineAppellationId;
    public Integer getWineAppellationId() { return wineAppellationId; }
	public void setWineAppellationId(Integer wineAppellationId) { this.wineAppellationId = wineAppellationId; }
	
    @Column(name = "wineAppellationName")
    private String wineAppellationName;
    public String getWineAppellationName() { return wineAppellationName; }
	public void setWineAppellationName(String wineAppellationName) { this.wineAppellationName = wineAppellationName; }
	
    @Column(name = "wineColourId")
    private Integer wineColourId;
    public Integer getWineColourId() { return wineColourId; }
	public void setWineColourId(Integer wineColourId) { this.wineColourId = wineColourId; }
	
    @Column(name = "wineColourName")
    private String wineColourName;
    public String getWineColourName() { return wineColourName; }
	public void setWineColourName(String wineColourName) { this.wineColourName = wineColourName; }
	
    @Column(name = "wineVintage")
    private Integer wineVintage;
    public Integer getWineVintage() { return wineVintage; }
	public void setWineVintage(Integer wineVintage) { this.wineVintage = wineVintage; }
	
    @Column(name = "wineName")
    private String wineName;
    public String getWineName() { return wineName; }
	public void setWineName(String wineName) { this.wineName = wineName; }
	
	@Column(name = "wineShortDescription")
	private String wineShortDescription;
	public String getWineShortDescription() { return wineShortDescription; }
	public void setWineShortDescription(String wineShortDescription) { this.wineShortDescription = wineShortDescription; }
	
    @Column(name = "wineDefaultDescription")
    private String wineDefaultDescription;
    public String getWineDefaultDescription() { return wineDefaultDescription; }
	public void setWineDefaultDescription(String wineDefaultDescription) { this.wineDefaultDescription = wineDefaultDescription; }
	
    @Column(name = "wineBottleSize")
    private Integer wineBottleSize;
    public Integer getWineBottleSize() { return wineBottleSize; }
	public void setWineBottleSize(Integer wineBottleSize) { this.wineBottleSize = wineBottleSize; }
	
    @Column(name = "wineAbv")
    private Float wineAbv;
    public Float getWineAbv() { return wineAbv; }
	public void setWineAbv(Float wineAbv) { this.wineAbv = wineAbv; }
	
    @Column(name = "wineImageURL")
    private String wineImageURL;
    public String getWineImageURL() { return wineImageURL; }
	public void setWineImageURL(String wineImageURL) { this.wineImageURL = wineImageURL; }
	
    @Column(name = "wineClosureId")
    private Integer wineClosureId;
    public Integer getWineClosureId() { return wineClosureId; }
	public void setWineClosureId(Integer wineClosureId) { this.wineClosureId = wineClosureId; }
	
    @Column(name = "wineClosureName")
    private String wineClosureName;
    public String getWineClosureName() { return wineClosureName; }
	public void setWineClosureName(String wineClosureName) { this.wineClosureName = wineClosureName; }
	
    @Column(name = "wineGtin")
    private String wineGtin;
    public String getWineGtin() { return wineGtin; }
	public void setWineGtin(String wineGtin) { this.wineGtin = wineGtin; }
	
	@Column(name = "wineMinimumPrice")
	private Float wineMinimumPrice;
	public Float getWineMinimumPrice() { return wineMinimumPrice; }
	public void setWineMinimumPrice(Float wineMinimumPrice) { this.wineMinimumPrice = wineMinimumPrice; }
	
	@Column(name = "winePreviousMaxPrice")
	private Float winePreviousMaxPrice;
	public Float getWinePreviousMaxPrice() { return winePreviousMaxPrice; }
	public void setWinePreviousMaxPrice(Float winePreviousMaxPrice) { this.winePreviousMaxPrice = winePreviousMaxPrice; }
	
	@Column(name = "wineMoneySaving")
	private Float wineMoneySaving;
	public Float getWineMoneySaving() { return wineMoneySaving; }
	public void setWineMoneySaving(Float wineMoneySaving) { this.wineMoneySaving = wineMoneySaving; }
	
	@Column(name = "winePercentageOff")
	private Integer winePercentageOff;
	public Integer getWinePercentageOff() { return winePercentageOff; }
	public void setWinePercentageOff(Integer winePercentageOff) { this.winePercentageOff = winePercentageOff; }
	
	@Column(name = "wineMinimumPriceClicktag")
	private String wineMinimumPriceClicktag;
	public String getWineMinimumPriceClicktag() { return wineMinimumPriceClicktag; }
	public void setWineMinimumPriceClicktag(String wineMinimumPriceClicktag) { this.wineMinimumPriceClicktag = wineMinimumPriceClicktag; }
	
	@Column(name = "wineDeleted")
    private Boolean wineDeleted;
	public Boolean getWineDeleted() { return wineDeleted; }
	public void setWineDeleted(Boolean wineDeleted) { this.wineDeleted = wineDeleted; }
	
	
	public viewBestOffersbyMerchants(Integer id) { this.id = id; } 
	public viewBestOffersbyMerchants() 
	{
        this.id = null;
        this.merchantId = null;
    	this.wineId = null;
    	this.positionIndex = null;
    	this.merchantName = null;
    	this.merchantImage = null;
    	this.merchantHomePage = null;
        this.wineCountryId = null; 
        this.wineCountryName = null;
        this.wineRegionId = null;
        this.wineRegionName = null;
        this.wineWineryId = null;
        this.wineWineryName = null;
        this.wineAppellationId = null;
        this.wineAppellationName = null;
        this.wineColourId = null;
        this.wineColourName = null;
        this.wineVintage = null;
        this.wineName = null;
        this.wineShortDescription = null;
        this.wineDefaultDescription = null;
        this.wineBottleSize = null;
        this.wineAbv = null;
        this.wineImageURL = null;
        this.wineClosureId = null;
        this.wineClosureName = null;
        this.wineGtin = null;
        this.wineMinimumPrice = null;
        this.winePreviousMaxPrice = null;
        this.wineMoneySaving = null;
        this.winePercentageOff = null;
        this.wineMinimumPriceClicktag = null;
        this.wineDeleted = false;
    }
	
	@Override
	public String toString() {
		return "{ \"id\" : \"" + id + "\", merchantId\" : \"" + merchantId + "\", wineId\" : \"" + wineId
				+ "\", positionIndex\" : \"" + positionIndex + "\", merchantName\" : \"" + merchantName
				+ "\", merchantImage\" : \"" + merchantImage + "\", merchantHomePage\" : \"" + merchantHomePage
				+ "\", wineCountryId\" : \"" + wineCountryId + "\", wineCountryName\" : \"" + wineCountryName
				+ "\", wineRegionId\" : \"" + wineRegionId + "\", wineRegionName\" : \"" + wineRegionName
				+ "\", wineWineryId\" : \"" + wineWineryId + "\", wineWineryName\" : \"" + wineWineryName
				+ "\", wineAppellationId\" : \"" + wineAppellationId + "\", wineAppellationName\" : \""
				+ wineAppellationName + "\", wineColourId\" : \"" + wineColourId + "\", wineColourName\" : \""
				+ wineColourName + "\", wineVintage\" : \"" + wineVintage + "\", wineName\" : \"" + wineName
				+ "\", wineShortDescription\" : \"" + wineShortDescription + "\", wineDefaultDescription\" : \""
				+ wineDefaultDescription + "\", wineBottleSize\" : \"" + wineBottleSize + "\", wineAbv\" : \"" + wineAbv
				+ "\", wineImageURL\" : \"" + wineImageURL + "\", wineClosureId\" : \"" + wineClosureId
				+ "\", wineClosureName\" : \"" + wineClosureName + "\", wineGtin\" : \"" + wineGtin
				+ "\" , \"wineMinimumPrice\" : \"" + wineMinimumPrice
				+ "\" , \"winePreviousMaxPrice\" : \"" + winePreviousMaxPrice
				+ "\" , \"wineMoneySaving\" : \"" + wineMoneySaving
				+ "\" , \"winePercentageOff\" : \"" + winePercentageOff
				+ "\" , \"wineMinimumPriceClicktag\" : \"" + wineMinimumPriceClicktag
				+ "\" , \"wineDeleted\" : \"" + wineDeleted
				+ "\" }";
	}
}
