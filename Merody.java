public class Merody{
	public static void main(String[] args){
		int[] channel = {14,10,10,10,10,123};
		Sound s = new Sound(channel,85);

		int merody_cnt=0;
		
		// 「カノン」
		merody_cnt = canon(s, merody_cnt);
		// 「世界に1つだけの花」/ SMAP
		merody_cnt = sekaiNiHitotudakenoHana(s, merody_cnt);
			// 「負けないで」/ZARD  (時間の都合で割愛)
			// merody_cnt = makenaide(s, merody_cnt);
		// 「さくらんぼ」/大塚愛
		merody_cnt = sakuranbo(s, merody_cnt);
		// 「小さな恋の歌」/MONGOL800
		merody_cnt = tisanakoinouta(s, merody_cnt);
		// 「愛唄」/GReeeeN
		merody_cnt = aiuta(s, merody_cnt);
		//「キセキ」/GReeeeN
		merody_cnt = kiseki(s, merody_cnt);
		merody_cnt = kiseki2(s, merody_cnt);

		for(int i=0; i < merody_cnt-1; i++){
			base(s, 0);
		}
		base(s,1);

		s.play();

	}

	static void base(Sound s, int flag){
		baseFirst(s);

		baseLast(s,flag);

		s.setTone(5,64,90,96);

	}

	static void baseFirst(Sound s){
		int strength = 70;
		// 1
		s.setTone(1,64,strength,48);
		s.setTone(2,0,0,12);
		s.setTone(2,67,strength,36);
		s.setTone(3,0,0,24);
		s.setTone(3,72,strength,24);

		
		s.setTone(1,62,strength,48);
		s.setTone(2,0,0,12);
		s.setTone(2,67,strength,36);
		s.setTone(3,0,0,24);
		s.setTone(3,71,strength,24);

		//2
		s.setTone(1,64,strength,48);
		s.setTone(2,0,0,12);
		s.setTone(2,69,strength,36);
		s.setTone(3,0,0,24);
		s.setTone(3,72,strength,24);

		s.setTone(1,59,strength,48);
		s.setTone(2,0,0,12);
		s.setTone(2,64,strength,36);
		s.setTone(3,0,0,24);
		s.setTone(3,67,strength,24);
	

		//3
		s.setTone(1,60,strength,48);
		s.setTone(2,0,0,12);
		s.setTone(2,65,strength,36);
		s.setTone(3,0,0,24);
		s.setTone(3,69,strength,24);
	

		s.setTone(1,60,strength,48);
		s.setTone(2,0,0,12);
		s.setTone(2,64,strength,36);
		s.setTone(3,0,0,24);
		s.setTone(3,67,strength,24);

		s.setTone(4,0,0,288);
		
	}

	static void baseLast(Sound s, int flag){
		int strength = 70;
		if (flag==0){
			//4
			s.setTone(1,60,strength,48);
			s.setTone(2,0,0,12);
			s.setTone(2,65,strength,36);
			s.setTone(3,0,0,24);
			s.setTone(3,69,strength,24);
		

			s.setTone(1,62,strength,48);
			s.setTone(2,0,0,12);
			s.setTone(2,67,strength,36);
			s.setTone(3,0,0,24);
			s.setTone(3,71,strength,24);

			s.setTone(4,0,0,96);

		}else{
			s.setTone(1,60,strength,48);
			s.setTone(2,0,0,12);
			s.setTone(2,65,strength-20,36);
			s.setTone(3,0,0,24);
			s.setTone(3,69,strength-20,24);
			s.setTone(4,0,0,36);
			s.setTone(4,77,strength-20,24);
			s.setTone(1,84,strength-20,96);
		}
	
	}


	static int sekaiNiHitotudakenoHana(Sound s, int cnt){
		//1 
		s.setTone(60,127,12);
		s.setTone(60,127,6);
		s.setTone(60,127,18);
		s.setTone(67,127,12);
		s.setTone(67,127,18);
		s.setTone(65,127,18);
		s.setTone(64,127,12);

		//2
		s.setTone(62,127,18);
		s.setTone(64,127,18);
		s.setTone(65,127,12);
		s.setTone(64,127,24);
		s.setTone(62,127,24);

		//3
		s.setTone(60,127,12);
		s.setTone(60,127,6);
		s.setTone(60,127,18);
		s.setTone(64,127,12);
		s.setTone(64,127,18);
		s.setTone(62,127,18);
		s.setTone(57,127,12);

		//4
		s.setTone(60,127,12);
		s.setTone(59,127,6);
		s.setTone(60,127,18);
		s.setTone(62,127,12);
		s.setTone(64,127,24);
		s.setTone(62,127,9);
		s.setTone(60,127,9);
		s.setTone(0,0,6);

		return ++cnt;
	}

	static int sakuranbo(Sound s, int cnt){
		//1
		s.setTone(60,127,36);
		s.setTone(64,127,12);
		s.setTone(67,127,24);
		s.setTone(59,127,24);

		//2
		s.setTone(60,127,24);
		s.setTone(0,0,6);
		s.setTone(60,127,6);
		s.setTone(59,127,6);
		s.setTone(60,127,6);
		s.setTone(64,127,18);
		s.setTone(55,127,6);
		s.setTone(55,127,24);

		//3
		s.setTone(57,127,12);
		s.setTone(60,127,12);
		s.setTone(59,127,6);
		s.setTone(60,127,6);
		s.setTone(62,127,6);
		s.setTone(55,127,6);
		s.setTone(64,127,18);
		s.setTone(60,127,6);
		s.setTone(60,127,18);
		s.setTone(53,127,6);

		//4
		s.setTone(53,127,12);
		s.setTone(55,127,12);
		s.setTone(57,127,12);
		s.setTone(60,127,6);
		s.setTone(60,127,6);
		s.setTone(59,127,6);
		s.setTone(60,127,6);
		s.setTone(62,127,6);
		s.setTone(62,127,18);
		s.setTone(0,0,12);

		return ++cnt;
	}

	static int makenaide(Sound s, int cnt){
		//1
		s.setTone(60,127,12);
		s.setTone(55,127,12);
		s.setTone(60,127,12);
		s.setTone(64,127,12);
		s.setTone(62,127,24);
		s.setTone(59,127,18);
		s.setTone(0,0,6);
		
		//2
		s.setTone(60,127,24);
		s.setTone(59,127,12);
		s.setTone(57,127,12);
		s.setTone(55,127,24);
		s.setTone(55,127,24);

		//3
		s.setTone(57,127,12);
		s.setTone(55,127,12);
		s.setTone(55,127,12);
		s.setTone(53,127,12);
		s.setTone(55,127,24);
		s.setTone(60,127,12);
		s.setTone(60,127,12);

		//4
		s.setTone(57,127,12);
		s.setTone(60,127,24);
		s.setTone(64,127,12);
		s.setTone(62,127,24);
		s.setTone(60,127,12);
		s.setTone(62,127,12);

		return ++cnt;
	}

	static int tisanakoinouta(Sound s, int cnt){
		//1
		s.setTone(64,127,12);
		s.setTone(64,127,12);
		s.setTone(62,127,12);
		s.setTone(60,127,12);
		s.setTone(67,127,24);
		s.setTone(67,127,12);
		s.setTone(67,127,12);

		//2
		s.setTone(69,127,12);
		s.setTone(67,127,12);
		s.setTone(65,127,12);
		s.setTone(69,127,12);
		s.setTone(67,127,24);
		s.setTone(67,127,12);
		s.setTone(67,127,12);

		//3
		s.setTone(69,127,12);
		s.setTone(67,127,12);
		s.setTone(65,127,12);
		s.setTone(69,127,12);
		s.setTone(67,127,24);
		s.setTone(64,127,12);
		s.setTone(64,127,12);

		//4
		s.setTone(65,127,12);
		s.setTone(64,127,6);
		s.setTone(60,127,12);
		s.setTone(62,127,6);
		s.setTone(62,127,18);
		s.setTone(0,0,30);

		//「愛唄」につながる
		s.setTone(64,127,12);
		

		return ++cnt;
	}

	static int aiuta(Sound s, int cnt){
		//1, 2
		for(int i=0; i<2; i++){
			s.setTone(67,127,8);
			s.setTone(64,127,4);
			s.setTone(67,127,8);
			s.setTone(64,127,4);
			s.setTone(67,127,8);
			s.setTone(67,127,4);
			s.setTone(69,127,6);
			s.setTone(67,127,18);
			s.setTone(0,0,12);
			if(i==0){ //1
				s.setTone(0,0,8);
				s.setTone(64,127,4);
				s.setTone(64,127,8);
				s.setTone(65,127,4);
			} else { //2
				s.setTone(0,0,12);
				s.setTone(62,127,6);
				s.setTone(64,127,6);
			}
		}

		//3~4
		s.setTone(65,127,12);
		s.setTone(64,127,12);
		s.setTone(62,127,12);
		s.setTone(60,127,6);
		s.setTone(65,127,18);
		s.setTone(64,127,12);
		s.setTone(62,127,12);
		s.setTone(60,127,6);
		s.setTone(65,127,18);

		//~4
		s.setTone(64,127,12);
		s.setTone(62,127,12);
		s.setTone(64,127,6);
		s.setTone(64,127,6);
		s.setTone(62,127,24);

		//「キセキ」につながる
		s.setTone(64,127,12);
		s.setTone(67,127,12);

		

		return ++cnt;
	}

	static int kiseki(Sound s, int cnt){
		//1
		s.setTone(67,127,24);
		s.setTone(64,127,12);
		s.setTone(67,127,12);
		s.setTone(67,127,12);
		s.setTone(65,127,12);
		s.setTone(64,127,12);
		s.setTone(62,127,12);

		//2
		s.setTone(62,127,12);
		s.setTone(60,127,12);
		s.setTone(62,127,12);
		s.setTone(64,127,6);
		s.setTone(64,127,30);
		s.setTone(0,0,6);
		s.setTone(64,127,6);
		s.setTone(64,127,6);
		s.setTone(64,127,6);

		//3
		s.setTone(65,127,12);
		s.setTone(64,127,12);
		s.setTone(62,127,12);
		s.setTone(60,127,12);
		s.setTone(60,127,12);
		s.setTone(55,127,12);
		s.setTone(0,0,6);
		s.setTone(64,127,12);
		s.setTone(64,127,6);

		//4
		s.setTone(65,127,12);
		s.setTone(64,127,12);
		s.setTone(60,127,12);
		s.setTone(64,127,12);
		s.setTone(62,127,12);
		s.setTone(0,0,12);
		s.setTone(64,127,12);
		s.setTone(67,127,12);

		return ++cnt;
	}

	static int kiseki2(Sound s, int cnt){
		//1
		s.setTone(67,127,24);
		s.setTone(64,127,12);
		s.setTone(67,127,12);
		s.setTone(68,127,12);
		s.setTone(64,127,12);
		s.setTone(62,127,12);
		s.setTone(64,127,6);
		s.setTone(64,127,6);

		//2
		s.setTone(62,127,6);
		s.setTone(60,127,6);
		s.setTone(0,0,6);
		s.setTone(62,127,6);
		s.setTone(0,0,6);
		s.setTone(67,127,12);
		s.setTone(67,127,12);
		s.setTone(64,127,18);
		s.setTone(0,0,12);
		s.setTone(64,127,12);

		//4
		s.setTone(65,127,12);
		s.setTone(64,127,12);
		s.setTone(62,127,12);
		s.setTone(60,127,12);
		s.setTone(62,127,24);
		s.setTone(62,127,12);
		s.setTone(60,127,12);

		//last
		s.setTone(60,127,72);
		
		return ++cnt;
	}

	static int canon(Sound s, int cnt){
		//1
		s.setTone(67,127,12);
		s.setTone(64,127,6);
		s.setTone(65,127,6);
		s.setTone(67,127,12);
		s.setTone(64,127,6);
		s.setTone(65,127,6);
		s.setTone(67,127,6);
		s.setTone(55,127,6);
		s.setTone(57,127,6);
		s.setTone(59,127,6);
		s.setTone(60,127,6);
		s.setTone(62,127,6);
		s.setTone(64,127,6);
		s.setTone(65,127,6);

		//2
		s.setTone(64,127,12);
		s.setTone(60,127,6);
		s.setTone(62,127,6);
		s.setTone(64,127,12);
		s.setTone(52,127,6);
		s.setTone(53,127,6);
		s.setTone(55,127,6);
		s.setTone(57,127,6);
		s.setTone(55,127,6);
		s.setTone(53,127,6);
		s.setTone(55,127,6);
		s.setTone(52,127,6);
		s.setTone(53,127,6);
		s.setTone(55,127,6);

		//3
		s.setTone(53,127,12);
		s.setTone(57,127,6);
		s.setTone(55,127,6);
		s.setTone(53,127,12);
		s.setTone(52,127,6);
		s.setTone(50,127,6);
		s.setTone(52,127,6);
		s.setTone(50,127,6);
		s.setTone(48,127,6);
		s.setTone(50,127,6);
		s.setTone(52,127,6);
		s.setTone(53,127,6);
		s.setTone(55,127,6);
		s.setTone(57,127,6);

		//4
		s.setTone(53,127,12);
		s.setTone(57,127,6);
		s.setTone(55,127,6);
		s.setTone(57,127,12);
		s.setTone(59,127,6);
		s.setTone(60,127,6);
		s.setTone(55,127,6);
		s.setTone(57,127,6);
		s.setTone(59,127,6);
		s.setTone(60,127,6);
		s.setTone(62,127,6);
		s.setTone(64,127,6);
		s.setTone(65,127,6);
		s.setTone(67,127,6);

		return ++cnt;
	}

	
}



