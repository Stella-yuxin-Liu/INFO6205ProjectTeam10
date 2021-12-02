package main;

public class Timer {
	public long startTime;
	public long endTime;
	public long during = 0;
	public int times;

	public Timer(int times) {
		this.times = times;
	}

	public void startCounting() {
		startTime = System.currentTimeMillis();
//		System.out.println("startCounting");
	}

	public void endCounting() {
		endTime = System.currentTimeMillis();
		during += endTime - startTime;
	}

	public long getTime() {
		return during;
	}

	public long getFinalDuring() {
		return during / times;
	}
}
