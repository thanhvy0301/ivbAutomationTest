package seatech.ibv.data;

import com.univocity.parsers.annotations.Parsed;

public class CreateTransactionInternalData extends LoginData{
    @Parsed(field = "type", defaultNullRead = "")
    private String type;
    @Parsed(field = "receive account", defaultNullRead = "")
    private String receiveAccount;
    @Parsed(field = "amount", defaultNullRead = "")
    private String amount;
    @Parsed(field = "content", defaultNullRead = "")
    private String content;
    @Parsed(field = "account number", defaultNullRead = "")
    private String accountNumber;
    @Parsed(field = "fee transaction", defaultNullRead = "")
    private String feeTransaction;

    @Parsed(field = "type authen", defaultNullRead = "")
    private String typerAuthen;
    @Parsed(field = "authen device", defaultNullRead = "")
    private String authenDevice;

    public String getOtp() {
        return otp;
    }

    @Parsed(field = "otp", defaultNullRead = "")
    private String otp;
    public String getType() {
        return type;
    }

    public String getFeeTransaction() {
        return feeTransaction;
    }

    public String getReceiveAccount() {
        return receiveAccount;
    }

    public String getAmount() {
        return amount;
    }

    public String getTyperAuthen() {
        return typerAuthen;
    }

    public String getAuthenDevice() {
        return authenDevice;
    }

    public String getContent() {
        return content;
    }

    public String getAccountNumber() {
        return accountNumber;
    }




}
