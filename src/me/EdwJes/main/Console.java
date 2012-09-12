package me.EdwJes.main;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.EdwJes.main.input.PlayerInput;
import me.EdwJes.main.objects.RenderableObject;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;


/**
* Console Class
*  
* <P>A command prompt within the game. It also stands for the in-game chat.  
* <P>The view is responsible for the console.
*  
* @author Lolirofle
* @version 1.0
*/
public class Console extends RenderableObject{
	
	private static boolean initiated=false;
	private boolean isOn = false,keyCTRL=false;
	private String input = "",inputFieldPrefix="> ";
	public char commandPrefix='/';
	private final int MAX_ARGUMENTS=32;
	private int inputPosition=0;
	private List<String> inputHistory=new ArrayList<String>();
	private int inputHistoryIndex=0;
	private List<ConsoleOutputText> outputList=new ArrayList<ConsoleOutputText>();
	private static List<Command> commandsGlobal=new ArrayList<Command>();
	private List<Command> commandsLocal=new ArrayList<Command>();
	/*TODO:Output lines in console and limit text to boundaries of the window + black transparent box under console 
	 * private int outputLines = 0;
	 * private int outputMaxLines = 6;*/
	
	public Console(){
		Console.initCommands();
	}
	
	/**
	 * Checks if the command exists, both in the static Console scope and in this console. 
	 * @param commandName The name of the command
	 * @return true if exists
	 */
	public boolean commandExists(String commandName){
		return getCommand(commandName)!=null;
	}
	
	/**
	 * Adds a command to this console only, see {@link Command} for more information on the command parameter
	 * @param command
	 */
	public void commandAdd(Command command){
		commandsLocal.add(command);
	}
	
	/**
	 * Adds a global command for the use of all console objects, see {@link Command} for more information on the command parameter
	 * @param command
	 */
	public static void commandGlobalAdd(Command command){
		commandsGlobal.add(command);
	}
	
	/**
	 * Initiates the basic commands to the global scope of Console
	 */
	private static void initCommands(){
		if(!initiated){
			initiated=true;
			
			commandGlobalAdd(new Command("shit"){
				@Override public void execute(Console console,String... args){
					console.outputConsole("Well yes my shit");
				}
			});
			
			commandGlobalAdd(new Command("helplist"){
				@Override public void execute(Console console,String... args){
					String __str="";
					for(Command cmd:commandsGlobal){
						__str+=cmd.getName()+", ";
					}
					
					for(Command cmd:console.commandsLocal){
						__str+=cmd.getName()+", ";
					}
					console.outputConsole(__str);
				}
			});
		
				
			commandGlobalAdd(new Command("help"){
				@Override public void execute(Console console,String... args){
					for(Command cmd:commandsGlobal){
						console.outputConsole(console.commandGetSyntax(cmd));
					}
					for(Command cmd:console.commandsLocal){
						console.outputConsole(console.commandGetSyntax(cmd));
					}
				}
			});
			
			
			commandGlobalAdd(new Command("test",new Arg("arg",Arg.Type.STRING,Arg.Flag.INFINITE)){
				@Override public void execute(Console console,String... args){
					String _str="";
					for(int i=0;i<args.length;i++)
						_str+="["+i+"]="+args[i]+"; ";
					console.outputConsole(_str);
				}
			});

			commandGlobalAdd(new Command("exit"){
				@Override public void execute(Console console,String... args){
				Main.getContainer().exit();
				}
			});
			
		}
	}
	
