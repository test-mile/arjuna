/*******************************************************************************
 * Copyright 2015-16 AutoCognite Testing Research Pvt Ltd
 * 
 * Website: www.AutoCognite.com
 * Email: support [at] autocognite.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package pvt.unitee.reporter.lib.writer.json;

import org.apache.log4j.Logger;

import pvt.arjunapro.enums.ArjunaProperty;
import pvt.batteries.config.Batteries;
import pvt.unitee.reporter.lib.issue.Issue;

public class JsonIssueWriter extends JsonResultWriter<Issue> {
	private Logger logger = Logger.getLogger(Batteries.getCentralLogName());
	private JsonConsolidatedIssueWriter childWriter;
	
	public JsonIssueWriter() throws Exception{
		super(Batteries.value(ArjunaProperty.DIRECTORY_RUNID_REPORT_JSON_RAW_ISSUES).asString());
		childWriter = new JsonConsolidatedIssueWriter();
	}
	
	public void update(Issue reportable) throws Exception {
		String jsonString = reportable.asJsonObject().toString();
		super.update(this.createFileID(reportable.objectProps().objectId()), jsonString);
		childWriter.update(jsonString);
	}
	
	public void setUp() throws Exception{
		super.setUp();
		childWriter.setUp();
	}

	public void tearDown() throws Exception{
		super.tearDown();
		childWriter.tearDown();
	}

}