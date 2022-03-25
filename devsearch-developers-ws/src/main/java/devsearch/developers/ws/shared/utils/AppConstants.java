package devsearch.developers.ws.shared.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AppConstants {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm z";
    public static final DateFormat DATE_FORMATTER = new SimpleDateFormat(AppConstants.DATE_FORMAT);
    public static final int PUBLIC_ID_LENGTH = 30;
    public static final int PRIVATE_ID_LENGTH = 30;
    public static final String DEFAULT_ENCODING = "UTF-8";
}
