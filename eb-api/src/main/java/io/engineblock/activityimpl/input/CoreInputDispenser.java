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

package io.engineblock.activityimpl.input;

import io.engineblock.activityapi.core.ActivitiesAware;
import io.engineblock.activityapi.core.Activity;
import io.engineblock.activityapi.input.Input;
import io.engineblock.activityapi.input.InputDispenser;
import io.engineblock.activityapi.input.InputType;
import io.engineblock.util.SimpleConfig;

import java.util.Map;

public class CoreInputDispenser implements InputDispenser, ActivitiesAware {

    private Activity activity;
    private Map<String, Activity> activities;
    private Input input;

    public CoreInputDispenser(Activity activity) {
        this.activity = activity;
    }

    @Override
    public Input getInput(long slot) {
        if (this.input == null) {
            this.input = createInput(slot);
        }
        return input;
    }

    private synchronized Input createInput(long slot) {
        SimpleConfig conf = new SimpleConfig(activity, "input");
        String inputType = conf.getString("type").orElse("targetrate");
        InputType inputTypeImpl = InputType.FINDER.getOrThrow(inputType);
        InputDispenser inputDispenser = inputTypeImpl.getInputDispenser(activity);
        if (inputDispenser instanceof ActivitiesAware) {
            ((ActivitiesAware)inputDispenser).setActivitiesMap(activities);
        }
        Input input = inputDispenser.getInput(slot);
        return input;
    }

    @Override
    public void setActivitiesMap(Map<String, Activity> activities) {
        this.activities = activities;
    }

}
