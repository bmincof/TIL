package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = getAddedMonths(payData.getPayAmount());

        // NPE를 방지하기 위한 조건문
        if(payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        // 첫 납부일과 납부일의 일자가 다르면 첫 납부일의 일자를 만료일의 일자로 사용하도록 처리
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

        if(!isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
            // 첫 납부일의 날짜
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            // 후보 만료일이 속한 월의 마지막 일자
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);

            // 후보 만료일이 포함된 달의 마지막날 < 첫 납부일의 일자이면
            if(dayLenOfCandiMon < dayOfFirstBilling) {
                // 후보 만료일을 그 달의 마지막 날로 조정
                // 아니면 DateTimeException : Invalid date 발생
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(LocalDate date1, LocalDate date2) {
        return date1.getDayOfMonth() == date2.getDayOfMonth();
    }

    private int lastDayOfMonth(LocalDate date) {
        return YearMonth.from(date).lengthOfMonth();
    }

    private int getAddedMonths(int payAmount) {
        int addedMonths = 0;

        if(payAmount >= 100_000) {
            addedMonths = payAmount / 100_000 * 12;
            payAmount %= 100_000;
        }

        addedMonths += payAmount / 10_000;

        return addedMonths;
    }
}
