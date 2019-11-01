/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hello;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import com.harry.app.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void paramGreetingShouldReturnTailoredMessagee() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("<harryKart>");
		sb.append("<numberOfLoops>3</numberOfLoops>");
		sb.append("<startList>");
		sb.append("<participant>");
		sb.append("<lane>1</lane>");
		sb.append("<name>TIMETOBELUCKY</name>");
		sb.append("<baseSpeed>10</baseSpeed>");
		sb.append("</participant>");
		sb.append("<participant>");
		sb.append("<lane>2</lane>");
		sb.append("<name>CARGO DOOR</name>");
		sb.append("<baseSpeed>10</baseSpeed>");
		sb.append("</participant>");
		sb.append("<participant>");
		sb.append("<lane>3</lane>");
		sb.append("<name>aCARGO DOOR</name>");
		sb.append("<baseSpeed>10</baseSpeed>");
		sb.append("</participant>");
		sb.append("<participant>");
		sb.append("<lane>4</lane>");
		sb.append("<name>bCARGO DOOR</name>");
		sb.append("<baseSpeed>10</baseSpeed>");
		sb.append("</participant>");
		sb.append("</startList>");
		sb.append("<powerUps>");
		sb.append("<loop number=\"1\">");
		sb.append("<lane number=\"1\">1</lane>");
		sb.append("<lane number=\"2\">1</lane>");
		sb.append("<lane number=\"3\">1</lane>");
		sb.append("<lane number=\"4\">1</lane>");
		sb.append("</loop>");
		sb.append("<loop number=\"2\">");
		sb.append("<lane number=\"1\">1</lane>");
		sb.append("<lane number=\"2\">1</lane>");
		sb.append("<lane number=\"3\">1</lane>");
		sb.append("<lane number=\"4\">1</lane>");
		sb.append("</loop>");
		sb.append("<loop number=\"3\">");
		sb.append("<lane number=\"1\">1</lane>");
		sb.append("<lane number=\"2\">1</lane>");
		sb.append("<lane number=\"3\">1</lane>");
		sb.append("<lane number=\"4\">1</lane>");
		sb.append("</loop>");
		sb.append("</powerUps>");
		sb.append("</harryKart>");
		String xmlString = sb.toString();

		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("xmlStr", xmlString);

		this.mockMvc.perform(post("/api/play")
				.contentType(MediaType.APPLICATION_XML)
				.content(xmlString.getBytes()))
				.andExpect(status().isOk());
	}

}
