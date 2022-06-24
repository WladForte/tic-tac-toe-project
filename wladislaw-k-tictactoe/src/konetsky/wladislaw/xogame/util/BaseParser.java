package konetsky.wladislaw.xogame.util;

/**
 * This interface parses the input of type S into the object of type T.
 * @param <S> - the type of data, that should be parsed: CharSequence(String, StringBuilder, ...), Character Array, etc.
 * @param <T> - type of object that is being operated on.
 */
@FunctionalInterface
public interface BaseParser<S, T> {
	
	T parse(S input);
	
}
