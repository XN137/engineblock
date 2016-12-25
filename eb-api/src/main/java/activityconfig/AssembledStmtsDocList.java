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

package activityconfig;

import activityconfig.yaml.StmtsDocList;
import io.engineblock.util.TagFilter;

import java.util.List;
import java.util.stream.Collectors;

public class AssembledStmtsDocList {

    private StmtsDocList rawStmtsDocList;

    public AssembledStmtsDocList(StmtsDocList rawStmtsDocList) {
        this.rawStmtsDocList = rawStmtsDocList;
    }

    public List<AssembledStmtsDoc> getAssembledStmtsDocs(String tagFilter) {
        TagFilter tf = new TagFilter(tagFilter);
        return getAssembledStmtsDocs().stream()
                .filter(tf::matchesTagged)
                .collect(Collectors.toList());
    }

    public List<AssembledStmtsDoc> getAssembledStmtsDocs() {
        return rawStmtsDocList.getStmtsDocs().stream()
                .map(AssembledStmtsDoc::new)
                .collect(Collectors.toList());
    }
}
