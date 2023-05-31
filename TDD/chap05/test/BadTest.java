package chap05;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * 테스트 메서드가 특정 순서대로 실행된다는 가정하에 테스트 메서드를 작성하면 안 된다 (버전에 따라 달라질 수 있다)
 * 각 테스트 메서드는 서로 독립적으로 동작해야 한다.
 * 한 테스트 메서드의 결과에 따라 다른 테스트 메서드의 실행 결과가 달라지면 안 된다
 */
@Disabled
public class BadTest {
    private FileOperator op =new FileOperator();
    // 두 테스트에서 데이터를 공유할 목적으로 생성한 필드
    private static File file;

    @Test
    void fileCreationTest() {
        File createdFile = op.createdFile();
        assertTrue(createdFile.length() > 0);
        this.file = createdFile;
    }

    @Test
    void readFileTest() {
        long data = op.readData(file);
        assertTrue(data > 0);
    }
}
