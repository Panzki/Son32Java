package son32map;
import com.ochafik.lang.jnaerator.runtime.Structure;
/**
 * <i>native declaration : Son.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class TTextMark extends Structure<TTextMark, TTextMark.ByValue, TTextMark.ByReference > {
	/**
	 * the marker structure<br>
	 * C type : TMarker
	 */
	public TMarker m;
	/**
	 * the attached text data<br>
	 * C type : char[2048]
	 */
	public byte[] t = new byte[(2048)];
	public TTextMark() {
		super();
		initFieldOrder();
	}
	protected void initFieldOrder() {
		setFieldOrder(new java.lang.String[]{"m", "t"});
	}
	/**
	 * @param m the marker structure<br>
	 * C type : TMarker<br>
	 * @param t the attached text data<br>
	 * C type : char[2048]
	 */
	public TTextMark(TMarker m, byte t[]) {
		super();
		this.m = m;
		if (t.length != this.t.length) 
			throw new IllegalArgumentException("Wrong array size !");
		this.t = t;
		initFieldOrder();
	}
	protected ByReference newByReference() { return new ByReference(); }
	protected ByValue newByValue() { return new ByValue(); }
	protected TTextMark newInstance() { return new TTextMark(); }
	public static TTextMark[] newArray(int arrayLength) {
		return Structure.newArray(TTextMark.class, arrayLength);
	}
	public static class ByReference extends TTextMark implements Structure.ByReference {
		
	};
	public static class ByValue extends TTextMark implements Structure.ByValue {
		
	};
}