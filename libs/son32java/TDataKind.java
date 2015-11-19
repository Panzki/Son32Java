package son32map;
import com.ochafik.lang.jnaerator.runtime.LibraryExtractor;
import com.ochafik.lang.jnaerator.runtime.MangledFunctionMapper;
import com.ochafik.lang.jnaerator.runtime.Mangling;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeLong;

public interface TDataKind {
	/**
	 * the channel is OFF -<br>
	 * <i>native declaration : Son.h:62</i>
	 */
	public static final int ChanOff = 0;
	/**
	 * a 16-bit waveform channel<br>
	 * <i>native declaration : Son.h:63</i>
	 */
	public static final int Adc = 1;
	/**
	 * Event times (falling edges)<br>
	 * <i>native declaration : Son.h:64</i>
	 */
	public static final int EventFall = 2;
	/**
	 * Event times (rising edges)<br>
	 * <i>native declaration : Son.h:65</i>
	 */
	public static final int EventRise = 3;
	/**
	 * Event times (both edges)<br>
	 * <i>native declaration : Son.h:66</i>
	 */
	public static final int EventBoth = 4;
	/**
	 * Event time plus 4 8-bit codes<br>
	 * <i>native declaration : Son.h:67</i>
	 */
	public static final int Marker = 5;
	/**
	 * Marker plus Adc waveform data<br>
	 * <i>native declaration : Son.h:68</i>
	 */
	public static final int AdcMark = 6;
	/**
	 * Marker plus float numbers<br>
	 * <i>native declaration : Son.h:69</i>
	 */
	public static final int RealMark = 7;
	/**
	 * Marker plus text string<br>
	 * <i>native declaration : Son.h:70</i>
	 */
	public static final int TextMark = 8;
	/**
	 * waveform of float numbers<br>
	 * <i>native declaration : Son.h:71</i>
	 */
	public static final int RealWave = 9;
}