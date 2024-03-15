package seatech.ibv.data;

import com.univocity.parsers.annotations.Parsed;

public class CreateTransaction247Data extends LoginData{
	@Parsed(field = "account number")
    private String accountNumber;
    @Parsed(field = "receive bank")
    private String receiveBank;
    @Parsed(field = "receive account", defaultNullRead = "")
    private String receiveAccount;
    @Parsed(field = "amount", defaultNullRead = "")
    private String amount;
    @Parsed(field = "content", defaultNullRead = "")
    private String content;
    @Parsed(field = "fee transaction", defaultNullRead = "")
    private String feeTransaction;

    @Parsed(field = "type authen", defaultNullRead = "")
    private String typerAuthen;
    @Parsed(field = "authen device", defaultNullRead = "")
    private String authenDevice;
    @Parsed(field = "otp", defaultNullRead = "")
    private String otp;
    public String getOtp() {
        return otp;
    }
	public String getAccountNumber() {
		return accountNumber;
	}

	public String getReceiveBank() {
		return receiveBank;
	}

	public String getReceiveAccount() {
		return receiveAccount;
	}

	public String getAmount() {
		return amount;
	}

	public String getContent() {
		return content;
	}

	public String getFeeTransaction() {
		return feeTransaction;
	}

	public String getTyperAuthen() {
		return typerAuthen;
	}

	public String getAuthenDevice() {
		return authenDevice;
	}

}
