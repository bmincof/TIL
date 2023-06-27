package chap07.user;

import chap07.autodebit.AutoDebitInfo;
import chap07.autodebit.AutoDebitInfoRepository;

public class JpaAutoDebitInfoRepository implements AutoDebitInfoRepository {
    @Override
    public void save(AutoDebitInfo info) {

    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return null;
    }
}