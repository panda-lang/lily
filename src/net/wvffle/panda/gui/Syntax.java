package net.wvffle.panda.gui;

import java.util.ArrayList;

public class Syntax {
	private String javascript = "";
	private ArrayList<String> token = new ArrayList<String>();
	
	public void addToken(String method, String type){
		switch(type){
			case "object":
				token.add("{regex: /"+method+"/, token: '"+type+"', next: 'objectmethod'},");
				break;
			case "method":
				token.add("{regex: /"+method+"/, token: '"+type+"'},");
				break;
			default:
				token.add("{regex: /"+method+"/, token: '"+type+"'},");
		}
		
	}
	
	public String parse(){
		javascript  = "CodeMirror.defineSimpleMode('panda', {";		
		javascript += "start: [";
		//-- defined
		javascript += String.join("\n", token);
		//-- string
		javascript += "{regex: /\"(?:[^\\\\]|\\\\.)*?\"/, token: 'string'},";
		//-- keywords
		javascript += "{regex: /(?:return|if|for|while|else)\\b/, token: 'keyword'},";
		//-- atoms
		javascript += "{regex: /true|false|null|undefined/, token: 'atom'},";
		//-- numbers
		javascript += "{regex: /0x[a-f\\d]+|[-+]?(?:\\.\\d+|\\d+\\.?\\d*)(?:e[-+]?\\d+)?/i, token: 'number'},";
		//-- comment
		javascript += "{regex: /\\/\\/.*/, token: 'comment'},";
		//-- start multiline comment
		//javascript += "{regex: /\\/\\*/, token: 'comment', next: 'comment'},";
		//-- start methods
		javascript += "{regex: /^\\s*?method/, token: 'method', next: 'method'},";
		//-- indention on { and (
		javascript += "{regex: /[\\{\\(]/, indent: true},";
		javascript += "{regex: /[\\}\\)]/, dedent: true}";
		javascript += "";
		//-- multiline comment
		//javascript += "], comment: [";
		//javascript += "{regex: /.*?\\*\\//, token: 'comment', next: 'start'},";
		//javascript += "{regex: /.*/, token: 'comment'}";
		//-- end methods
		javascript += "], method: [";
		javascript += "{regex: /.*(?=\\()/, token: 'method-name', next: 'argument'}";
		javascript += "], argument: [";
		javascript += "{regex: /(?!\\().*(?=\\))/, token: 'argument', next: 'start'}";
		//-- end object methods
		javascript += "],objectmethod: [";
		javascript += "{regex: /(?!\\.).*(?=\\()/, token: 'method', next: 'start'}";
		//-- some fixes
		javascript += "], meta: {";
		javascript += "dontIndentStates: ['comment'],";
		javascript += "lineComment: '//'";
		javascript += "}";
		javascript += "});";
		
		return javascript;
	}
}