	@Override public void render(Graphics g){
		int i=0,lineHeight=g.getFont().getLineHeight();
		for(ConsoleOutputText text:outputList){
			i++;
			int alpha=255;
			if(text.fadeOutTick<ConsoleOutputText.fadeOutTimer||isOn){
				int y=(int)Main.WINDOW_HEIGHT - lineHeight*2-6 - ((outputList.size()-i)*lineHeight);
				if(!isOn)
					alpha=255-((int)(((float)text.fadeOutTick/ConsoleOutputText.fadeOutTimer)*255));
				g.setColor(new Color(255,255,255,alpha));
				g.drawString(text.getText(), 6, y);
				g.setColor(Color.white);}
		}
		if(isOn){
			g.setColor(new Color(64,64,64,128));
			g.fillRect(0, Main.WINDOW_HEIGHT-lineHeight-4, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
			g.setColor(Color.white);
			g.drawString(inputFieldPrefix + input, 6, Main.WINDOW_HEIGHT-lineHeight-2);
			int inputPositionWidth=g.getFont().getWidth(inputFieldPrefix + input.substring(0,inputPosition))+2;
			g.setColor(Color.red);
			g.drawLine(inputPositionWidth+2, Main.WINDOW_HEIGHT - lineHeight,inputPositionWidth+2, Main.WINDOW_HEIGHT - 4);
			g.setColor(Color.white);
		}
	}
	
	/**
	 * Enable input to the console
	 */
	public void enterConsole(PlayerInput player){
		isOn = true;
		player.acceptsInput = false;
	}
	
	/**
	 * Disable input to the console
	 */
	public void closeConsole(PlayerInput player){
		inputReset();
		isOn=false;
		player.acceptsInput = true;
	}
	
	/**
	 * Outputs a string to the console
	 * @param str The string to be outputted
	 */
	public void outputConsole(String str){
		outputList.add(new ConsoleOutputText(str));
	}
	
	public boolean isOn(){
		return isOn;
	}
	
	public void processInput(String str){
		str=str.trim();
		if(!str.isEmpty()){
			onInput(str);
			if(str.charAt(0)==commandPrefix){
				str=str.substring(1);
				String[] input=str.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)",MAX_ARGUMENTS);
				int argumentCount=input.length-1;
				input[0]=input[0].toLowerCase();
				onCommand(input);
				Command command=getCommand(input[0]);
				if(command!=null){
					boolean isInfinite=false;
					int countedArg=0,countedRequiredArg=0,isWrongType=0;
					ARGLOOP: for(Arg arg:command.getArgs()){
						countedArg++;
						if(argumentCount>=countedArg){
							/*
							 * INTEGER
							 */
							if(arg.getType()==Arg.Type.INTEGER){
								try{
									Integer.parseInt(input[countedArg]);}
								catch(NumberFormatException e){
									isWrongType=countedArg;
									break ARGLOOP;
								}
							}
							/*
							 * DOUBLE 
							 */
							else if(arg.getType()==Arg.Type.DOUBLE){
								try{
									Double.parseDouble(input[countedArg]);}
								catch(NumberFormatException e){
									isWrongType=countedArg;
									break ARGLOOP;
								}
							}
							/*
							 * BOOLEAN
							 */
							else if(arg.getType()==Arg.Type.BOOLEAN){
								if (input[countedArg] == "on" || input[countedArg] == "true" || input[countedArg] == "1"){
									input[countedArg]="true";
								}
								else if (input[countedArg] == "off" || input[countedArg] == "false" || input[countedArg] == "0"){
									input[countedArg]="false";
								}
								else{
									isWrongType=countedArg;
									break ARGLOOP;
								}
							}
							/*
							 * STRING
							 */
							else if(arg.getType()==Arg.Type.STRING){
								
							}
						}
						
						if (arg.getFlag()==Arg.Flag.NONE){
							countedRequiredArg++;}
						else if(arg.getFlag()==Arg.Flag.INFINITE){
							isInfinite=true;
							break ARGLOOP;}
					}
					if(isWrongType!=0){
						outputConsole("Wrong type on argument "+isWrongType);outputConsole("Syntax: "+commandGetSyntax(command));}
					else if((argumentCount<=countedArg&&argumentCount>=countedRequiredArg)||(isInfinite&&argumentCount>=countedRequiredArg)){
						command.execute(this,input);
					}
					else if(argumentCount>countedArg){outputConsole("Too many arguments, expecting "+countedRequiredArg);outputConsole("Syntax: "+commandGetSyntax(command));}
					else{outputConsole("Too few arguments, expecting "+countedRequiredArg);outputConsole("Syntax: "+commandGetSyntax(command));}
				}
				else outputConsole("Unknown Command: "+input[0]);
			}
			else outputConsole("> "+str);
		}
	}
	
	public Command getCommand(String commandName){
		for(Command c:commandsGlobal){
			if(c.getName().equalsIgnoreCase(commandName))
				return c;
		}
		for(Command c:commandsLocal){
			if(c.getName().equalsIgnoreCase(commandName))
				return c;
		}
		return null;
	}
	
	public String commandGetSyntax(Command command){
		String str=command.getName()+"(",delimiter="";
		int i=0;
		for(Arg arg:command.getArgs()){
			if(i==0)
				i=1;
			else
				delimiter=", ";
			if(arg.getFlag()==Arg.Flag.OPTIONAL)
				str+=" ["+delimiter+arg.getType().toStringShort()+" "+arg.getName()+"]";
			else if(arg.getFlag()==Arg.Flag.INFINITE)
				str+=" ["+delimiter+arg.getType().toStringShort()+"... "+arg.getName();
			else 
				str+=delimiter+arg.getType().toStringShort()+" "+arg.getName();
		}
		return str+")";
    }
	
	public String strInsert(String str,String insertStr,int index){
		return str.substring(0,index)+insertStr+str.substring(index);
	}
	
	public String strInsert(String str,char insertStr,int index){
		return str.substring(0,index)+insertStr+str.substring(index);
	}
	
	public String strExclude(String str,int indexFrom,int indexTo){
		return str.substring(0,Math.max(0,indexFrom))+str.substring(Math.min(str.length(),indexTo));
	}
	
	public void inputSet(String str){
		input=str;
		inputPositionMove(str.length());
	}
	
