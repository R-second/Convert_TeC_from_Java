public class MerodyTest{
	public static void main(String[] args){
		int[] channel={1,127};
		Sound s = new Sound(channel,140);
		/* phrase1(s);
		for (int i=0; i<2; i++){
			s.setTone(67,127,24);
			s.setTone(67,127,24);
			s.setTone(65,127,24);
			s.setTone(65,127,24);
			s.setTone(64,127,24);
			s.setTone(64,127,24);
			s.setTone(62,127,24);
		}
		phrase1(s);
*/
		s.setTone(0,62,127,96);
		s.setTone(1,62,127,96);
		s.setCurrentTick(0,0);
		s.setTone(0,72,127,96);
		s.setTone(1,62,127,96);
		s.play();

	}

/*	static void phrase1(Sound s){
		s.setTone(60,127,24);
		s.setTone(60,127,24);
		s.setTone(67,127,24);
		s.setTone(67,127,24);
		s.setTone(69,127,24);
		s.setTone(69,127,24);
		s.setTone(67,127,48);
		s.setTone(65,127,24);
		s.setTone(65,127,24);
		s.setTone(64,127,24);
		s.setTone(64,127,24);
		s.setTone(62,127,24);
		s.setTone(62,127,24);
		s.setTone(60,127,48);
	}*/
}