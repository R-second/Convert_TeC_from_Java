import javax.sound.midi.*;
	/** 
* MIDIシーケンスを扱いやすくするためのクラス
* @version 2.0
* @author chikara
*/
public class Sound{
	/** 現在のティック、ティック単位で現在どこまで演奏したかまたはどこまで登録したかを管理する。 */
	long currenttick[];
	/** シーケンス */
	Sequence sequence;
	/** シーケンサー */
	Sequencer sequencer;

/**
* デフォルト値(音色1(アコースティックピアノ),テンポ120)で単音のSoundクラスのインスタンスを生成する。
* Sound(1,120)と同じ。
* @see Sound#Sound(int,int)
*/
	public Sound(){
		this(1,120);//音色:1(アコースティックピアノ),テンポ:120
	}

/**
* 指定の音色とテンポで単音のSoundクラスのインスタンスを生成する。
* @param programs 音色
* @param tempo テンポ
*/
	public Sound(int programs,int tempo){
		currenttick=new long[1];
		try{
			sequence=new Sequence(Sequence.PPQ,24);
			MetaMessage mmes = new MetaMessage();
   		int l = 60*1000000/tempo;
     	mmes.setMessage(0x51, new byte[]{(byte)(l/65536), (byte)(l%65536/256), (byte)(l%256)},3);
			Track track=sequence.createTrack();
			track.add(new MidiEvent(mmes, 0));
			setMessage(0,ShortMessage.PROGRAM_CHANGE,programs-1,0,0L);
			sequencer=MidiSystem.getSequencer(false);
			Receiver receiver=MidiSystem.getReceiver();
			sequencer.getTransmitter().setReceiver(receiver);
			sequencer.open();
			sequencer.setSequence(sequence);
		}catch(InvalidMidiDataException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}catch(MidiUnavailableException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}		
	}


/**
* 複数音色用のSoundクラスのインスタンスを生成する。
* @param programs 音色の配列
* @param tempo テンポ
*/
	public Sound(int[] programs,int tempo){
		currenttick=new long[programs.length];
		try{
			sequence=new Sequence(Sequence.PPQ,24);
			MetaMessage mmes = new MetaMessage();
   		int l = 60*1000000/tempo;
     	mmes.setMessage(0x51, new byte[]{(byte)(l/65536), (byte)(l%65536/256), (byte)(l%256)},3);
			Track track=sequence.createTrack();
			track.add(new MidiEvent(mmes, 0));
			for(int i=0;i<programs.length;i++){
				setMessage(i,ShortMessage.PROGRAM_CHANGE,programs[i]-1,0,0L);
			}
			sequencer=MidiSystem.getSequencer(false);
			Receiver receiver=MidiSystem.getReceiver();
			sequencer.getTransmitter().setReceiver(receiver);
			sequencer.open();
			sequencer.setSequence(sequence);
		}catch(InvalidMidiDataException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}catch(MidiUnavailableException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}		
	}

/** 
* チャネル(音色)ごとに現在のティックを指定する。
* 現在のティックとは現在どこまで演奏したかまたはどこまで登録したかを示すティック単位の値である。
* @param channel チャネル番号(0～)
* @param tick 現在のティック
*/
	public void setCurrentTick(int channel,long tick){
		currenttick[channel]=tick;
	}


/** MIDIメッセージを直接登録するメソッド */
	public void setMessage(int channelnumber,int command,int data1,int data2,long tick){

		ShortMessage message = new ShortMessage();
		try{
			message.setMessage(command,channelnumber,data1,data2);
		}catch(InvalidMidiDataException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		MidiEvent event = new MidiEvent(message,tick);
		sequence.getTracks()[0].add(event);
	}


/**
* 音程、音の強さ,音の長さを指定して音が鳴るように登録する（単音用）。
* この時点では音は鳴らない。（全ての音を登録した後にplayメソッドで鳴らす）
* @param note 音程
* @param velocity 音の強さ
* @param length 音の長さ
* @see Sound#play()
*/
	public void setTone(int note,int velocity,int length){
		setMessage(0,ShortMessage.NOTE_ON,note,velocity,currenttick[0]);
		currenttick[0]+=length;
		setMessage(0,ShortMessage.NOTE_OFF,note,velocity,currenttick[0]);
	}


/**
* 音程、音の強さ,音の長さを指定して指定のチャネル(音色)で音が鳴るように登録する。
* この時点では音は鳴らない。（全ての音を登録した後にplayメソッドで鳴らす）
* @param channel チャネル
* @param note 音程
* @param velocity 音の強さ
* @param length 音の長さ
* @see Sound#play()
*/
	public void setTone(int channel,int note,int velocity,int length){
		setMessage(channel,ShortMessage.NOTE_ON,note,velocity,currenttick[channel]);
		currenttick[channel]+=length;
		setMessage(channel,ShortMessage.NOTE_OFF,note,velocity,currenttick[channel]);
	}


/**
* 音程、音の強さ,音の長さを指定して指定のチャネル(音色)で指定のティックの時点で音を鳴らすように登録する。
この時点では音は鳴らない。（全ての音を登録した後にplayメソッドで鳴らす）
* @param channel チャネル
* @param note 音程
* @param velocity 音の強さ
* @param noteOnTick ティック単位のこの音を鳴らす時点
* @param length 音の長さ
* @see Sound#play()
*/
	public void setTone(int channel,int note,int velocity,long noteOnTick,int length){
		setMessage(channel,ShortMessage.NOTE_ON,note,velocity,noteOnTick);
		setMessage(channel,ShortMessage.NOTE_OFF,note,velocity,noteOnTick+length);
	}


/** 
* setToneメソッドで登録した音を演奏する。
* @see Sound#setTone(int,int,int)
* @see Sound#setTone(int,int,int,int)
* @see Sound#setTone(int,int,int,long,int)
*/
	public void play(){
//		ArrayList<Long> arr=new ArrayList<Long>();
		try{
			sequencer.start();
			while(sequencer.isRunning()){
				Thread.sleep(100);
			}
		}
		catch(InterruptedException e){}
		sequencer.stop();
		sequencer.close();
	}


/**
* 登録した音のシーケンスをMIDIファイルに書き出す。
* @param filename ファイル名
*/
	public void outputFile(String filename){
		try{
			int types[]=MidiSystem.getMidiFileTypes(sequence);
			MidiSystem.write(sequence, types[0],new java.io.File(filename));
		}catch(java.io.IOException e){
			System.out.println(e.getMessage());
		}
	}

}	
