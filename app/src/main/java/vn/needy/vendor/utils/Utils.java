package vn.needy.vendor.utils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

/**
 * Created by lion on 08/10/2017.
 */

public class Utils {

    public static class PhoneNumberUtils {
        public static boolean isValidPhoneNumber(String phoneNumber) {
            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            try {
                Phonenumber.PhoneNumber pNumber = phoneNumberUtil.parse(phoneNumber, "VN");
                PhoneNumberUtil.PhoneNumberType phoneNumberType = phoneNumberUtil.getNumberType(pNumber);
                return phoneNumberType == PhoneNumberUtil.PhoneNumberType.MOBILE;
            } catch (final Exception e) {
            }
            return false;
        }

        public static String formatPhoneNumber(String phoneNumber) {
            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            try {
                Phonenumber.PhoneNumber swissNumberProto = phoneNumberUtil.parse(phoneNumber, "VN");
                phoneNumber = phoneNumberUtil.format(swissNumberProto, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
                return phoneNumber.replaceAll("\\s+", "");
            } catch (NumberParseException e) {
            }
            return phoneNumber;
        }
    }

    public static class DateTimeUtils {

    }
}
