package net.dzikoysk.panda.gui;

import net.dzikoysk.panda.Panda;
import net.dzikoysk.panda.core.ElementsBucket;
import net.dzikoysk.panda.core.scheme.ObjectScheme;
import net.wvffle.panda.gui.Javascript;
import net.wvffle.panda.gui.Syntax;
import netscape.javascript.JSObject;

import javax.swing.*;

import com.sun.javafx.application.PlatformImpl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;

@SuppressWarnings("serial")
public class TextPanel extends JPanel {

	private JScrollPane scrollPane;

	Stage stage;
	JFXPanel jfxPanel;  
    Group root;  
    Scene scene;
    WebView browser;
    WebEngine webEngine;
    
	String panda = "// Panda " + Panda.PANDA_VERSION + "\nmethod main() {\n\t// your code goes here\n\tSystem.print(\"Hello Panda\");\n}\n";
	String ret = "";
	JSObject jsobj;
	JSObject javascript;
	Syntax Syntax = new Syntax();
	
	public TextPanel(){

        jfxPanel = new JFXPanel();  
		createScene();
		
		scrollPane = new JScrollPane(jfxPanel);
		scrollPane.setPreferredSize(new Dimension(591, 500-65));
		
		this.add(scrollPane);
	}

	public String getSource(){
    	ret  = jsobj.call("getValue").toString();
		 return ret;
	}
    private void createScene() {  
        PlatformImpl.startup(new Runnable() {  
            @Override
            public void run() {  
                Group root = new Group();  
                Scene scene = new Scene(root);
                
                browser = new WebView();
                browser.setPrefSize(591, 500-90);
                webEngine = browser.getEngine();
                webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
                	@Override
                    public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, State oldState, State newState) {
                        if (newState == State.SUCCEEDED) {         
                        	
                            defineMethods();
                            javascript = (JSObject) webEngine.executeScript("window");
                            javascript.setMember("java", new Javascript());
                            javascript.call("java.exit");
                            webEngine.executeScript(Syntax.parse());
                            webEngine.executeScript("window.editor = CodeMirror.fromTextArea(document.getElementById(\"editor\"), {lineNumbers: true,mode: \"panda\",matchBrackets: true});");
              	            jsobj = (JSObject)webEngine.executeScript("editor");
              	            jsobj.call("setValue", panda).toString();

                	          String heightText = webEngine.executeScript("window.getComputedStyle(document.body, null).getPropertyValue('height')").toString();
                	          double height = Double.valueOf(heightText.replace("px", ""));
                  	          String widthText = webEngine.executeScript("window.getComputedStyle(document.body, null).getPropertyValue('width')").toString();
                  	          double width = Double.valueOf(widthText.replace("px", ""));
                              browser.setPrefSize(width-10, height);
                        }
                    }
                });
        	    URL url = getClass().getResource("/net/wvffle/panda/gui/resources/codemirror-5.4/index.html");
                webEngine.load(url.toExternalForm());
                
                
                ObservableList<Node> children = root.getChildren();
                children.add(browser);        
                
                jfxPanel.setScene(scene);  
            }  
        });  
    }
    
    @SuppressWarnings("static-access")
	private void defineMethods(){
    	String[] classes = new String[]{
				"PArray",
				"PBoolean",
				"PCharacter",
				"PFile",
				"PList",
				"PMap",
				"PNumber",
				"PObject",
				"PPanda",
				"PRunnable",
				"PStack",
				"PString",
				"PSystem",
				"PThread"
		};
        ElementsBucket eb = new ElementsBucket();
        try {
			eb.loadClasses("net.dzikoysk.panda.lang", classes);
		} catch (Exception e){
			e.printStackTrace();
		}
        for (ObjectScheme object : eb.getObjects()) Syntax.addToken(object.getName(), "object");
    }
}
