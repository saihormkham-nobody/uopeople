
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stock Price Analysis Program This program implements various methods to
 * analyze stock prices stored in both arrays and ArrayLists.
 */
public class main {

    public static void main(String[] args) {
        // Sample data: Daily stock prices for a period
        int[] stockPricesArray = {145, 167, 156, 145, 172, 168, 145};

        // Creating ArrayList with the same data
        ArrayList<Integer> stockPricesList = new ArrayList<>();
        for (int price : stockPricesArray) {
            stockPricesList.add(price);
        }

        // Testing average price calculation
        System.out.println("Average Stock Price (Array): $" + calculateAveragePrice(stockPricesArray));
        System.out.println("Average Stock Price (ArrayList): $" + calculateAveragePrice(stockPricesList));

        // Testing maximum price finding
        System.out.println("Maximum Stock Price (Array): $" + findMaximumPrice(stockPricesArray));
        System.out.println("Maximum Stock Price (ArrayList): $" + findMaximumPrice(stockPricesList));

        // Testing occurrence count
        int targetPrice = 145;
        System.out.println("Occurrences of $" + targetPrice + " (Array): "
                + countOccurrences(stockPricesArray, targetPrice));
        System.out.println("Occurrences of $" + targetPrice + " (ArrayList): "
                + countOccurrences(stockPricesList, targetPrice));

        // Testing occurrence count with a non-existent price
        targetPrice = 200;
        System.out.println("Occurrences of $" + targetPrice + " (Array): "
                + countOccurrences(stockPricesArray, targetPrice));
        System.out.println("Occurrences of $" + targetPrice + " (ArrayList): "
                + countOccurrences(stockPricesList, targetPrice));

        // Testing cumulative sum for Array
        int[] cumulativeSumArray = computeCumulativeSum(stockPricesArray);
        System.out.println("Cumulative Sum of Stock Prices (Array): " + Arrays.toString(cumulativeSumArray));
        // Testing cumulative sum for ArrayList
        ArrayList<Integer> cumulativeSumArrayList = computeCumulativeSum(stockPricesList);
        System.out.println("Cumulative Sum of Stock Prices (ArrayList): " + cumulativeSumArrayList);

    }

    /**
     * Calculates the average price from an array of stock prices
     *
     * @param prices Array of stock prices
     * @return The average stock price
     */
    public static double calculateAveragePrice(int[] prices) {
        if (prices == null || prices.length == 0) {
            throw new IllegalArgumentException("Stock price array cannot be null or empty");
        }

        double sum = 0;
        for (int price : prices) {
            sum += price;
        }

        return sum / prices.length;
    }

    /**
     * Calculates the average price from an ArrayList of stock prices
     *
     * @param prices ArrayList of stock prices
     * @return The average stock price
     */
    public static double calculateAveragePrice(ArrayList<Integer> prices) {
        if (prices == null || prices.isEmpty()) {
            throw new IllegalArgumentException("Stock price list cannot be null or empty");
        }

        double sum = 0;
        for (int price : prices) {
            sum += price;
        }

        return sum / prices.size();
    }

    /**
     * Finds the maximum price from an array of stock prices
     *
     * @param prices Array of stock prices
     * @return The maximum stock price
     */
    public static int findMaximumPrice(int[] prices) {
        if (prices == null || prices.length == 0) {
            throw new IllegalArgumentException("Stock price array cannot be null or empty");
        }

        int max = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > max) {
                max = prices[i];
            }
        }

        return max;
    }

    /**
     * Finds the maximum price from an ArrayList of stock prices
     *
     * @param prices ArrayList of stock prices
     * @return The maximum stock price
     */
    public static int findMaximumPrice(ArrayList<Integer> prices) {
        if (prices == null || prices.isEmpty()) {
            throw new IllegalArgumentException("Stock price list cannot be null or empty");
        }

        int max = prices.get(0);
        for (int i = 1; i < prices.size(); i++) {
            if (prices.get(i) > max) {
                max = prices.get(i);
            }
        }

        return max;
    }

    /**
     * Counts occurrences of a target price in an array of stock prices
     *
     * @param prices Array of stock prices
     * @param targetPrice The price to count occurrences of
     * @return Number of occurrences of the target price
     */
    public static int countOccurrences(int[] prices, int targetPrice) {
        if (prices == null) {
            throw new IllegalArgumentException("Stock price array cannot be null");
        }

        int count = 0;
        for (int price : prices) {
            if (price == targetPrice) {
                count++;
            }
        }

        return count;
    }

    /**
     * Counts occurrences of a target price in an ArrayList of stock prices
     *
     * @param prices ArrayList of stock prices
     * @param targetPrice The price to count occurrences of
     * @return Number of occurrences of the target price
     */
    public static int countOccurrences(ArrayList<Integer> prices, int targetPrice) {
        if (prices == null) {
            throw new IllegalArgumentException("Stock price list cannot be null");
        }

        int count = 0;
        for (int price : prices) {
            if (price == targetPrice) {
                count++;
            }
        }

        return count;
    }

    /**
     * Computes the cumulative sum of stock prices in an ArrayList
     *
     * @param prices ArrayList of stock prices
     * @return ArrayList containing cumulative sum at each position
     */
    public static ArrayList<Integer> computeCumulativeSum(ArrayList<Integer> prices) {
        if (prices == null || prices.isEmpty()) {
            throw new IllegalArgumentException("Stock price list cannot be null or empty");
        }

        ArrayList<Integer> cumulativeSum = new ArrayList<>();
        int sum = 0;

        for (int price : prices) {
            sum += price;
            cumulativeSum.add(sum);
        }

        return cumulativeSum;
    }

    /**
     * Computes the cumulative sum of stock prices in an array
     *
     * @param prices Array of stock prices
     * @return Array containing cumulative sum at each position
     */
    public static int[] computeCumulativeSum(int[] prices) {
        if (prices == null || prices.length == 0) {
            throw new IllegalArgumentException("Stock price array cannot be null or empty");
        }

        int[] cumulativeSum = new int[prices.length];
        cumulativeSum[0] = prices[0];

        for (int i = 1; i < prices.length; i++) {
            cumulativeSum[i] = cumulativeSum[i - 1] + prices[i];
        }

        return cumulativeSum;
    }
}