	public void inputInsert(String str){
		input=strInsert(input,str,inputPosition);
		inputPositionMove(str.length());
	}
	
	public void inputInsert(char chr){
		input=strInsert(input,chr,inputPosition);
		inputPositionMove(1);
	}
	
	public void inputDelete(int index){
		input=strExclude(input,index,index+1);
		inputPositionSet(index);
	}
	
	public void inputPositionMove(int pos){
		inputPosition=Math.max(Math.min(inputPosition+pos,input.length()),0);
	}
	
	public void inputPositionSet(int pos){
		inputPosition=Math.max(Math.min(pos,input.length()),0);
	}
	
	public void inputReset(){
		input="";
		inputPositionSet(0);
	}
	
	public void onInput(String text){}
	public void onCommand(String[] command){}


	


	public void onKeyPressed(int key, char chr, PlayerInput player){
		if(isOn){		
			if(keyCTRL==true){
				if(key==47)//V
					inputInsert(Misc.getClipboard().replaceAll("^[\\x20-\\xFF]"," "));
				else if(key==46){//C
					Misc.setClipboard(input);
					outputConsole("Copied to clipboard");}
				else if(key==45){//X
					Misc.setClipboard(input);
					inputReset();}
			}
			if(chr>=32&&chr<256){
				inputInsert(chr);
				input=input.replace("(!M)","MEGAFANTASTICMEGALUSPECTACULARYESYESYES");}
			else if(key==14)//Backspace
				inputDelete(inputPosition-1);
			else if(key==211)//Delete
				inputDelete(inputPosition);
			else if(key==199)//Home
				inputPositionSet(0);
			else if(key==207)//End
				inputPositionSet(input.length());
			else if(key==29)//CTRL
				keyCTRL=true;
			else if(key==player.KEY_LEFT){
				inputPositionMove(-1);}
			else if(key==player.KEY_RIGHT){
				inputPositionMove(1);}
			else if(key==Input.KEY_ENTER){
				inputHistory.add(input);
				inputHistoryIndex=inputHistory.size()-1;
				processInput(input);
				closeConsole(player);}
			else if(key==player.KEY_UP){
				if(inputHistoryIndex>0){
					inputSet(inputHistory.get(inputHistoryIndex));
					inputHistoryIndex--;}}
			else if(key==player.KEY_DOWN){
				if(inputHistoryIndex<inputHistory.size()-1){
					inputSet(inputHistory.get(inputHistoryIndex));
					inputHistoryIndex++;}
				}
		}
		else{
			if(chr == 't' || chr == 'T') enterConsole(player);
		}
	}

	//Debug.console.println(key+", "+chr+"="+((int)chr));
	

	public void onKeyReleased(int key, char chr,PlayerInput playerInput) {
		if(key==29){//CTRL
			keyCTRL=false;
		}
	}


	public void handleInput(Input input, PlayerInput playerInput) {

	}

	public boolean isKeyRepeat(){
		return true;
	}

	@Override
	public void update() {
		
		
	}
}

class ConsoleOutputText extends Updater{
	private String text;
	private Date date;
	public static int fadeOutTimer=1000;
	public int fadeOutTick=0;
	public DateFormat format;
	//protected static List<ConsoleOutputText> list = new ArrayList<ConsoleOutputText>();
	
	public ConsoleOutputText(String text){
		super();
		//list.add(this);
		this.text=text;
	}
	
	@Override
	public void update(){
		if(fadeOutTick<fadeOutTimer)
			fadeOutTick++;
	}
	
	public String getText(){
		return text;
	}
	
	public String getDate(){
		return format.format(date);
	}
	
	public void remove(){
		list.remove(this);
	}
	
	
}

abstract class Command{
	private final String name;
	private final Arg[] args;
	
	public Command(String name,Arg... args){
		this.name=name;
		this.args=args;}

	public Command(String name){
		this.name=name;
		this.args=new Arg[]{};}
	
	public String getName(){
		return name;
	}
	
	public Arg[] getArgs(){
		return args;}
	
	public Arg getArg(int index){
		return args[index];}
	
	public abstract void execute(Console console,String... args);
}

class Arg{
	static enum Type{
		INTEGER("int"),
		STRING("str"),
		DOUBLE("double"),
		BOOLEAN("bool");
		private String shortName;
		Type(String shortName){
			this.shortName=shortName;}
		public String toStringShort(){
			return shortName;}
		}
	static enum Flag{
		NONE,
		OPTIONAL,
		INFINITE;}
	private String name;
	private Type type;
	private Flag flag;
	
	Arg(String name,Type type,Flag flag){
		this.name=name;
		this.type=type;
		this.flag=flag;}
	Arg(String name,Type type){
		this.name=name;
		this.type=type;
		this.flag=Flag.NONE;}
	
	public Type getType(){
		return type;}
	
	public Flag getFlag(){
		return flag;}
	
	public String getName(){
		return name;}
}
