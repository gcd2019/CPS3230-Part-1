package utils;

public interface WebsiteElementsProvider {

    String result1 = "Search results for batman";
    String result2 = "Advanced Search";

    String[] titles = {"batman 1", "batman 2", "batman 3", "batman 4", "batman 5"};

    String[] descriptions = {"descriptions1", "descriptions2", "descriptions3", "descriptions4", "descriptions5"};

    String getResult(String search);

    String[] getTitles(String search);

    String[] getDescriptions(String search);

    String[] getUrls(String search);

    String[] getImageUrls(String search);

    int[] getPrices(String search);

}
