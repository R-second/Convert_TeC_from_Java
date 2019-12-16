import javax.sound.midi.*;
	/** 
* MIDI�V�[�P���X�������₷�����邽�߂̃N���X
* @version 2.0
* @author chikara
*/
public class Sound{
	/** ���݂̃e�B�b�N�A�e�B�b�N�P�ʂŌ��݂ǂ��܂ŉ��t�������܂��͂ǂ��܂œo�^���������Ǘ�����B */
	long currenttick[];
	/** �V�[�P���X */
	Sequence sequence;
	/** �V�[�P���T�[ */
	Sequencer sequencer;

/**
* �f�t�H���g�l(���F1(�A�R�[�X�e�B�b�N�s�A�m),�e���|120)�ŒP����Sound�N���X�̃C���X�^���X�𐶐�����B
* Sound(1,120)�Ɠ����B
* @see Sound#Sound(int,int)
*/
	public Sound(){
		this(1,120);//���F:1(�A�R�[�X�e�B�b�N�s�A�m),�e���|:120
	}

/**
* �w��̉��F�ƃe���|�ŒP����Sound�N���X�̃C���X�^���X�𐶐�����B
* @param programs ���F
* @param tempo �e���|
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
* �������F�p��Sound�N���X�̃C���X�^���X�𐶐�����B
* @param programs ���F�̔z��
* @param tempo �e���|
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
* �`���l��(���F)���ƂɌ��݂̃e�B�b�N���w�肷��B
* ���݂̃e�B�b�N�Ƃ͌��݂ǂ��܂ŉ��t�������܂��͂ǂ��܂œo�^�������������e�B�b�N�P�ʂ̒l�ł���B
* @param channel �`���l���ԍ�(0�`)
* @param tick ���݂̃e�B�b�N
*/
	public void setCurrentTick(int channel,long tick){
		currenttick[channel]=tick;
	}


/** MIDI���b�Z�[�W�𒼐ړo�^���郁�\�b�h */
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
* �����A���̋���,���̒������w�肵�ĉ�����悤�ɓo�^����i�P���p�j�B
* ���̎��_�ł͉��͖�Ȃ��B�i�S�Ẳ���o�^�������play���\�b�h�Ŗ炷�j
* @param note ����
* @param velocity ���̋���
* @param length ���̒���
* @see Sound#play()
*/
	public void setTone(int note,int velocity,int length){
		setMessage(0,ShortMessage.NOTE_ON,note,velocity,currenttick[0]);
		currenttick[0]+=length;
		setMessage(0,ShortMessage.NOTE_OFF,note,velocity,currenttick[0]);
	}


/**
* �����A���̋���,���̒������w�肵�Ďw��̃`���l��(���F)�ŉ�����悤�ɓo�^����B
* ���̎��_�ł͉��͖�Ȃ��B�i�S�Ẳ���o�^�������play���\�b�h�Ŗ炷�j
* @param channel �`���l��
* @param note ����
* @param velocity ���̋���
* @param length ���̒���
* @see Sound#play()
*/
	public void setTone(int channel,int note,int velocity,int length){
		setMessage(channel,ShortMessage.NOTE_ON,note,velocity,currenttick[channel]);
		currenttick[channel]+=length;
		setMessage(channel,ShortMessage.NOTE_OFF,note,velocity,currenttick[channel]);
	}


/**
* �����A���̋���,���̒������w�肵�Ďw��̃`���l��(���F)�Ŏw��̃e�B�b�N�̎��_�ŉ���炷�悤�ɓo�^����B
���̎��_�ł͉��͖�Ȃ��B�i�S�Ẳ���o�^�������play���\�b�h�Ŗ炷�j
* @param channel �`���l��
* @param note ����
* @param velocity ���̋���
* @param noteOnTick �e�B�b�N�P�ʂ̂��̉���炷���_
* @param length ���̒���
* @see Sound#play()
*/
	public void setTone(int channel,int note,int velocity,long noteOnTick,int length){
		setMessage(channel,ShortMessage.NOTE_ON,note,velocity,noteOnTick);
		setMessage(channel,ShortMessage.NOTE_OFF,note,velocity,noteOnTick+length);
	}


/** 
* setTone���\�b�h�œo�^�����������t����B
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
* �o�^�������̃V�[�P���X��MIDI�t�@�C���ɏ����o���B
* @param filename �t�@�C����
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
