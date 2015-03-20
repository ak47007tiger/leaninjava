package 内部类;

public class Car {

	private double price;
	private Cheer cheer[] = new Cheer[4];
	private Lunzi lzLunzi = new Lunzi();
	protected YouXiang youXiang = new YouXiang();
	protected static class YouXiang{
		private double size;
		public static int years;//导致该类必须为静态类，为什么呢？
		public double getSize() {
			return size;
		}

		public void setSize(double size) {
			this.size = size;
		}
	}

	public Lunzi getLzLunzi() {
		return lzLunzi;
	}

	public void setLzLunzi(Lunzi lzLunzi) {
		this.lzLunzi = lzLunzi;
	}

	public Cheer[] getCheer() {
		return cheer;
	}

	public void setCheer(Cheer[] cheer) {
		this.cheer = cheer;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private class Lunzi {
		private double size;

		@SuppressWarnings("unused")
		public double getSize() {
			return size;
		}

		@SuppressWarnings("unused")
		public void setSize(double size) {
			this.size = size;
		}

	}

	class Cheer {

	}
}
