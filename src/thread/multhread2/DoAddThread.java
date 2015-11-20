package multhread2;
public class DoAddThread extends Thread{
	private Num num = new Num();
	public DoAddThread(Runnable runnable){
		super(runnable);
		init();
	}
	private void init(){
		num.setCount(0);
	}
	public Num getNum() {
		return num;
	}
	public void setNum(Num num) {
		this.num = num;
	}
}