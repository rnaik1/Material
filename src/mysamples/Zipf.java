package mysamples;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Zipf
{
	class Song
	{
		long noOfPlays;
		String songName;
		int position;

		public Song(int position, long noOfPlays, String songName) {
			this.position = position;
			this.noOfPlays = noOfPlays;
			this.songName = songName;
		}

		/*
		 * According to Zip's law, zi is proportional to 1/i
		 * According to the problem statement quality of a song qi = fi/zi, but here we can take zi as 1/i
		 * Therefore we have qi = fi * i
		 */
		public long qualityofSong()
		{
			return noOfPlays * position;
		}
	}

	public static PriorityQueue<Song> addSongs(Scanner s, long noOfSongs){
		
		PriorityQueue<Song> songsPQueue = new PriorityQueue<Song>((int)noOfSongs, new Comparator<Song>(){
			public int compare(Song song1, Song song2) {
				if (song1.qualityofSong() < song2.qualityofSong()) return 1;
				if (song1.qualityofSong() > song2.qualityofSong()) return -1;
				return 0;
			}
		});
		
		for(int i = 0; i < noOfSongs; i++)
		{
			long listens = s.nextLong();
			String name = s.next();
			Song song = new Zipf().new Song((i + 1), listens, name);
			songsPQueue.add(song);
		}
		return songsPQueue;
	}
	
	public static void displayTopSongs(PriorityQueue<Song> songsPQueue, long noOfTop){
		for(long i = 0; i < noOfTop; i++)
		{
			Song x = songsPQueue.poll();
			System.out.println(x.songName);
		}
	}

	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);

		long noOfSongs = s.nextLong();
		long noOfTop = s.nextLong();
		
		PriorityQueue<Song> songsPQueue = addSongs(s, noOfSongs);
		displayTopSongs(songsPQueue, noOfTop);
	}

}