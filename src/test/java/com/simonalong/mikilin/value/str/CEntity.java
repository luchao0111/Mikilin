package com.simonalong.mikilin.value.str;

import com.simonalong.mikilin.annotation.Check;
import com.simonalong.mikilin.annotation.Matcher;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhouzhenyong
 * @since 2019/1/5 下午7:05
 */
@Data
@Accessors(chain = true)
public class
CEntity {

    @Matcher({"a", "b"})
    private String name;
    @Check
    private List<BEntity> bEntities;
}
