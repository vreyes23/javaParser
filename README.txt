#Parser (Antlr4.5.3)
#Additional Plugins that need to be installed:
#Eclipse Java EE Developer Tools Plugin
#Xtext Redistributable
#maven plugin
#Eclipse DSL Tools
#Eclipse Faceted Project Framework

1. Antlrv4.5.3 prerequisities
	1. Luna Eclipse installed not Mars Eclipse version
	2. ANTLR4 IDE Eclipse plugin
	3. ANTLR4 JAR file which you can download: http://tutorial-academy.com/json-parser-antlr4-eclipse/

2. Install ANTLR4 Eclipse plugin
	1. In Eclipse go to Help => Eclipse Marketplace
	2. Search for "ANTLRv4" and install it (Current version: ANTLR 4 IDE 0.3.5)
	3. Restart Eclipse
3. Create ANTLR4 Project
	1. Right click in the Project Explorer and select New => Project
	2. Search for "ANTLR" and select ANTLR 4 Project
	3. Name the project e.g. "JavaParser"

4. Adapt the ANTLR4 Project
The project wizard creates a simple Java.g4 grammar and automatically builds Lexer, Parser etc.
In order to program we have to adapt the Project Facets and add the generated files to our source folder as well as add the ANTLR4 JAR file to the Java Build Path.
1. Adapt the Project Facets
	1. Right click your created project and click on properties
	2. Select Project Facets and click on “Convert to faceted form…”
	3. Check Java and adapt he version
	4. Click Apply and OK
2. Add ANTLR4 target to the Java source folder 
	1. Right click your created project and click on properties
	2. Select “Java Build Path” and click on “Add Folder” in the Source tab
	3. Select the “target/generated-sources/antlr4” folder and  press OK
	4. You will see errors occure which appear because you have to add the ANTLR4 JAR file to your project
3. Recommended (path independent): Add the ANTLR4 JAR to your project (download and copy)
	1. Create a new folder called “lib” in your project root folder
	2. Copy the antlr-4.4-complete.jar into “lib”
	3. Right click the JAR file and select Build Path => Add to Build Path
	4. The errors should be resolved
4. Optional (path dependent): Add the ANTLR4 JAR to your project (from Eclipse plugin)
	1. Right click your project and select Properties
	2. Select Java Build Path and click on the tab Libraries
	3. Click on “Add external JARs”
	4. The path from the library differs from the OS you are using. Check your console output where the ANTLR4 Tool log

Depending whether you only develop on one computer, you can select option 4. We recommand downloading the JAR though. If you use another version than the ANTLR Tool, you may receive a warning in the console later when running the program.

 
