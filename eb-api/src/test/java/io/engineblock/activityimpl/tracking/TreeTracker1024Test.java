package io.engineblock.activityimpl.tracking;

import io.engineblock.activityimpl.marker.longheap.TreeTracker1024;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeTracker1024Test {

    @Test
    public void testFullCompletion() {
        TreeTracker1024 tt = new TreeTracker1024(0L);
        assertThat(tt.isCompleted()).isFalse().as("initial state");
        for(long i=0;i<1022;i++) {
            tt.setPosition(i);
            assertThat(tt.isCompleted()).isFalse().as("cycle: {}",i);
        }
        tt.setPosition(1023);
//        assertThat(tt.isCycleCompleted()).isTrue().as("cycle: 1023");
    }
}