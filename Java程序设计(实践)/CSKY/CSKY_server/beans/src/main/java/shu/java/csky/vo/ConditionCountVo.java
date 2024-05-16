package shu.java.csky.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;

/**
 * @author: Silence
 * @date: 2022/2/27 13:25
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionCountVo {
    private Long likeCount;
    private Long favoriteCount;
    private Long repostCount;
}
