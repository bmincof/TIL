package chap07.user;

import chap07.autodebit.AutoDebitInfo;
import chap07.autodebit.AutoDebitInfoRepository;

import java.util.HashMap;
import java.util.Map;

// DB를 대신할 대역 객체
// 맵을 이용하므로 영속성을 제공하지는 않지만 테스트에는 사용할 수 있을 만큼의 기능은 제공한다.
public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository {
    private Map<String, AutoDebitInfo> infos = new HashMap<>();

    @Override
    public void save(AutoDebitInfo info) {
        infos.put(info.getUserId(), info);
    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return infos.get(userId);
    }
}
