package multhread;

public class TimeEndControl {
	private long continueTime = -1;
	public long getContinueTime() {
		return continueTime;
	}
	public void setContinueTime(long continueTime) {
		this.continueTime = continueTime;
	}
	private long startTime;
	
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public boolean isTimeEnd(){
		if(0 > continueTime){
			throw new TimeMinus();
		}
		if(continueTime <= (System.currentTimeMillis() - startTime))
			return true;
		return false;
	}
	class TimeMinus extends RuntimeException{
		private static final long serialVersionUID = 1L;
		public TimeMinus() {
			super("time is minus");
		}
	}
}
