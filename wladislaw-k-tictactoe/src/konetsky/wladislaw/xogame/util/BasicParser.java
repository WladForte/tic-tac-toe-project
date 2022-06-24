package konetsky.wladislaw.xogame.util;

/**
 * This interface parses the input of type S into the object of type T. 
 * @param <T> - type of object that is being operated on.
 * @param <S> - the type of data, that should be parsed: CharSequence(String, StringBuilder, ...), Character Array, etc.
 */
@FunctionalInterface
public interface BasicParser<T, S> {
	
	T parse(S input);
	
}
