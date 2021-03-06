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

package io.engineblock.planning;

import java.util.List;

/**
 * An OpSequence provides fast access to a set of operations in a specific
 * order.
 * @param <T> The type of element which is to be sequenced
 */
public interface OpSequence<T> {

    /**
     * Get the next operation for the given long value. This is simply
     * the offset indicated by the offset sequence array at a modulo
     * position.
     *
     * @param selector the long value that determines the next op
     * @return An op of type T
     */
    T get(long selector);

    /**
     * Get the list of individual operations which could be returned by {@link #get(long)}.
     * @return A {@link List} of T
     */
    List<T> getOps();

    /**
     * Get the integer sequence that is used to index into the operations.
     * @return an offset pointer array in int[] form
     */
    int[] getSequence();

}
