/*
*   Copyright 2016 jshook
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/
package io.engineblock.runtime;

import java.util.Objects;

/**
 * <p>This class and documentation satisfies the artifact rule checks
 * that don't know about dependency-aggregating modules.
 * </p>
 * <p>
 * If you really want to know more about engine block, consult the docs folder in the src repo.
 * </p>
 */
public class RuntimeCheck {
    public static void sanityCheck() {
        try {
            Class<?> activityTypeClass = Class.forName("io.engineblock.activityapi.core.ActivityType");
            Objects.requireNonNull(activityTypeClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
