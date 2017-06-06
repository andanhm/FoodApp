package app.andanhm.foodworld.analytics;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.crashlytics.android.answers.AddToCartEvent;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.crashlytics.android.answers.PurchaseEvent;
import com.crashlytics.android.answers.RatingEvent;
import com.crashlytics.android.answers.SearchEvent;
import com.crashlytics.android.answers.ShareEvent;
import com.crashlytics.android.answers.StartCheckoutEvent;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Tacking how often user purchasing item
 */
public class TrackingPurchase {
    private static final String TAG = TrackingApp.class.getSimpleName();
    // Currency code
    private static String CURRENCY_TYPE = "INR";

    /**
     * Track purchase event to see revenue in real-time,
     * understand how many users are making purchases,
     * see which items are most popular, and track plenty of other important purchase-related metrics
     *
     * @param itemPrice      The item’s amount in the currency specified
     * @param itemName       The human-readable name for the item
     * @param itemType       The category the item falls under
     * @param itemId         A unique identifier used to track the item
     * @param purchaseStatus Was the purchase completed succesfully?
     */
    private static void logPurchase(@NonNull double itemPrice, @NonNull String itemName, @NonNull String itemType, @NonNull String itemId, @NonNull boolean purchaseStatus) {
        Answers.getInstance().logPurchase(new PurchaseEvent()
                .putItemPrice(BigDecimal.valueOf(itemPrice))
                .putCurrency(Currency.getInstance(CURRENCY_TYPE))
                .putItemName(itemName)
                .putItemType(itemType)
                .putItemId(itemId)
                .putSuccess(purchaseStatus));
    }

    /**
     * Track users adding items to a shopping cart in real-time,
     * understand how many users start the purchase flow,
     * see which items are most popular,
     * and track plenty of other important purchase-related metrics
     *
     * @param itemPrice The item’s amount in the currency specified
     * @param itemName  The human-readable name for the item
     * @param itemType  The category the item falls under
     * @param itemId    A unique identifier used to track the item
     */
    private static void logAddToCart(@NonNull double itemPrice, @NonNull String itemName, @NonNull String itemType, @NonNull String itemId) {
        Answers.getInstance().logAddToCart(new AddToCartEvent()
                .putItemPrice(BigDecimal.valueOf(itemPrice))
                .putCurrency(Currency.getInstance(CURRENCY_TYPE))
                .putItemName(itemName)
                .putItemType(itemType)
                .putItemId(itemId));
    }

    /**
     * Track users moving through the purchase funnel in real-time,
     * understand how many users are doing this and how much they’re spending per checkout,
     * and see how it related to other important purchase-related metrics
     * @param totalPrice The total price of all items in cart in the currency specified
     * @param itemCount  The count of items in cart
     **/
    private static void logCheckout(@NonNull double totalPrice, @NonNull int itemCount) {
        Answers.getInstance().logStartCheckout(new StartCheckoutEvent()
                .putTotalPrice(BigDecimal.valueOf(totalPrice))
                .putCurrency(Currency.getInstance(CURRENCY_TYPE))
                .putItemCount(itemCount));
    }

    /**
     * Track user viewing content within your app in real-time and
     * understand what content is most engaging, from the type or genre down to the specific item.
     *
     * @param contentName The Human-readable name of product name
     * @param contentType The category your item falls under
     * @param contentId   A unique identifier used to track the item
     **/
    private static void logContentView(@NonNull String contentName, @NonNull String contentType, @NonNull String contentId) {
        Answers.getInstance().logContentView(new ContentViewEvent()
                .putContentName(contentName)
                .putContentType(contentType)
                .putContentId(contentId));
    }

    /**
     * Track what users searching within your app in real-time and understand exactly what they’re searching for
     *
     * @param searchedItem What the user is searching for
     **/
    private static void logContentView(@NonNull String searchedItem) {
        Answers.getInstance().logSearch(new SearchEvent()
                .putQuery(searchedItem));
    }

    /**
     * Attributes to get a better understanding of your users’ activity related to sharing
     *
     * @param method      The method used to share content (Like Twitter,Facebook,Google+...)
     * @param contentName The description of the content
     * @param contentType The type or genre of content (Like tweet,feed...)
     * @param contentId   A unique key identifying the content
     **/
    private static void logShare(@NonNull String method, @NonNull String contentName, @NonNull String contentType, @NonNull String contentId) {
        Answers.getInstance().logShare(new ShareEvent()
                .putMethod(method)
                .putContentName(contentName)
                .putContentType(contentType)
                .putContentId(contentId));
    }
    /**
     * Attributes to get a better understanding of your users’ activity related to rating content within your app.
     * @param rating      An integer rating of the content
     * @param contentName The description of the content
     * @param contentType The category your item falls under
     * @param contentId   A unique key identifying the content
     **/
    private static void logRating(@IntRange(from=0,to=5) int rating, @NonNull String contentName, @NonNull String contentType, @NonNull String contentId) {
        Answers.getInstance().logRating(new RatingEvent()
                .putRating(rating)
                .putContentName(contentName)
                .putContentType(contentType)
                .putContentId(contentId));
    }

}
