import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringHelper {

    public static String convert(String value, String fromEncoding, String toEncoding) {
        return new String(value.getBytes(Charset.forName(fromEncoding)), Charset.forName(toEncoding));
    }

    public static String charset(String value, String charsets[]) {
        String probe = StandardCharsets.UTF_8.name();
        for(String c : charsets) {
            Charset charset = Charset.forName(c);
            if(charset != null) {
                if(value.equals(convert(convert(value, charset.name(), probe), probe, charset.name()))) {
                    return c;
                }
            }
        }
        return StandardCharsets.UTF_8.name();
    }
}
