/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.cxf.tools.wadlto.jaxrs;

import java.net.URISyntaxException;

import org.apache.cxf.tools.common.ProcessorTestBase;
import org.apache.cxf.tools.common.ToolContext;
import org.apache.cxf.tools.wadlto.WadlToolConstants;

import org.junit.Test;

public class ValidateWadlTest extends ProcessorTestBase {

    @Test    
    public void testInvalidWadl() throws Exception {
        try {
            JAXRSContainer container = new JAXRSContainer(null);

            ToolContext context = new ToolContext();
            context.put(WadlToolConstants.CFG_WADLURL, getLocation("/wadl/invalidWadl.xml"));
            context.put(WadlToolConstants.CFG_VALIDATE_WADL, "true");
            container.setContext(context);
            container.execute();
            fail("Validation exception expected");
        } catch (ValidationException e) {
            // expected
        }
    }
    
    @Test    
    public void testInvalidParameterStyle() throws Exception {
        try {
            JAXRSContainer container = new JAXRSContainer(null);

            ToolContext context = new ToolContext();
            context.put(WadlToolConstants.CFG_WADLURL, getLocation("/wadl/invalidParamStyle.xml"));
            container.setContext(context);
            container.execute();
            fail();            
        } catch (ValidationException e) {
            String message = e.getMessage();
            assertTrue(message.startsWith("Unsupported parameter style: plain"));
        }
    }
        
    protected String getLocation(String wsdlFile) throws URISyntaxException {
        return getClass().getResource(wsdlFile).toString();
    }
}
