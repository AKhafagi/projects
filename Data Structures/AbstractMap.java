import java.util.Map;

/**
 * Created by Anas on 3/26/17.
 */
@SuppressWarnings("ALL")
abstract class AbstractMap<K,V> implements Map<K,V> {
    int currentSize;

    public int size(){
        return currentSize;
    }

}
