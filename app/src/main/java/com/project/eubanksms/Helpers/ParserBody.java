package com.project.eubanksms.Helpers;

import com.project.eubanksms.R;

public class ParserBody {
    public static final String ADDRESS_SMS = "eubank";

    private static final String ATM_MASHINE_NUMBER_START = "000";
    private static final int ATM_MASHINE_NUMBER_LENGTH = 8;

    private static final String ATM_UNKNOWN_STATUS = "Неизвестное состояние";
    private static final String ATM_CONNECTION_ESTABLISHED = "ATM Connection established";
    private static final String ATM_CONNECTION_DROPPED = "ATM Connection dropped";
    private static final String ATM_HASH_HANDLER = "Hash Handler Some notes have been retracted";
    private static final String ATM_CARD_READER_ERROR = "Card Reader Error";
    private static final String ATM_CURRENCY_JAM = "Currency jam in presenter transport";
    private static final String ATM_RECIEPT_PRINTER_FATAL_ERROR = "Reciept Printer Fatal Error";

    private static final String ATM_BANCH_NOTE_ACCEPTOR_FATAL_ERROR = "Bunch Note Acceptor - Fatal Error";

    public static String getTextAtm(String textBody){
        String text;
        text = textBody.substring(0,ATM_MASHINE_NUMBER_START.length());
        if (text.compareToIgnoreCase(ATM_MASHINE_NUMBER_START) == 0) {
            return textBody.substring(ATM_MASHINE_NUMBER_START.length(),ATM_MASHINE_NUMBER_LENGTH);
        } else {
            return null;
        }
    }

    public static boolean getFindAtm(String textAtmNumber, String textBody) {
        //String text;
        //text = textBody.substring(ATM_MASHINE_NUMBER_START.length(),ATM_MASHINE_NUMBER_LENGTH);
        if (textBody.indexOf(textAtmNumber) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int getImageStatus(String textBody){
        if (textBody.indexOf(ATM_CONNECTION_ESTABLISHED) >0) {
            return R.drawable.ic_up;
        }
        if (textBody.indexOf(ATM_CONNECTION_DROPPED) >0) {
            return R.drawable.ic_down;
        }
        if (textBody.indexOf(ATM_HASH_HANDLER) >0) {
            return R.drawable.ic_cached;
        }
        if (textBody.indexOf(ATM_CARD_READER_ERROR) >0) {
            return R.drawable.ic_error;
        }
        if (textBody.indexOf(ATM_CURRENCY_JAM) >0) {
            return R.drawable.ic_error;
        }
        if (textBody.indexOf(ATM_RECIEPT_PRINTER_FATAL_ERROR) >0) {
            return R.drawable.ic_print;
        }
        if (textBody.indexOf(ATM_BANCH_NOTE_ACCEPTOR_FATAL_ERROR) >0) {
            return R.drawable.ic_error;
        } else
            return R.drawable.ic_unknown;

    }

    public static String getTextStatus(String textBody){
        if (textBody.indexOf(ATM_CONNECTION_ESTABLISHED) >0) {
            return ATM_CONNECTION_ESTABLISHED;
        }
        if (textBody.indexOf(ATM_CONNECTION_DROPPED) >0) {
            return ATM_CONNECTION_DROPPED;
        }
        if (textBody.indexOf(ATM_HASH_HANDLER) >0) {
            return ATM_HASH_HANDLER;
        }
        if (textBody.indexOf(ATM_CARD_READER_ERROR) >0) {
            return ATM_CARD_READER_ERROR;
        }
        if (textBody.indexOf(ATM_CURRENCY_JAM) >0) {
            return ATM_CURRENCY_JAM;
        }
        if (textBody.indexOf(ATM_RECIEPT_PRINTER_FATAL_ERROR) >0) {
            return ATM_RECIEPT_PRINTER_FATAL_ERROR;
        }
        if (textBody.indexOf(ATM_BANCH_NOTE_ACCEPTOR_FATAL_ERROR) >0) {
            return ATM_BANCH_NOTE_ACCEPTOR_FATAL_ERROR;
        } else
            return ATM_UNKNOWN_STATUS;

    }
}
