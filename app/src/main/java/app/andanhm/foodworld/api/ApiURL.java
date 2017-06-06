package app.andanhm.foodworld.api;

/**
 * <h1>ApiURL class store paths of the Server API</h1>
 */
public final class ApiURL {
    /**
     * Locations of the api
     */
    private static String BASE_URL="http://localhost:8080/";
    /**
     * Node where user email id need to be checked checked where user already
     * registered or not(ie "userLists")
     * The status code of the response obtained from the below api request.
     * <p>
     * <li>c101: User doesn't exist/li>
     * <li>c201: User exist </li>
     * <li>c100: Required information not available </li>
     * </p>
     */
    public static String CHECK_USER_URL=BASE_URL+"user";

    /**
     * Node where user data need to be stored (ie "userLists")
     * The status code of the response obtained from the below api request.
     * <p>
     * <li>c102: Unable to add the user information to the database</li>
     * <li>c202: Successfully stored the user information & response with a token</li>
     * <li>c100: Required information not available </li>
     * </p>
     */
    public static String ADD_USER_URL=BASE_URL+"users";
    /**
     * Node where user email id and password need to be checked (ie "userLists")
     * The status code of the response obtained from the below api request.
     * <p>
     * <li>c103: User doesn't exist/li>
     * <li>c203: User exist & response with a token </li>
     * <li>c100: Required information not available </li>
     * </p>
     */
    public static String LOGIN_USER_URL=BASE_URL+"login";
    /**
     * Node where user details need to be updated (ie "userLists")
     * The status code of the response obtained from the below api request.
     * <p>
     * <li>c104: User doesn't exist/Unable to update the user details/li>
     * <li>c204: User details updated</li>
     * <li>c100: Required information not available </li>
     * </p>
     */
    public static String UPDATE_USER_URL=BASE_URL+"api/users";
}
