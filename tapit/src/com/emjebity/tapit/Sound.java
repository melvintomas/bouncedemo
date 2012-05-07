package com.emjebity.tapit;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

public class Sound {
	static SoundPool soundPool;
	static int soundShort;
	static int soundLong;
	static int soundTapIt;
	static int soundGreen;
	static int soundPurple;
	static int soundPink;
	static int soundBlue;
	static int soundDoubleGreen;
	static int soundDoublePurple;
	static int soundDoublePink;
	static int soundDoubleBlue;
	static int soundShakeIt;

	public static void loadSound(Context context) {
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		soundShort = soundPool.load(context, R.raw.shortbuzz, 1);
		soundLong = soundPool.load(context, R.raw.longbuzz, 1);
		soundGreen = soundPool.load(context, R.raw.tapitgreen, 1);
		soundBlue = soundPool.load(context, R.raw.tapitblue, 1);
		soundPink = soundPool.load(context, R.raw.tapitpink, 1);
		soundPurple = soundPool.load(context, R.raw.tapitpurple, 1);
		soundDoubleGreen = soundPool.load(context, R.raw.doublegreen, 1);
		soundDoubleBlue = soundPool.load(context, R.raw.doubleblue, 1);
		soundDoublePink = soundPool.load(context, R.raw.doublepink, 1);
		soundDoublePurple = soundPool.load(context, R.raw.doublepurple, 1);
		soundShakeIt = soundPool.load(context, R.raw.shakeit, 1);
		
	}

	public static void playShort() {
		soundPool.play(soundShort, 1, 1, 1, 0, 1);
	}

	public static void playLong() {
		soundPool.play(soundLong, 1, 1, 1, 0, 1);
	}
	
	public static void playsGreen(){
		soundPool.play(soundGreen, 1, 1, 1, 0, 1);
	}
	
	public static void playdGreen(){
		soundPool.play(soundDoubleGreen, 1, 1, 1, 0, 1);
	}
	
	public static void playsBlue(){
		soundPool.play(soundBlue, 1, 1, 1, 0, 1);
	}
	
	public static void playdBlue(){
		soundPool.play(soundDoubleBlue, 1, 1, 1, 0, 1);
	}
	
	public static void playsPink(){
		soundPool.play(soundPink, 1, 1, 1, 0, 1);
	}
	
	public static void playdPink(){
		soundPool.play(soundDoublePink, 1, 1, 1, 0, 1);
	}
	
	public static void playsPurple(){
		soundPool.play(soundPurple, 1, 1, 1, 0, 1);
	}
	
	public static void playdPurple(){
		soundPool.play(soundDoublePurple, 1, 1, 1, 0, 1);
	}
	
	public static void playShake(){
		soundPool.play(soundShakeIt, 1, 1, 1, 0, 1);
	}
}
