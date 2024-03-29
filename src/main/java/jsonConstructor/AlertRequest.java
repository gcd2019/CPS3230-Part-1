package jsonConstructor;

public class AlertRequest {
    private int alertType;
    private String heading;
    private String description;
    private String url;
    private String imageUrl;
    private String postedBy;
    private int priceInCents;

    public int getAlertType(){
        return alertType;
    }

    public void setAlertType(int alertType){
        this.alertType = alertType;
    }

    public String getHeading(){
        return heading;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setPostedBy(String postedBy){
        this.postedBy = postedBy;
    }

    public String getPostedBy(){
        return postedBy;
    }

    public void setHeading(String heading){
        this.heading = heading;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public int getPriceInCents(){
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents){
        this.priceInCents = priceInCents;
    }
}
