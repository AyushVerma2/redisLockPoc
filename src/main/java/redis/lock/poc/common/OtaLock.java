package redis.lock.poc.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by aykumar on 3/5/2018.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtaLock implements Serializable {
    private long current;
    private boolean isFinished;



}
