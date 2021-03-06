/*
 * Copyright 2015. All rights reserved.
 */

package org.gamemonkey;

import java.io.IOException;
import java.util.List;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.gamemonkey.parser.GMParserFactory;

@Messages({
    "LBL_gm_LOADER=Files of gm"
})
@MIMEResolver.ExtensionRegistration(
        displayName = "#LBL_gm_LOADER",
        mimeType = "text/x-gamemonkey",
        extension = {"gm", "GM"}
)
@DataObject.Registration(
        mimeType = "text/x-gamemonkey",
        iconBase = "org/gamemonkey/monkey_icon16x16.png",
        displayName = "#LBL_gm_LOADER",
        position = 300
)
@ActionReferences({
    @ActionReference(
            path = "Loaders/text/x-gamemonkey/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
            position = 100,
            separatorAfter = 200
    ),
    @ActionReference(
            path = "Loaders/text/x-gamemonkey/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
            position = 300
    ),
    @ActionReference(
            path = "Loaders/text/x-gamemonkey/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
            position = 400,
            separatorAfter = 500
    ),
    @ActionReference(
            path = "Loaders/text/x-gamemonkey/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
            position = 600
    ),
    @ActionReference(
            path = "Loaders/text/x-gamemonkey/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
            position = 700,
            separatorAfter = 800
    ),
    @ActionReference(
            path = "Loaders/text/x-gamemonkey/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
            position = 900,
            separatorAfter = 1000
    ),
    @ActionReference(
            path = "Loaders/text/x-gamemonkey/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
            position = 1100,
            separatorAfter = 1200
    ),
    @ActionReference(
            path = "Loaders/text/x-gamemonkey/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
            position = 1300
    ),
    @ActionReference(
            path = "Loaders/text/x-gamemonkey/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
            position = 1400
    )
})
public class gmDataObject extends MultiDataObject {

    public gmDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("text/x-gamemonkey", false);
    }

    @Override
    protected int associateLookup() {
        return 1;
    }
    
    @Override 
    protected Node createNodeDelegate() { 
        return new DataNode(
                this, 
                Children.create(new GmChildFactory(this), true), 
                getLookup()); 
    }
    
    
    
    private static class GmChildFactory extends ChildFactory<String> 
    { 
        private final gmDataObject dObj; 
        public GmChildFactory(gmDataObject dObj) 
        { this.dObj = dObj; } 
        
        @Override 
        protected boolean createKeys(List list) 
        { 
            FileObject fObj = dObj.getPrimaryFile(); 
            try 
            { 
                List<String> dObjContent = fObj.asLines(); 
                list.addAll(dObjContent); 
            } catch (IOException ex) { 
                Exceptions.printStackTrace(ex); 
            } return true; 
        }
        
        @Override 
        protected Node createNodeForKey(String key) { 
            Node childNode = new AbstractNode(Children.LEAF); 
            childNode.setDisplayName(key); 
            return childNode; 
        } 
    }

    
}
