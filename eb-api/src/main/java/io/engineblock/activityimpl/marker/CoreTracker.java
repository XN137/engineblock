/*
 *
 *    Copyright 2016 jshook
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package io.engineblock.activityimpl.marker;

import io.engineblock.activityapi.cycletracking.buffers.CycleSegment;
import io.engineblock.activityapi.cycletracking.trackers.Tracker;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CoreTracker implements Tracker {

    private LinkedList<ByteTrackerExtent> extents = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock(false);
    private Condition extentsAvailable = lock.newCondition();

    @Override
    public CycleSegment getSegment(int stride) {

        while (true) {

            ByteTrackerExtent extent = extents.peekFirst();
            while (extent == null) {
                try {
                    extentsAvailable.await(10, TimeUnit.SECONDS);
                } catch (InterruptedException ignored) {
                }
                extent = extents.peekFirst();
            }

            CycleSegment segment = extent.getSegment(stride);

            if (extent.isFullyServed()) {
                extents.removeFirst();
            }

            if (segment!=null) {
                return segment;
            } else {
                throw new RuntimeException("Null segment when extent is exhausted.");
            }
        }
    }

    public void onExtent(ByteTrackerExtent extent) {
        extents.addLast(extent);
    }

    public void onExtentsFinished() {
    }

}