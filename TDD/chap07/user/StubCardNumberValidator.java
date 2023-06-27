package chap07.user;

import chap07.autodebit.CardNumberValidator;
import chap07.autodebit.CardValidity;

// CardNumberValidator를 대신할 대역 클래스
public class StubCardNumberValidator extends CardNumberValidator {
    private String invalidNo;
    private String theftNo;

    public void setInvalidNo(String invalidNo) {
        this.invalidNo = invalidNo;
    }

    public void setTheftNo(String theftNo) {
        this.theftNo = theftNo;
    }

    // 실제 카드번호 검증 기능을 구현하지는 않고 단순한 구현으로 실제 구현을 대체한다.
    @Override
    public CardValidity validate(String cardNumber) {
        if(invalidNo != null && invalidNo.equals(cardNumber)) {
            return CardValidity.INVALID;
        }
        if(theftNo != null && theftNo.equals(cardNumber)) {
            return CardValidity.THEFT;
        }
        return CardValidity.VALID;
    }
}
