/*  
 *  Imixs-Workflow 
 *  
 *  Copyright (C) 2001-2020 Imixs Software Solutions GmbH,  
 *  http://www.imixs.com
 *  
 *  This program is free software; you can redistribute it and/or 
 *  modify it under the terms of the GNU General Public License 
 *  as published by the Free Software Foundation; either version 2 
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 *  General Public License for more details.
 *  
 *  You can receive a copy of the GNU General Public
 *  License at http://www.gnu.org/licenses/gpl.html
 *  
 *  Project: 
 *      https://www.imixs.org
 *      https://github.com/imixs/imixs-workflow
 *  
 *  Contributors:  
 *      Imixs Software Solutions GmbH - Project Management
 *      Ralph Soika - Software Developer
 */

package org.imixs.workflow.engine.plugins;

import java.util.List;
import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.exceptions.PluginException;

/**
 * This Pluginmodul cuts the length of the technical log entries generated by
 * the WorkflowKernel:
 * 
 * txtWorkflowPluginLog
 * 
 * txtWorkflowActivityLog
 * 
 * The Attribute numWorkflowLogLength indicates the maximum number of entries.
 * if <= 0 no limit is set.
 * 
 * 
 * @author Ralph Soika
 * @version 1.2
 * @see org.imixs.workflow.WorkflowManager
 * 
 */

public class LogPlugin extends AbstractPlugin {

    /**
     * the log entries generated form the kernel will be cut if the attribute
     * numWorkflowLogLength was provided
     */
    public ItemCollection run(ItemCollection documentContext, ItemCollection adocumentActivity) throws PluginException {
        List<?> vActivityLog;

        vActivityLog = documentContext.getItemValue("txtWorkflowActivityLog");

        // check if maximum length is defined
        int iMaxLogLength = documentContext.getItemValueInteger("numWorkflowLogLength");
        if (iMaxLogLength > 0) {

            while (vActivityLog.size() >= iMaxLogLength) {
                vActivityLog.remove(0);
            }

            // update Log entries now...
            documentContext.replaceItemValue("txtWorkflowActivityLog", vActivityLog);
        }

        return documentContext;
    }

}
