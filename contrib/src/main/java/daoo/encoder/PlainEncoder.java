package daoo.encoder;

import com.sun.istack.internal.NotNull;
import daoo.ioc.MessageEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: keevstessens
 * Date: 17/05/13
 * Time: 16:29
 * To change this template use File | Settings | File Templates.
 */
public class PlainEncoder implements MessageEncoder {

    @Override
    public String encode(@NotNull String message) {
        return message;
    }

    @Override
    public String decode(@NotNull String message) {
        return message;
    }
}
