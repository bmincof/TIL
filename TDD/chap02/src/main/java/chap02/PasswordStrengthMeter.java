package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        // 빈 문자열일 경우 INVALID
        if(s == null || s.isEmpty()) return PasswordStrength.INVALID;

        // 충족한 조건의 개수를 세기
        int metCounts = getMetCriteriaCounts(s);

        ////////////////////////////////////////////////////////////////////

        // 규칙을 검사한 결과에 따라 암호 강도를 계산하는 로직
        // 1개의 조건만 충족했을 경우 WEAK
        if(metCounts <= 1) return PasswordStrength.WEAK;
        // 2개의 조건만 충족했을 경우 NORMAL
        if(metCounts == 2) return PasswordStrength.NORMAL;
        // 모두 만족하면 STRONG
        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(String s) {
        // 충족하는 조건의 개수를 저장하는 변수
        int metCounts = 0;

        // 개별 규칙을 검사하는 로직
        // 길이가 8 이상
        if(s.length() >= 8) metCounts++;
        // 숫자가 포함됨
        if(meetsContainingNumberCriteria(s)) metCounts++;
        // 대문자가 포함됨
        if(meetsContainingUpperCaseCriteria(s)) metCounts++;

        return metCounts;
    }

    private boolean meetsContainingNumberCriteria(String s) {
        for(char ch : s.toCharArray()) {
            if(ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUpperCaseCriteria(String s) {
        for(char ch : s.toCharArray()) {
            if(Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}
